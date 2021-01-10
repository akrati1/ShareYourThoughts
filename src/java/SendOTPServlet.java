import java.io.IOException;
import java.sql.*;
import java.net.*;
import java.io.*;
import java.io.PrintWriter;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static cons.Connections.*;
import org.apache.taglibs.standard.lang.jstl.test.StaticFunctionTests;
@WebServlet(name = "SendOTPServlet", urlPatterns = {"/sendotp"})
public class SendOTPServlet extends HttpServlet implements Runnable
{
    String email,otp,cnt;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        email=null;
        Cookie ck[]=req.getCookies();
        for(Cookie c:ck)
                if(c.getName().equals("email"))
                    email=EncryptClass.decrypt(
                            c.getValue());
        HttpSession ses=req.getSession();
        cnt=ses.getAttribute("cnt").toString();
        otp=getOTPString();
        new Thread(this).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                //URL url=new URL("http://api.mvaayoo.com/mvaayooapi/MessageCompose?user=rishab24rg@gmail.com:rishab@123&senderID=TEST%20SMS&receipientno="+cnt+"&dcs=0&msgtxt=Your%20OTP%20is%20"+otp+"&state=4");
                //url.openStream();
                }catch(Exception w)
                {}
            }
        }).start();
        resp.sendRedirect("validateOTP.html");
    }

    @Override
    public void run() {
        Statement st=connect();
        try{
        st.executeUpdate("update newusers set otp='"+otp+"',otptime=now() where email='"+email+"'");
        }catch(Exception e)
        {}
        disconnect();
        MailClass m=new MailClass();
        m.sendMail(otp, email);
    }
    
    public String getOTPString()
    {
        String s="";
        for(int i=1;i<=4;i++){
            int val=(int)(10*Math.random());
            s=s+val;
        }
        return s;
    }
}
