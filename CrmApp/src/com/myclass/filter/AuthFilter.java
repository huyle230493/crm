package com.myclass.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.LoginDTO;

@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//Ép kiểu ServletRequest thành HttpServletRequest để sử dụng được hàm getSession()
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String action = req.getServletPath();
		// Nếu url bắt đầu bằng login thì bỏ qua ko kiểm tra session (Kiểm tra đăng nhập)
		if (action.startsWith("/login")) {
			chain.doFilter(request, response);
			return;
		}
		System.out.println("OK");
		// Kiểm tra session
		HttpSession session = req.getSession();
		// Nếu session = null => bắt quay lại trang đăng nhập để đăng nhập trước
		System.out.println(session.getAttribute("USER_LOGIN"));
		if (session == null || session.getAttribute("USER_LOGIN") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		// Kiểm tra phân quyền
		
		// Lấy được rolename của user lưu trong session
		LoginDTO login = (LoginDTO) session.getAttribute("USER_LOGIN");
		String roleName = login.getRoleName();
		req.setAttribute("roleName", roleName);
		// check url và rolename
		boolean match = true;
		if (roleName.equals("ROLE_MANAGER") && action.startsWith("/role")) {
				match = false;
		}
		else if (roleName.equals("ROLE_MEMBER") && (!action.startsWith("/home") && !action.startsWith("/task")) ){
			match = false;
		}
		
		if (!match) {
			resp.sendRedirect(req.getContextPath() + "/error/404");
			return;
		}
		
		chain.doFilter(req, resp);
		// check url và rolename
		//if ((action.startsWith("/user") || action.startsWith("/role")) 
				//&& !roleName.equals("ROLE_ADMIN")) {
			//resp.sendRedirect(req.getContextPath() + "/error/403");
			//return;
		//}
		//else if ((action.startsWith("/project")) 
				//&& roleName.equals("ROLE_MEMBER")) {
			//resp.sendRedirect(req.getContextPath() + "/error/403");
			//return;
		//}
		//chain.doFilter(req, resp);
	}

}
