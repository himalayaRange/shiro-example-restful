<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/tag.jsp"%>
<!DOCTYPE html>
<html>
   <head>
     <title>用户登录</title>
     <%@include file="/WEB-INF/views/jsp/common/head.jsp"%>
     <link rel="shortcut icon" href="${ctx}/front/spring/favicon.ico"/>
     <style type="text/css">
     	.login{
     		margin-top: 120px;
     		margin-left: 40%
     	}
     </style>
   </head>
<body>
	<form id="login_form" accept-charset="utf-8"   method="post" class="login">
		<fieldset>
			<div>
				<label>用户名：</label>
				<input type="text" name="username" id="username" autocomplete="off" placeholder="用户名" />
			</div>
			<div>
				<label>密码：</label>
				<input type="password" name="password" id="password" autocomplete="off" placeholder="密码"/>
			</div>
			<div>
				<input onclick="login()" type="button" value="登录"/>
			</div>
		</fieldset>
	</form>
	<script type="text/javascript" src="${ctx}/static/js/jquery/jquery-1.11.1.js"></script>
	<script type="text/javascript">
		function login(){
			$.ajax({
				url:'${ctx}/shiroAuth',
				type:'POST',
				data:$("#login_form").serialize(),
				dataType:'json',
				success:function(data){
					if(data.success){
						window.location.href="${ctx}";
					}else {
						alert(data.emsg);
					}
				},
				error:function(data){
				}
			});
		}
	</script>
</body>
</html>