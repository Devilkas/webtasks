<%@ page import="org.apache.commons.lang.exception.ExceptionUtils"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%
	String status = String.valueOf(request
			.getAttribute("javax.servlet.error.status_code"));

	String errorMessage = null;
	String fullStackTrace = null;
	Exception exception = null;

	if ("404".equals(status.trim()) || "true".equals(request.getAttribute("notfound"))) {
		errorMessage = " Page not found.";
	} else {
		errorMessage = String.valueOf(request
				.getAttribute("javax.servlet.error.message"));
		exception = (Exception) request
				.getAttribute("javax.servlet.error.exception");
		if (exception != null) {
			errorMessage = exception.getMessage();
			fullStackTrace = ExceptionUtils
					.getFullStackTrace(exception).replace('\t', ' ')
					.trim();
		}
	}
%>

<div class="data_container_for_not_found">
	Error<br /> Message :
	<%=errorMessage%>
	<br />
	<%
		if (fullStackTrace != null) {
	%>
	Full stack trace :
	<%=fullStackTrace%>
	<br />
	<%
		}
	%>
</div>