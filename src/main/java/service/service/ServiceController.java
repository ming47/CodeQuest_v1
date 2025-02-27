package service.service;

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

import game.playtime.PlaytimeDAO;
import game.playtime.PlaytimeDAOImpl;
import member.member.MemberDAO;
import member.member.MemberDAOImpl;
import member.member.MemberDTO;
import service.blacklist.BlackListDAO;
import service.blacklist.BlackListDAOImpl;
import service.blacklist.BlackListDTO;
import service.qna.QnADAO;
import service.qna.QnADAOImpl;
import service.qnareply.QnAReplyDAO;
import service.qnareply.QnAReplyDAOImpl;
import utils.AnalyzeDTO;
import utils.ConvertURL;
import utils.PageNavi;


@WebServlet("/service/*")
public class ServiceController extends HttpServlet {
	PlaytimeDAO playtimeDAO = PlaytimeDAOImpl.INSTANCE;
	MemberDAO memberDAO = MemberDAOImpl.INSTANCE;
	BlackListDAO blackListDAO = BlackListDAOImpl.INSTANCE;
	QnADAO qnaDao = QnADAOImpl.INSTACNE;
	QnAReplyDAO qnaReplyDao = QnAReplyDAOImpl.INSTANCE;
	
	Gson g = new Gson();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {		
			String cmd = ConvertURL.of(request);
			 
			if(cmd.equals("/service/qna/addForm.do")) {
				MemberDTO dto = (MemberDTO) request.getSession().getAttribute("member");
				if (dto == null) {
					response.sendRedirect("/");
					return;
				}
				request.getRequestDispatcher("/WEB-INF/views/support/qnaWrite.jsp").forward(request, response);
			} else if(cmd.equals("/service/admin/main.do")) {
				MemberDTO dto = (MemberDTO) request.getSession().getAttribute("member");
				
				if (dto == null || dto.getRole().equals("user")) {
					response.sendRedirect("/");
				}
			
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
					dto = playtimeDAO.selectAnaGroupByGameId(type);
				}else {
					dto = playtimeDAO.selectAnaGroupByGender(type);
				}
				response.getWriter().append(g.toJson(dto));
			} else if(cmd.equals("/service/admin/playtime/search/today.do")) {
				String type = request.getParameter("type");
				
				double result = playtimeDAO.selectTodayAna(type);
				
				String dto = (type.equals("count")) ? String.valueOf((int) result) : toString((int) result);
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
				
				String dto = (type.equals("count")) ? String.valueOf((int) result) : toString((int) result);
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
				if (startData != 0 || (startData == 0 && endData > 0)) {
					if(startData == 0) {
						startData = 1;
					}
					rate = Math.round(((endData - startData) / startData) * 100 * 100) / 100.0;
				}

				response.getWriter().append(String.valueOf(rate));
			} else if(cmd.equals("/service/member/list.do")) {
				int page = Integer.parseInt(request.getParameter("page"));
				
				Map<String, Object> json = new HashMap<>();
				
				List<MemberDTO> dto = memberDAO.selectAll(page);
				json.put("members", dto);
				json.put("pageNavi", new PageNavi(page, memberDAO.getSize()).generate());
				
				response.getWriter().append(g.toJson(json));
			} else if(cmd.equals("/service/member/banned/search.do")) {
				int memberId = Integer.parseInt(request.getParameter("id"));
				
				boolean isBanned = blackListDAO.isBanned(memberId);
				response.getWriter().append(String.valueOf(isBanned));
			} else if(cmd.equals("/service/member/ban/count.do")) {
				int memberId = Integer.parseInt(request.getParameter("id"));
				
				int result = blackListDAO.countBanByMemberId(memberId);
				response.getWriter().append(String.valueOf(result));
			} else if(cmd.equals("/service/member/ban/list.do")) {
				int memberId = Integer.parseInt(request.getParameter("id"));
				
				List<BlackListDTO> dto = blackListDAO.selectByMemberId(memberId);
				response.getWriter().append(g.toJson(dto));
			} else if(cmd.equals("/service/member/ban/detail.do")) {
				int memberId = Integer.parseInt(request.getParameter("id"));
				BlackListDTO dto = blackListDAO.selectBanByMmeberId(memberId);
				
				response.getWriter().append(g.toJson(dto));
			} else if(cmd.equals("/service/member/ban/delete.do")) {
				int memberId = Integer.parseInt(request.getParameter("id"));
				
				MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
				
				if(member.getRole().equals("admin")) {
					blackListDAO.deleteBanByMemberId(memberId);
				}
			} else if(cmd.equals("/service/member/isbanned/list.do")) {
				int page = Integer.parseInt(request.getParameter("page"));
				boolean ban = Boolean.parseBoolean(request.getParameter("ban"));
				
				Map<String, Object> json = new HashMap<>();
				
				List<MemberDTO> dto = memberDAO.selectByIsBanned(ban, page);
				for(MemberDTO a : dto) {
					System.out.println(a.getNickName());
				}
				json.put("members", dto);
				json.put("pageNavi", new PageNavi(page, memberDAO.getSelectByIsBannedSize(ban)).generate());
				
				response.getWriter().append(g.toJson(json));
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
			
			if(cmd.equals("/service/member/ban/add.do")) {
				MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
				if(member.getRole().equals("admin")) {
					Integer memberId = Integer.parseInt(request.getParameter("memberId"));
					String reason = request.getParameter("reason");
					Integer period = Integer.parseInt(request.getParameter("endDate"));
					
					if(period == -1) {
						blackListDAO.permanentBan(new BlackListDTO(memberId, reason));
					} else {						
						blackListDAO.insert(new BlackListDTO(memberId, reason, period));
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    private String toString(long millisec) {
    	int hours = 0;
    	int minutes = 0;
    	int sec = 0;
    	int milli = (int) millisec;
    	
    	if (millisec >= 1000) {    		
    		sec = (int) (millisec / 1000);
    		milli = (int) millisec % 1000;
    	}
    	
    	String smillisec = String.valueOf(milli);
    	if (milli < 100) {
    		smillisec = "0" + String.valueOf(milli);
    		
    		if (milli < 10) {
    			smillisec = "00" + String.valueOf(milli);
    		}
    	}
    	
    	if (sec >= 60) {
    		minutes = (int) (sec / 60);
    		sec = sec % 60;
    	} else {
    		return String.format("%d.%s초", sec, smillisec);
    	}
    	
    	if (minutes >= 60) {
    		hours = (int) (minutes / 60);
    		minutes = minutes % 60;
    	} else {
        	return String.format("%d분 %d.%s초", minutes, sec, smillisec);
    	}
    	
    	return String.format("%d시간 %d분 %d.%s초", hours, minutes, sec, smillisec);
    }
}
