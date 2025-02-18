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
			if (cmd.equals("/member/addForm.do")) { // 회원가입 폼
				request.getRequestDispatcher("/WEB-INF/views/member/signup.jsp").forward(request, response);
			} else if(cmd.equals("/member/valueCheck.do")) { //ajax 중복체크
				String value = request.getParameter("value");
			    String field = request.getParameter("field");
			    String column = null;
			    if ("login_id".equals(field)) {
			        column = "login_id";
			    } else if ("nickname".equals(field)) {
			        column = "nickname";
			    } else if ("email".equals(field)) {
			        column = "email";
			    } else if ("phone".equals(field)) {
			        column = "phone";
			    }				
			    if (column != null) {
			    	boolean result = dao.isDuplicate(column, value);
			    	if(result == true) {
			    		response.getWriter().append("exist");
			    	}
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
		} catch (Exception e) {
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
				String nickName = request.getParameter("nickName");

				//주민번호 앞자리,뒷자리 받은 후 DB입력할수있게 폼 완성
				String ssnFront = request.getParameter("ssnFront");
				String ssnBack = request.getParameter("ssnBack");
				String ssn = ssnFront + "-" + ssnBack + "******";
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				
				
				String postcodeStr = request.getParameter("postcode");
				int postcode = 0;
				if(postcodeStr != "") {
					postcode = Integer.parseInt(postcodeStr);
				} else if(postcodeStr == "") {
					postcode = 0;
				}
						
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");


				int result = dao.insert(new MemberDTO(id,encryptPw,name,nickName,ssn,email,phone,postcode,address1,address2,"user")); //role은 정해진게없어서 null
				if(result > 0) {
					System.out.println("가입성공!");
				}
				response.sendRedirect("/");
			} else if (cmd.equals("/member/login.do")) {

				String id = request.getParameter("id");
				String pw = request.getParameter("pw");

				String encryptPw = SecurityUtil.hashPassword(pw);

				MemberDTO member = dao.login(id, encryptPw);
				if (member != null) {
					System.out.println("로그인성공!");
					request.getSession().setAttribute("member", member);
					response.getWriter().write("success");
				} else {
					response.getWriter().write("fail");
				}

			} else if (cmd.equals("/printout.do")) { // 출력

			} else if (cmd.equals("/member/update.do")) { // 수정
				// 세션에서 가져옴
				MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
				String id = member.getId();
				if (id == null) {
					response.sendRedirect("/member/login.do");
					return;
				}
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				int zipCode = Integer.parseInt(request.getParameter("postcode"));
				String address = request.getParameter("address");
				String detail_address = request.getParameter("detail_address");
				try {
					int result = dao.update(new MemberDTO(id,email, phone, zipCode, address, detail_address));
					if (result > 0) {
						response.sendRedirect("/member/mypage.jsp");
					} else {
						response.sendRedirect("/error.jsp");
					}
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("/error.jsp");
				}
			} else if (cmd.equals("/delete.do")) {

			} else if (cmd.equals("/validate.do")) {

			} else if (cmd.equals("/shortvalid.do")) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}