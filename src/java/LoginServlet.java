import com.thoughtworks.xstream.XStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reuse.User;
import reuse.Users;
import javax.servlet.http.*;
import static cons.Connections.*;
import java.sql.*;
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet 
{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        try{
        String s1=req.getParameter("email");
        String s2=req.getParameter("pass");
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shareyourthoughts","root","123");
        PreparedStatement st=con.prepareStatement(
        "select * from newusers where email=? and passwd=?");
        st.setString(1, s1);
        st.setString(2, s2);
        //Statement st=con.createStatement();
        //ResultSet rs=st.executeQuery("select * from newusers where email='"+s1+"' and passwd='"+s2+"'");
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            String name=rs.getString(1)+" "+rs.getString(2);
            HttpSession ses=req.getSession();
            ses.setAttribute("useremail", s1);
            ses.setAttribute("username", name);
            resp.sendRedirect("welcome.jsp");
        }else{
            resp.sendRedirect("loginerr.html");
        }
        st.close();
        con.close();
        }catch(Exception e)
        {}
    }   
}
