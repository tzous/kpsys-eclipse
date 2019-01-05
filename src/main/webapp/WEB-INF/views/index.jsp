<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>支行设备管理台账</title>
 <link rel="stylesheet" type="text/css" href="public/easyui/themes/default/easyui.css">
 <link rel="stylesheet" type="text/css" href="public/easyui/themes/icon.css">
 <script type="text/javascript" src="public/easyui/jquery.min.js"></script>
 <script type="text/javascript" src="public/easyui/jquery.easyui.min.js"></script>
 <script type="text/javascript" src="js/common.js"></script>
 <script type="text/javascript" src="js/index.js"></script>

</head>
<body style="border:none;visibility:visible;width: 100%;height: 100%;" onload="showTime();">
<div id="cc" class="easyui-layout" style="width:100%;height:100%;">
 <!-- 页面顶部top及菜单栏 -->
 <div region="north" style="height:110px;width: 100%;">
  <div class="header" style="background:#fff url('public/img/banner.jpg');">
   <div style="text-align: right; padding-right: 20px; padding-top: 20px; height:50px;width:50%;float:right;">
    <span style="color:#FDFDFD" id="loginuserInfo">欢迎你，${aduser.remarks}</span>
    <span style="color:#FDFDFD" id="loginuserArea">，</span>
    <span style="color:#FDFDFD" id="timeInfo"></span>
    <a href="login.do" style="color:#FDFDFD;text-decoration:none;">退出</a>
   </div>
   <div class="maintitle"  style="padding-top: 30px;padding-bottom:0px;font-size:28px;color:#FDFDFD; padding-left:5px;height:50px;">支行设备管理台账</div>
  </div>
  <div id="topmenu" class="topmenu" style="cursor:pointer;padding-top:5px;height:28px;background:url('public/img/maintop.png');color:#fff;font-size:14px;font-weight:bold;">
   &nbsp;&nbsp;&nbsp;&nbsp;
   <a href="javascript:addTab('首页','welcome.html')" >首&nbsp;&nbsp;页</a>
  </div>
 </div>
 <!-- 页面底部信息 -->
 <div region="south" style="height: 18px;" >
  <center>
  </center>
 </div>
 <!-- 左侧导航菜单 -->
 <div region="west"  title="导航菜单栏" style="width:180px;">
  <ul id="tt1" class="easyui-tree" data-options="animate:true,dnd:true"></ul>
 </div>
 <!-- 主显示区域选项卡界面 -->
 <div region="center">
  <div class="easyui-tabs" fit="true" id="tt">
   <div title="首页" data-options="closable:true">
    <iframe width='100%' height='100%'  id='iframe' name='iframe' frameborder='0' scrolling='auto'  src='welcome.html'></iframe>
   </div>
  </div>
 </div>
 <div id="dd"></div>
</div>
</body>
</html>