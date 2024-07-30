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

@WebServlet({"/admin/userManagement","/admin/updUser","/admin/removeUser","/admin/editUser/*"})
public class UserManagementServlet extends HttpServlet{
	User user = new User();
	UserDAO userDAO = new UserDAO();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String uri = req.getRequestURI();	
		
		try {
			if (uri.contains("/admin/userManagement")) {
				System.out.println("index");
				req.setAttribute("users", userDAO.findUser());
				req.getRequestDispatcher("/admin/userManagement.jsp").forward(req, resp);
			} else if (uri.contains("/admin/updUser")) {
				System.out.println("update");
				this.doUpdate(req, resp);
				req.getRequestDispatcher("/admin/userManagement.jsp").forward(req, resp);
			} else if (uri.contains("/admin/editUser")) {
				System.out.println("edit");
				String id = uri.substring(uri.lastIndexOf("/") + 1);
				user = userDAO.findById(id);
				req.setAttribute("editUser", user);
				req.setAttribute("users", userDAO.findUser());
				req.getRequestDispatcher("/admin/userManagement.jsp").forward(req, resp);
			}else if (uri.contains("removeUser")) {
				this.doRemove(req,resp);
			}
			System.out.println(uri);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//req.setAttribute("users", userDAO.findUser());
		//req.getRequestDispatcher("/admin/userManagement.jsp").forward(req, resp);
		//super.service(req, resp);
	}
		
	private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("idUser");
        String fullname = req.getParameter("fullnameUser");
        String email = req.getParameter("emailUser");        

        try {
            User user = new User(id,false,email,fullname);
			/*
			 * user.setIdUser(id); user.setFullnameUser(fullname); user.setEmailUser(email);
			 * user.setAdminUser(false);
			 */
            userDAO.update(user);
            req.setAttribute("message", "Cập nhật thành công!");
        } catch (Exception e) {
            req.setAttribute("message", "Cập nhật thất bại: " + e.getMessage());
            e.printStackTrace();
        }
		//req.getRequestDispatcher("update.jsp").forward(req, resp);
	}
	
	private void doRemove(HttpServletRequest req,  HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("idUser");
		try {			
			userDAO.remove(id);
			req.setAttribute("message", "Xóa thành công!");
		} catch (Exception e) {
			req.setAttribute("message", "Xóa thất bại!"+e.getMessage());
			e.printStackTrace();
		}
	}
}
