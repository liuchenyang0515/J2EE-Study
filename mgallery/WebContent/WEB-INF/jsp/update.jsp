<%@page contentType="text/html;charset=utf-8"%>
<!-- 修改油画页面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>作品更新</title>
<link rel="stylesheet" type="text/css" href="css\create.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/validation.js"></script>
<script type="text/javascript">
	<!-- 提交前表单校验 -->
	function checkSubmit(){
		var result = true;
		var r1 = checkEmpty("#pname","#errPname");
		var r2 = checkCategory('#category', '#errCategory');
		var r3 = checkPrice('#price', '#errPrice');
		var r4 = checkFile('#painting', '#errPainting');
		var r5 = checkEmpty('#description', '#errDescription');
		if (r1 && r2 && r3 && r4 && r5){
			return true;
		}else{
			return false;
		}
	}
	$(function() {
		// 后面的EL表达式${painting.category}在服务端渲染，而$("#category").val在前端
		$("#category").val(${painting.category});
	})
</script>
</head>
<body>
	<div class="container">
		<fieldset>
			<legend>作品名称</legend>
			<form action="[这里写更新URL]" method="post"
				autocomplete="off" enctype="multipart/form-data"
				onsubmit = "return checkSubmit()">
				<ul class="ulform">
					<li>
						<span>油画名称</span>
						<span id="errPname"></span>
						<input id="pname" name="pname" onblur="checkEmpty('#pname','#errPname')" value="${painting.pname }"/>
					</li>
					<li>
						<span>油画类型</span>
						<span id="errCategory"></span>
						<select id="category" name="category" onchange="checkCategory('#category','#errCategory')">
							<option value="-1">请选择油画类型</option>
							<option value="1">现实主义</option>
							<option value="2">抽象主义</option>
						</select>
					</li>
					<li>
						<span>油画价格</span>
						<span id="errPrice"></span>
						<input id="price" name="price" onblur="checkPrice('#price','#errPrice')" value="${painting.price }"/>
					</li>
					<li>
						<span>作品预览</span><span id="errPainting"></span><br/>
						<img id="preview" src="${painting.preview }" style="width:361px;height:240px"/><br/>
						<input id="painting" name="painting" type="file" style="padding-left:0px;" accept="image/*"/>
					</li>

					<li>
						<span>详细描述</span>
						<span id="errDescription"></span>
						<textarea
							id="description" name="description"
							onblur="checkEmpty('#description','#errDescription')"
							>${painting.description }</textarea>
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
