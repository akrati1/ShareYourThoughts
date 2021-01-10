
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailClass 
{
    public void sendMail(String otp,String receiver)
    {
        try{
        String host="smtp.gmail.com";
        String user="zabaish12345";
        String email="zabaish12345@gmail.com";
        String pass="varargs@123";
        Properties props=new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", user);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtps.auth",true);
        props.put("mail.smtp.port",465);
        Session ses=Session.getDefaultInstance(props);
        MimeMessage message=new MimeMessage(ses);
        message.setSubject("New OTP received from ShareYourThoughts.com");
        message.setContent("Welcome Guest,<br><br>"
                + "Greetings from ShareYourThoughts!!!!<br>"
                + "Your OTP "+otp+"<br><br><br><br>Regards'<br>"
                + "<b>Technical Team</b>",
                "text/html; charset=utf-8");
        message.setFrom(new InternetAddress(email));
        message.setRecipient(Message.RecipientType.TO, 
                new InternetAddress(receiver));
        Transport trans=ses.getTransport("smtps");
        trans.connect(host,user, pass);
        trans.sendMessage(message, 
                message.getAllRecipients());
        trans.close();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
