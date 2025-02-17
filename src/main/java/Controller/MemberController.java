package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.ConvertURL;
import Common.SecurityUtil;
import DAO.MemberDAO;
import DAOImpl.MemberDAOImpl;
import DTO.MemberDTO;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {

	private MemberDAO dao = MemberDAOImpl.INSTANCE;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try {		
			String cmd = ConvertURL.of(request);
			System.out.println(cmd);
			if (cmd.equals("/member/addForm.do")) { //회원가입 폼
				request.getRequestDispatcher("/WEB-INF/views/member/signup.jsp").forward(request, response);
			} else if(cmd.equals("/member/idCheck.do")) {
				String id = request.getParameter("id");
				boolean result = dao.idVali(id);
				if (result == true) {
					response.getWriter().append("exist");
				}
			} else if (cmd.equals("/member/mypage.do")) { //마이페이지 폼
				request.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp").forward(request, response);
			} else if (cmd.equals("/member/logout.do")) {
				request.getSession().invalidate();
				response.sendRedirect("/");
			} else if (cmd.equals("/delete.do")) {

			} else if (cmd.equals("/validate.do")) {

			} else if (cmd.equals("/shortvalid.do")) {

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
			System.out.println(cmd);
			if (cmd.equals("/member/add.do")) {

				String id = request.getParameter("id");
				String pw = request.getParameter("pw");

				String encryptPw = SecurityUtil.hashPassword(pw);

				String name = request.getParameter("name");

				//주민번호 앞자리,뒷자리 받은 후 DB입력할수있게 폼 완성
				String ssnFront = request.getParameter("ssnFront");
				String ssnBack = request.getParameter("ssnBack");
				String ssn = ssnFront + "-"+ssnBack+"******";

				String email = request.getParameter("email");
				String phone = request.getParameter("phone");

				int postcode = Integer.parseInt(request.getParameter("postcode"));
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");

				int result = dao.insert(new MemberDTO(id,encryptPw,name,ssn,email,phone,postcode,address1,address2,null)); //role은 정해진게없어서 null
				if(result > 0) {
					System.out.println("가입성공!");
				}	
				response.sendRedirect("/");

			} else if(cmd.equals("/member/login.do")) {

				HttpSession session = request.getSession();

				Integer failedCount = (Integer) session.getAttribute("failedLoginCount");
				Long lockoutTime = (Long) session.getAttribute("lockoutTime");
				long currentTime = System.currentTimeMillis();
				final long LOCKOUT_DURATION = 30 * 1000;
				System.out.println(lockoutTime + "세션에 담긴 락타임");
				System.out.println(failedCount + "실패횟수 카운팅");
				if(lockoutTime != null && currentTime < lockoutTime + LOCKOUT_DURATION) {
					response.getWriter().write("locked");
					return;
				} else if(lockoutTime != null && currentTime >= lockoutTime + LOCKOUT_DURATION) {
					session.removeAttribute("failedLoginCount");
					session.removeAttribute("lockoutTime");
				}
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");

				String encryptPw = SecurityUtil.hashPassword(pw);

				MemberDTO member = dao.login(id, encryptPw);

				if(member != null) {
					System.out.println("로그인성공!");
					session.removeAttribute("failedLoginCount");
					session.removeAttribute("lockoutTime");
					request.getSession().setAttribute("member", member);
					response.getWriter().write("success");
				} else {
					if(failedCount == null) {
						failedCount = 1;
					} else {
						failedCount++;
					}
					session.setAttribute("failedLoginCount", failedCount);

					if(failedCount >= 5) {
						session.setAttribute("lockoutTime", currentTime);
						response.getWriter().write("locked");
					} else {
						response.getWriter().write("fail");
					}  
				}
			} else if (cmd.equals("/printout.do")) {

			} else if (cmd.equals("/update.do")) {

			} else if (cmd.equals("/delete.do")) {

			} else if (cmd.equals("/validate.do")) {

			} else if (cmd.equals("/shortvalid.do")) {

			}
		} catch(Exception e) {
			e.printStackTrace();
		}


	}
}