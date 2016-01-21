<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<head>
<meta charset="utf8" />
<meta name="description" content="My first demo jee application" />
<meta name="keywords" content="jee, java, web, demo, webtasks" />
<meta name="author" content="nata" />
<title>Demo web application</title>

<link rel="stylesheet" type="text/css"
	href="${CONTEXT }/resources/css/jquery-ui-1.10.4.custom.css" />
<link rel="stylesheet" type="text/css"
	href="${CONTEXT }/resources/css/webtasks.css" />
<script type="text/javascript"
	src="${CONTEXT }/resources/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${CONTEXT }/resources/js/webtasks.js"></script>
	<script type="text/javascript"
	src="${CONTEXT }/resources/js/jquery-ui-1.10.4.custom.js"></script>

<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : "dd/mm/yy"
		});
		$("#anim").change(function() {
			$("#datepicker").datepicker("option", "showAnim", $(this).val());
		});
	});

	var context = "${CONTEXT}";
</script>



</head>

<body>
	<header>
		<table>
			<tr>
				<td>Система управления студентами и их успеваемостью</td>
			</tr>
		</table>
		<div><jsp:include page="logout/logoutLink.jsp" flush="true" /></div>
	</header>
	<section>
		<jsp:include page="${currentPage}" flush="true" />
	</section>
</body>
</html>