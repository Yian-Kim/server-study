package com.test.home.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/view.do")
public class View extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//View.java
		//board/view.do?seq=10
		
		//1. 데이터 가져오기
		//2. DB 작업 > DAO 위임(select where)
		//3. 결과 반환 + JSP 호출
		
		//1.
		String seq = req.getParameter("seq");
		
		//2.
		BoardDAO dao = new BoardDAO();
		
		//조회수 증가
		HttpSession session = req.getSession();
		
		if (session.getAttribute("isRead") == null 
				|| session.getAttribute("isRead").toString().equals("n")) {
			dao.addReadcount(seq);
			session.setAttribute("isRead", "y"); //F5 금지
		}
		
		
		BoardDTO dto = dao.get(seq);
		
		dao.close();
		
		//데이터 조작
		
		//태그 사용 안함 -> <a> -> &lt;a&gt;
		String content = dto.getContent();
		
		if (dto.getTag().equals("n")) {
			content = content.replace("<", "&lt;").replace(">", "&gt;");
			dto.setContent(content);
		}
		
		content = content.replace("<script", "&lt;script").replace("</scirpt", "&lt;/script");
		
		//개행문자 -> <br>
		content = content.replace("\r\n", "<br>");
		dto.setContent(content);
		
		
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
		dispatcher.forward(req, resp);
	}

}









