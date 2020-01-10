package com.myclass.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.connection.JDBCConnection;
import com.myclass.dao.RoleDAO;
import com.myclass.entity.Role;
import com.myclass.util.PathConstants;
import com.myclass.util.UrlConstants;

@WebServlet(name = "RoleServlet", urlPatterns = { UrlConstants.URL_ROLE_LIST, UrlConstants.URL_ROLE_ADD,
		UrlConstants.URL_ROLE_EDIT, UrlConstants.URL_ROLE_DELETE })
public class RoleController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private RoleDAO roleDao = null;

	@Override
	public void init() throws ServletException {
		roleDao = new RoleDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		System.out.println("ROLE_GET");
		HttpSession session = req.getSession();
		
		switch (action) {
		case UrlConstants.URL_ROLE_LIST:
			// Lay ra thong tin chua trong session voi key la message
			String message = (String)session.getAttribute("message");
			req.setAttribute("notify", message);
			// Xoa session co key la message
			session.removeAttribute("message");
			
			getRoleList(req, resp);
			break;
		case UrlConstants.URL_ROLE_ADD:
			getRoleAdd(req, resp);
			break;
		case UrlConstants.URL_ROLE_EDIT:
			getRoleEdit(req, resp);
			break;
		case UrlConstants.URL_ROLE_DELETE:
			deleteRole(req, resp);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String action = req.getServletPath();

		switch (action) {
		case UrlConstants.URL_ROLE_ADD:
			postRoleAdd(req, resp);
			break;
		case UrlConstants.URL_ROLE_EDIT:
			postRoleEdit(req, resp);
			break;
		default:
			break;
		}
	}

	private void getRoleList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Role> roles = roleDao.findAll();

		// Buuoc 3: Gui du lieu ve trang jsp de show len
		req.setAttribute("roles", roles);
		req.getRequestDispatcher(PathConstants.PATH_ROLE_LIST).forward(req, resp);
	}

	private void getRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(PathConstants.PATH_ROLE_ADD).forward(req, resp);
	}

	private void getRoleEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Lay id client truyen len url
		int id = Integer.parseInt(req.getParameter("id"));
		Role role = roleDao.findById(id);

		req.setAttribute("role", role);
		req.getRequestDispatcher(PathConstants.PATH_ROLE_EDIT).forward(req, resp);
	}

	private void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Lay id client truyen len url
		int id = Integer.parseInt(req.getParameter("id"));
		boolean result = roleDao.deleteById(id);

		// Tao session
		HttpSession session = req.getSession();
		
		if (result) {
			// Xuat thong bao xoa thanh cong
			session.setAttribute("message", "Xóa thành công!");
		} else {
			// Xuat thong bao xoa that bai
			session.setAttribute("message", "Xóa thất bại!");
		}

		// Quay ve trang danh sach
		resp.sendRedirect(req.getContextPath() + UrlConstants.URL_ROLE_LIST);
	}

	private void postRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		// Lay du lieu tu form
		String name = req.getParameter("name"); // "ROLE_ABC"
		String description = req.getParameter("description"); // "Mo ta"
		
		// Validate (Kiem tra du lieu nguoi dung nhap)
		if(name == null || name.isEmpty()) {
			req.setAttribute("message", "Vui lòng nhập tên!");
			req.getRequestDispatcher("/WEB-INF/views/role/add.jsp").forward(req, resp);
			return;
		}

		Role role = new Role();
		role.setName(name);
		role.setDescription(description);

		boolean result = roleDao.insert(role);

		if (result) {
			// Xuat thong bao xoa thanh cong
			HttpSession session = req.getSession();
			session.setAttribute("message", "Thêm mới thành công!");
			// Quay ve trang danh sach
			resp.sendRedirect(req.getContextPath() + UrlConstants.URL_ROLE_LIST);
		} else {
			// Xuat thong bao xoa that bai
			req.setAttribute("message", "Thêm mới thất bại!");
			req.getRequestDispatcher("/WEB-INF/views/role/add.jsp").forward(req, resp);
		}
	}

	private void postRoleEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// Lay du lieu tu form
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		
		Role role = new Role(id, name, description);
		roleDao.update(role);
		
		// Quay ve trang danh sach
		resp.sendRedirect(req.getContextPath() + UrlConstants.URL_ROLE_LIST);
	}
}
