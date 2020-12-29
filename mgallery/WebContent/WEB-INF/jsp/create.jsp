<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 新增油画页面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>新增油画</title>
<link rel="stylesheet" type="text/css" href="css\create.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/validation.js"></script>
<script>
	function checkSubmit() {
		let result = true;
		let r1 = checkEmpty('#pname', '#errPname'); // 对名称的校验
		let r2 = checkCategory('#category', '#errCategory'); // 对类别校验
		let r3 = checkPrice('#price', '#errPrice'); // 对价格的校验
		let r4 = checkFile('#painting', '#errPainting'); // 对上传文件的校验
		let r5 = checkEmpty('#description', '#errDescription'); // 对描述文本框的校验
		if (r1 && r2 && r3 && r4 && r5) {
			return true;
		} else {
			return false;
		}
	}
</script>
</head>
<body>
	<div class="container">
		<fieldset>
			<legend>新增油画</legend>
			<form action="/management?method=create" method="post"
				autocomplete="off" enctype="multipart/form-data" onsubmit="return checkSubmit()">
				<!-- form的onsubmit对提交监听，return true表示允许提交，false会拦截此次提交，所有不满足条件的校验都会提示错误信息 -->
				<ul class="ulform">
					<li>
						<span>油画名称</span>
						<span id="errPname"></span>
						<input id="pname" name="pname" onblur="checkEmpty('#pname', '#errPname')"/>
					</li>
					<li>
						<span>油画类型</span>
						<span id="errCategory"></span>
						<select id="category" name="category" onchange="checkCategory('#category', '#errCategory')">
							<option value="-1">请选择油画类型</option>
							<option value="1">现实主义</option>
							<option value="2">抽象主义</option>
						</select>
					</li>
					<li>
						<span>油画价格</span>
						<span id="errPrice"></span>
						<input id="price" name="price" onblur="checkPrice('#price', '#errPrice')"/>
					</li>
					<li>
						<span>作品预览</span>
						<span id="errPainting"></span>
						<input id="painting" name="painting" type="file" 
							style="padding-left: 0px;" accept="image/*" onchange="checkFile('#painting', '#errPainting')"/>
					</li>

					<li>
						<span>详细描述</span>
						<span id="errDescription"></span>
						<textarea
							id="description" name="description"
							onblur="checkEmpty('#description', '#errDescription')"></textarea>
					</li>
					<li style="text-align: center;">
						<button type="submit" class="btn-button">提交表单</button>
					</li>
				</ul>
			</form>
		</fieldset>
	</div>

</body>
</html>
