<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%String path = request.getContextPath();%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
		var ctx = '${path}';
</script>
		