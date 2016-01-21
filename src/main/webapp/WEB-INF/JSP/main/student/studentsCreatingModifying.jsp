<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="link_container">
	<table>
		<tr>
			<td><a href="${CONTEXT}${CURRENT_MAPPING}/home.php">На
					главную</a></td>
			<td><a href="${CONTEXT}${CURRENT_MAPPING}/studentsList.php">Назад</a></td>
		</tr>
	</table>
</div>

<div class="student_creating_container ">

	<div class="main_line">
		<c:choose>
			<c:when test="${CURRENT_BUTTON eq 1}">
      Для создания студетна заполните все поля и нажмите кнопку "Создать".
  </c:when>
			<c:otherwise>
     Для модификации введите новые значения и нажмите кнопку "Применить".
  </c:otherwise>
		</c:choose>

	</div>

	<c:choose>
		<c:when test="${CURRENT_BUTTON eq 1}">
			<form action="${CONTEXT }${CURRENT_MAPPING}/studentsCreating.php"
				method="POST">
		</c:when>
		<c:otherwise>
			<form action="${CONTEXT }${CURRENT_MAPPING}/studentsModifying.php"
				method="POST">
		</c:otherwise>
	</c:choose>

	<input type="hidden" name="id" value="${student.id }" />
	
	<table cellpadding="4">

		<tr>
			<td width="115px" align="right">Фамилия</td>
			<td><input type="text" class="text" name="lastName"
				value="${student.lastName }"></td>
		</tr>

		<tr>
			<td align="right">Имя</td>
			<td><input type="text" class="text" name="firstName"
				value="${student.firstName }"></td>
		</tr>

		<tr>
			<td align="right">Группа</td>
			<td><input type="text" class="text" name="group"
				value="${student.group }"></td>
		</tr>

		<tr>
			<td align="right">Дата поступления</td>
			<td><input type="text" class="text" id="datepicker" name="date"
				value="${student.date }"></td>
		</tr>

		<tr>
			<td>&nbsp;</td>

			<td style="padding-left: 6px" height="80px"><c:choose>
					<c:when test="${CURRENT_BUTTON eq 1}">
						<input type="submit" value="Создать" id="button">
					</c:when>
					<c:otherwise>
						<input type="submit" value="Применить" id="button">
					</c:otherwise>
				</c:choose></td>
		</tr></form>

	</table>
	<jsp:include page="/WEB-INF/JSP/modules/validationMessage.jsp" />
	

</div>