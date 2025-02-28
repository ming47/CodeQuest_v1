package board.reply;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import board.board.BoardDAOImpl;
import member.member.MemberDTO;
import utils.PageNavi;

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	ReplyDAOImpl rdao = ReplyDAOImpl.INSTANCE;
	BoardDAOImpl bdao = BoardDAOImpl.INSTANCE;
	Gson g = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getRequestURI();

		try {
			if (cmd.equals("/reply/list.do")) {
				int page = Integer.parseInt(request.getParameter("page"));
				int id = Integer.parseInt(request.getParameter("boardId"));
				
				Map<String, Object> json = new HashMap<>();
				
				List<ReplyDTO> rdto = rdao.selectByBoardId(id, page);
				json.put("list", rdto);
				
				PageNavi pageNavi = new PageNavi(page, rdao.getSelectByBoardIdSize(id), 5, 10);
				json.put("pageNavi", pageNavi.generate());
				
				response.setContentType("text/html; charset=utf8");
				response.getWriter().append(g.toJson(json));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getRequestURI();
		try {
			if (cmd.equals("/reply/add.do")) { 
				int boardId = Integer.parseInt(request.getParameter("boardId"));
				String contents = request.getParameter("contents");
				HttpSession session = request.getSession();
				MemberDTO member = (MemberDTO) session.getAttribute("member");
				if (member == null) {
					response.sendRedirect("/");
					return;
				} else if(member.getIsbanned()) { 
		               response.sendRedirect("/");
		               return;
				}

				ReplyDTO dto = new ReplyDTO();
				dto.setBoardId(boardId);
				dto.setMemberId(member.getMemberId());
				dto.setContents(contents);
				
				rdao.insert(dto);
				bdao.increaseReplyCount(boardId);	
				response.sendRedirect("/board/detail.do?id=" + boardId);
			} else if (cmd.equals("/reply/delete.do")) {
				int dto = Integer.parseInt(request.getParameter("id"));
				int boardId = Integer.parseInt(request.getParameter("boardId"));
					
				MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
				if (member.getRole().equals("user") && rdao.selectById(dto).getMemberId() != member.getMemberId()) {
					return;
				}
				
				int result = rdao.deleteById(dto);
				bdao.decreaseReplyCount(boardId);	
				response.getWriter().append(String.valueOf((result != 0)));
			} else if (cmd.equals("/reply/update.do")) { 

				int replyId = Integer.parseInt(request.getParameter("id"));

				MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");	

				if (member.getMemberId() == rdao.selectById(replyId).getMemberId()
						|| member.getRole().equals("admin")) {
					String contents = request.getParameter("contents");
					
					ReplyDTO dto = new ReplyDTO(replyId, contents);
					int result = rdao.update(dto);
					
					response.getWriter().append(String.valueOf((result != 0)));
				} else {
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
