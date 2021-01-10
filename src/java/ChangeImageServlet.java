import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
@WebServlet(name = "ChangeImageServlet", urlPatterns = {"/changeimg"})
public class ChangeImageServlet extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        try{
        Part p=req.getPart("file");
        InputStream in=p.getInputStream();
        HttpSession ses=req.getSession();
        String email=ses.getAttribute("useremail").toString();
        File f=new File(getServletContext().getRealPath("/")+"\\"+email+"\\profile_images");
        f.mkdirs();
        Class.forName("com.mysql.jdbc.Driver");
        Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/shareyourthoughts","root","123");
        PreparedStatement pst1=c.prepareStatement(
                "select profile_image from newusers where email=?");
        pst1.setString(1, email);
        ResultSet rs=pst1.executeQuery();
        rs.next();
        InputStream in1=rs.getBinaryStream(1);
        byte b[]=new byte[in1.available()];
        in1.read(b);
        File f1=null;
        while(true){
            int v=(int)((100*Math.random()));
            f1=new File(f.getPath()+"\\"+v+".jpg");
            if(!f1.exists())
                break;
        }
        FileOutputStream fout=new FileOutputStream(f1);
        fout.write(b);
        fout.close();
        PreparedStatement pst2=c.prepareStatement("update newusers set profile_image=? where email=?");
        pst2.setBlob(1, in);
        pst2.setString(2, email);
        pst2.executeUpdate();;
        pst2.close();
        c.close();
        resp.sendRedirect("welcome.jsp");
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }   
}
