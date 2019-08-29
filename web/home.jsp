<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>BankSystem</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="css/templatemo_style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="css/coda-slider.css" type="text/css" media="screen" charset="utf-8" />

<script src="js/jquery-1.2.6.js" type="text/javascript"></script>
<script src="js/jquery.scrollTo-1.3.3.js" type="text/javascript"></script>
<script src="js/jquery.localscroll-1.2.5.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.serialScroll-1.2.1.js" type="text/javascript" charset="utf-8"></script>
<script src="js/coda-slider.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.easing.1.3.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>

</head>

<script type="text/javascript">

    //检查浏览器是否支持ajax
    //var xhr=new XMLHttpRequest();
    function getajaxHttp() {
        var xmlHttp;
        try {
            // Firefox, Opera 8.0+, Safari
            xmlHttp = new XMLHttpRequest();
        } catch (e) {
            // Internet Explorer
            try {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                try {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e) {
                    alert("您的浏览器不支持AJAX，请切换至更高版本的浏览器！");
                    return false;
                }
            }
        }
        xmlHttp.withCredentials=true;
        return xmlHttp;
    }

    //检查该用户ID是否存在
    function checkUser(T,Ttip){
        // var userID=document.getElementById("transferUserAccount").value;
        if(T.value!=""){
            var xmlhttp =getajaxHttp();
            // if(xmlhttp!=false) {
                xmlhttp.onreadystatechange =function(){
                    if (xmlhttp.readyState==4 && xmlhttp.status==200){
                        if(xmlhttp.responseText=="true"){
                            // 用户存在
                            Ttip.innerHTML="";
                            return true;
                        }else{
                            // 用户不存在
                            // var Ttip=document.getElementById("transferUserAccountTip");
                            Ttip.innerHTML="不存在";
                            return false;
                        }
                    }
                } //响应函数
                xmlhttp.open("GET","ValidateUser.ajax?userid="+T.value, true);   //设置访问的页面
            // alert("666");
                xmlhttp.send(null);  //执行访问
            // }
        }

    }

    //验证用户输入的金额格式是否符合要求
    function checkAmount(T,Ttip){
        if(T.value==""){
            return false;
        }
        //匹配正数的正则表达式（正浮点数、正整数，但不包括正负号）
        // var moneyFormat=/\d+|[1-9]\d*\.\d*|0\.\d*[1-9]\d*/i;
        var moneyFormat=/^\d+(\.\d+)?$/i;
        if(!moneyFormat.test(T.value)){
            Ttip.innerHTML="输入错误";
            return false;
        }
        Ttip.innerHTML="";
        return true;
    }

    //提交取款交易
    function submitWithdraw(T,Ttip) {
        if(checkAmount(T,Ttip)){
            var xmlhttp=getajaxHttp();
            if(xmlhttp!=false) {
                xmlhttp.onreadystatechange =function(){
                    if(xmlhttp.readyState==4&&xmlhttp.status==200){
                        Ttip.innerHTML="";
                        alert(decodeURI(xmlhttp.responseText));
                        // if(xmlhttp.responseText=="true"){
                        //     alert("取款成功！");
                        //     // location.reload(true);
                        //     // location.replace("home.html");
                        //     return true;
                        // }else{
                        //     alert("取款失败！"+xmlhttp.responseText);
                        //     return false;
                        // }
                    }
                } //响应函数
                xmlhttp.open("GET","ProcessDeal.ajax?cmd=withdraw&amount="+T.value, true);   //设置访问的页面
                xmlhttp.send(null);  //执行访问
            }
        }
    }

    //提交存款交易
    function submitDeposit(T,Ttip) {
        if(checkAmount(T,Ttip)){
            var xmlhttp=getajaxHttp();
            if(xmlhttp!=false) {
                xmlhttp.onreadystatechange =function(){
                    if(xmlhttp.readyState==4&&xmlhttp.status==200){
                        Ttip.innerHTML="";
                        alert(decodeURI(xmlhttp.responseText));
                        // if(xmlhttp.responseText=="true"){
                        //     alert("交易成功！页面即将刷新……");
                        //     // location.reload(true);
                        //     location.replace("home.html");
                        //     return true;
                        // }else{
                        //     alert("交易失败！"+xmlhttp.responseText);
                        //     return false;
                        // }
                    }
                } //响应函数
                xmlhttp.open("GET","ProcessDeal.ajax?cmd=deposit&amount="+T.value, true);   //设置访问的页面
                xmlhttp.send(null);  //执行访问
            }
        }
    }

    //提交转账交易
    function submitTransfer(T1,T1tip,T2,T2tip){
        if(checkUser(T1,T1tip)&&checkAmount(T2,T2tip)){
            var xmlhttp=getajaxHttp();
            if(xmlhttp!=false) {
                xmlhttp.onreadystatechange =function(){
                    if(xmlhttp.readyState==4&&xmlhttp.status==200){
                        T1tip.innerHTML="";
                        T2tip.innerHTML="";
                        alert(decodeURI(xmlhttp.responseText));
                        // if(xmlhttp.responseText!="false"){
                        //     alert("交易成功！页面即将刷新……");
                        //     // location.reload(true);
                        //     location.replace("home.html");
                        //     return true;
                        // }else{
                        //     alert("交易失败！");
                        //     return false;
                        // }
                    }
                } //响应函数
                xmlhttp.open("GET","ProcessDeal.ajax?cmd=transfer&receiverID="+T1.value+"&amount="+T2.value, true);   //设置访问的页面
                xmlhttp.send(null);  //执行访问
            }
        }
    }


    setInterval(function() {
        $("#home").load(location.href+" #home>*","");
    }, 1000);
