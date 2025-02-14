package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Common.ConvertURL;
import DAO.MemberDAO;
import DAOImpl.MemberDAOImpl;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private MemberDAO dao = MemberDAOImpl.INSTANCE;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {		
			String cmd = ConvertURL.of(request);
			
			if (cmd.equals("/add.member")) {
				
			} else if (cmd.equals("/printout.member")) {
				
			} else if (cmd.equals("/update.member")) {
				
			} else if (cmd.equals("/delete.member")) {
				
			} else if (cmd.equals("/validate.member")) {
				
			} else if (cmd.equals("/shortvalid.member")) {
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {		
			String cmd = ConvertURL.of(request);
			if (cmd.equals("/add.member")) {
				
			} else if (cmd.equals("/printout.member")) {
				
			} else if (cmd.equals("/update.member")) {
				
			} else if (cmd.equals("/delete.member")) {
				
			} else if (cmd.equals("/validate.member")) {
				
			} else if (cmd.equals("/shortvalid.member")) {
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}


	}
}
