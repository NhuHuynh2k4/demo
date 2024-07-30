package com.poly.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.dao.FavoriteDAO;
import com.poly.dao.UserDAO;
import com.poly.dao.VideoDAO;
import com.poly.model.Favorite;
import com.poly.model.User;
import com.poly.model.Video;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet({"/home","/videoDetails/*","/shareFriend","/likeVideo"})
public class HomeServlet extends HttpServlet {
	Video video = new Video();
	VideoDAO videoDAO = new VideoDAO();
	FavoriteDAO favoriteDAO = new FavoriteDAO();

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = req.getRequestURI();
		if(uri.contains("home")) {
			this.doHome(req, resp);			
		}else if (uri.contains("videoDetails")) {
			this.doVideoDetail(req, resp);
		} else if (uri.contains("likeVideo")) {
			
		} else if (uri.contains("shareFriend")) {
			
		} 
	}

	
	protected void doHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Video> videoList = videoDAO.findAll();
		request.setAttribute("listVideos", videoList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	protected void doLikeVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Favorite> favoriteList = favoriteDAO.findAll();
		request.setAttribute("videosFavorite", favoriteList);
		request.getRequestDispatcher("/likeVideo.jsp").forward(request, response);
	}

	protected void doVideoDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		String videoId = extractVideoId(uri);
		Video video = videoDAO.findById(videoId);
		request.setAttribute("videoDetail", video);
		List<Video> videos = videoDAO.findAll();
		request.setAttribute("videoList", videos);
		request.getRequestDispatcher("/video.jsp").forward(request, response);
	}
	
	private String extractVideoId(String uri) {
		// Extract the video ID from the URI
		return uri.substring(uri.lastIndexOf("/") + 1);
	}
}
