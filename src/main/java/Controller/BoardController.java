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
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try {			
			String cmd = ConvertURL.convert(request);

			if(cmd.equals("/board/add.do")) {//게시글 추가

			}else if(cmd.equals("/board/printout.do")) {//게시글 출력


			}else if(cmd.equals("/board/update.do")) {//게시글 등록

			}else if(cmd.equals("/board/delete.do")) {//게시글 삭제

			}else if(cmd.equals("/board/mypage.do")) {//

			}
		}catch (Exception e) {

		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cmd = request.getRequestURI();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		if(cmd.equals("/add.board")) {

		}else if(cmd.equals("/printout.board")) {

		}else if(cmd.equals("/update.board")) {

		}else if(cmd.equals("/delete.board")) {

		}else if(cmd.equals("/mypage.board")) {

		}
	}

}
