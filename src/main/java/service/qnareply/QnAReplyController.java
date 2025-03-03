package service.qnareply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.member.MemberDTO;
import service.qna.QnADAOImpl;

@WebServlet("/qna_reply/*")
public class QnAReplyController extends HttpServlet {
	QnADAOImpl qnaDAO = QnADAOImpl.INSTACNE;
	QnAReplyDAOImpl qnaReplyDAO = QnAReplyDAOImpl.INSTANCE;
	
	Gson g = new Gson();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			String cmd = request.getRequestURI();
			
			if(cmd.equals("/qna_reply/board/qna.do")) {
				int qnaId = Integer.parseInt(request.getParameter("id"));
				
				QnAReplyDTO dto = qnaReplyDAO.selectByQnAId(qnaId);
				response.getWriter().append(g.toJson(dto));
			} else if(cmd.equals("/qna_reply/delete.do")) {
				int id = Integer.parseInt(request.getParameter("id"));
				
				qnaReplyDAO.deleteById(id);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			String cmd = request.getRequestURI();
			
			if (cmd.equals("/qna_reply/add.do")) {
				int qnaId = Integer.parseInt(request.getParameter("qnaId"));
				String context = request.getParameter("context");
				
				MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
				
				qnaReplyDAO.insert(new QnAReplyDTO(qnaId, member.getMemberId(), context));
				qnaDAO.updateResponseYNById(qnaId, "Y");
			} else if (cmd.equals("/qna_reply/update.do")) {
				int qnaId = Integer.parseInt(request.getParameter("qnaId"));
				String context = request.getParameter("context");
				
				MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
				System.out.println(context);
				qnaReplyDAO.update(new QnAReplyDTO(qnaId, member.getMemberId(), context));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
