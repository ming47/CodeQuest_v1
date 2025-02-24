package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Common.ConvertURL;
import Common.EmailUtil;
import Common.SecurityUtil;
import DAO.BlackListDAO;
import DAO.BoardDAO;
import DAO.MemberDAO;

import DAO.PlaytimeDAO;
import DAOImpl.BlackListDAOImpl;
import DAOImpl.BoardDAOImpl;
import DAOImpl.MemberDAOImpl;
import DAOImpl.PlaytimeDAOImpl;
import DTO.BoardDTO;
import DTO.MemberDTO;
import DTO.PlaytimeDTO;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	Gson g = new Gson();
	private MemberDAO dao = MemberDAOImpl.INSTANCE;
	private BoardDAO boardDao = BoardDAOImpl.INSTANCE;
	private BlackListDAO blackListDao = BlackListDAOImpl.INSTANCE;
	private PlaytimeDAO playtimeDao = PlaytimeDAOImpl.INSTANCE;

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
				//최근 작성한 게시글
				List<BoardDTO> recentPost = boardDao.selectByMemberId(memberId);
				request.setAttribute("recentPost", recentPost);
				
				//최근 플레이한 게임
			    List<PlaytimeDTO> recentPlayTime = playtimeDao.selectRecentByMemberId(memberId);
			    request.setAttribute("recentPlayTime", recentPlayTime);
			    
			    for (PlaytimeDTO pt : recentPlayTime) {
			        int totalSeconds = pt.getPlaytime();
			        int minutes = totalSeconds / 60;
			        int seconds = totalSeconds % 60;
			        String formattedTime = minutes + "분 " + seconds + "초";
			        pt.setFormatTime(formattedTime);
			    }
				
				request.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp").forward(request, response);
			} else if (cmd.equals("/member/logout.do")) {
				request.getSession().invalidate();
				response.sendRedirect("/");

			} else if (cmd.equals("/member/pwResetForm.do")) {
				request.getRequestDispatcher("/WEB-INF/views/member/pwResetForm.jsp").forward(request, response);
			} else if (cmd.equals("/member/out.do")) { //회원 탈퇴
				int id = Integer.parseInt(request.getParameter("id"));
				int result = dao.deleteById(id);
				if (result > 0) {
					request.getSession().invalidate();
				}
				response.sendRedirect("/");

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
				String pw = SecurityUtil.hashPassword(request.getParameter("pw"));
				System.out.println(pw);

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


				int result = dao.insert(new MemberDTO(id,pw,name,nickName,ssn,email,phone,postcode,address1,address2,"user")); //role은 정해진게없어서 null
				if(result > 0) {
					System.out.println("가입성공!");
				}
				response.sendRedirect("/");
			} else if (cmd.equals("/member/easySignup.do")) {
				
				String name = request.getParameter("name");
				String nickName = request.getParameter("nickName");

				String ssnFront = request.getParameter("ssnFront");
				String ssnBack = request.getParameter("ssnBack");
				String ssn = ssnFront + "-" + ssnBack + "******";
				
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				
				String postcodeStr = request.getParameter("postcode");
				int postcode = 0;
				if(postcodeStr != "") {
					postcode = 0;
				} else if(postcodeStr == "") {
					postcode = 0;
				} else if(postcodeStr == null) {
					postcode = 0;
				} else {
					postcode = Integer.parseInt(postcodeStr);
				}
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");
				int memberAdd = dao.insert(new MemberDTO(null,null,name,nickName,ssn,email,phone,postcode,address1,address2,"user"));
				if(memberAdd > 0) {
					System.out.println("가입성공!");
				}
				response.sendRedirect("/");
			} else if (cmd.equals("/member/login.do")) { //로그인
				String id = request.getParameter("id");
				String pw = SecurityUtil.hashPassword(request.getParameter("pw"));

				MemberDTO member = dao.login(id, pw);
				if(member == null) {
					response.sendRedirect("/?login=fail");
					return;
				}
				// 밴 유저 검사
				boolean banned = blackListDao.isBanned(member.getMemberId());
				member.setIsbanned(banned);
				request.getSession().setAttribute("member", member);
				
				response.sendRedirect("/");

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

			} else if (cmd.equals("/member/sendResetEmail.do")) {
				String email = request.getParameter("email");

				// 1. 인증코드 생성
				int authCode = (int)(Math.random() * 900000) + 100000; 
				String codeStr = String.valueOf(authCode);
				System.out.println("컨트롤러 인증코드 : " + codeStr);

				// 2. 세션에 인증 코드와 이메일 저장 (유효기간은 세션 타임아웃으로 대체 가능)
				request.setAttribute("authEmail", email);
				request.setAttribute("authCode", codeStr);

				// 3. 이메일 전송
				boolean emailSent = EmailUtil.sendResetEmail(email, codeStr);
				if(emailSent) {
					System.out.println("메일전송");
				} else {
					System.out.println("메일전송 실패");
				}
				request.getRequestDispatcher("/WEB-INF/views/member/pwResetForm.jsp").forward(request, response);

			} else if (cmd.equals("/member/pwReset.do")) {
				System.out.println("호출!");
				String pw = request.getParameter("pw");
				System.out.println("넘어온 pw: "+pw);
				String encryptPw = SecurityUtil.hashPassword(pw);
				String email = request.getParameter("resetEmail");
				System.out.println("넘어온 email: "+email);

				int result = dao.updatePw(email,encryptPw);
				if(result > 0) {
					System.out.println("패스워드 변경 성공!");
					response.getWriter().write("<script>alert('패스워드 변경 성공!'); window.close();</script>");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}