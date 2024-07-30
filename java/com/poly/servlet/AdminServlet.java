package com.poly.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.UserDAO;
import com.poly.dao.VideoDAO;
import com.poly.model.Favorite;
import com.poly.model.User;
import com.poly.model.Video;
import com.poly.utils.JpaUtils;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet({"/admin/homeAdmin","/admin/favorites","/admin/favoriteUser","/admin/favoriteUser/findVideoByUser","/admin/shareFriend"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user = new User();
	UserDAO userDAO = new UserDAO();	
	Video video = new Video();
	VideoDAO videoDAO = new VideoDAO();
	private EntityManager em = JpaUtils.getEntityManager();
    
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = req.getRequestURI();
		if(uri.contains("homeAdmin")) {
			req.getRequestDispatcher("/admin/indexAdmin.jsp").forward(req, resp);		
		}else if (uri.contains("favorites")) {
			req.getRequestDispatcher("/admin/favorites.jsp").forward(req, resp);
		} else if (uri.contains("favoriteUser")) {
			this.doFavoriteUser(req, resp);
		} else if (uri.contains("shareFriend")) {
			req.getRequestDispatcher("/admin/shareFriend.jsp").forward(req, resp);
		} 
	}
	
	private void doFavoriteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.contains("/favoriteUser/findVideoByUser")) {
			String username = req.getParameter("username");
			User user = em.find(User.class, username);
			List<Favorite> favorites = user.getFavorites();
			req.setAttribute("user", user);
			req.setAttribute("favorites", favorites);	

		}
		req.getRequestDispatcher("/admin/favoriteUser.jsp").forward(req, resp);
	}
	
	

}
