package controllers.student;

import constants.WebtasksConstants;
import controllers.AbstractWebtasksServletHandler;
import entity.Student;
import exeptions.InvalidDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class StudentModifyingServletHandler extends
        AbstractWebtasksServletHandler {
	private static final long serialVersionUID = 6548530642766813146L;

	@Override
	protected void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {

			int idStudent = Integer.parseInt(request.getParameter("id"));
			gotoToJSPStudentsModifying(
					"main/student/studentsCreatingModifying.jsp", idStudent,
					request, response);
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			String lastName = request.getParameter("lastName");
			String firstName = request.getParameter("firstName");
			String group = request.getParameter("group");
			String dateString = request.getParameter("date");
			try {
				validateRequestStudent(lastName, firstName, group, dateString);
				Student std = new Student(id, firstName, lastName, group,
						dateString);
				getDataService().updateStudent(std);
				String mapping = request.getSession()
						.getAttribute("CURRENT_MAPPING").toString();
				redirectRequest(mapping + "/studentsList.php", request, response);

			} catch (InvalidDataException e) {
				request.setAttribute(WebtasksConstants.VALIDATION_MESSAGE,
						e.getMessage());
				int idStudent = Integer.parseInt(request.getParameter("id"));
				gotoToJSPStudentsModifying(
						"main/student/studentsCreatingModifying.jsp",
						idStudent, request, response);

			}

		}

	}

	private void gotoToJSPStudentsModifying(String string, int idStudent,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setAttribute(CURRENT_BUTTON, "2");
		Student student = getDataService().getStudent(idStudent);
		request.setAttribute("student", student);
		gotoToJSP("main/student/studentsCreatingModifying.jsp", request,
				response);
	}

}
