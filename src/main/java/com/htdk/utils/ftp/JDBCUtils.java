package com.htdk.utils.ftp;

import java.sql.*;
public final class JDBCUtils {


	public JDBCUtils(SqlProperties prop) {
		try {
			Class.forName(prop.getDriver());
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}


	public  static Connection getConnection( SqlProperties prop) throws SQLException {
		System.out.println(prop.getDriver());
		System.out.println(prop.getUrl());
		System.out.println(prop.getUserName());
		System.out.println(prop.getPassWord());
		return DriverManager.getConnection(prop.getUrl(), prop.getUserName(), prop.getPassWord());
	}

	public  void colseResource(Connection conn, Statement st, ResultSet rs) {
		closeResultSet(rs);
		closeStatement(st);
		closeConnection(conn);

	}

	public  void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		conn = null;
	}

	public  void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		st = null;
	}

	public  void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		rs = null;
	}
}