</script>

<body>

<div id="slider">
	
    <div id="templatemo_sidebar">
    	<div id="templatemo_header">
        	<a href="#"><img src="images/BankIcon2.png" alt="BankSystem" /></a>
        </div> <!-- end of header -->
        
        <ul class="navigation">
            <li><a href="#home" onclick="function refresh(){window.location.reload()}">Home<span class="ui_icon home"></span></a></li>
            <li><a href="#withdraw">Withdraw<span class="ui_icon withdraw"></span></a></li>
            <li><a href="#deposit">Deposit<span class="ui_icon deposit"></span></a></li>
            <li><a href="#transfer">Transfer<span class="ui_icon transfer"></span></a></li>
            <li><a href="BusinessManagementServlet?cmd=getlogs">Log<span class="ui_icon log"></span></a></li>

            <!-- 管理员才可以看到 -->
            <c:if test="${sessionScope.user.adminGrant ==1}">
            <li><a href="#administration">Administration<span class="ui_icon administration"></span></a></li>
            </c:if>
        </ul>
    </div> <!-- end of sidebar -->

	<div id="templatemo_main">
    	<ul id="social_box" style="vertical-align: center">
            <li><a href="#home" title="UserID：${sessionScope.user.id}"><img src="images/userid.png" alt="UserID" /></a></li>
            <li><a href="#home" title="Username：${sessionScope.user.username}"><img src="images/username.png" alt="Username" /></a></li>

            <!--是否被冻结-->
            <li><a href="#home" title="是否被冻结：${sessionScope.user.flag >0}"><img src="images/locked.png" alt="是否账户冻结" /></a></li>
            <!--是否管理员-->
            <li><a href="#home" title="是否管理员：${sessionScope.user.adminGrant >0}"><img src="images/admin.png" alt="technorati" /></a></li>
            <li><a href="logout.jsp" title="点击退出当前登录"><img src="images/logout.png" alt="myspace" /></a></li>
