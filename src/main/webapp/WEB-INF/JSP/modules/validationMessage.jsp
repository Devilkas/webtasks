<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${VALIDATION_MESSAGE ne null }">
	<table id="table_for_error_message">
		<tr>
			<td class="error_message"><c:choose>
					<c:when test="${VALIDATION_MESSAGE eq 'lastName'}">
					Поле 'Фамилия' не заполнено
				</c:when>
					<c:when test="${VALIDATION_MESSAGE eq 'firstName'}">
					Поле 'Имя' не заполнено
				</c:when>
					<c:when test="${VALIDATION_MESSAGE eq 'group'}">
					Поле 'Группа' не заполнено
				</c:when>
					<c:when test="${VALIDATION_MESSAGE eq 'date'}">
					Поле 'Дата поступления' не заполнено
				</c:when>
					<c:when test="${VALIDATION_MESSAGE eq 'name'}">
					Поле 'Название' не заполнено
				</c:when>
					<c:when test="${VALIDATION_MESSAGE eq 'disciplines'}">
					Выберите дисциплины в семестре
				</c:when>
					<c:when test="${VALIDATION_MESSAGE eq 'duration'}">
					Поле 'Длительность' не заполнено
				</c:when>
					<c:when test="${VALIDATION_MESSAGE eq 'username'}">
					Поле 'Имя' не заполнено
				</c:when>
					<c:when test="${VALIDATION_MESSAGE eq 'password'}">
					Поле 'Пароль' не заполнено
				</c:when>
					<c:when test="${VALIDATION_MESSAGE eq 'role'}">
					Роль не утверждена
				</c:when>
					<c:when test="${VALIDATION_MESSAGE eq 'char'}">
					Не корректные символы в поле
						'Длительность'. Используйте только цифры.
				</c:when>
					<c:when test="${VALIDATION_MESSAGE eq 'invPass'}">
					Неверный пароль
				</c:when>
					<c:when test="${VALIDATION_MESSAGE eq 'notFound'}">
					Аккаунт не найден
				</c:when>
				<c:when test="${VALIDATION_MESSAGE eq 'no'}">
					Выберите студента и семестр!
				</c:when>
				</c:choose></td>
		</tr>
	</table>
</c:if>





