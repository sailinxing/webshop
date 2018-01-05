package com.lixinxin.DBCP;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DbcpUtils {
	private static DataSource pool = null;
	static{
InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("dbcp.properties");
Properties p=new Properties();
		try {
	p.load(is);
	pool=BasicDataSourceFactory.createDataSource(p);
			} catch (IOException e) {
	e.printStackTrace();
				} catch (Exception e) {
	e.printStackTrace();
					}		
}
	public static Connection getConnection(){
		Connection conn=null;
		try {
			conn = pool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static DataSource getDataSource(){
		return pool;
	}

}