<!--            <li>UserID:dssf</li>-->
<!--            <li>Username:fdsfdsfds</li>-->
<!--            <li>Locked:fsfdsfdff</li>-->
<!--            <li>UserID:fsssssss</li>-->
        </ul>
        
        <div id="content">
        
        <!-- scroll -->
        
        	
            <div class="scroll">
                <div class="scrollContainer">
                
                    <div class="panel" id="home">
                        <h1>欢迎登录银行系统, ${sessionScope.user.username}.</h1>
                        <p>现在开始您的理财生涯！</p>
                        <p>您账户当前余额为：${sessionScope.user.balance}</p>
                        <p>点击 <a href="#">这里</a> 进入 <a href="#">个人中心</a>.</p>
                    </div> <!-- end of home -->


                    <div class="panel" id="withdraw">
                        <h1>Withdraw</h1>
                        <p>请输入您要取款的金额</p>
                        <div class="contact_form">
                            <formp class="aform" method="post" name="contact">

                                <label>金额：<span id="withdrawAmountTip" style="color:  #E53935"></span></label><input type="text" onblur="checkAmount(this,document.getElementById('withdrawAmountTip'))" id="withdrawAmount" class="required input_field" />
                                <div class="cleaner_h10"></div>

<!--                                <label for="email">Your Email:</label> <input type="text" name="email" class="validate-email required input_field" />-->
<!--                                <div class="cleaner_h10"></div>-->

                                <label for="text">Message:</label> <textarea name="message" rows="0" cols="0" class="required"></textarea>
                                <div class="cleaner_h10"></div>

                                <input type="button" class="submit_btn" onclick="submitWithdraw(document.getElementById('withdrawAmount'),document.getElementById('withdrawAmountTip'))" name="submit" value="Send" />
                                <input type="reset" class="submit_btn" name="reset" value="Reset" />

                            </formp>
                        </div>
                    </div>

                    <div class="panel" id="deposit">
                        <h1>Deposit</h1>
                        <p>请输入您要存款的金额</p>
                        <div class="contact_form">
                            <formp class="aform" method="post" name="contact">

                                <label for="depositAmount">金额:<span id="depositAmountTip" style="color:  #E53935"></span></label> <input type="text" onblur="checkAmount(this,document.getElementById('depositAmountTip'))" id="depositAmount" class="required input_field" />
                                <div class="cleaner_h10"></div>

<!--                                <label for="email">Your Email:</label> <input type="text" name="email" class="validate-email required input_field" />-->
<!--                                <div class="cleaner_h10"></div>-->

                                <label for="text">Message:</label> <textarea id="text" name="text" rows="0" cols="0" class="required"></textarea>
                                <div class="cleaner_h10"></div>

<!--                                <input type="submit" class="submit_btn" name="submit" id="submit" value="Send" />-->
<!--                                <input type="reset" class="submit_btn" name="reset" id="reset" value="Reset" />-->
                                <input type="button" class="submit_btn" onclick="submitDeposit(document.getElementById('depositAmount'),document.getElementById('depositAmountTip'))" name="submit" value="Send" />
                                <input type="reset" class="submit_btn"  name="reset" value="Reset" />

                            </formp>
                        </div>
                    </div>

                    <div class="panel" id="transfer">
                        <h1>Transfer</h1>
                        <p>请输入您要转账的账户ID及金额</p>
                        <div class="contact_form">
                            <formp class="aform" method="post" name="contact">

                                <label for="transferUserAccount">转账给:<span id="transferUserAccountTip" style="color:  #E53935"></span></label> <input type="text" onblur="checkUser(this,document.getElementById('transferUserAccountTip'))" id="transferUserAccount" name="userid" class="required input_field" />
                                <div class="cleaner_h10"></div>

                                <label for="transferAmount">金额:<span id="transferAmountTip" style="color:  #E53935"></span></label> <input type="text" onblur="checkAmount(this,document.getElementById('transferAmountTip'))" id="transferAmount" name="email" class="validate-email required input_field" />
                                <div class="cleaner_h10"></div>

                                <label for="text">Message:</label> <textarea name="text" rows="0" cols="0" class="required"></textarea>
                                <div class="cleaner_h10"></div>

                                <input type="button" class="submit_btn" onclick="
                                submitTransfer(
                                    document.getElementById('transferUserAccount'),
                                    document.getElementById('transferUserAccountTip'),
                                    document.getElementById('transferAmount'),
                                    document.getElementById('transferAmountTip'))" name="submit" value="Send" />
                                <input type="reset" class="submit_btn" name="reset" value="Reset" />

                            </formp>
                        </div>

                    </div>

                    <div class="panel" id="log">
                        <h1>User logs</h1>
                        <div class="list_container">
                            <c:if test="${!empty sessionScope.logs}">
                                <c:forEach items="${sessionScope.logs}" var="log" varStatus="varStatus">
                                <div class="list_box">
                                    <img src="images/transfer/image_01.jpg" alt="${varStatus.count}" />
                                    <h4>操作类型：${log.logtype}</h4>
                                    <p>操作金额：${log.amount}</p>
                                    <p>操作时间：${log.logtime}</p>
