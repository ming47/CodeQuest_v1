package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Common.ConvertURL;
import DAOImpl.QnADAOImpl;
import DAOImpl.QnAReplyDAOImpl;
import DTO.QnAReplyDTO;

@WebServlet("/qna_reply/*")
public class QnAReplyController extends HttpServlet {
	QnADAOImpl qnaDAO = QnADAOImpl.INSTACNE;
	QnAReplyDAOImpl qnaReplyDAO = QnAReplyDAOImpl.INSTANCE;
	
	Gson g = new Gson();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {		
			String cmd = ConvertURL.of(request);
			
			if(cmd.equals("/qna_reply/board/qna")) {
				int qnaId = Integer.parseInt(request.getParameter("qna_id"));
				
				QnAReplyDTO dto = qnaReplyDAO.selectByQnAId(qnaId);
				response.getWriter().append(g.toJson(dto));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {		
			String cmd = ConvertURL.of(request);
			
			if (cmd.equals("/qna_reply/insert.do")) {
				int qnaId = Integer.parseInt(request.getParameter("qna_id"));
				int memberId = Integer.parseInt(request.getParameter("member_id"));
				String context = request.getParameter("context");
				
				qnaReplyDAO.insert(new QnAReplyDTO(qnaId, memberId, context));
				qnaDAO.updateResponseYNById(qnaId, "Y");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
