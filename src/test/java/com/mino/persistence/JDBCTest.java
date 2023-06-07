package com.mino.persistence;


import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;



//마리아 디비 연결 테스트
public class JDBCTest {
	
	static {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		try (Connection con = 
				DriverManager.getConnection(
						"jdbc:mariadb://localhost:3306/shop?user=root&password=12345")) {
			if(con != null) {
				System.out.println("DB Connection Success!="+con);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}