<%--                                    <div class="btn_more"><a href="#">Visit <span>&raquo;</span></a></div>--%>
                                    <div class="cleaner"></div>
                                </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty sessionScope.logs}">
                            <div class="list_box">
                                <p>当前还没有操作记录！</p>
                            </div>
                            </c:if>
<%--                            <div class="list_box">--%>
<%--                                <img src="images/transfer/image_02.jpg" alt="02" />--%>
<%--                                <h4>Project No. 2</h4>--%>
<%--                                <p>Donec ac eros ac nunc blandit hendrerit. Vestibulum tincidunt, odio at ultricies sollicitudin, ante felis luctus justo, non venenatis quam mauris non tortor.</p>--%>
<%--                                <div class="btn_more"><a href="#">Visit <span>&raquo;</span></a></div>--%>
<%--                                <div class="cleaner"></div>--%>
<%--                            </div>--%>
<%--                            <div class="list_box">--%>
<%--                                <img src="images/transfer/image_03.jpg" alt="03" />--%>
<%--                                <h4>Project Three</h4>--%>
<%--                                <p> Mauris ligula tortor, congue laoreet rutrum eget, suscipit nec augue. In congue consectetur est, sit amet hendrerit velit adipiscing eget.</p>--%>
<%--                                <div class="btn_more"><a href="#">Visit <span>&raquo;</span></a></div>--%>
<%--                                <div class="cleaner"></div>--%>
<%--                            </div>--%>
<%--                            <div class="list_box">--%>
<%--                                <img src="images/transfer/image_04.jpg" alt="04" />--%>
<%--                                <h4>Project No. 4</h4>--%>
<%--                                <p> Curabitur iaculis, erat pharetra porttitor sollicitudin, turpis lectus placerat arcu, ac mattis eros sem ut metus. Nunc congue iaculis lectus in interdum.</p>--%>
<%--                                <div class="btn_more"><a href="#">Visit <span>&raquo;</span></a></div>--%>
<%--                                <div class="cleaner"></div>--%>
<%--                            </div>--%>
<%--                            <div class="list_box">--%>
<%--                                <img src="images/transfer/image_05.jpg" alt="05" />--%>
<%--                                <h4>Project Five</h4>--%>
<%--                                <p> Curabitur iaculis enim dolor. Sed quis augue ligula. Quisque aliquet egestas felis, in egestas turpis sodales et. In ac diam ut orci viverra bibendum. </p>--%>
<%--                                <div class="btn_more"><a href="#">Visit <span>&raquo;</span></a></div>--%>
<%--                                <div class="cleaner"></div>--%>
<%--                            </div>--%>
<%--                            <div class="list_box">--%>
<%--                                <img src="images/transfer/image_06.jpg" alt="06" />--%>
<%--                                <h4>Project No. 6</h4>--%>
<%--                                <p>Sed in viverra nulla. Duis rutrum vehicula ligula, non tempor nunc congue et. Nunc sit amet tortor nulla, ut eleifend enim sed condimentum tellus vestibulum in.</p>--%>
<%--                                <div class="btn_more"><a href="#">Visit <span>&raquo;</span></a></div>--%>
<%--                                <div class="cleaner"></div>--%>
<%--                            </div>--%>
                            <div class="cleaner"></div>
                        </div>
<!--                        <div id="contact_form">-->
<!--                            <form method="post" name="contact" action="#log">-->

<!--                                <label for="author">Your Name:</label> <input type="text" name="author" class="required input_field" />-->
<!--                                <div class="cleaner_h10"></div>-->

