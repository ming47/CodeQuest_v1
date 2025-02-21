package Controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAOImpl.ReplyDAOImpl;
import DTO.MemberDTO;
import DTO.ReplyDTO;

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	ReplyDAOImpl rdao = ReplyDAOImpl.INSTANCE;

	Gson g = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html; charset=UTF-8");
		String cmd = request.getRequestURI();
		String ip = request.getRemoteAddr();

		try {

			Gson g = new Gson();
			if (cmd.equals("/reply/addContents.do")) {
//				int boardId = Integer.parseInt(request.getParameter("boardId"));
//				int memberId = Integer.parseInt(request.getParameter("name"));
//				String contents = request.getParameter("contents");
//				Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
//
//				ReplyDTO dto = new ReplyDTO(0, memberId, boardId, contents, currentTimestamp);
//
//				rdao.insert(dto);
//
//				response.getWriter().append(g.toJson(dto));

			}

			else if (cmd.equals("/reply/ContentsAll.do")) {
				int id = Integer.parseInt(request.getParameter("boardId"));
				List<ReplyDTO> rdto = rdao.selectByBoardId(id);
				response.setContentType("text/html; charset=utf8");
				response.getWriter().append(g.toJson(rdto));
			}

			else if (cmd.equals("/reply/update.do")) { // 수정
				int replyId = Integer.parseInt(request.getParameter("replyId"));

				String contents = request.getParameter("writerdiv");
				int boardId = Integer.parseInt(request.getParameter("BoardId"));

				ReplyDTO dto = new ReplyDTO(replyId, contents);

				rdao.update(dto);

				response.sendRedirect("/WEB-INF/views/board/detail.board?id=" + boardId);
			} else if (cmd.equals("/reply/delete.do")) { // 삭제		내일 다시와서 볼것
				int dto = Integer.parseInt(request.getParameter("id"));
				System.out.println(dto + ": dto");
				
				int result = rdao.deleteById(dto);
				
				response.getWriter().append(String.valueOf((result != 0)));
			
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf8");
		response.setContentType("text/html; charset=UTF-8");
		String cmd = request.getRequestURI();
		try {
			if (cmd.equals("/reply/add.do")) { // 댓글 출력
				int boardId = Integer.parseInt(request.getParameter("parent_seq"));
				System.out.println(boardId + ": boardId");
				String contents = request.getParameter("contents");
				System.out.println(contents + ": contents");

				HttpSession session = request.getSession();
				MemberDTO member = (MemberDTO) session.getAttribute("member");
				if (member == null) {
					response.sendRedirect("/login.do");
					return;
				}
				System.out.println(member.getMemberId() + ": memberId");

				ReplyDTO dto = new ReplyDTO();
				dto.setBoardId(boardId);
				dto.setMemberId(member.getMemberId());
				dto.setContents(contents);

				try {
					ReplyDAOImpl dao = ReplyDAOImpl.INSTANCE;
					dao.insert(dto);
					response.sendRedirect("/board/detail.do?id=" + boardId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (

		Exception e) {
			// TODO: handle exception
		}

	}
}
