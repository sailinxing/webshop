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

public class LoginCount1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int loginCount=0;
	public void init() throws ServletException {
		super.init();
		this.getServletContext().setAttribute("Count", loginCount);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());		
		String sql="select * from users where uname=? and password=?";
		User user = null;
		try {
			user= qr.query(sql, new BeanHandler<User>(User.class), username,password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(user!=null){
			 loginCount = (int) this.getServletContext().getAttribute("Count");
			 loginCount++;
			 response.getWriter().write("welcome to my home!"+user.getUname()+",you are the "+loginCount+" to come to my home!");
			 this.getServletContext().setAttribute("Count", loginCount);
		}else{
			response.getWriter().write("Login failed!");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}