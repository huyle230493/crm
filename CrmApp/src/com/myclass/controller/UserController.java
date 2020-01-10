package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dao.JobDAO;
import com.myclass.dao.RoleDAO;
import com.myclass.dao.TaskDAO;
import com.myclass.dao.UserDAO;
import com.myclass.dto.LoginDTO;
import com.myclass.dto.TaskDTO;
import com.myclass.dto.TaskOfJobDTO;
import com.myclass.dto.UserDto;
import com.myclass.entity.Job;
import com.myclass.entity.Role;
import com.myclass.entity.Task;
import com.myclass.entity.User;
import com.myclass.util.PathConstants;
import com.myclass.util.UrlConstants;

@WebServlet(name = "UserServlet", urlPatterns = { UrlConstants.URL_USER_LIST, UrlConstants.URL_USER_ADD,
		UrlConstants.URL_USER_EDIT, UrlConstants.URL_USER_DELETE, UrlConstants.URL_USER_SHOW,
		UrlConstants.URL_USER_DETAILS })
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int NEW_STATUS_ID = 1;
	private static final int TODO_STATUS_ID = 2;
	private static final int DONE_STATUS_ID = 3;
	private UserDAO userDao = null;
	private RoleDAO roleDao = null;
	private JobDAO jobDao;
	private TaskDAO taskDao;

	@Override
	public void init() throws ServletException {
		userDao = new UserDAO();
		roleDao = new RoleDAO();
		jobDao = new JobDAO();
		taskDao = new TaskDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {
		case UrlConstants.URL_USER_LIST:
			getUserList(req, resp);
			break;
		case UrlConstants.URL_USER_ADD:
			getUserAdd(req, resp);
			break;
		case UrlConstants.URL_USER_EDIT:
			getUserEdit(req, resp);
			break;
		case UrlConstants.URL_USER_DETAILS:
			getUserDetails(req, resp);
			break;
		case UrlConstants.URL_USER_DELETE:
			deleteUser(req, resp);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case UrlConstants.URL_USER_ADD:
			postUserAdd(req, resp);
			break;
		case UrlConstants.URL_USER_EDIT:
			postUserEdit(req, resp);
			break;
		default:
			break;
		}
	}

	private void getUserList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<UserDto> users = userDao.findAllUserDto();
		req.setAttribute("users", users);
		req.getRequestDispatcher(PathConstants.PATH_USER_LIST).forward(req, resp);
	}

	private void getUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("roles", roleDao.findAll());
		req.getRequestDispatcher(PathConstants.PATH_USER_ADD).forward(req, resp);
	}

	private void getUserEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		User user = userDao.findById(id);
		req.setAttribute("user", user);
		req.getRequestDispatcher(PathConstants.PATH_USER_EDIT).forward(req, resp);
	}

	private void getUserDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginDTO login = (LoginDTO) req.getSession().getAttribute("USER_LOGIN");
		req.setAttribute("username", login.getFullname());
		req.setAttribute("useremail", login.getEmail());
		int id = Integer.parseInt(req.getParameter("id"));
		User user = userDao.findById(id);
		req.setAttribute("id", user.getId());
		req.setAttribute("name", user.getFullname());
		req.setAttribute("email", user.getEmail());
		Role role = roleDao.findById(user.getRoleId());
		req.setAttribute("role", role.getName());
		List<TaskDTO> tasks = taskDao.findTaskByUserId(user.getId());
		int totalTasks = tasks.size();
		int totalTodoTasks = 0;
		int totalNewTasks = 0;
		int totalDoneTasks = 0;
		for (TaskDTO task : tasks) {
			if (task.getStatusId() == NEW_STATUS_ID)
				totalNewTasks++;
			if (task.getStatusId() == TODO_STATUS_ID)
				totalTodoTasks++;
			if (task.getStatusId() == DONE_STATUS_ID)
				totalDoneTasks++;
		}
		if (totalTasks != 0) {
			totalNewTasks = (totalNewTasks / totalTasks) * 100;
			totalTodoTasks = (totalTodoTasks / totalTasks) * 100;
			totalDoneTasks = (totalDoneTasks / totalTasks) * 100;
		}
		req.setAttribute("newTasks", totalNewTasks);
		req.setAttribute("todoTasks", totalTodoTasks);
		req.setAttribute("doneTasks", totalDoneTasks);
		req.setAttribute("tasks", tasks);
		req.getRequestDispatcher(PathConstants.PATH_USER_DETAILS).forward(req, resp);
	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		List<Job> jobs = jobDao.findByUserId(id);
		if (jobs != null) {
			for (Job job : jobs) {
				List<TaskOfJobDTO> tasks = taskDao.findTaskByJobId(job.getId());
				for (TaskOfJobDTO task : tasks)
					taskDao.deleteById(task.getId());
			}
		}
		
		List<Task> tasks = taskDao.findByUserId(id);
		if (tasks != null)
			for (Task task : tasks)
				taskDao.deleteById(task.getId());
		
		userDao.deleteById(id);
		// Quay ve trang danh sach
		resp.sendRedirect(req.getContextPath() + UrlConstants.URL_USER_LIST);
	}

	private void postUserAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// Lay du lieu tu form
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String avatar = req.getParameter("avatar");
		int roleId = Integer.parseInt(req.getParameter("roleId"));

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setFullname(fullname);
		user.setAvatar(avatar);
		user.setRoleId(roleId);

		// Mã hóa mật khẩu
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
		user.setPassword(hashed);

		userDao.insert(user);

		// Quay ve trang danh sach
		resp.sendRedirect(req.getContextPath() + UrlConstants.URL_USER_LIST);
	}

	private void postUserEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// Lay du lieu tu form
		int id = Integer.parseInt(req.getParameter("id"));
		// Lay du lieu tu form
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String avatar = req.getParameter("avatar");
		int roleId = Integer.parseInt(req.getParameter("roleId"));

		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setPassword(password);
		user.setFullname(fullname);
		user.setAvatar(avatar);
		user.setRoleId(roleId);

		userDao.update(user);

		// Quay ve trang danh sach
		resp.sendRedirect(req.getContextPath() + UrlConstants.URL_USER_LIST);
	}
}
