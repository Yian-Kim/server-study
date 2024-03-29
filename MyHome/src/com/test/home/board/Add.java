package com.test.home.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.home.AuthCheck;

@WebServlet("/board/add.do")
public class Add extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//인증 사용자?
		AuthCheck auth = new AuthCheck(req.getSession(), resp);
		auth.allow();
		
		
		
		//Add.java
		req.setAttribute("thread", req.getParameter("thread"));
		req.setAttribute("depth", req.getParameter("depth"));
		req.setAttribute("reply", req.getParameter("reply"));

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/add.jsp");
		dispatcher.forward(req, resp);
	}

}






