package com.whut.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

public class DbUtils {
	// 获取链接
	public static Connection getConnection() {
		Properties prop = new Properties();
		InputStream in = DbUtils.class.getResourceAsStream("db.properties");
		Connection conn = null;
		try {
			// 从db.properties文件中读取相关的配置
			prop.load(in);
			String drivername = prop.getProperty("drivername");
			String url = prop.getProperty("url");
			String user = prop.getProperty("username");
			String password = prop.getProperty("password");
			Class.forName(drivername);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	// 清理资源:先打开的后关闭
	public static void close(ResultSet rs, PreparedStatement pstm, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstm != null) {
				pstm.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public static void main(String[] args) {
		Connection conn = DbUtils.getConnection();
		System.out.println(conn);
	}
}
