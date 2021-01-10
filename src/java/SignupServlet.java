import com.thoughtworks.xstream.XStream;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import reuse.User;
import reuse.Users;
@WebServlet(name = "SignupServlet", urlPatterns = {"/signup"})
public class SignupServlet extends HttpServlet 
{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String fn=req.getParameter("fname");
        String ln=req.getParameter("lname");
        String e=req.getParameter("email");
        String pass=req.getParameter("cpass");
        String cn=req.getParameter("cnct");
        String gen=req.getParameter("gen");
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/shareyourthoughts","root","123");
        Statement st=c.createStatement();
        st.executeUpdate("insert into newusers(first_name,last_name,email,passwd,contact,gender) values('"+fn+"','"+ln+"','"+e+"','"+pass+"',"+Long.parseLong(cn)+",'"+gen+"')");
        st.close();
        c.close();
        EncryptClass ec=new EncryptClass();
        Cookie ck=new Cookie("email", ec.encrypt(e));
        resp.addCookie(ck);
        HttpSession ses=req.getSession();
        ses.setAttribute("cnt", cn);
        resp.sendRedirect("updateimage.html");
        }catch(Exception ex)
        {
            System.out.println("Exception is "+ex);
        }
    }   
}
