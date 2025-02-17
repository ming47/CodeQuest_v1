package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Common.ConvertURL;

@WebServlet("/board/*")

public class BoardController extends HttpServlet {
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {			
			String cmd = ConvertURL.of(request);
			
			if(cmd.equals("/board/add.do")) {
				
			}else if(cmd.equals("/board/printout.do")) {
				
			}else if(cmd.equals("/board/update.do")) {
				
			}else if(cmd.equals("/board/delete.do")) {
				
			}else if(cmd.equals("/board/mypage.do")) {
				
			}
		}catch (Exception e) {
			
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {		
			String cmd = ConvertURL.of(request);
			if(cmd.equals("/add.board")) {
			
			}else if(cmd.equals("/printout.board")) {
			
			}else if(cmd.equals("/update.board")) {
			
			}else if(cmd.equals("/delete.board")) {
			
			}else if(cmd.equals("/mypage.board")) {
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
