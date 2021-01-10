<%@page  import="java.net.*"%>
<html>
    <head>
        <title>Welcome....</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            .maindiv{
                height: 600px;
                width: 1000px;
                /*border: 1px solid #000;
                margin-left: 10%;*/
            }
            .txt{
                background-image: url(image/search1.jpg);
                background-repeat: no-repeat;
                background-position: right;
                border:1px solid activecaption;
                border-radius: 10px;
                width:250px;
                height:30px;
                float: top;
            }
            .btn
            {
                height: 30px;
                width: 100px;
                background-color: #4eb9ed;
                color: #ffffff;
                font-family: verdana;
                border: 1px solid #000000;
            }
            .leftnav{
                width: 100%;
                height: 100%;
            }
            .imgdiv{
                width: 90%;
                height: 200px;
                border: 1px solid #979797;
                margin: 10px;
                float: left;
            }
            .onlinediv{
                width: 90%;
                height: auto;
                border: 1px solid #979797;
                margin: 10px;
                float: left;
            }
            .changeimg{
                font-family: verdana;
                font-size: 17px;
                width: 220px;
                height: 30px;
                background-color: #000000;
                color: #ffffff;
                position: absolute;
                text-align: center;
                padding-top: 10px;
                margin-top: -60px;
                opacity: 0.6;
                visibility: hidden;
            }
            .searchcontent{
                width: 250px;
                height: auto;
                position: absolute;
                border:1px solid activecaption;
                margin-left: 322px;
                margin-top: 67px;
                border-radius: 10px; 
                background-color: #979797;
                display: none;
            }
            .srchimg{
                width: 60px;
                height: 60px;
                float:left;
                margin-left: 10px;
            }
            .srchlbl{
                float: left;
                margin-top: 25px;
                margin-left: 20px;
                font-family: verdana;
                font-size: 13px;
            }
        </style>
        <script src="script/jquery-latest.js"></script>
        <script>
            $(document).ready(function(){
                $("#srch").keyup(function(){
                    data=$(this).val();
                    if(data!=""){
                        $.post("search_new.jsp?txt="+data,function(result){
                            $("#srchcon").html(result);
                        });
                        $("#srchcon").show();
                    }
                    else{
                        $("#srchcon").hide();
                    }
                });
            });
        </script>
    </head>
    <body>
    <center>
        <div class="maindiv" id="maindiv">
            <table style="width:100%;">
                <tr>
                    <td colspan="3" style="width: 100%;">
                        <table style="width: 100%;">
                            <tr>
                                <td><img src="image/sharegraphic.png" height="100" width="250"/></td>
                                <td><input type="text" class="txt" id="srch"/></td>
                                <div class="searchcontent" id="srchcon">
                                    
                                </div>    
                                <td style="color: cadetblue;">Last seen: 12/07/2016  12:12:12</td>
                                <td style="float: right; padding: 30px 10px 0 20px;"><a href="">logout</a></td>
                            </tr>
                        </table><hr></hr>
                    </td>
                </tr>
                <%
                    InetAddress address=InetAddress.getLocalHost();
                    String ip=address.getHostAddress();
                %>
                <tr>
                    <td style="width:25%; height: 400px; border: 1px solid activecaption;">
                        <div class="leftnav">
                            <div class="imgdiv">
                                <img src="http://localhost:8080/ShareYourThoughts/image" height="180" width="200" style="margin: 10px" onmouseover="visible()" onmouseout="hide()">
                                <div class="changeimg" id="changeimg"  onmouseover="visible()" onmouseout="hide()"><a href="" onclick="return change()" style="text-decoration: none;color: #ffffff">Change Image</a></div>
                                <div id="browsediv" style="visibility: hidden">
                                    <form action="changeimg" method="post" enctype="multipart/form-data">
                                        <input type="file" name="file" style="float: left;width: 140px;font-size: 13px;"/>
                                        <input type="submit" name="sbt" value="UPLOAD" style="float: left; width: 80px;font-size: 12px;">
                                    </form>
                                </div>
                            </div>
                            <div class="onlinediv">
                                <div style="width: 100%;float: left;background-color: #4eb9ed;height: 25px;color: #fff;text-align: center;padding-top: 4px;font-size: 20px;"><a href="myprofileimages.jsp?page=1" style="text-decoration: none;color:#fff">Profile Images</a></div>
                            </div>
                            <div class="onlinediv">
                                <div style="width: 100%;float: left;background-color: #4eb9ed;height: 25px;color: #fff;text-align: center;padding-top: 4px;font-size: 20px;">My Posts</div>
                            </div>
                            <div class="onlinediv">
                                <div style="width: 100%;float: left;background-color: #4eb9ed;height: 25px;color: #fff;text-align: center;padding-top: 4px;font-size: 20px;">Logout</div>
                            </div>
                            <div class="onlinediv">
                                <div style="width: 100%;float: left;background-color: #4eb9ed;height: 25px;color: #fff;text-align: center;padding-top: 4px;font-size: 20px;">Online Friends</div>
                            </div>
                        </div>
                    </td>
                    <td style="width:50%;padding-left: 30px;">
                        <table style="margin-bottom: 280px;">
                            <tr>
                                <td style="font-family: verdana;font-size: 15px;">Welcome <b><%--<%
                                    String n=session.getAttribute("username").toString();
                                    out.println(n);
                                %>--%><%=session.getAttribute("username").toString()%></b></td>
                            </tr>
                            <tr>
                                <td>
                                    <textarea cols="50" rows="3" placeholder="Share Your Ideas" style="margin: 10px 0 10px 0;"></textarea>
                                    <br/>
                                    <input type="button" name="btnpost" class="btn" value="POST"/>
                                </td></tr></table></td>
                    <td style="width:25%; height: 400px; border: 1px solid activecaption;"></td>
                </tr>
            </table>  
        </div>
    </center>   
</body>
       <script>
            var oldcon="";
            d=document.getElementById("changeimg");
            function visible()
            {
                d.style.visibility='visible';
            }
            function hide()
            {
                /*if(oldcon!=""){
                    document.getElementById("changeimg").innerHTML=oldcon;
                    oldcon="";
                }*/
                d.style.visibility='hidden';
            }
            function change()
            {
                oldcon=document.getElementById("changeimg").innerHTML;
                var data=document.getElementById("browsediv").innerHTML;
                document.getElementById("changeimg").innerHTML=data;
                return false;
            }
        </script>
</html>