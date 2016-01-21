package controllers.mark;

import constants.WebtasksConstants;
import controllers.AbstractWebtasksServletHandler;
import entity.Discipline;
import entity.Student;
import entity.Term;
import exeptions.WebtasksException;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class MarkListServletHandler extends AbstractWebtasksServletHandler {
	private static final long serialVersionUID = -7953655218521234979L;

	protected void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			String[] idStudStr = request.getParameterValues("chooserStd");
			String[] idTermStr = request.getParameterValues("chooserTerm");
			Student std = null;
			Term term = null;
			
			if (idStudStr == null) {
				int i = 0;
				while (std == null) {
					i++;
					std = getDataService().getStudent(i);
				}
			}
			if (idTermStr == null) {
				int i = 0;
				while (term == null) {
					i++;
					term = getDataService().getTerm(i);
				}
			} else {
				int idStd = 0;
				for (int i = 0; i < idStudStr.length; i++) {
					idStd = Integer.parseInt(idStudStr[i]);
				}
				std = getDataService().getStudent(idStd);

				int idTerm = 0;
				for (int i = 0; i < idTermStr.length; i++) {
					idTerm = Integer.parseInt(idTermStr[i]);
				}
				term = getDataService().getTerm(idTerm);
			}
			gotoToJSPMarkList("main/mark/marksList.jsp", request, response,
					std, term);
		} else {
			int idStudent = Integer.parseInt(request.getParameter("idS"));
			int idTerm = Integer.parseInt(request.getParameter("idT"));
			Term term = getDataService().getTerm(idTerm);
			List<Discipline> disciplines = getDataService()
					.listTermDisciplines(term.getId());
			for (Iterator<Discipline> iter = disciplines.iterator(); iter
					.hasNext();) {
				Discipline d = iter.next();
				if (d != null) {
					String marks = request.getParameter(d.getId().toString());
					getDataService().addMark(idStudent, idTerm, d.getId(),
							marks);
				}

			}
			request.setAttribute(WebtasksConstants.VALIDATION_MESSAGE, "yes");
			Student selectedStudent = getDataService().getStudent(idStudent);
			Term selectedTerm = getDataService().getTerm(idTerm);
			gotoToJSPMarkList("main/mark/marksList.jsp", request, response,
					selectedStudent, selectedTerm);
		}
	}

	private void gotoToJSPMarkList(String string, HttpServletRequest request,
			HttpServletResponse response, Student selectedStudent,
			Term selectedTerm) throws WebtasksException, ServletException,
			IOException {
		List<Student> students = getDataService().listStudent();
		List<Term> terms = getDataService().listTerm();
		request.setAttribute("selectedStudent", selectedStudent);
		request.setAttribute("students", students);
		request.setAttribute("selectedTerm", selectedTerm);
		request.setAttribute("terms", terms);
		List<Discipline> disciplines = getDataService().listTermDisciplines(
				selectedTerm.getId());
		request.setAttribute("disciplines", disciplines);
		gotoToJSP("main/mark/marksList.jsp", request, response);
	}

}
