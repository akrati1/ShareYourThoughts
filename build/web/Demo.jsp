<%@page import="java.util.ArrayList"%>
<%@page import="search.SearchUsers"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<html>
<sql:setDataSource driver="com.mysql.jdbc.Driver" 
                   url="jdbc:mysql://localhost:3306/shareyourthoughts" 
                   user="root" password="123" var="con"/>
<sql:query var="rs" dataSource="${con}">
    select * from newusers
</sql:query>
<table border="1">    
<c:forEach var="row" items="${rs.rows}">
    <tr>
        <td><c:out value="${row.first_name}"/></td>
        <td><c:out value="${row.last_name}"/></td>
        <td><c:out value="${row.email}"/></td>
        <td><c:out value="${row.contact}"/></td>
        <td><c:out value="${row.gender}"/></td>
    </tr>
</c:forEach>    
</table>    
</html>
    