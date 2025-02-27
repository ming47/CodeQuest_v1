package game.game;

import java.io.IOException;
import java.util.ArrayList;
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
import game.score.ScoreDAO;
import game.score.ScoreDAOImpl;
import game.score.ScoreDTO;
import member.member.MemberDTO;
import utils.ConvertURL;


@WebServlet("/game/*")
public class GameController extends HttpServlet {
	GameDAO gdao = GameDAOImpl.INSTANCE;
	PlaytimeDAO pdao = PlaytimeDAOImpl.INSTANCE;
	ScoreDAO scoreDAO = ScoreDAOImpl.INSTANCE;
	
	Gson g = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			String cmd = ConvertURL.of(request);
			
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
				
				response.sendRedirect(game.getGameGateway());
			} else if (cmd.equals("/game/score/list/game.do")) {
				String gameId = request.getParameter("id");
				String userId = request.getParameter("user");
				
				List<ScoreDTO> dto = new ArrayList<>();
				dto = (userId == null) ? 
						scoreDAO.selectByGameId(Integer.parseInt(gameId)) : 
						scoreDAO.selectByMemberIdAndGameId(Integer.parseInt(userId), Integer.parseInt(gameId));

				response.getWriter().append(g.toJson(dto));
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
				int gameId = Integer.parseInt(request.getParameter("gameId"));
				int playtime = Integer.parseInt(request.getParameter("playtime"));
				
				MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
				
				pdao.insert(new PlaytimeDTO(gameId, member.getMemberId(), playtime));
			} else if(cmd.equals("/game/score/add.do")) {
				int gameId = Integer.parseInt(request.getParameter("gameId"));
				int score = Integer.parseInt(request.getParameter("score"));
				
				MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
				
				scoreDAO.insert(new ScoreDTO(gameId, member.getMemberId(), score));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
