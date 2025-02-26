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
import DAO.GameDAO;
import DAO.PlaytimeDAO;
import DAOImpl.GameImpl;
import DAOImpl.PlaytimeDAOImpl;
import DTO.GameDTO;
import DTO.MemberDTO;
import DTO.PlaytimeDTO;


@WebServlet("/game/*")
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GameDAO daog= GameImpl.INSTANCE;
	PlaytimeDAO pdao = PlaytimeDAOImpl.INSTANCE;
	
	Gson g = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String cmd = ConvertURL.of(request);
			System.out.println(cmd);

			String ip = request.getRemoteAddr();
			System.out.println(ip);
			
			if(cmd.equals("/game/list.do")) {
				request.getRequestDispatcher("/WEB-INF/views/game/gamelist.jsp").forward(request, response);
			}else if(cmd.equals("/game/call.do")) {
				int id = Integer.parseInt(request.getParameter("gameId"));
				GameDTO game = daog.selectById(id);
				response.getWriter().append(g.toJson(game));
				
                if (game == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"Game not found\"}");
                    return;
                }

                // JSON 응답 설정
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(g.toJson(game));
			} else {
				request.getRequestDispatcher("/WEB-INF/views/support/test.html").forward(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try {		
			String cmd = ConvertURL.of(request);
			
			if (cmd.equals("/game/playtime/add.do")) {
				System.out.println(cmd);
				
				int gameId = Integer.parseInt(request.getParameter("gameId"));
				int playtime = Integer.parseInt(request.getParameter("playtime"));
				
				MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
				
				pdao.insert(new PlaytimeDTO(gameId, member.getMemberId(), playtime));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
