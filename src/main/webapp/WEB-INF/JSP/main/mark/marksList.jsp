<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type='text/javascript'
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js'></script>

<div class="link_container">
	<table>
		<tr>
			<td><a href="${CONTEXT}${CURRENT_MAPPING}/home.php">На
					главную</a></td>
		</tr>
	</table>

</div>
<div class="terms_list_container">
	<c:if test=""></c:if>
	<form action="${CONTEXT }${CURRENT_MAPPING}/marksList.php" method="get">
		<table cellspacing="0" style="margin-top: 20px">
			<tr>
				<td style="font-size: large;">Выбрать студента</td>

				<td style="padding-left: 50px"><select id="opening_list"
					name="chooserStd"><c:forEach var="student"
							items="${students }">
							<c:choose>
								<c:when test="${student.id  eq selectedStudent.id }">
									<option selected="selected" value="${student.id }">
										${student.lastName} ${student.firstName }</option>
								</c:when>
								<c:otherwise>
									<option value="${student.id }">${student.lastName}
										${student.firstName }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach></select></td>
			</tr>

			<tr>
				<td style="font-size: large;">Выбрать семестр</td>

				<td style="padding-left: 50px; line-height: 50px"><select
					id="opening_list" name="chooserTerm"><c:forEach var="term"
							items="${terms }">
							<c:choose>
								<c:when test="${term.id  eq selectedTerm.id }">
									<option selected="selected" value="${term.id }">Семестр
										${term.id}</option>
								</c:when>
								<c:otherwise>
									<option value="${term.id }">Семестр ${term.id}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach></select></td>
			</tr>
			<tr>
				<td></td>
				<td style="padding-left: 52px; line-height: 50px"><input
					type="submit" value="Выбрать" id="button"></td>
			</tr>

			<tr style="font-size: large;">
				<td colspan="2"><h4>Список дисциплин семестра</h4>
					<p>(Если раннее оценки были внесены, они будут обновлены)</td>
			</tr>
		</table>
	</form>

	<table style="margin-top: 20px">
		<form action="${CONTEXT }
			${CURRENT_MAPPING}/marksList.php"
			method="post">

			<input type="hidden" name="idS" value="${selectedStudent.id }" /> <input
				type="hidden" name="idT" value="${selectedTerm.id }" />
			<tr>
				<td style="padding: 0" width="600px"><table cellspacing="0"
						cellpadding="3" id="small_font_80_600" border="1px">
						<tr bgcolor="#DCDCDC" align="left" style="border-color: #8B8989;">
							<th colspan="2">Наименование дисциплины</th>
						</tr>
						<c:forEach var="discipline" items="${disciplines }">

							<tr>
								<td>${discipline.name }</td>
								<td align="center"><input type="number" min="0" max="5"
									step="1" name="${discipline.id }" value="5" id="number"></td>
							</tr>
						</c:forEach>

					</table></td>
			<tr>
				<td colspan="2" align="right" style="padding-top: 20px"><input
					type="submit" value="Применить" id="button_marks"></td>

			</tr>
		</form>
	</table>
	<jsp:include page="/WEB-INF/JSP/modules/validationMessage.jsp" />
	<jsp:include page="/WEB-INF/JSP/modules/successfulMessage.jsp" /></div>


