package member.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import service.blacklist.BlackListDAO;
import service.blacklist.BlackListDAOImpl;
import utils.OAuthTokenDTO;

@WebServlet("/KakaoLogin")
public class LoginController extends HttpServlet {
    private MemberDAO memberDao = MemberDAOImpl.INSTANCE;
    private BlackListDAO blackListDao = BlackListDAOImpl.INSTANCE;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        try {
            String code = request.getParameter("code");
            Context envContext = (Context) new InitialContext().lookup("java:comp/env");
            String clientId = (String) envContext.lookup("kakao/client_id");
            String redirectUri = (String) envContext.lookup("kakao/redirect_uri");
            String pUrl = "https://kauth.kakao.com/oauth/token";
            String bodyData = "grant_type=authorization_code&"
                    + "client_id=" + clientId + "&"
                    + "redirect_uri=" + redirectUri + "&"
                    + "code=" + code;
            URL url = new URL(pUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            con.setDoOutput(true);
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"))) {
                bw.write(bodyData);
                bw.flush();
            }
            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"))) {
                String input;
                while ((input = br.readLine()) != null) {
                    sb.append(input);
                }
            }
            Gson gson = new Gson();
            OAuthTokenDTO oAuthToken = gson.fromJson(sb.toString(), OAuthTokenDTO.class);
            String accessToken = oAuthToken.getAccess_token();
            URL userInfoUrl = new URL("https://kapi.kakao.com/v2/user/me");
            HttpURLConnection userCon = (HttpURLConnection) userInfoUrl.openConnection();
            userCon.setRequestMethod("GET");
            userCon.setRequestProperty("Authorization", "Bearer " + accessToken);
            userCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            int responseCode = userCon.getResponseCode();
            BufferedReader userBr;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                userBr = new BufferedReader(new InputStreamReader(userCon.getInputStream(), "UTF-8"));
            } else {
                userBr = new BufferedReader(new InputStreamReader(userCon.getErrorStream(), "UTF-8"));
            }
            StringBuilder userSb = new StringBuilder();
            String line;
            while ((line = userBr.readLine()) != null) {
                userSb.append(line);
            }
            userBr.close();
            String userJson = userSb.toString();
            com.google.gson.JsonObject jsonObj = gson.fromJson(userJson, com.google.gson.JsonObject.class);
            com.google.gson.JsonObject kakaoAccount = jsonObj.getAsJsonObject("kakao_account");
            com.google.gson.JsonObject profile = null;
            if (kakaoAccount.has("profile") && !kakaoAccount.get("profile").isJsonNull()) {
                profile = kakaoAccount.getAsJsonObject("profile");
            }
            String profileNickname = "정보없음";
            if (profile != null && profile.has("nickname") && !profile.get("nickname").isJsonNull()) {
                profileNickname = profile.get("nickname").getAsString();
            } else if (kakaoAccount.has("name") && !kakaoAccount.get("name").isJsonNull()) {
                profileNickname = kakaoAccount.get("name").getAsString();
            }
            String profileImage = "정보없음";
            if (profile != null && profile.has("profile_image_url") && !profile.get("profile_image_url").isJsonNull()) {
                profileImage = profile.get("profile_image_url").getAsString();
            }
            String accountEmail = kakaoAccount.has("email") && !kakaoAccount.get("email").isJsonNull()
                    ? kakaoAccount.get("email").getAsString()
                    : "정보없음";
            boolean result = memberDao.isDuplicate("email", accountEmail);
            if(result == false) {
                request.setAttribute("email", accountEmail);
                request.setAttribute("nickName", profileNickname);
                request.getRequestDispatcher("/WEB-INF/views/member/easySignupForm.jsp").forward(request, response);
            } else {
                MemberDTO member = memberDao.easyLogin(accountEmail);
				boolean banned = blackListDao.isBanned(member.getMemberId());
				member.setIsbanned(banned);
                request.getSession().setAttribute("member", member);
                response.sendRedirect("/");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
