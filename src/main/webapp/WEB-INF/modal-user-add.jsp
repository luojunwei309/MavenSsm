<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="useraddModal" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">尚筹网系统弹窗</h4>
			</div>
			<div class="modal-body">
				<form class="form-signin" role="form" id="userForm">
					<div class="panel panel-default">
					<div class="panel-heading">
						表单数据
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						   <p>${requestScope.exception.message }</p>
						   
							<div class="form-group">
								<label for="exampleInputPassword1">登录账号</label> <input
									name="loginAcct" type="text" class="form-control" id="exampleInputPassword1"
									placeholder="请输入登陆账号">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">登录密码</label> <input
									name="userPswd" type="password" class="form-control" id="exampleInputPassword1"
									placeholder="请输入登陆密码">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">用户昵称</label> <input
									name="userName" type="text" class="form-control" id="exampleInputPassword1"
									placeholder="请输入用户昵称">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">邮箱地址</label> <input type="email"
									name="email" class="form-control" id="exampleInputEmail1"
									placeholder="请输入邮箱地址">
								<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为：
									xxxx@xxxx.com</p>
							</div>

						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button id="saveUserBtn" type="button" class="btn btn-primary">
					保 存</button>
			</div>
		</div>
	</div>
</div>