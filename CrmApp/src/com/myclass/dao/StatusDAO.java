package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnection;
import com.myclass.entity.Status;

public class StatusDAO {
	public List<Status> findAll() {
		String query = "SELECT * FROM status";
		List<Status> statusList = new ArrayList<Status>();
		// Buoc 1: Mo ket noi den database
		try (Connection conn = JDBCConnection.getConnection()) {
			// Buoc 2: Truy van du lieu tu database

			// Tao doi tuong PreparedStatement de thuc hien truy van du lieu
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");

				Status status = new Status(id, name);
				statusList.add(status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return statusList;
	}

	public Status findById(int id) {

		String query = "SELECT * FROM status WHERE id = ?";

		Status status = null;
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao ca truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt("id");
				String name = resultSet.getString("name");

				status = new Status(id, name);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public boolean deleteById(int id) {

		String query = "DELETE FROM status WHERE id = ?";

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

	public boolean insert(Status status) {
		String query = "INSERT INTO status (name) VALUES (?)";
		// Tao ket noi database
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao cau truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, status.getName());
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Status status) {
		String query = "UPDATE status SET name = ? WHERE id = ?";
		// Tao ket noi database
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tao doi tuong PreparedStatement tao cau truy van
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, status.getName());
			statement.setInt(2, status.getId());

			// Chay cau truy van
			statement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
