package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getRequestURI();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		if (cmd.equals("/add.do")) {

		} else if (cmd.equals("/member/printout.do")) {

		} else if (cmd.equals("/member/update.do")) {

		} else if (cmd.equals("/member/delete.do")) {

		} else if (cmd.equals("/member/validate.do")) {

		} else if (cmd.equals("/member/shortvalid.do")) {

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
