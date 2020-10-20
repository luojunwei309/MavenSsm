<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CH">

<%@ include file="/WEB-INF/include-head.jsp" %>
<link rel="stylesheet" href="css/pagination.css" />
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<link href="css/back/style.css" rel="stylesheet">
<script type="text/javascript">
   $(function(){
   });

</script>
<body>

	<%@ include file="/WEB-INF/include-nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/include-sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<ol class="breadcrumb">
			        <li><a href="admin/categoryGet.html">分类</a></li>
					<li class="active">产品</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					
		<div class="editPVDiv">
		
		<c:forEach items="${propertyName }" var="propertyName">
		   <form action="admin/PropertyADD/Edit.html">
				<div class="eachPV">
				<input type="hidden" name="cid" value="${cid }"/>
				<input type="hidden" name="pid" value="${pid }"/>
				<input type="hidden" name="ptid" value="${propertyName.id }"/>
					<span class="pvName">${propertyName.name}</span>
					<span class="pvValue"><input class="pvValue" name="value"  type="text" value=""></span>
						<button>添加/修改</button>
				</div>
				</form>
	   </c:forEach>
		
		</div>
		
		
		<div class="editPVDiv1">
		<c:if test="${empty propertyNamevalue }">
		
		</c:if>
	<c:forEach items="${propertyNamevalue }" var="propertyNamevalue">
	
				<div class="eachPV">
					<span class="pvName">${propertyNamevalue.name.name }</span>
					<span class="pvValue"><input class="pvValue" name="value" type="text" value="${propertyNamevalue.value }"></span>
				</div>
				
			
		</c:forEach>	

		</div>
		
				</div>
			</div>
		</div>
		
	</div>
</body>
</html>