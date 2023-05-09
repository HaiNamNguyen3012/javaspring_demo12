package com.example.cruddemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.model.User;

@Repository("postgres")
public class H2DbDao implements UserDao {

	private final JdbcTemplate jdbcTemplate;

	// Constructor
	@Autowired
	public H2DbDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// Create RowMapper for Employee
	private static final RowMapper<User> EMPLOYEE_ROW_MAPPER = (resultSet, rowNum) -> {
		// Declare an user
		User user = new User();

		// Set value for user
		user.setId(resultSet.getString("id"));
		user.setUsername(resultSet.getString("name"));
		user.setPassword(resultSet.getString("password"));
		user.setEmail(resultSet.getString("email"));

		return user;
	};

	// Add user to database
	@Override
	public User insertUser(User user) {
		// Create Prepare statement
		PreparedStatementCreator preparedStmtCreator = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps = connection
						.prepareStatement("INSERT INTO userdemo (id, name, password, email) VALUES(?,?,?,?)");
				ps.setString(1, user.getId());
				ps.setString(2, user.getUsername());
				ps.setString(3, user.getPassword());
				ps.setString(4, user.getEmail());
				return ps;
			}

		};

		// Insert an user to data base
		jdbcTemplate.update(preparedStmtCreator);
		
		return user;
	}

	//Select all user in database method
	@Override
	public Collection<User> selectAllUsers() {
		// Declare select all user query
		final String sql = "SELECT id, name, password, email FROM userdemo";

		// Select all user in database
		Collection<User> userList = jdbcTemplate.query(sql, EMPLOYEE_ROW_MAPPER);
		return userList;
	}

	
	//Select user by id method
	@Override
	public User selectUserById(String id) {
		// Declare select by id query
		final String sql = "SELECT * FROM userdemo WHERE id=?";

		// Select an user have id
		try {
			User user = jdbcTemplate.queryForObject(sql, EMPLOYEE_ROW_MAPPER, id);
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	//Update user by id method
	@Override
	public User updateUserById(User user) {
		// Select
		PreparedStatementCreator preparedStmtCreator = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"UPDATE userdemo SET name =  ? , password =  ? , email = ? WHERE ID = ?");
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getId());
				return ps;
			}
			
		};
		
		
		//Update database
		jdbcTemplate.update(preparedStmtCreator);		
		return user;
	}

	//Delete user by id method
	@Override
	public User deleteUserById(String id) {
		// TODO Auto-generated method stub
		
		//Get user from database
		User deletedUser = selectUserById(id);
		
		//Delete user
		PreparedStatementCreator preparedStmtCreator = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"DELETE FROM userdemo WHERE ID = ?");
				ps.setString(1, deletedUser.getId());
				return ps;
			}
			
		};
		
		//Update database
		jdbcTemplate.update(preparedStmtCreator);		
		return deletedUser;
	}

}
