package com.poly.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.UserDAO;
import com.poly.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/login", "/logout", "/register", "/forgotPass", "/changePass" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = req.getRequestURI();
		if (uri.contains("login")) {
			this.doLogin(req, resp);
		} else if (uri.contains("logout")) {
			this.doLogout(req, resp);
		} else if (uri.contains("register")) {
			this.doRegister(req, resp);
		} else if (uri.contains("forgotPass")) {

		} else if (uri.contains("changePass")) {
			this.doChange(req, resp);
		}

	}

	private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if (method.equalsIgnoreCase("POST")) {
			String id = req.getParameter("username");
			String pw = req.getParameter("password");
			try {
				UserDAO dao = new UserDAO();
				User user = dao.findById(id);

				if (user != null && user.getPassUser().equals(pw)) {
					if (user.getAdminUser() == true) {
						req.getSession().setAttribute("user", user);
						System.out.println("Đăng nhập thành công!");
						resp.sendRedirect(req.getContextPath() + "/admin/homeAdmin");
					} else {
						req.getSession().setAttribute("user", user);
						System.out.println("Đăng nhập thành công!");
						resp.sendRedirect(req.getContextPath() + "/home");
					}
				} else {
					req.setAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
					System.out.println("Sai tên đăng nhập hoặc mật khẩu!");
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				}
			} catch (Exception e) {
				req.setAttribute("message", "Đã xảy ra lỗi!");
				System.out.println("Đã xảy ra lỗi!" + e);
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		} else {
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}

	private void doRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * String method = req.getMethod(); if (method.equalsIgnoreCase("POST")) { try {
		 * User entity = new User(); BeanUtils.populate(entity, req.getParameterMap());
		 * UserDAO dao = new UserDAO(); dao.create(entity); req.setAttribute("message",
		 * "Đăng ký thành công!"); } catch (Exception e) { req.setAttribute("message",
		 * "Lỗi đăng ký!"); } }
		 */

		String id = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");

		try {
			UserDAO dao = new UserDAO();
			User existingUser = dao.findById(id);

			if (existingUser != null) {
				req.setAttribute("message", "Username already exists.");
				req.getRequestDispatcher("register.jsp").forward(req, resp);
			} else {
				User newUser = new User();
				newUser.setIdUser(id);
				newUser.setPassUser(password);
				newUser.setEmailUser(email);
				newUser.setFullnameUser(fullname);
				newUser.setAdminUser(false); // Assuming all registered users are not admins by default
				dao.create(newUser);
				System.out.println("Đăng ký thành công!");
				req.setAttribute("message", "Registration successful. You can now log in.");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			System.out.println("Đăng ký thất bại" + e);
			req.setAttribute("message", "An error occurred while processing your request.");
			//req.getRequestDispatcher("register.jsp").forward(req, resp);
			e.printStackTrace(); // Log the exception for debugging
		}

		req.getRequestDispatcher("register.jsp").forward(req, resp);
	}

	private void doChange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		String method = req.getMethod();
		if (method.equalsIgnoreCase("POST")) {
			String oldPass = req.getParameter("oldpassword");
			String newPass = req.getParameter("newpassword");
			String pass = req.getParameter("password");
			if (!oldPass.equalsIgnoreCase(user.getPassUser())) {
				System.out.println("Mật khẩu cũ không chính xác!");
				req.getRequestDispatcher("changePass.jsp").forward(req, resp);
			} else {
				if (!newPass.equalsIgnoreCase(pass)) {
					System.out.println("Mật khẩu xác nhận không đúng!");
					req.getRequestDispatcher("changePass.jsp").forward(req, resp);
				} else {
					try {
						UserDAO dao = new UserDAO();
						User userNew = new User();
						userNew.setAdminUser(user.getAdminUser());
						userNew.setEmailUser(user.getEmailUser());
						userNew.setFullnameUser(user.getFullnameUser());
						userNew.setIdUser(user.getIdUser());
						userNew.setPassUser(newPass);
						dao.update(userNew);
						System.out.println("Đổi mật khẩu thành công!");
						req.setAttribute("messageEdit", "Đổi mật khẩu thành công!");
						// Redirect to another page after successful update
						resp.sendRedirect(req.getContextPath() + "/home");
						return;
					} catch (Exception e) {
						req.setAttribute("messageEdit", "Lỗi cập đổi mật khẩu");
						System.out.println("Đổi mật khẩu thành công!");
						e.printStackTrace();
					}
				}
			}
		}
		req.getRequestDispatcher("changePass.jsp").forward(req, resp);

	}

	private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			HttpSession session = req.getSession();
			session.removeAttribute("user");
			System.out.println("Đăng xuất thành công!");
			resp.sendRedirect(req.getContextPath() + "/home");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi đăng xuất!");
		}

	}

}
