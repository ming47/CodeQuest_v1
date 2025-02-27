package service.qnareply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.qna.QnADAOImpl;
import utils.ConvertURL;

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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {		
			String cmd = ConvertURL.of(request);
			
			if (cmd.equals("/qna_reply/insert.do")) {
				int qnaId = Integer.parseInt(request.getParameter("qnaId"));
				String context = request.getParameter("context");
				
				qnaReplyDAO.insert(new QnAReplyDTO(qnaId, 100001, context));
				qnaDAO.updateResponseYNById(qnaId, "Y");
			} else if (cmd.equals("/qna_reply/update.do")) {
				int qnaId = Integer.parseInt(request.getParameter("qnaId"));
				String context = request.getParameter("context");
				
				qnaReplyDAO.update(new QnAReplyDTO(qnaId, 100001, context));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
