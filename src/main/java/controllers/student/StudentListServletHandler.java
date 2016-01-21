package controllers.student;

import controllers.AbstractWebtasksServletHandler;
import entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StudentListServletHandler extends AbstractWebtasksServletHandler {
	private static final long serialVersionUID = -6062681278819210864L;

	@Override
	protected void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (request.getMethod().equals("GET")) {
			List<Student> result = getDataService().listStudent();
			request.setAttribute("students", result);
			gotoToJSP("main/student/studentsList.jsp", request, response);
		} else {
			String idStudentsStr = request.getParameter("ids");
			StringTokenizer st = new StringTokenizer(idStudentsStr, ",");
			List<Integer> idStudents = new ArrayList<Integer>();
			while (st.hasMoreTokens()) {
				Integer idStudent = Integer.parseInt(st.nextToken());
				idStudents.add(idStudent);
			}
			getDataService().deleteStudents(idStudents);
			String mapping = request.getSession()
					.getAttribute("CURRENT_MAPPING").toString();
			redirectRequest(mapping + "/studentsList.php", request, response);

		}
	}

}
