<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="title_page_container">
	<table cellpadding="0">
		<tr>
			<td align="left"><a
				href="${CONTEXT}${CURRENT_MAPPING}/studentsList.php">Студенты</a></td>
			<td align="center"><a
				href="${CONTEXT }${CURRENT_MAPPING}/disciplinesList.php">Дисциплины</a></td>

			<c:if test="${CURRENT_ROLE eq 1}">
				<td align="center"><a href="${CONTEXT }${CURRENT_MAPPING}/marksList.php">Внести
						оценки</a></td>
			</c:if>

			<td align="right"><a href="${CONTEXT}${CURRENT_MAPPING}/termsList.php">Семестры</a></td>
		</tr>
	</table>
</div>