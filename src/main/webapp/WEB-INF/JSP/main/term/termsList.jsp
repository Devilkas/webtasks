<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	<form action="${CONTEXT }${CURRENT_MAPPING}/termsList.php" method="GET">
		<table cellspacing="0" style="margin-top: 20px">
			<tr>
				<td style="font-size: large;">Выбрать семестр</td>

				<td style="padding-left: 50px"><select id="opening_list"
					name="chooser"><c:forEach var="term" items="${terms }">
							<c:choose>
								<c:when test="${term.id  eq selectedTerm.id }">
									<option selected="selected" value="${term.id }">Семестр
										${term.name }</option>
								</c:when>
								<c:otherwise>
									<option value="${term.id }">Семестр ${term.name }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach></select></td>

				<td style="padding-left: 20px"><input type="submit"
					value="Выбрать" id="button"></td>
			</tr>
			</form>
			<tr height="80px" style="font-size: large; font-weight: bold;">
				<td colspan="3">Длительность семестра: ${selectedTerm.duration
					} недели</td>
			</tr>

			<tr style="font-size: large;">
				<td colspan="3">Список дисциплин семестра</td>
			</tr>
		</table>

		<table style="margin-top: 20px">
			<tr>
				<td style="padding: 0" width="450px"><table cellspacing="0"
						cellpadding="3" id="small_font_100_380" border="1px">
						<tr bgcolor="#DCDCDC" align="left" style="border-color: #8B8989;">
							<th>Наименование дисциплины</th>
						</tr>
						<c:forEach var="discipline" items="${disciplines }">
						<c:if test="${discipline ne null }">
							<tr>
								<td>${discipline.name }</td>
							</tr></c:if>
						</c:forEach>

					</table></td>

				<td align="left" valign="top" style="padding: 0"><c:if
						test="${CURRENT_ROLE eq 1}">
						<table cellspacing="0">
							<form action="${CONTEXT }${CURRENT_MAPPING}/termCreating.php"
								method="GET">
								<tr>
									<td style="padding-bottom: 20px; padding-top: 0"><input
										type="submit" id="button_long_name" value="Создать семестр..."></td>
								</tr>
							</form>
							
							
							<tr>
								<td style="padding-bottom: 20px"><input type="button" onclick="modifyingTerm()" id="button_long_name"
									value="Модифицировать текущий семестр..."></td>
							</tr>
							
							
							<tr>
								<td style="padding-bottom: 20px"><input type="button"
									id="button_long_name" value="Удалить текущий семестр..." onclick="deleteTerm()"></td>
							</tr>
						</table>
					</c:if></td>
		</table>
</div>