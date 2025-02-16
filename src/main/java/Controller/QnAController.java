package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Common.ConvertURL;
import DAOImpl.QnADAOImpl;
import DTO.QnADTO;

@WebServlet("/qna/*")
public class QnAController<QnADAO> extends HttpServlet {
	QnADAOImpl dao = QnADAOImpl.INSTACNE;
	
	Gson g = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {		
			String cmd = ConvertURL.of(request);
			
			if (cmd.equals("/qna/board.do")) {
				int id = Integer.parseInt(request.getParameter("id"));
				
				QnADTO dto = dao.selectById(id);
				response.getWriter().append(g.toJson(dto));
			} else if (cmd.equals("/qna/list.do")) {
				int page = Integer.parseInt(request.getParameter("page"));
				
				List<QnADTO> dtos = dao.selectAll();
				response.getWriter().append(g.toJson(dtos));
			} else if (cmd.equals("/qna/list/response_yn.do")) {
				String responseYN = request.getParameter("response_yn");
				int page = Integer.parseInt(request.getParameter("page"));
				
			} else if (cmd.equals("/qna/list/search.do")) {
				
			} else if (cmd.equals("/qna/delete.do")) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
