package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dao.JobDAO;
import com.myclass.dao.TaskDAO;
import com.myclass.dao.UserDAO;
import com.myclass.dto.JobDTO;
import com.myclass.dto.UserDto;
import com.myclass.entity.Job;
import com.myclass.entity.Task;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		getStaffCount(req, resp);
		req.getRequestDispatcher("/WEB-INF/views/home/index.jsp").forward(req, resp);
	}
	
	private void getStaffCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO userDao = new UserDAO();
		JobDAO jobDao = new JobDAO();
		TaskDAO taskDao = new TaskDAO();
		List<UserDto> users = userDao.findAllUserDto();
		List<Job> jobs = jobDao.findAll();
		List<Task> tasks = taskDao.findAll();
		req.setAttribute("userCount", users.size());
		req.setAttribute("jobCount", jobs.size());
		req.setAttribute("taskCount", tasks.size());
		req.getRequestDispatcher("/WEB-INF/views/home/index.jsp").forward(req, resp);
	}
	
	
}
