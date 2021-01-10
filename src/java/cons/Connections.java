package cons;
import java.sql.*;
public class Connections 
{
    static Statement st=null;
    static Connection con;
    public static Statement connect()
    {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shareyourthoughts","root","123");
        st=con.createStatement();
        }catch(Exception e)
        {}
        return st;
    }
    public static void disconnect(){
        try{
        st.close();
        con.close();
        }catch(Exception e)
        {}
    }
}
