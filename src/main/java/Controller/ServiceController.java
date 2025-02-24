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
import Common.TimeUtil;
import DAO.PlaytimeDAO;
import DAOImpl.PlaytimeDAOImpl;
import DTO.AnalyzeDTO;


@WebServlet("/service/*")
public class ServiceController extends HttpServlet {
	private PlaytimeDAO playtimeDAO = PlaytimeDAOImpl.INSTANCE;
	
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
				String group = request.getParameter("group");
				
				String[] type = {"count", "sum", "avg"};
				
				Map<String, List<AnalyzeDTO>> json = new HashMap<>();
				
				for (int i = 0; i < type.length; i++) {					
					List<AnalyzeDTO> dto = new ArrayList<>();
					if(group.equals("day")) {					
						dto = playtimeDAO.selectAnaRecent7days(type[i]);
					} else {					
						dto = playtimeDAO.selectAnaRecent12Months(type[i]);
					}
					
					json.put(type[i], dto);
				}
				
				response.getWriter().append(g.toJson(json));
			} else if(cmd.equals("/service/admin/playtime/search.do")) {
				String target = request.getParameter("group");
				String type = request.getParameter("type");
				
				List<AnalyzeDTO> dto = new ArrayList<>();
				
				if(target.equals("age")) {
					dto = playtimeDAO.selectAnaGroupByAges(type);
				} else if(target.equals("game")) {
					dto = playtimeDAO.selectAnaGroupBy(type, target);
				}else {
					dto = playtimeDAO.selectAnaGroupBy(type, target);
				}
				response.getWriter().append(g.toJson(dto));
			} else if(cmd.equals("/service/admin/playtime/search/today.do")) {
				String type = request.getParameter("type");
				
				double result = playtimeDAO.selectTodayAna(type);
				
				String dto = (type.equals("count")) ? String.valueOf((int) result) : TimeUtil.toString((int) result);
				response.getWriter().append(dto);
			} else if(cmd.equals("/service/admin/playtime/rate.do")) {
				String type = request.getParameter("range");
				
				double startData = 0;
				double endData = 0;
				if(type.equals("day")) {
					startData = playtimeDAO.selectAnaByMinusDate("count", 2);
					endData = playtimeDAO.selectAnaByMinusDate("count", 1);
				} else {
					startData = playtimeDAO.selectAnaByMinusMonth("count", 2);
					endData = playtimeDAO.selectAnaByMinusMonth("count", 1);
				}
				
				double rate = 0;
				if (startData != 0) {					
					rate = Math.round(((endData - startData) / startData) * 100 * 100) / 100.0;
				}

				response.getWriter().append(String.valueOf(rate));
			} else if(cmd.equals("/service/admin/playtime/days/game.do")) {
				int gameId = Integer.parseInt(request.getParameter("id"));
				
				List<AnalyzeDTO> dto = playtimeDAO.selectAnaRecent7days("count", gameId);
				response.getWriter().append(g.toJson(dto));
			} else if(cmd.equals("/service/admin/playtime/serach/today/game.do")) {
				int gameId = Integer.parseInt(request.getParameter("id"));
				String type = request.getParameter("type");
				
				double result = playtimeDAO.selectTodayAnaByGameId(type, gameId);
				System.out.println("days/game.do :" + result);
				
				String dto = (type.equals("count")) ? String.valueOf((int) result) : TimeUtil.toString((int) result);
				response.getWriter().append(dto);
			} else if(cmd.equals("/service/admin/playtime/rate/game.do")) {
				int gameId = Integer.parseInt(request.getParameter("id"));
				String type = request.getParameter("range");
				
				double startData = 0;
				double endData = 0;

				if(type.equals("day")) {
					startData = playtimeDAO.selectAnaByMinusDateAndGameId("count", 2, gameId);
					endData = playtimeDAO.selectAnaByMinusDateAndGameId("count", 1, gameId);
				} else {
					startData = playtimeDAO.selectAnaByMinusMonthAndGameId("count", 2, gameId);
					endData = playtimeDAO.selectAnaByMinusMonthAndGameId("count", 1, gameId);
				}
				
				double rate = 0;
				if (startData != 0) {					
					rate = Math.round(((endData - startData) / startData) * 100 * 100) / 100.0;
				}

				response.getWriter().append(String.valueOf(rate));
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
