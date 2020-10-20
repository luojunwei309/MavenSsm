<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CH">
<%@include file="include-head.jsp"%>
<link rel="stylesheet" href="ztree/zTreeStyle.css" />
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="crowd/my-menu.js"></script>
<script type="text/javascript">
     $(function(){
    	 generateTree();
     });
</script>
<body>
	<%@include file="include-nav.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<%@include file="include-sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						<ul id="treeDemo" class="ztree">
						
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>	
	<div class="navbar-fixed-bottom" style="text-align: center;width: 100%;height: 30px;background: black;color: white;line-height: 30px;">
	  <a  href="http://beian.miit.gov.cn"> 粤ICP备2020097285号-1</a>
     </div>
<%@ include file="/WEB-INF/modal-menu-add.jsp" %>
<%@ include file="/WEB-INF/modal-menu-confirm.jsp" %>
<%@ include file="/WEB-INF/modal-menu-edit.jsp" %>
</body>
</html>