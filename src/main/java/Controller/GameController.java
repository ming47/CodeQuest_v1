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
import DAOImpl.GameImpl;
import DTO.GameDTO;


@WebServlet("/game/*")
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GameDAO daog= GameImpl.INSTANCE;
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
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
