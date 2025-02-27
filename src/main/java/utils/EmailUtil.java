package utils;

import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class EmailUtil {
    public static boolean sendResetEmail(String recipientEmail, String authCode, String action) {
        try {
            Context envContext = (Context) new InitialContext().lookup("java:comp/env");
            String username = (String) envContext.lookup("mail/username");
            String password = (String) envContext.lookup("mail/password");

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "CodeQuest"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            if(action.equals("out")) {
                message.setSubject("회원탈퇴 인증 코드");
                message.setText("회원탈퇴를 위해 아래 인증 코드를 입력하세요:\n" + authCode);	
                Transport.send(message);
                return true;
            }
            message.setSubject("비밀번호 재설정 인증 코드");
            message.setText("비밀번호 재설정을 위해 아래 인증 코드를 입력하세요:\n" + authCode);
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
