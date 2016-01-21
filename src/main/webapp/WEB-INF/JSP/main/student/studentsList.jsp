<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="link_container">
	<table>
		<tr>
			<td><a href="${CONTEXT}${CURRENT_MAPPING}/home.php">На
					главную</a></td>
		</tr>
	</table>
</div>

<div class="student_list_container">

	<table>
		<tr>
			<td><input type="button" id="button_long_name" name="progress"
				value="Просмотреть успеваемость выбранного студента" onclick="progressStudents()"></td>
			<c:if test="${CURRENT_ROLE eq 1}">
				<form action="${CONTEXT }${CURRENT_MAPPING}/studentsCreating.php"
					method="GET">
					<td style="padding-left: 20px"><input type="submit"
						value="Создать студента..." id="button_short_name" name="creating"></td>
				</form>
		</tr>
		<tr>
			<td style="padding-top: 20px"><input type="submit"
				value="Модифицировать выбранного студетна..." id="button_long_name"
				name="modifying" onclick="modifyingStudent()"></td>

			<td style="padding-left: 20px; padding-top: 20px"><input
				type="button" onclick="deleteStudents()" name="delete"
				value="Удалить выбранных студентов" id="button_short_name"></td>
		</tr>
		</c:if>
	</table>
	<table style="margin-top: 30px" width="100%">
		<tr>
			<td align="left" style="font-size: large;">Список студентов</td>
		</tr>
		<tr>
			<td><table border="2" cellspacing="0" cellpadding="4"
					id="student_list_table">
					<tr bgcolor="#DCDCDC" valign="bottom" align="left"
						style="border-color: #838B83">
						<th width="10%" height="100px">&nbsp;</th>
						<th width="30%" style="padding-bottom: 20px">Фамилия</th>
						<th width="30%" style="padding-bottom: 20px">Имя</th>
						<th width="15%" style="padding-bottom: 20px">Группа</th>
						<th width="15%" style="padding-bottom: 20px">Дата поступления</th>
					</tr>
					<c:forEach var="student" items="${students }">
						<tr valign="bottom">
							<td height="80px" style="padding-bottom: 20px" align="center"><input
								type="checkbox" id="${student.id }"></td>
							<td style="padding-bottom: 20px">${student.lastName }</td>
							<td style="padding-bottom: 20px">${student.firstName }</td>
							<td style="padding-bottom: 20px">${student.group }</td>
							<td style="padding-bottom: 20px">${ student.date }</td>
						</tr>
					</c:forEach>


				</table></td>
		</tr>
	</table>

</div>