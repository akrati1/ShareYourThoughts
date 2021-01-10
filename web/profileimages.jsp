<%@page  import="java.io.*"%>
    <style>
        .mainimgdiv{
            width: 670px;
            height: auto;
            float: left;
        }
        .newimgdiv{
            width: 200px;
            height: 200px;
            border: 1px solid black;
            margin: 10px;
            float: left;
        }
        .navdiv{
            width: 670px;
            height: 50px;
            margin-top: 10px;
            float:left;
            text-align: center;
        }
        .btn{
            width: 120px;
            height: 25px;
            background-color: #979797;
            border:  1px solid #000;
            margin-top: 5px;
            padding-top: 12px;
            margin-left: 10px;
            color: black;
        }
    </style>
    <div class="mainimgdiv">
      <%
        int p=Integer.parseInt(request.getParameter("page"));
        String email=session.getAttribute("useremail").toString();
        String path=application.getRealPath("/")+"\\"+email+"\\profile_images";
        File f=new File(path);
        String filenames[]=f.list();
        for(int i=(p-1)*6;i<filenames.length&&i<p*6;i++){  
            String fn=email+"\\profile_images\\"+filenames[i];
      %>  
        <div class="newimgdiv"><img src="<%=fn%>" width="200" height="200"></div>
      <%
        }
      %>  
    </div>
    <%
        if(filenames.length>6)
        {
    %>
    <div class="navdiv">
        <%
            if(p!=1){
        %>
        <a href="?page=<%=p-1%>">
        <div class="btn" 
       style="text-decoration: none;
       float:left">&lt;&lt;PREVIOUS
        </div></a>
        <%
            }
        %>
        <%
         int len=filenames.length,a,b;
            a=len%6;
            b=len/6;
            if(a!=0)
                b=b+1;
            for(int i=1;i<=b;i++){
        %>
        <a href="?page=<%=i%>" 
        style="text-decoration: none">
        <div class="btn" 
        style="width: 40px;float: left">
            <%=i%></div></a>
        <%
           }
        %>
        <%
            if(p!=b){
        %>
        <a href="?page=<%=p+1%>">
        <div class="btn" 
        style="text-decoration: none;
        float:left">NEXT&gt;&gt;</div>
        </a>
        <%
            }
        %>
    </div>
    <%
         }
    %>