<!--                                <label for="email">Your Email:</label> <input type="text" name="email" class="validate-email required input_field" />-->
<!--                                <div class="cleaner_h10"></div>-->

<!--                                <label for="text">Message:</label> <textarea id="text" name="text" rows="0" cols="0" class="required"></textarea>-->
<!--                                <div class="cleaner_h10"></div>-->

<!--                                <input type="submit" class="submit_btn" name="submit" id="submit" value="Send" />-->
<!--                                <input type="reset" class="submit_btn" name="reset" id="reset" value="Reset" />-->

<!--                            </form>-->
<!--						</div>-->
                    </div>

                    <div class="panel" id="administration">
                        <h1>Administrator to manage</h1>
                        <div class="list_container">
                            <div class="list_box">
                                <img src="images/transfer/image_01.jpg" alt="01" />
                                <h4>Project One</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean nec ligula vitae ipsum blandit condimentum. Nam fringilla luctus mauris, non ornare turpis lobortin.</p>
                                <div class="btn_more"><a href="#">Visit <span>&raquo;</span></a></div>
                                <div class="cleaner"></div>
                            </div>
                            <div class="list_box">
                                <img src="images/transfer/image_02.jpg" alt="02" />
                                <h4>Project No. 2</h4>
                                <p>Donec ac eros ac nunc blandit hendrerit. Vestibulum tincidunt, odio at ultricies sollicitudin, ante felis luctus justo, non venenatis quam mauris non tortor.</p>
                                <div class="btn_more"><a href="#">Visit <span>&raquo;</span></a></div>
                                <div class="cleaner"></div>
                            </div>
                            <div class="list_box">
                                <img src="images/transfer/image_03.jpg" alt="03" />
                                <h4>Project Three</h4>
                                <p> Mauris ligula tortor, congue laoreet rutrum eget, suscipit nec augue. In congue consectetur est, sit amet hendrerit velit adipiscing eget.</p>
                                <div class="btn_more"><a href="#">Visit <span>&raquo;</span></a></div>
                                <div class="cleaner"></div>
                            </div>
                            <div class="list_box">
                                <img src="images/transfer/image_04.jpg" alt="04" />
                                <h4>Project No. 4</h4>
                                <p> Curabitur iaculis, erat pharetra porttitor sollicitudin, turpis lectus placerat arcu, ac mattis eros sem ut metus. Nunc congue iaculis lectus in interdum.</p>
                                <div class="btn_more"><a href="#">Visit <span>&raquo;</span></a></div>
                                <div class="cleaner"></div>
                            </div>
                            <div class="list_box">
                                <img src="images/transfer/image_05.jpg" alt="05" />
                                <h4>Project Five</h4>
                                <p> Curabitur iaculis enim dolor. Sed quis augue ligula. Quisque aliquet egestas felis, in egestas turpis sodales et. In ac diam ut orci viverra bibendum. </p>
                                <div class="btn_more"><a href="#">Visit <span>&raquo;</span></a></div>
                                <div class="cleaner"></div>
                            </div>
                            <div class="list_box">
                                <img src="images/transfer/image_06.jpg" alt="06" />
                                <h4>Project No. 6</h4>
                                <p>Sed in viverra nulla. Duis rutrum vehicula ligula, non tempor nunc congue et. Nunc sit amet tortor nulla, ut eleifend enim sed condimentum tellus vestibulum in.</p>
                                <div class="btn_more"><a href="#">Visit <span>&raquo;</span></a></div>
                                <div class="cleaner"></div>
                            </div>
                            <div class="cleaner"></div>
                        </div>
                    </div>
                
                </div>
			</div>
            
        <!-- end of scroll -->
        
        </div> <!-- end of content -->
        
        <div id="templatemo_footer">

            2019 BankSystem
        
        </div> <!-- end of templatemo_footer -->
    
    </div> <!-- end of main -->
</div>
<!-- templatemo 284 mini social -->
<!-- 
Mini Social Template 
http://www.templatemo.com/preview/templatemo_284_mini_social 
-->
</body>
</html>