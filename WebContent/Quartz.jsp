<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스케줄러</title>
	<script src="/quartz/lib/jQuery/jquery-3.4.1.min.js"></script>
	<script src="/quartz/lib/jQuery/jquery-3.4.1.js"></script>
	<script src="/quartz/lib/BootStrap/js/bootstrap.min.js"></script>
	<script src="/quartz/js/Quartz.js"></script>
	<link rel="stylesheet" href="/quartz/lib/BootStrap/css/bootstrap.css">
	<link rel="stylesheet" href="/quartz/lib/BootStrap/css/bootstrap-theme.css">
</head>
<body>
	<h1 class = "text-center">
		스케줄러 상태 페이지
	</h1>
	<h2>● 스케줄러 상태 제어</h2>
	<div>
	<table>
	<tr>
		<td><button type="button" class ="btn btn-info" id = "RunBtn1">Run1</button></td>
		<td><button type="button" class ="btn btn-info" id = "StopBtn1">Stop1</button></td>
	</tr>
	<tr>
		<td><button type="button" class ="btn btn-info" id = "RunBtn2">Run2</button></td>
		<td><button type="button" class ="btn btn-info" id = "StopBtn2">Stop2</button></td>
	 </tr>
	 </table>
	 
	 </div>
		<h2>● 실행 목록 </h2>
		 <div id='quartzList'></div>
	 <script>
	 
	 </script>
</body>
</html>