<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<script type="text/javascript">
	setTimeout(function() {
		$('#box').fadeOut('fast');
	}, 2000);
</script>

<c:if test="${VALIDATION_MESSAGE ne null }">
	<div id="box">Оценки успешно добавлены</div>
</c:if>