package com.lixinxin.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.lixinxin.C3P0.C3P0Utils;
import com.lixinxin.domain.User;


public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String uname = request.getParameter("username");
			String password = request.getParameter("password");
			QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
			User user=null;
			String sql="select * from users where uname=? and password=?";
			try {
				user = qr.query(sql, new BeanHandler<User>(User.class),uname,password);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(user!=null){
				response.getWriter().write(user.getUname()+"  welcome to my home!");
			}
			else{
				response.getWriter().write("Login failed!");
			}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}