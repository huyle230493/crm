package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dao.UserDAO;
import com.myclass.dto.LoginDTO;
import com.myclass.entity.User;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class AuthController extends HttpServlet{

	UserDAO userDao = null;
	
	@Override
	public void init() throws ServletException {
		userDao = new UserDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String action = req.getServletPath();
		if(action.equals("/logout")) {
			//Xóa session để đăng xuất
			HttpSession session = req.getSession();
			if(session != null && session.getAttribute("USER_LOGIN") != null){
				session.removeAttribute("USER_LOGIN");
			}
//			resp.sendRedirect(req.getContextPath() + "/login");
//			return;
		}
		
		req.getRequestDispatcher("/WEB-INF/views/login/index.jsp")
		.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// Lấy thông tin từ form
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		// Truy vấn database kiểm tra xem email có tồn tại trong database ko
		System.out.println(email);
		LoginDTO login = userDao.check(email);
		if (login == null)
			System.out.println("Chưa tìm thấy loginDTO");
		//System.out.println(login.getEmail());
		//System.out.println(login.getPassword());
		if(login == null || BCrypt.checkpw(password, login.getPassword()) == false) {
			req.setAttribute("message", "Thông tin đăng nhập không đúng!");
			req.getRequestDispatcher("/WEB-INF/views/login/index.jsp").forward(req, resp);
			return;
		}
		
		// Kiểm tra đúng thông tin đăng nhập
		// Lưu thông tin user vào session -> redirect về HomeController
		HttpSession session = req.getSession();
		session.setAttribute("USER_LOGIN", login);
		
		// Trước khi đi đến trang home nó sẽ quay lại AuthFilter một lần nữa để kiểm tra phân quyền.
		resp.sendRedirect(req.getContextPath() +  "/home");
		
	}
}
