import com.thoughtworks.xstream.XStream;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reuse.User;
@WebServlet(name = "ReadServlet", urlPatterns = {"/read"})
public class ReadServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path=getServletContext().getRealPath("/")+"user.xml";
        PrintWriter pw=resp.getWriter();
        XStream xs=new XStream();
        Object o=xs.fromXML(new FileReader(path));
        User u=(User)o;
        pw.println(u.getFirst_name()+":"+u.getLast_name()+":"+u.getEmail());    
    }
}
