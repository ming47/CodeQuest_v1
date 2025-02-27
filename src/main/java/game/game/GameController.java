package game.game;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import game.playtime.PlaytimeDAO;
import game.playtime.PlaytimeDAOImpl;
import game.playtime.PlaytimeDTO;
import member.member.MemberDTO;
import utils.ConvertURL;


@WebServlet("/game/*")
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GameDAO gdao = GameDAOImpl.INSTANCE;
	PlaytimeDAO pdao = PlaytimeDAOImpl.INSTANCE;
	
	Gson g = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String cmd = ConvertURL.of(request);
			System.out.println(cmd);

			String ip = request.getRemoteAddr();
			System.out.println(ip);
			
			if(cmd.equals("/game/list.do")) {
				int id = Integer.parseInt(request.getParameter("id"));
				GameDTO game = gdao.selectById(id);
				
				List<GameDTO> games = gdao.selectAll();
				request.setAttribute("list", games);
				
				request.setAttribute("game", game);
				request.getRequestDispatcher("/WEB-INF/views/game/gamelist.jsp").forward(request, response);

			} else if(cmd.equals("/game/play.do")) {
				int gameId = Integer.parseInt(request.getParameter("id"));
				GameDTO game = gdao.selectById(gameId);
				MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
				if (member == null) {
					response.sendRedirect("/");
					return;
				} else if(member.getIsbanned()) { // 밴 유저는 불가
					response.sendRedirect("/");
					return;
				}
				response.sendRedirect(game.getGameGateway());
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
