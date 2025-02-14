package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getRequestURI();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		if (cmd.equals("/add.member")) {

		} else if (cmd.equals("/printout.member")) {

		} else if (cmd.equals("/update.member")) {

		} else if (cmd.equals("/delete.member")) {

		} else if (cmd.equals("/validate.member")) {

		} else if (cmd.equals("/shortvalid.member")) {

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getRequestURI();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		if (cmd.equals("/add.member")) {

		} else if (cmd.equals("/printout.member")) {

		} else if (cmd.equals("/update.member")) {

		} else if (cmd.equals("/delete.member")) {

		} else if (cmd.equals("/validate.member")) {

		} else if (cmd.equals("/shortvalid.member")) {

		}

	}
}
