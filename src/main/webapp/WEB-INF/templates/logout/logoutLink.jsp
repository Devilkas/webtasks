<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${CURRENT_SESSION_ACCOUNT ne null }">
	<a href="${CONTEXT }/logout.php" style="font-size: small;">Logout</a>
</c:if>
