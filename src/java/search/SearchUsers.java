package search;
import java.util.*;
import java.sql.*;
import static cons.Connections.*;
public class SearchUsers 
{
    private String srchtext;
    public ArrayList<String> users=new ArrayList<String>();
    public String getSrchtext() {
        return srchtext;
    }

    public void setSrchtext(String srchtext) {
        this.srchtext = srchtext;
    }

    public ArrayList<String> getUsers() 
    {
        ArrayList<String> al=new ArrayList<String>();
        try{
        Statement st=connect();
        ResultSet rs=st.executeQuery("select email from newusers where first_name like '%"+srchtext+"%' or last_name like '%"+srchtext+"%'");
        while(rs.next()){
            al.add(rs.getString(1));
        }
        rs.close();
        disconnect();
        this.users=al;
        }catch(Exception e)
        {}
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
    
    
}
