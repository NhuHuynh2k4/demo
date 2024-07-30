package com.poly.servlet;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.UserDAO;
import com.poly.dao.VideoDAO;
import com.poly.model.User;
import com.poly.model.Video;

@WebServlet({"/admin/videoMangement","/admin/createVideo","/admin/updateVideo","/admin/deleteVideo","/admin/edit/*"})
@MultipartConfig
public class VideoManagementServlet extends HttpServlet{
	Video video = new Video();
	VideoDAO videoDAO = new VideoDAO();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String uri = req.getRequestURI();

		if (uri.contains("createVideo")) {
			this.doCreateVideo(req, resp);
		} else if (uri.contains("edit")) {
			String id = uri.substring(uri.lastIndexOf("/") + 1);
			video = videoDAO.findById(id);
			req.setAttribute("editVideo", video);
		} else if (uri.contains("updateVideo")) {
			this.doUpdate(req,resp);
		} else if (uri.contains("deleteVideo")) {
			this.doRemove(req,resp);
		}
		
		req.setAttribute("videos", videoDAO.findAll());
		req.getRequestDispatcher("/admin/videoManagement.jsp").forward(req, resp);
		//super.service(req, resp);
	}
	
	private void doCreateVideo(HttpServletRequest req,  HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("idVideo");
		String title = req.getParameter("title");
		String view = req.getParameter("viewsVideo");
		Integer v = (view != null && !view.isEmpty()) ? Integer.parseInt(view) : 0;
		String active = req.getParameter("active");
		boolean a = (active != null) && active.equalsIgnoreCase("true");
		String description = req.getParameter("descriptions");

		try {			
			Video existingVideo = videoDAO.findById(id);

			if (existingVideo != null) {
				req.setAttribute("message", "Video đã tồn tại");
			} else {
				Video newVideo = new Video();
				newVideo.setIdVideo(id);
				newVideo.setTitile(title);
				newVideo.setViewsVideo(v);
				newVideo.setActive(a);
				newVideo.setDescriptions(description);
				
				Part filePart = req.getPart("imgFile");
	            if (filePart != null && filePart.getSize() > 0) {
	                String fileName = UUID.randomUUID().toString() + "-" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	                // Assuming you have a method to store the file, store it here
	                // For example: filePart.write(yourFilePath);
	                newVideo.setPoster(fileName); 
	            }
				videoDAO.create(newVideo);
				req.setAttribute("message", "Thêm thành công!");
			}
		} catch (Exception e) {
			req.setAttribute("message", "Thêm thất bại!");
			e.printStackTrace(); // Log the exception for debugging
			
		}
		//req.getRequestDispatcher("/admin/videoManagement.jsp").forward(req, resp);
	}
	
	private void doUpdate(HttpServletRequest req,  HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("idVideo");
        String title = req.getParameter("title");
        String view = req.getParameter("viewsVideo");
        Integer v = (view != null && !view.isEmpty()) ? Integer.parseInt(view) : 0;
        String active = req.getParameter("active");
        boolean a = (active != null) && active.equalsIgnoreCase("true");
        String description = req.getParameter("descriptions");

        try {
            Video video = new Video();
            video.setIdVideo(id);
            video.setTitile(title);
            video.setViewsVideo(v);
            video.setActive(a);
            video.setDescriptions(description);

            Part filePart = req.getPart("imgFile");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = UUID.randomUUID().toString() + "-" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                // Assuming you have a method to store the file, store it here
                // For example: filePart.write(yourFilePath);
                video.setPoster(fileName); // Set the file name to the video object
            }
            videoDAO.update(video);
            req.setAttribute("message", "Cập nhật thành công!");
        } catch (Exception e) {
            req.setAttribute("message", "Cập nhật thất bại: " + e.getMessage());
        }
	}
	
	private void doRemove(HttpServletRequest req,  HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("idVideo");
		try {			
			videoDAO.remove(id);
			req.setAttribute("message", "Xóa thành công!");
		} catch (Exception e) {
			req.setAttribute("message", "Xóa thất bại!"+e.getMessage());
			e.printStackTrace();
		}
	}
}
