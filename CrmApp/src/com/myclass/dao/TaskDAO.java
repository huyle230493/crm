package com.myclass.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnection;
import com.myclass.dto.TaskDTO;
import com.myclass.dto.TaskOfJobDTO;
import com.myclass.entity.Task;

public class TaskDAO {

	public List<Task> findAll() {
		String query = "SELECT * FROM tasks";
		List<Task> tasks = new ArrayList<Task>();
		// Buoc 1: Mo ket noi den database
		try (Connection conn = JDBCConnection.getConnection()) {
			// Buoc 2: Truy van du lieu tu database

			// Tao doi tuong PreparedStatement de thuc hien truy van du lieu
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Date startDate = resultSet.getDate("start_date");
				Date endDate = resultSet.getDate("end_date");
				int userId = resultSet.getInt("user_id");
				int jobId = resultSet.getInt("job_id");
				int statusId = resultSet.getInt("status_id");
				Task task = new Task(id, name, startDate, endDate, userId, jobId, statusId);
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasks;
	}

	public Task findById(int id) {

		String query = "SELECT * FROM tasks WHERE id = ?";

		Task task = null;
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao ca truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Date startDate = resultSet.getDate("start_date");
				Date endDate = resultSet.getDate("end_date");
				int userId = resultSet.getInt("user_id");
				int jobId = resultSet.getInt("job_id");
				int statusId = resultSet.getInt("status_id");
				task = new Task(id, name, startDate, endDate, userId, jobId, statusId);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return task;
	}

	public List<Task> findByUserId(int userId) {

		String query = "SELECT * FROM tasks WHERE user_id = ?";

		List<Task> tasks = new ArrayList<Task>();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao ca truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, userId);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Date startDate = resultSet.getDate("start_date");
				Date endDate = resultSet.getDate("end_date");
				// userId = resultSet.getInt("user_id");
				int jobId = resultSet.getInt("job_id");
				int statusId = resultSet.getInt("status_id");
				Task task = new Task(id, name, startDate, endDate, userId, jobId, statusId);
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasks;
	}
	
	public List<Task> findByJobId(int jobId) {

		String query = "SELECT * FROM tasks WHERE job_id = ?";

		List<Task> tasks = new ArrayList<Task>();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao ca truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, jobId);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Date startDate = resultSet.getDate("start_date");
				Date endDate = resultSet.getDate("end_date");
				int userId = resultSet.getInt("user_id");
				//jobId = resultSet.getInt("job_id");
				int statusId = resultSet.getInt("status_id");
				Task task = new Task(id, name, startDate, endDate, userId, jobId, statusId);
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasks;
	}
	
	public List<Task> findByStatusId(int statusId) {

		String query = "SELECT * FROM tasks WHERE status_id = ?";

		List<Task> tasks = new ArrayList<Task>();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao ca truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, statusId);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Date startDate = resultSet.getDate("start_date");
				Date endDate = resultSet.getDate("end_date");
				int userId = resultSet.getInt("user_id");
				int jobId = resultSet.getInt("job_id");
				//int statusId = resultSet.getInt("status_id");
				Task task = new Task(id, name, startDate, endDate, userId, jobId, statusId);
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasks;
	}
	
	public List<Task> findByManagerId(int managerId) {

		String query = "SELECT * FROM tasks WHERE job_id = (SELECT id FROM jobs WHERE user_id = ?)";

		List<Task> tasks = new ArrayList<Task>();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao ca truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, managerId);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Date startDate = resultSet.getDate("start_date");
				Date endDate = resultSet.getDate("end_date");
				int userId = resultSet.getInt("user_id");
				int jobId = resultSet.getInt("job_id");
				int statusId = resultSet.getInt("status_id");
				Task task = new Task(id, name, startDate, endDate, userId, jobId, statusId);
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasks;
	}

	public boolean deleteById(int id) {

		String query = "DELETE FROM tasks WHERE id = ?";

		// Tao ket noi database
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao cau truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			// Chay cau truy van
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insert(Task task) {
		String query = "INSERT INTO tasks(name, start_date, end_date, user_id, job_id, status_id) VALUES (?, ?, ?, ?, ?, ?)";
		// Tao ket noi database
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao cau truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, task.getName());
			statement.setDate(2, task.getStartDate());
			statement.setDate(3, task.getEndDate());
			statement.setInt(4, task.getUserId());
			statement.setInt(5, task.getJobId());
			statement.setInt(6, task.getStatusId());
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Task task) {
		String query = "UPDATE tasks SET name = ?, start_date = ?, end_date = ?, user_id = ?, job_id = ?, status_id = ? WHERE id = ?";
		// Tao ket noi database
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao cau truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, task.getName());
			statement.setDate(2, task.getStartDate());
			statement.setDate(3, task.getEndDate());
			statement.setInt(4, task.getUserId());
			statement.setInt(5, task.getJobId());
			statement.setInt(6, task.getStatusId());
			statement.setInt(7, task.getId());

			// Chay cau truy van
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<TaskDTO> findTaskByUserId(int id) {
		String query = "SELECT t.id, t.name, t.start_date, t.end_date, t.user_id, j.name as jobName, t.status_id"
				+ " FROM tasks t, jobs j"
				+ " WHERE t.job_id = j.id"
				+ " AND t.user_id = ?;";
		List<TaskDTO> tasks = new ArrayList<TaskDTO>();
		// Buoc 1: Mo ket noi den database
		try (Connection conn = JDBCConnection.getConnection()) {
			// Buoc 2: Truy van du lieu tu database

			// Tao doi tuong PreparedStatement de thuc hien truy van du lieu
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Date startDate = resultSet.getDate("start_date");
				Date endDate = resultSet.getDate("end_date");
				int userId = resultSet.getInt("user_id");
				String jobName = resultSet.getString("jobName");
				int status_id = resultSet.getInt("status_id");
				TaskDTO task = new TaskDTO(id, name, startDate, endDate, userId, jobName, status_id);
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasks;
	}
	
	public List<TaskOfJobDTO> findTaskByJobId(int jobId) {
		String query = "SELECT t.id, t.name, t.start_date, t.end_date, u.fullname as userName, t.job_id, t.status_id"
				+ " FROM tasks t, users u"
				+ " WHERE t.user_id = u.id"
				+ " AND t.job_id = ?;";
		List<TaskOfJobDTO> tasks = new ArrayList<TaskOfJobDTO>();
		// Buoc 1: Mo ket noi den database
		try (Connection conn = JDBCConnection.getConnection()) {
			// Buoc 2: Truy van du lieu tu database

			// Tao doi tuong PreparedStatement de thuc hien truy van du lieu
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, jobId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Date startDate = resultSet.getDate("start_date");
				Date endDate = resultSet.getDate("end_date");
				String userName = resultSet.getString("userName");
				jobId = resultSet.getInt("job_id");
				int status_id = resultSet.getInt("status_id");
				TaskOfJobDTO task = new TaskOfJobDTO(id, name, startDate, endDate, userName, jobId, status_id);
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasks;
	}

}
