package com.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.store.model.Member;


public class MemberDAO implements DAO<Member> {

	DataSource ds = null;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public List<Member> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		final String sqlSelect = "SELECT member_id,member_login,member_password,first_name,last_name,email,phone, address,cre_date" + "\r\n" + "FROM members" + "\r\n"
				+ "ORDER BY member_id ASC";

		try {
			// Mượn một đối tượng Kết nối từ nhóm kết nối.
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlSelect);

			ArrayList<Member> members = new ArrayList<Member>();

			while (rs.next()) {
				members.add(new Member().setId(rs.getInt("member_id")).setUserName(rs.getString("member_login")).setEmail(rs.getString("email")).setPassword(rs.getString("member_password")).setPhone(rs.getString("phone"))
						.setFirstName(rs.getString("first_name")).setLastName(rs.getString("last_name")).setAddress(rs.getString("address")).setCreatedDate(rs.getDate(9)));
			}

			return members;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			/*
			 * Ý nghĩa của close() trên đối tượng Connection do ds cung cấp không phải là chấm dứt kết nối mà là trả đối tượng về nhóm kết nối bên trong ds.
			 * 
			 */
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int insert(Member member) throws Exception {
		Connection connection = null;
		int result = 0;
		PreparedStatement stmt = null;
		final String sqlInsert = "INSERT INTO members(member_login,member_password,first_name,last_name,email,phone,address" + "\r\n" + ",cre_date,mod_date)" + "\r\n"
				+ "VALUES(?, ?, ?,?, ?, ?, ?, NOW(), NOW())";

		try {
			// Mượn một đối tượng Kết nối từ nhóm kết nối.
			connection = ds.getConnection();

			stmt = connection.prepareStatement(sqlInsert);
			stmt.setString(1, member.getUserName());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getFirstName());
			stmt.setString(4, member.getLastName());
			stmt.setString(5, member.getEmail());
			stmt.setString(6, member.getPhone());
			stmt.setString(7, member.getAddress());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			//Khi nào dùng xong hãy trả lại.
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public int delete(int no) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int result = 0;

		Member member = null;
		final String sqlUpdate = "DELETE FROM MEMBERS" + " WHERE member_id=?";
		try {
			//Mượn một đối tượng Kết nối từ nhóm kết nối.
			connection = ds.getConnection();
			stmt = connection.prepareStatement(sqlUpdate);
			stmt.setInt(1, no);
			result = stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}

			// Khi nào dùng xong hãy trả lại.
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;

	}

	@Override
	public Member selectOne(int no) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		Member member = null;
		final String sqlUpdate = "SELECT * FROM MEMBERS" + " WHERE member_id=?";
		try {
			// Mượn một đối tượng Kết nối từ nhóm kết nối.
			connection = ds.getConnection();
			stmt = connection.prepareStatement(sqlUpdate);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			if (rs.next()) {
				member = new Member().setId(rs.getInt("member_id")).setUserName(rs.getString("member_login")).setEmail(rs.getString("email")).setPassword(rs.getString("member_password")).setPhone(rs.getString("phone"))
						.setFirstName(rs.getString("first_name")).setLastName(rs.getString("last_name")).setAddress(rs.getString("address")).setCreatedDate(rs.getDate(9));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}

			// Khi nào dùng xong hãy trả lại.
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return member;
	}

	@Override
	public int update(Member member) throws Exception {
		Connection connection = null;
		int result = 0;
		PreparedStatement stmt = null;
		final String sqlUpdate = "UPDATE MEMBERS SET member_login=?,member_password=?,first_name=?,last_name=?,email=?,phone=?,address=?,MOD_DATE=now()" + " WHERE member_id=?";
		try {
			//Lấy kết nối Connection
			connection = ds.getConnection();
			stmt = connection.prepareStatement(sqlUpdate);
			stmt.setString(1, member.getUserName());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getFirstName());
			stmt.setString(4, member.getLastName());
			stmt.setString(5, member.getEmail());
			stmt.setString(6, member.getPhone());
			stmt.setString(7, member.getAddress());
			stmt.setInt(8, member.getId());
			result = stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}

			//Khi nào dùng xong hãy trả lại.
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public Member exist(String email, String password) throws Exception {
		Connection connection = null;
		Member member = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final String sqlExist = "SELECT member_login,member_password FROM MEMBERS" + " WHERE member_login=? AND member_password=?";

		try {
			// Mượn một đối tượng Kết nối từ nhóm kết nối.
			connection = ds.getConnection();

			stmt = connection.prepareStatement(sqlExist);
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				member = new Member().setUserName(rs.getString("member_login")).setEmail(rs.getString("member_password"));
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}

			try {
				if (connection != null)
					connection.close();// Khi nào dùng xong hãy trả lại.
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return member;
	}

}
