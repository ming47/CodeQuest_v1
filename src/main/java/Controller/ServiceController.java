package Controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import Common.TimeUtil;
import DAOImpl.PlaytimeDAOImpl;
import DTO.AnalyzeDTO;
import DTO.PlaytimeDTO;


@WebServlet("/service/*")
public class ServiceController extends HttpServlet {
	PlaytimeDAOImpl playtimeDAO = PlaytimeDAOImpl.INSTANCE;
	
	Gson g = new Gson();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {		
			String cmd = ConvertURL.of(request);
			
			
			if(cmd.equals("/service/qna/addForm.do")) {
				request.getRequestDispatcher("/WEB-INF/views/support/servicewrite.jsp").forward(request, response);
			} else if(cmd.equals("/service/admin/main.do")) {
				request.getRequestDispatcher("/WEB-INF/views/support/admin.html").forward(request, response);
			} else if(cmd.equals("/service/admin/playtime/search/days.do")) {
				String type = request.getParameter("type");
				String target = request.getParameter("priad");

				//List<AnalyzeDTO> dto = playtimeDAO.selectAnaRecent7days(type);
				List<AnalyzeDTO> dto = playtimeDAO.selectAnaRecent12Months(type);
				System.out.println(dto.size());
				for (AnalyzeDTO a : dto) {
					System.out.println(a.getData() + " : " + a.getLabel());
				}
				
				response.getWriter().append(g.toJson(dto));
			} else if(cmd.equals("/service/admin/playtime/search.do")) {
				String target = request.getParameter("group");
				String type = request.getParameter("type");
				
				System.out.println(target);
				
				List<AnalyzeDTO> dto = new ArrayList<>();
				
				if(target.equals("age")) {
					dto = playtimeDAO.selectAnaGroupByAges(type);
				} else {
					dto = playtimeDAO.selectAnaGroupBy(type, target);
				}
				response.getWriter().append(g.toJson(dto));
			} else if(cmd.equals("/service/admin/playtime/today/search.do")) {
				
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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
