<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="link_container">
	<table>
		<tr>
			<td><a href="${CONTEXT}${CURRENT_MAPPING}/home.php">На
					главную</a></td>
			<td><a href="${CONTEXT}${CURRENT_MAPPING}/disciplinesList.php">Назад</a></td>
		</tr>
	</table>
</div>

<div class="student_creating_container ">

	<div class="main_line">
		<c:choose>
			<c:when test="${CURRENT_BUTTON eq 1}">
      Для того что создать новую дисциплину заполните все поля и нажмите кнопку "Создать".
  </c:when>
			<c:otherwise>
     Для того чтобы модифицировать дисциплину введите новое значение и нажмите кнопку "Применить".
  </c:otherwise>
		</c:choose>
	</div>


	<c:choose>
		<c:when test="${CURRENT_BUTTON eq 1}">
			<form action="${CONTEXT }${CURRENT_MAPPING}/disciplinesCreating.php"
				method="POST">
		</c:when>
		<c:otherwise>
			<form action="${CONTEXT }${CURRENT_MAPPING}/disciplinesModifying.php"
				method="POST">
		</c:otherwise>
	</c:choose>
	
	<input type="hidden" name="id" value="${discipline.id }" />

	<table style="line-height: 50px">
		<tr>
			<td width="60px">Название</td>
			<td style="padding-left: 30px"><input type="text" name="name"
				maxlength="50" size="35" class="text"  value="${discipline.name }"></td>
		</tr>


		<tr>
			<td></td>
			<td style="padding-left: 32px" height="50px"><c:choose>
					<c:when test="${CURRENT_BUTTON eq 1}">
						<input type="submit" value="Создать" id="button">
					</c:when>
					<c:otherwise>
						<input type="submit" value="Применить" id="button">
					</c:otherwise>
				</c:choose></td>
		</tr>

	</table>
	</form>
	<jsp:include page="/WEB-INF/JSP/modules/validationMessage.jsp" />

</div>