import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;
import static cons.Connections.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import java.awt.image.*;
import javax.imageio.*;
@WebServlet(name = "ImgServlet", urlPatterns = {"/img"})
public class ImgServlet extends HttpServlet 
{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        try{
        String email=req.getParameter("img");
        Statement st=connect();
        ResultSet rs=st.executeQuery("select profile_image from newusers where email='"+email+"'");
        rs.next();
        Blob b=rs.getBlob(1);
        rs.close();
        disconnect();
        InputStream in=b.getBinaryStream();
        BufferedImage image=ImageIO.read(in);
        ServletOutputStream out=resp.getOutputStream(); 
        ImageIO.write(image, "PNG", out);    
        }catch(Exception e)
        {}
    }   
}
