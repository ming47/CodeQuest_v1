package Common;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {
    public static boolean sendResetEmail(String recipientEmail, String resetLink) {
        // 이메일 계정 정보 (보안상 환경변수나 설정 파일에서 관리)
        final String username = "your_email@example.com"; // 보내는 이메일 주소
        final String password = "your_email_password";      // 보내는 이메일 비밀번호

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS 사용
        props.put("mail.smtp.host", "smtp.example.com");  // SMTP 서버 주소 (예: smtp.gmail.com)
        props.put("mail.smtp.port", "587");               // SMTP 포트 (TLS의 경우 587)

        // 메일 세션 생성
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "CodeQuest"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("비밀번호 재설정 안내");
            String msgContent = "비밀번호 재설정을 위해 아래 링크를 클릭하세요:\n" + resetLink;
            message.setText(msgContent);

            Transport.send(message);
            
            System.out.println("메일 발송 성공!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
