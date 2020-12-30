<%@page contentType="text/html;charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>油画列表</title>
<script src="js/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="js/sweetalert2.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css\list.css">
<script type="text/javascript">
	function showPreview(previewObj) {
		var preview = $(previewObj).attr("data-preview");
		var pname = $(previewObj).attr("data-pname");
		Swal.fire({
			title: pname,
			html:"<img src='" + preview + "' style='width:361px;height:240px'>",
			showCloseButton:true, // 右上角叉叉
			showConfirmButton: false
		})	
	}
	function del(delObj) {
		var preview = $(delObj).attr("data-preview");
		var pname = $(delObj).attr("data-pname");
		var id = $(delObj).attr("data-id");
		Swal.fire({
			title: pname,
			html:"<img src='" + preview + "' style='width:361px;height:240px'>",
			showCloseButton:true, // 右上角叉叉
			confirmButtonText: '确定',// 确定按钮的 文字
			showCancelButton: true, // 是否显示取消按钮
			cancelButtonText: "取消", // 取消按钮的 文字
		}).then(async (isConfirm) => {
	        //判断 是否 点击的 确定按钮
	        if (isConfirm.value) {
	        	try {
	        		let response = await fetch('/management?method=delete&id=' + id);
		        	let data = await response.json();
		        	console.log(data);
	        	} catch (error) {
	        		// 想测试触发error逻辑很简单，只要取消刷新，再次删除会报错该id的油画不存在，删除失败
	        		console.error(error);
	        	}
	        	// 最后注释：
	        	// 因为数据存在于XML中，上传的图片和XML配置在重启tomcat后就会消失。后续讲解数据库后有解决办法
	        	location.reload(true);
	        }
		});
	}

</script>
</head>
<body>
	<div class="container">
		<fieldset>
			<legend>油画列表</legend>
			<div style="height: 40px">
				<a href="/management?method=show_create" class="btn-button">新增</a>
			</div>
			<!-- 油画列表 -->
			<table cellspacing="0px">
				<thead>
					<tr style="width: 150px;">
						<th style="width: 100px">分类</th>
						<th style="width: 150px;">名称</th>
						<th style="width: 100px;">价格</th>
						<th style="width: 400px">描述</th>
						<th style="width: 100px">操作</th>
					</tr>
				</thead>
				<c:forEach items="${pageModel.pageData }" var="painting">
					<tr>
						<td>
							<c:choose>
								<c:when test="${painting.category==1 }">现实主义</c:when>
								<c:when test="${painting.category==2 }">抽象主义</c:when>
								<c:otherwise>未知的类型</c:otherwise>
							</c:choose>
						</td>
						<td>${painting.pname }</td>
						<td><fmt:formatNumber pattern="￥0.00" value="${painting.price}"></fmt:formatNumber></td>
						<td>${painting.description }</td>
						<td>
							<a class="oplink" data-preview="${painting.preview }" data-pname="${painting.pname }" href="javascript:void(0)" onclick="showPreview(this)">预览</a>
							<a class="oplink" href="/management?method=show_update&id=${painting.id }">修改</a>
							<a class="oplink" href="javascript:void(0)" data-id="${painting.id }" data-pname="${painting.pname }" 
									data-preview="${painting.preview }" onclick="del(this)">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<!-- 分页组件 -->
			<ul class="page">
				<li><a href="/management?method=list&p=1">首页</a></li>
				<li><a href="/management?method=list&p=${pageModel.hasPreviousPage?pageModel.page-1:1 }">上页</a></li>
				<!-- c不存在，则href="/page?p=1" -->
				<!-- c存在，则href="/page?p=1&c=1" -->
				<c:forEach begin="1" end="${pageModel.totalPages }" var="pno" step="1">
					
					<a href="/management?method=list&p=${pno}"><li ${pno == pageModel.page?"class='active'":""}>
					${pno }</li>
					</a>
				</c:forEach>
				<!-- <li class='active'><a href="#">1</a></li>
				<li ><a href="#">2</a></li> -->
				<li><a href="/management?method=list&p=${pageModel.hasNextPage?pageModel.page+1:pageModel.totalPages }">下页</a></li>
				<li><a href="/management?method=list&p=${pageModel.totalPages}">尾页</a></li>
			</ul>
		</fieldset>
	</div>

</body>
</html>
