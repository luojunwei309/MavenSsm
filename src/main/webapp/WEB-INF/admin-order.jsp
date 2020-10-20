<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CH">

<%@ include file="/WEB-INF/include-head.jsp"%>
<link rel="stylesheet" href="css/pagination.css" />
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<link href="css/back/style.css" rel="stylesheet">
<script>
$(function() {
	initPageination();
	$("button.orderPageCheckOrderItems").click(function() {
		var oid = $(this).attr("oid");
		$("tr.orderPageOrderItemTR[oid=" + oid + "]").toggle();
	});
});

//生成页码的导航条函数
function initPageination(){
	// 获取总记录数
	var totalRecord=${pageInfo.total};
	// 声明一个JSON对象存储Pagination的属性
	var propeties={
		num_edge_entries: 3,								// 边缘页数
		num_display_entries: 5,								// 主体页数
		callback: pageSelectCallback,						// 指定用户点击“翻页”的按钮时跳转页面的回调函数
		load_first_page: false,
		current_page: ${requestScope.pageInfo.pageNum - 1},	// Pagination内部使用pageIndex来管理页码，pageIndex从0开始，pageNum从1开始，所以要减一
		prev_text: "上一页",									// 上一页按钮上显示的文本
		next_text: "下一页",									// 下一页按钮上显示的文本
		items_per_page: ${requestScope.pageInfo.pageSize}	// 每页要显示的数据的数量
	
	};
	// 生成页码单行条
	$("#Pagination").pagination(totalRecord,propeties);
}

// 页码单击函数
function pageSelectCallback(pageIndex,JQuery){
	var pageNum=pageIndex+1;
	window.location.href="admin/orderGet.html?pageNum="+pageNum;
return false;
}
</script>
<body>

	<%@ include file="/WEB-INF/include-nav.jsp"%>
<div class="container-fluid">
	<div class="row">
		<%@ include file="/WEB-INF/include-sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li class="active">分类</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>

<br>
			<div class="workingArea">
				<br> <br>

				<div class="listDataTableDiv">
					<table
						class="table table-striped table-bordered table-hover1  table-condensed">
						<thead>
							<tr class="success">
							    <th>ID</th>
								<th>订单号</th>
								<th>状态</th>
								<th>金额</th>
								<th>商品数量</th>
								<th>买家名称</th>
								<th>创建时间</th>
								<th>支付时间</th>
								<th>发货时间</th>
								<th>确认收货时间</th>
								<th width="120px">操作</th>
							</tr>
						</thead>
						<tbody>
						
						<c:forEach items="${pageInfo.list }" var="order" varStatus="a">
							<tr>
							    <td>${order.order.id}</td>
								<td>${order.order.orderCode }</td>
								<c:if test="${order.order.status == 'waitPay'}">
								<td>待付款</td>
								</c:if>
								<c:if test="${order.order.status == 'waitDelivery'}">
								<td>待发货</td>
								</c:if>
								<c:if test="${order.order.status == 'waitConfirm'}">
								<td>待收货</td>
								</c:if>
								<c:if test="${order.order.status == 'waitReview'}">
								<td>待评论</td>
								</c:if>
								<c:if test="${order.order.status == 'toCar'}">
								<td>购物车</td>
								</c:if>
								
                                <td>￥ <fmt:formatNumber value="${order.product.promotePrice * order.number }" pattern="##.##" /></td>
								<td align="center">${order.number }</td>
								<td align="center">${order.user.name }</td>

								<td>${order.order.createDate }</td>
								<td>${order.order.payDate }</td>
								<td>${order.order.deliveryDate }</td>
								<td>${order.order.confirmDate }</td>
								<td>
									<button oid="${order.id }"
										class="orderPageCheckOrderItems btn btn-primary btn-xs">查看详情</button>
										
								   <c:if test="${order.order.status == 'waitDelivery'}">
								   <a href="order/fahuo.html?oid=${order.pid }&pageNum=${pageInfo.pageNum }">
								   <button oid="${order.id }"
										class=" btn btn-primary btn-xs">发货</button></a>
										</c:if>
								</td>
							</tr>
								<tr class="orderPageOrderItemTR" oid="${order.id }">
								<td colspan="11" align="center">

									<div class="orderPageOrderItem">
										<table width="800px" align="center"
											class="orderPageOrderItemTable">

											<tr>
												<td align="left"><img width="40px" height="40px"
													src="${order.product.pictName }"></td>
												<td><a href="http://123.57.141.69:3000/to/Product/get?pid=${order.pid }"> 
												<span>${order.product.name }</span>
												</a></td>
												<td align="right"><span class="text-muted">${order.number }个</span>
												</td>
												<td align="right"><span class="text-muted">单价：￥${order.product. promotePrice}</span>
												</td>
											</tr>

										</table>
									</div>
								</td>
							</tr>
							</c:forEach>
							<tfoot>
									<tr>
										<td colspan="11" align="center">
											<div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
										</td>
									</tr>
								</tfoot>

					</table>
				</div>


				</tbody>
			</div>




				</div>
			</div>
		</div>

	</div>
</body>
</html>