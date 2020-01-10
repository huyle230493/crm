package com.myclass.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnection;
import com.myclass.dto.JobDTO;
import com.myclass.entity.Job;

public class JobDAO {
	public List<Job> findAll() {
		String query = "SELECT * FROM Jobs";
		List<Job> jobs = new ArrayList<Job>();
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
				Job job = new Job(id, name, startDate, endDate, userId);
				jobs.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return jobs;
	}

	public Job findById(int id) {

		String query = "SELECT * FROM Jobs WHERE id = ?";

		Job job = null;
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
				job = new Job(id, name, startDate, endDate, userId);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return job;
	}
	
	public List<Job> findByUserId(int userId) {

		String query = "SELECT * FROM Jobs WHERE user_id = ?";

		List<Job> jobs = new ArrayList<Job>();
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
				//userId = resultSet.getInt("user_id");
				Job job = new Job(id, name, startDate, endDate, userId);
				jobs.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return jobs;
	}

	public boolean deleteById(int id) {

		String query = "DELETE FROM Jobs WHERE id = ?";

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

	public boolean insert(Job job) {
		String query = "INSERT INTO Jobs(name, start_date, end_date, user_id) VALUES (?, ?, ?, ?)";
		// Tao ket noi database
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao cau truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, job.getName());
			statement.setDate(2, job.getStartDate());
			statement.setDate(3, job.getEndDate());
			statement.setInt(4, job.getUserId());
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Job job) {
		String query = "UPDATE Jobs SET name = ?, start_date = ?, end_date = ?, user_id = ? WHERE id = ?";
		// Tao ket noi database
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao cau truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, job.getName());
			statement.setDate(2, job.getStartDate());
			statement.setDate(3, job.getEndDate());
			statement.setInt(4, job.getUserId());
			statement.setInt(5, job.getId());

			// Chay cau truy van
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public JobDTO findByJobId(int id) {

		String query = "SELECT j.id, j.name, j.start_date, j.end_date, u.fullname as userName "
				+ " FROM Jobs j, Users u"
				+ " WHERE j.user_id = u.id"
				+ " AND j.id = ?;";

		JobDTO job = null;
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
				String userName = resultSet.getString("userName");
				job = new JobDTO(id, name, startDate, endDate, userName);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return job;
	}
}
