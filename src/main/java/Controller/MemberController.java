package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			if (cmd.equals("/member/addForm.do")) {
				request.getRequestDispatcher("/WEB-INF/views/member/signup.jsp").forward(request, response);
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
				
		        String id = request.getParameter("id");
		        String pw = request.getParameter("pw");
		        
				String encryptPw = SecurityUtil.hashPassword(pw);
				
				MemberDTO member = dao.login(id, encryptPw);
				
				if(member != null) {
					System.out.println("로그인성공!");
					request.getSession().setAttribute("member", member);
					response.getWriter().write("success");
				} else {
				response.getWriter().write("fail");
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