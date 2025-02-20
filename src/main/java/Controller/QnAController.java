package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Common.ConvertURL;
import Common.PageNavi;
import DAOImpl.QnADAOImpl;
import DAOImpl.QnAReplyDAOImpl;
import DTO.QnADTO;

@WebServlet("/qna/*")
public class QnAController<QnADAO> extends HttpServlet {
	QnADAOImpl dao = QnADAOImpl.INSTACNE;
	QnAReplyDAOImpl qnaReplyDAO = QnAReplyDAOImpl.INSTANCE;
	
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
				
				Map<String, Object> json = new HashMap<>();
				
				List<QnADTO> dtos = dao.selectAll();
				json.put("qnaList", dtos);
				
				json.put("pageNavi", new PageNavi(page, dao.getSize(), 10, 10).generate());
				response.getWriter().append(g.toJson(json));
			} else if (cmd.equals("/qna/list/response_yn.do")) {
				String responseYN = request.getParameter("response_yn").toUpperCase();
				int page = Integer.parseInt(request.getParameter("page"));
				
				Map<String, Object> json = new HashMap<>();
				
				List<QnADTO> dtos = dao.selectByResponseYN(responseYN);
				json.put("qnaList", dtos);
				
				json.put("pageNavi", new PageNavi(page, dtos.size(), 10, 10).generate());
				response.getWriter().append(g.toJson(json));
			} else if (cmd.equals("/qna/list/search.do")) {
				int page = Integer.parseInt(request.getParameter("page"));
				String conditions = request.getParameter("conditions");
				String searchKey = request.getParameter("search-key");
				
				Map<String, Object> json = new HashMap<>();
				
				
				List<QnADTO> dtos = new ArrayList<>();
				if(conditions.equals("writer")) {
					dtos = dao.selectByWriterLike(searchKey);
				} else if(conditions.equals("contents")) {
					dtos = dao.selectByContentLike(searchKey);
				}
				json.put("qnaList", dtos);
				
				json.put("pageNavi", new PageNavi(page, dtos.size(), 10, 10).generate());
				response.getWriter().append(g.toJson(json));
			} else if (cmd.equals("/qna/delete.do")) {
				int id = Integer.parseInt(request.getParameter("id"));
				
				dao.deleteById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	

}
