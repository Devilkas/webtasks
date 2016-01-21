<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="link_container">
	<table>
		<tr>
			<td><a href="${CONTEXT}${CURRENT_MAPPING}/home.php">На
					главную</a></td>
			<td><a href="${CONTEXT}${CURRENT_MAPPING}/termsList.php">Назад</a></td>
		</tr>
	</table>
</div>

<div class="terms_list_container">


	<div class="main_line">
		<c:choose>
			<c:when test="${CURRENT_BUTTON eq 1}">
      Для создания семестра заполните следующие данные и нажмите кнопку "Создать".
  </c:when>
			<c:otherwise>
     Для модификации семестра отредактируйте данные и нажмите кнопку "Применить".
  </c:otherwise>
		</c:choose>
	</div>

	<c:choose>
		<c:when test="${CURRENT_BUTTON eq 1}">
			<form action="${CONTEXT }${CURRENT_MAPPING}/termCreating.php"
				method="POST">
		</c:when>
		<c:otherwise>
			<form action="${CONTEXT }${CURRENT_MAPPING}/termModifying.php"
				method="POST">
		</c:otherwise>
	</c:choose>

	<input type="hidden" name="id" value="${term.id }" />

	<table class="under_main_table" style="font-family: cursive;">
		<tr>
			<td width="210px">Длительность (в неделях)</td>
			<td><input type="text" name="duration" class="text"
				value="${term.duration }" id="text" placeholder="24"></td>
		</tr>

		<tr>
			<td style="padding-top: 30px" valign="top">Дисциплины в семестре</td>
			<td style="padding-top: 30px">
				<table>
					<tr>
						<td style="padding: 0"><select multiple size="8"
							id="multi_list" multiple="multiple" name="multi_list">
								<c:forEach var="discipline" items="${disciplines }">
									<c:choose>
										<c:when test="${discipline.id  ne selectedDiscipline.id }">
											<option value="${discipline.id }">${discipline.name
												}</option>
										</c:when>
										<c:otherwise>
											<option selected="selected" value="${discipline.id }">${discipline.name
												}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td style="padding-top: 20px"><c:choose>
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

			</td>
		</tr>
	</table><jsp:include page="/WEB-INF/JSP/modules/validationMessage.jsp" />


</div>