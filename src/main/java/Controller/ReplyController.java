package Controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAOImpl.ReplyDAOImpl;
import DTO.ReplyDTO;

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	ReplyDAOImpl rdao = ReplyDAOImpl.INSTANCE;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html; charset=UTF-8");
		String cmd = request.getRequestURI();
		String ip = request.getRemoteAddr();

		try {

			Gson g = new Gson();
			if (cmd.equals("/reply/addContents.do")) {
				int boardId = Integer.parseInt(request.getParameter("boardId"));
				String name = request.getParameter("name");
				String contents = request.getParameter("contents");
				Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

				ReplyDTO dto = new ReplyDTO(0, name, boardId, contents, currentTimestamp);

				rdao.insert(dto);

				response.sendRedirect("/WEB-INF/views/board/detail.board?id=" + boardId);

			}

			else if (cmd.equals("/reply/ContentsAll.do")) {

				int id = Integer.parseInt(request.getParameter("boardId"));
				ReplyDTO rdto = rdao.selectById(id);
				response.setContentType("text/html; charset=utf8");
				response.getWriter().append(g.toJson(rdto));
			}

			else if (cmd.equals("/reply/update.do")) {
				int replyId = Integer.parseInt(request.getParameter("replyId"));
				
	
				String contents = request.getParameter("writerdiv");
				int boardId = Integer.parseInt(request.getParameter("BoardId"));
				
				
				ReplyDTO dto = new ReplyDTO(replyId, contents);

				rdao.update(dto);

				response.sendRedirect("/WEB-INF/views/board/detail.board?id=" + boardId);
			}

			else if (cmd.equals("/reply/delete.do")) {
				int boardId = Integer.parseInt(request.getParameter("boardId"));
				int dto = Integer.parseInt(request.getParameter("replyId"));
				
				rdao.deleteById(dto);

				response.sendRedirect("/WEB-INF/views/board/detail.board?id=" + boardId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
