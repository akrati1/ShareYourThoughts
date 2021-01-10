<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cons.Connections"%>
<jsp:useBean scope="application" class="search.SearchUsers" id="srch"/>
<jsp:setProperty name="srch" property="srchtext" param="txt"/>
<%
        ArrayList<String> emails =srch.getUsers();
        Iterator<String> itr=emails.iterator();
        while(itr.hasNext()){
            String e=itr.next();
            Statement  st=Connections.connect();
            ResultSet rs=st.executeQuery("select first_name,last_name from newusers where email='"+e+"'");
            rs.next();
%>
<div style="width: 100%;height: 100%;border-bottom: 1px solid #4eb9ed;margin-bottom: 10px;float: left;">
    <div class="srchimg"><img src="http://localhost:8080/ShareYourThoughts/img?img=<%=e%>" width="60" height="60"></div>  
    <div class="srchlbl"><%=rs.getString(1)+" "+rs.getString(2)%></div>
</div>
<%
        Connections.disconnect();
        }
%>