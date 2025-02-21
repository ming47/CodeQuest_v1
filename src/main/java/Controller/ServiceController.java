package Controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Common.ConvertURL;
import Common.TimeUtil;
import DAOImpl.PlaytimeDAOImpl;
import DTO.PlaytimeDTO;


@WebServlet("/service/*")
public class ServiceController extends HttpServlet {
	PlaytimeDAOImpl playtimeDAO = PlaytimeDAOImpl.INSTANCE;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {		
			String cmd = ConvertURL.of(request);
			
			
			if(cmd.equals("/service/qna/addForm.do")) {
				request.getRequestDispatcher("/WEB-INF/views/support/servicewrite.jsp").forward(request, response);
			} else if(cmd.equals("/service/admin/main.do")) {
				request.getRequestDispatcher("/WEB-INF/views/support/admin.html").forward(request, response);
			} else if(cmd.equals("/service/admin/playtime/sum/search.do")) {
				String sdate = request.getParameter("date");
				System.out.println(sdate);
				
				LocalDate date = null;
				if (sdate != null) {
					date = LocalDate.parse(sdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					System.out.println(date);
					
					List<PlaytimeDTO> dto = playtimeDAO.selectByDate(date);
					for(PlaytimeDTO dto1 : dto) {
						System.out.println(dto1.getPlaytime());
					}
				}
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
