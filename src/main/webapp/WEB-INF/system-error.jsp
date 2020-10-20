<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/" />
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/login.css">
	<script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function(){
        	$("button").click(function(){
        		//相当于浏览i按钮器
        		window.history.back();
        	});
        });
    </script>
	<style>

	</style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="index.html" style="font-size:32px;">LJW平台</a></div>
        </div>
      </div>
    </nav>

    <div class="container" class="btn btn-lg btn-success btn-block">
        <h2 class="form-signin-heading">
        <i class="glyphicon glyphicon-log-in">
        </i> 消息 </h2>
        <c:if test="${exception.message == null }">出错消息为空</c:if>
         <c:if test="${exception.message != null }">
        <h3 style="text-align: center;">${requestScope.exception.message }</h3></c:if>
        <button style="width: 150px;margin: 50px auto 0px auto;" class="btn btn-lg btn-success btn-block">点我返回上一步</button>
         <a href="admin/to/login/page.html" style="width: 150px;margin: 50px auto 0px auto;" class="btn btn-lg btn-success btn-block">回登陆页面</a>
        
    </div>
    	<div class="navbar-fixed-bottom" style="text-align: center;width: 100%;height: 30px;background: black;color: white;line-height: 30px;">
	  <a  href="http://beian.miit.gov.cn"> 粤ICP备2020097285号-1</a>
     </div>
  </body>
</html>