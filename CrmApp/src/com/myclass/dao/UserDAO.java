package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.myclass.connection.JDBCConnection;
import com.myclass.dto.LoginDTO;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;

public class UserDAO {

	public List<User> findAll() {

		String query = "SELECT * FROM users";
		List<User> users = new ArrayList<User>();

		try (Connection conn = JDBCConnection.getConnection()) {
			// Bước 2: Gửi câu truy vấn
			// Tạo ra câu truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			// Thực thi truy vấn lấy dữu liệu
			ResultSet rs = statement.executeQuery();

			// Bước 3: Xử ký kết quả trả về
			while (rs.next()) {
				// Tạo entity User hứng dữ liệu mỗi dòng trả về từ database
				User user = new User();
				// Set thuộc tính cho User entity
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleId(rs.getInt("role_id"));
				// Add User vào danh sách để trả về cho jsp
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public List<UserDto> findAllUserDto() {
		String query = "SELECT u.id, u.email, u.fullname, r.description FROM users u JOIN roles r ON u.role_id = r.id";
		List<UserDto> users = new ArrayList<UserDto>();
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				UserDto user = new UserDto();
				// Set thuộc tính cho User DTO
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setDescription(rs.getString("description"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public User findById(int id) {

		String query = "SELECT * FROM users WHERE id = ?";
		User user = new User();

		try (Connection conn = JDBCConnection.getConnection()) {
			// Bước 2: Gửi câu truy vấn
			// Tạo ra câu truy vấn phù hợp với hệ quản trị CSDL mysql
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);

			// Thực thi truy vấn lấy dữu liệu
			ResultSet rs = statement.executeQuery();

			// Bước 3: Xử ký kết quả trả về
			while (rs.next()) {
				// Set thuộc tính cho User entity
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFullname(rs.getString("fullname"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleId(rs.getInt("role_id"));
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<User> findByRoleId(int roleId) {

		String query = "SELECT * FROM users WHERE role_id = ?";
		List<User> users = new ArrayList<User>();

		try (Connection conn = JDBCConnection.getConnection()) {
			// Bước 2: Gửi câu truy vấn
			// Tạo ra câu truy vấn phù hợp với hệ quản trị CSDL mysql
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, roleId);

			// Thực thi truy vấn lấy dữu liệu
			ResultSet rs = statement.executeQuery();

			// Bước 3: Xử ký kết quả trả về
			while (rs.next()) {
				// Set thuộc tính cho User entity
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFullname(rs.getString("fullname"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleId(rs.getInt("role_id"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public boolean insert(User user) {
		String query = "INSERT INTO Users(email, password, fullname, avatar, role_id)" + " VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = JDBCConnection.getConnection()) {
			// Bước 2: Gửi câu truy vấn
			// Tạo ra câu truy vấn phù hợp với hệ quản trị CSDL mysql
			PreparedStatement statement = conn.prepareStatement(query);
			// Set dữ liệu cho câu truy vấn
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRoleId());

			// Trả về kết quả truy vấn
			statement.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(User user) {
		String query = "UPDATE users SET email = ?, password = ?,"
				+ " fullname = ?, avatar = ?, role_id = ? WHERE id = ?";

		try (Connection conn = JDBCConnection.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRoleId());
			statement.setInt(6, user.getId());

			// Thực thi truy vấn lấy dữu liệu
			statement.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteById(int id) {
		String query = "DELETE FROM users WHERE id = ?";

		try (Connection conn = JDBCConnection.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);

			// Thực thi truy vấn lấy dữu liệu
			statement.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public User findByEmail(String email) {
		String query = "SELECT * FROM users WHERE email = ?";
		User user = null;

		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, email);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFullname(rs.getString("fullname"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleId(rs.getInt("role_id"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public LoginDTO check(String email) {
		LoginDTO login = null;
		String query = "SELECT u.id, u.email, u.password, u.fullname, r.name as roleName FROM users u "
				+ "JOIN roles r " + "ON u.role_id = r.id " + "WHERE u.email = ?";

		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, email);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				login = new LoginDTO();
				login.setId(rs.getInt("id"));
				login.setEmail(rs.getString("email"));
				login.setPassword(rs.getString("password"));
				login.setFullname(rs.getString("fullname"));
				login.setRoleName(rs.getString("roleName"));
				return login;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return login;
	}
}
