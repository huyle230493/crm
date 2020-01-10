package com.myclass.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dao.JobDAO;
import com.myclass.dao.TaskDAO;
import com.myclass.dao.UserDAO;
import com.myclass.dto.JobDTO;
import com.myclass.dto.LoginDTO;
import com.myclass.dto.TaskDTO;
import com.myclass.dto.TaskOfJobDTO;
import com.myclass.entity.Job;
import com.myclass.entity.Role;
import com.myclass.entity.Task;
import com.myclass.entity.User;
import com.myclass.util.PathConstants;
import com.myclass.util.UrlConstants;
@WebServlet(urlPatterns = {
		UrlConstants.URL_JOB_LIST, 
		UrlConstants.URL_JOB_ADD, 
		UrlConstants.URL_JOB_EDIT, 
		UrlConstants.URL_JOB_DELETE,
		UrlConstants.URL_JOB_DETAILS
		})
public class JobController extends HttpServlet{
	JobDAO jobDao;
	UserDAO userDao;
	TaskDAO taskDao;
	private static final int ROLE_MANAGER_ID = 2;
	private static final int NEW_STATUS_ID = 1;
	private static final int TODO_STATUS_ID = 2;
	private static final int DONE_STATUS_ID = 3;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		//super.init();
		jobDao = new JobDAO();
		userDao = new UserDAO();
		taskDao  = new TaskDAO();
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		String action = req.getServletPath();
		switch (action) {
			case UrlConstants.URL_JOB_LIST:
				getJobList(req, resp);
				break;
			case UrlConstants.URL_JOB_ADD:
				getJobAdd(req, resp);
				break;
			case UrlConstants.URL_JOB_EDIT:
				getJobEdit(req, resp);
				break;
			case UrlConstants.URL_JOB_DELETE:
				deleteJob(req, resp);
				break;
			case UrlConstants.URL_JOB_DETAILS:
				getJobDetails(req, resp);
				break;
			default:
				break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();
		switch (action) {
			case UrlConstants.URL_JOB_ADD:
				try {
					postJobAdd(req, resp);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case UrlConstants.URL_JOB_EDIT:
				try {
					postJobEdit(req, resp);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
		}
	}
	
	private void postJobAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
		// TODO Auto-generated method stub
		String name = req.getParameter("name");
		String startDateStr = req.getParameter("start-date");
		String endDateStr = req.getParameter("end-date");
		int userId = Integer.parseInt(req.getParameter("user-id"));
		Date startDate = Date.valueOf(startDateStr);
		Date endDate = Date.valueOf(endDateStr);
		
		Job job = new Job();
		job.setName(name);
		job.setStartDate(startDate);
		job.setEndDate(endDate);
		job.setUserId(userId);
		jobDao.insert(job);
		
		resp.sendRedirect(req.getContextPath() + UrlConstants.URL_JOB_LIST);
	}
	
	private void postJobEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String startDateStr = req.getParameter("start-date");
		String endDateStr = req.getParameter("end-date");
		int userId = Integer.parseInt(req.getParameter("user-id"));
		Date startDate = Date.valueOf(startDateStr);
		Date endDate = Date.valueOf(endDateStr);

		Job job = new Job();
		job.setId(id);
		job.setName(name);
		job.setStartDate(startDate);
		job.setEndDate(endDate);
		job.setUserId(userId);
		
		jobDao.update(job);
		resp.sendRedirect(req.getContextPath() + UrlConstants.URL_JOB_LIST);
	}
	
	private void getJobList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginDTO login = (LoginDTO) req.getSession().getAttribute("USER_LOGIN");
		List<Job> jobs = new ArrayList<>();
		if (login.getRoleName().equals("ROLE_ADMIN"))
			jobs = jobDao.findAll();
		else
			jobs = jobDao.findByUserId(login.getId());
		req.setAttribute("jobs", jobs);
		req.getRequestDispatcher(PathConstants.PATH_JOB_LIST).forward(req, resp);
	}
	
	private void getJobAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setAttribute("users", userDao.findByRoleId(ROLE_MANAGER_ID));
		req.getRequestDispatcher(PathConstants.PATH_JOB_ADD).forward(req, resp);
	}
	
	private void getJobEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setAttribute("users", userDao.findByRoleId(ROLE_MANAGER_ID));
		int id = Integer.parseInt(req.getParameter("id"));
		Job job = jobDao.findById(id);
		req.setAttribute("job", job);
		req.getRequestDispatcher(PathConstants.PATH_JOB_EDIT).forward(req, resp);
	}

	private void deleteJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int jobId = Integer.parseInt(req.getParameter("id"));
		List<Task> tasks = taskDao.findByJobId(jobId);
		for (Task task : tasks)
			taskDao.deleteById(task.getId());
		jobDao.deleteById(jobId);
		req.getRequestDispatcher(UrlConstants.URL_JOB_LIST).forward(req, resp);
	}
	
	private void getJobDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LoginDTO login = (LoginDTO) req.getSession().getAttribute("USER_LOGIN");
		req.setAttribute("username", login.getFullname());
		req.setAttribute("useremail", login.getEmail());
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println(id);
		JobDTO job = jobDao.findByJobId(id);
		if (job == null)
			System.out.println("Không tìm thấy job");
		req.setAttribute("id", job.getId());
		req.setAttribute("name", job.getName());
		req.setAttribute("startDate", job.getStartDate());
		req.setAttribute("endDate", job.getEndDate());
		req.setAttribute("userName", job.getUserName());
		
		List<TaskOfJobDTO> tasks = taskDao.findTaskByJobId(job.getId());
		int totalTasks = tasks.size();
		int totalTodoTasks = 0;
		int totalNewTasks = 0;
		int totalDoneTasks = 0;
		for (TaskOfJobDTO task : tasks) {
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
		req.getRequestDispatcher(PathConstants.PATH_JOB_DETAILS).forward(req, resp);
	}
}
