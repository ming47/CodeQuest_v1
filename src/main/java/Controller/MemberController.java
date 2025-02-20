package Controller;

import java.io.IOException;
import java.sql.Timestamp;

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
			if (cmd.equals("/member/addForm.do")) { //회원가입 폼

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
				
			} else if (cmd.equals("/member/findMember.do")) {
				
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
				String loginId = request.getParameter("loginId");
				String name = request.getParameter("name");
				String nickName = request.getParameter("nickName");
				String ssn = request.getParameter("ssn");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				String zipCode = request.getParameter("zipCode");
				String address = request.getParameter("address");
				String detailAddress = request.getParameter("detailAddress");
				String regDate = request.getParameter("regDate");
				System.out.println("로그인ID"+loginId);
				System.out.println("name"+name);
				System.out.println("nickName"+nickName);

				
			} else if (cmd.equals("/delete.do")) {

			} else if (cmd.equals("/validate.do")) {

			} else if (cmd.equals("/shortvalid.do")) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	   public static void main(String[] args) {
		      for (int i = 0; i < 100; i++) {
		         String id = "USER" + i;
		         
		         String sql = String.format("INSERT INTO MEMBERS (MEMBER_ID, LOGIN_ID, PASSWORD, NAME, NICKNAME, SSN, EMAIL, PHONE, ROLE) VALUES (MEMBER_ID_SEQ.NEXTVAL, '%s', '%s', '%s', '%s', '%s', '%s', '%s', 'user');", 
		               id, SecurityUtil.hashPassword(id), id, id, generateSSN(), id + "@" + id + ".com", generatePhoneNumber());
		         
		         System.out.println(sql);
		      }
		   }
		   
		   public static String generateSSN() {
		      String year = String.valueOf((int) (Math.random() * 29 + 70));      
		      int month = (int) (Math.random() * 11 + 1);
		      int day = (int) (Math.random() * 27 + 1);
		      
		      int gender = (Math.random() < 0.5) ? 1 : 2;
		      
		      String monthStr = (month < 10) ? '0' + String.valueOf(month) : String.valueOf(month);
		      String dayStr = (day < 10) ? '0' + String.valueOf(day) : String.valueOf(day);
		      
		      return year + monthStr + dayStr + "-" + gender + "******";
		   }
		   
		   public static String generatePhoneNumber() {
		      return "010-" + String.valueOf((int) (Math.random() * 8000 + 1000)) + "-" + String.valueOf((int) (Math.random() * 8000 + 1000));
		   }
}