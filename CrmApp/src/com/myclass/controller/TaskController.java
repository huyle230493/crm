package com.myclass.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dao.JobDAO;
import com.myclass.dao.StatusDAO;
import com.myclass.dao.TaskDAO;
import com.myclass.dao.UserDAO;
import com.myclass.dto.LoginDTO;
import com.myclass.entity.Task;
import com.myclass.util.PathConstants;
import com.myclass.util.UrlConstants;

@WebServlet(urlPatterns = { UrlConstants.URL_TASK_LIST, UrlConstants.URL_TASK_ADD, UrlConstants.URL_TASK_EDIT,
		UrlConstants.URL_TASK_DELETE, UrlConstants.URL_TASK_DETAILS })
public class TaskController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ROLE_MEMBER_ID = 11;
	TaskDAO taskDao;
	StatusDAO statusDao;
	UserDAO userDao;
	JobDAO jobDao;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		taskDao = new TaskDAO();
		statusDao = new StatusDAO();
		userDao = new UserDAO();
		jobDao = new JobDAO();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doGet(req, resp);
		String action = req.getServletPath();
		switch (action) {
		case UrlConstants.URL_TASK_LIST:
			getTaskList(req, resp);
			break;
		case UrlConstants.URL_TASK_ADD:
			getTaskAdd(req, resp);
			break;
		case UrlConstants.URL_TASK_EDIT:
			getTaskEdit(req, resp);
			break;
		case UrlConstants.URL_TASK_DELETE:
			deleteTask(req, resp);
			break;
		case UrlConstants.URL_TASK_DETAILS:
			getTaskDetails(req, resp);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(req, resp);
		String action = req.getServletPath();
		
		switch (action) {
			case UrlConstants.URL_TASK_ADD:
				postTaskAdd(req, resp);
				break;
			case UrlConstants.URL_TASK_EDIT:
				postTaskEdit(req, resp);
				break;
			default:
				break;
		}
	}

	private void getTaskList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Task> tasks = new ArrayList<>();
		LoginDTO login = (LoginDTO) req.getSession().getAttribute("USER_LOGIN");
		if (login.getRoleName().equals("ROLE_ADMIN"))
			tasks = taskDao.findAll();
		else if (login.getRoleName().equals("ROLE_MANAGER")) {
			tasks = taskDao.findByManagerId(login.getId());
		}
		else
			tasks = taskDao.findByUserId(login.getId());
		req.setAttribute("tasks", tasks);
		req.getRequestDispatcher(PathConstants.PATH_TASK_LIST).forward(req, resp);
	}

	private void getTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginDTO login = (LoginDTO) req.getSession().getAttribute("USER_LOGIN");
		req.setAttribute("users", userDao.findByRoleId(ROLE_MEMBER_ID));
		// Lấy danh sách dự án do nhân viên trên phụ trách
		if (login.getRoleName().equals("ROLE_ADMIN"))
			req.setAttribute("jobs", jobDao.findAll());
		else
			req.setAttribute("jobs", jobDao.findByUserId(login.getId()));
		// Lấy danh sách tình trạng công việc
		req.setAttribute("status", statusDao.findAll());
		req.getRequestDispatcher(PathConstants.PATH_TASK_ADD).forward(req, resp);
	}

	private void getTaskEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginDTO login = (LoginDTO) req.getSession().getAttribute("USER_LOGIN");
		if (!login.getRoleName().equals("ROLE_MEMBER")) {
			req.setAttribute("users", userDao.findByRoleId(ROLE_MEMBER_ID));
			// Lấy danh sách dự án do nhân viên trên phụ trách
			if (login.getRoleName().equals("ROLE_ADMIN"))
				req.setAttribute("jobs", jobDao.findAll());
			else
				req.setAttribute("jobs", jobDao.findByUserId(login.getId()));
			// Lấy danh sách tình trạng công việc
			req.setAttribute("status", statusDao.findAll());
			int id = Integer.parseInt(req.getParameter("id"));
			Task task = taskDao.findById(id);
			req.setAttribute("task", task);
		}
		else {
			req.setAttribute("users", userDao.findById(login.getId()));
			req.setAttribute("status", statusDao.findAll());
			int id = Integer.parseInt(req.getParameter("id"));
			Task task = taskDao.findById(id);
			req.setAttribute("task", task);
			req.setAttribute("jobs", jobDao.findById(task.getJobId()));
		}
		req.getRequestDispatcher(PathConstants.PATH_TASK_EDIT).forward(req, resp);
	}

	private void deleteTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		taskDao.deleteById(id);
		resp.sendRedirect(req.getContextPath() + UrlConstants.URL_TASK_LIST);
	}
	
	private void getTaskDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(PathConstants.PATH_TASK_DETAILS).forward(req, resp);
	}
	
	private void postTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String name = req.getParameter("name");
		Date startDate = Date.valueOf(req.getParameter("start-date"));
		Date endDate = Date.valueOf(req.getParameter("end-date"));
		int userId = Integer.parseInt(req.getParameter("user-id"));
		int jobId = Integer.parseInt(req.getParameter("job-id"));
		int statusId = Integer.parseInt(req.getParameter("status-id"));
		Task task = new Task();
		task.setName(name);
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		task.setUserId(userId);
		task.setJobId(jobId);
		task.setStatusId(statusId);
		taskDao.insert(task);
		resp.sendRedirect(req.getContextPath() + UrlConstants.URL_TASK_LIST);
	}
	
	private void postTaskEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		Date startDate = Date.valueOf(req.getParameter("start-date"));
		Date endDate = Date.valueOf(req.getParameter("end-date"));
		int userId = Integer.parseInt(req.getParameter("user-id"));
		int jobId = Integer.parseInt(req.getParameter("job-id"));
		int statusId = Integer.parseInt(req.getParameter("status-id"));
		Task task = new Task();
		task.setId(id);
		task.setName(name);
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		task.setUserId(userId);
		task.setJobId(jobId);
		task.setStatusId(statusId);
		taskDao.update(task);
		resp.sendRedirect(req.getContextPath() + UrlConstants.URL_TASK_LIST);
	}
}
