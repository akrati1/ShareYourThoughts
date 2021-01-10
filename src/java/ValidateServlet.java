import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import static cons.Connections.*;
import java.sql.*;
@WebServlet(name = "ValidateServlet", urlPatterns = {"/validate"})
public class ValidateServlet extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        try{
        String otp=req.getParameter("otp");
        HttpSession ses=req.getSession();
        String contact=ses.getAttribute("cnt").toString();
        Statement st=connect();
        ResultSet rs=st.executeQuery("SELECT TIMESTAMPDIFF(MINUTE,otptime,NOW()) FROM newusers WHERE contact='"+contact+"' AND otp='"+otp+"'");
        if(rs.next()){
            int min=rs.getInt(1);
            if(min>5)
            {
                resp.sendRedirect("expiredOTP.html");    
            }
            else
            {
                st.executeUpdate("update newusers set verified='Y' where contact='"+contact+"'");
            }
        }
        else{
            resp.sendRedirect("errorOTP.html");
        }
        rs.close();
        disconnect();
        }catch(Exception e)
        {
            resp.sendRedirect("register.html");
        }
    }   
}
