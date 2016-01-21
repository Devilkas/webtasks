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

<div class="disciplines_List_container">
	<div style="padding-left: 30px; font-size: larger; height: 60px">Список
		дисциплин</div>

	<table>
		<tr>
			<td style="padding-left: 30px; width: 430px;"><table
					cellspacing="0" cellpadding="3" id="small_font_100_380" border="1">
					<tr bgcolor="#DCDCDC" align="left" style="border-color: #8B8989;">
						<c:if test="${CURRENT_ROLE eq 1}">
							<th width="10%">&nbsp;</th>
						</c:if>
						<th width="320px">Наименование дисциплины</th>
					</tr>

					<c:forEach var="discipline" items="${disciplines }">
						<tr>
							<c:if test="${CURRENT_ROLE eq 1}">
								<td align="right"><input type="checkbox"
									id="${discipline.id }" onChange="toggle(this);"></td>
							</c:if>
							<td align="left">${discipline.name}</td>
						</tr>
					</c:forEach>

				</table></td>

			<td valign="top" align="left" style="padding: 0"><c:if
					test="${CURRENT_ROLE eq 1}">
					<table cellspacing="0">

						<tr>
							<td style="padding-bottom: 20px; padding-top: 0"><input
								type="submit" id="button_long_name" onclick="createDiscipline()"
								value="Создать дисциплину..."></td>
						</tr>

						<tr>
							<td style="padding-bottom: 20px"><input type="submit"
								id="button_long_name" onclick="modifyingDiscipline()"
								value="Модифицировать выбранную дисциплину..."></td>
						</tr>

						<tr>
							<td style="padding-bottom: 20px"><input type="submit"
								id="button_long_name" value="Удалить выбранную дисциплину..."
								onclick="deleteDiscipline()"></td>
						</tr>

					</table>
				</c:if></td>
		</tr>
	</table>

</div>