import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet(name = "UpdateServlet", urlPatterns = {"/update"})
public class UpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
        Part p1=req.getPart("about");
        Part p2=req.getPart("file");
        InputStream in1=p1.getInputStream();
        DataInputStream din=new DataInputStream(in1);
        String s=din.readLine();
        InputStream in2=p2.getInputStream();
        Class.forName("com.mysql.jdbc.Driver");
        Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/shareyourthoughts","root","123");
        Cookie ck[]=req.getCookies();
        String s2=null;
        for(Cookie c1:ck){
            String s1=c1.getName();
            if(s1.equals("email")){
               s2=EncryptClass.decrypt(
                       c1.getValue());
            }
        }
        String sql="update newusers set profile_image=?,aboutme=? where email='"+s2+"'";
        PreparedStatement pst=c.prepareStatement(sql);
        pst.setString(2, s);
        pst.setBlob(1, in2);
        pst.executeUpdate();
        pst.close();
        c.close();
        resp.sendRedirect("sendotp");
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
