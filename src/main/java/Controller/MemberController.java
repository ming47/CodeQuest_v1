package Controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Common.ConvertURL;
import Common.SecurityUtil;
import DAO.BoardDAO;
import DAO.MemberDAO;
import DAOImpl.BoardDAOImpl;
import DAOImpl.MemberDAOImpl;
import DTO.BoardDTO;
import DTO.MemberDTO;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	Gson g = new Gson();
	private MemberDAO dao = MemberDAOImpl.INSTANCE;
	private BoardDAO boardDao = BoardDAOImpl.INSTANCE;

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
				MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
				if (member == null) {
					response.sendRedirect("/");
					return;
				}
				int memberId = member.getMemberId();
				List<BoardDTO> recentPost = boardDao.selectByMemberId(memberId);
				request.setAttribute("recentPost", recentPost);
				request.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp").forward(request, response);
			} else if (cmd.equals("/member/logout.do")) {
				request.getSession().invalidate();
				response.sendRedirect("/");

			} else if (cmd.equals("/member/pwResetForm.do")) {
				request.getRequestDispatcher("/WEB-INF/views/member/pwResetForm.jsp").forward(request, response);
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
			if (cmd.equals("/member/add.do")) { //회원가입

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
			} else if (cmd.equals("/member/login.do")) { //로그인

				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String encryptPw = SecurityUtil.hashPassword(pw);

				MemberDTO member = dao.login(id, encryptPw);
				if (member != null) {
					System.out.println("로그인성공!");
					request.getSession().setAttribute("member", member);
				}
				response.sendRedirect("/");

			} else if (cmd.equals("/printout.do")) { // 출력

			} else if (cmd.equals("/member/update.do")) { // 수정
				int memberId = Integer.parseInt(request.getParameter("memberId"));
				String loginId = request.getParameter("loginId");
				String nickName = request.getParameter("nickName");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				String zipCodeStr = request.getParameter("zipCode");
				System.out.println(zipCodeStr);
				int postcode = 0;
				if(zipCodeStr.equals("입력된 정보가 없습니다.")) {
					postcode = 0;
				} else {
					postcode = Integer.parseInt(zipCodeStr);
				}

				String address = request.getParameter("address");
				String detailAddress = request.getParameter("detailAddress");

				int result = dao.update(new MemberDTO(loginId,nickName,email,phone,postcode,address,detailAddress));
				if(result > 0) {
					System.out.println("수정성공!");
					MemberDTO member = dao.selectById(memberId);
					request.getSession().setAttribute("member", member);
				}
				request.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp").forward(request, response);

			} else if (cmd.equals("/delete.do")) {
/*
			}else if (cmd.equals("/member/sendResetEmail.do")) {
				String email = request.getParameter("email");
				System.out.println("비밀번호 찾기 호출! " + email);

				// JWT를 생성하기 위한 비밀키 (실제 운영에서는 안전하게 관리)
				String secretKey = "mySuperSecretKey";  

				// 현재 시간과 만료시간(1시간 후) 설정
				Date now = new Date();
				Date expiry = new Date(now.getTime() + 3600000); // 1시간 = 3600000ms

				// JWT 토큰 생성: subject에 이메일을 담고, 발행 시간 및 만료 시간을 설정
				String token = Jwts.builder()
						.setSubject(email)
						.setIssuedAt(now)
						.setExpiration(expiry)
						.signWith(SignatureAlgorithm.HS256, secretKey)
						.compact();

				// 재설정 링크 구성 (토큰을 URL 인코딩 처리)
				String resetLink = request.getScheme() + "://" +
						request.getServerName() + ":" +
						request.getServerPort() +
						request.getContextPath() +
						"/member/resetPassword.do?token=" + URLEncoder.encode(token, "UTF-8");

				System.out.println("Reset link: " + resetLink);

				// 실제 운영에서는 EmailUtil.sendResetEmail() 등을 통해 메일 전송 처리
				// 여기서는 테스트용으로 링크를 응답합니다.
				response.getWriter().write("인증 링크가 전송되었습니다. 링크: " + resetLink);
				
				*/
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