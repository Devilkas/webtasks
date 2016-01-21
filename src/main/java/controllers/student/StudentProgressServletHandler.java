package controllers.student;

import controllers.AbstractWebtasksServletHandler;
import entity.Mark;
import entity.Student;
import entity.Term;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StudentProgressServletHandler extends
        AbstractWebtasksServletHandler {
	private static final long serialVersionUID = 9103095668248132471L;

	@Override
	protected void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int idStudent = Integer.parseInt(request.getParameter("id"));
		Student student = getDataService().getStudent(idStudent);
		List<Term> terms = getDataService().listTerm();

		String[] nameTerm = request.getParameterValues("chooser");
		Term term = null;
		List<Mark> marks = null;
		if (nameTerm == null) {
			int j = 1;
			while (term == null){
				try{
					term = getDataService().getTerm(j);	
					marks = getDataService().listMarks(idStudent, term);
				} catch (NullPointerException e){
					j++;
				}
			}
		} else {
			int id = 0;
			for (int i = 0; i < nameTerm.length; i++) {
				id = Integer.parseInt(nameTerm[i]);
			}
			term = getDataService().getTerm(id);
			marks = getDataService().listMarks(idStudent, term);
		}
		int summMarks = 0;
		int count = 0;
		for (Iterator<Mark> iter = marks.iterator(); iter.hasNext();) {
			count++;
			Integer value = iter.next().getValue();
			if (value != null)
				summMarks += value;
		}
		double middleValue = (double) summMarks / count;
		request.setAttribute("terms", terms);
		request.setAttribute("selectedTerm", term);
		request.setAttribute("student", student);
		request.setAttribute("marks", marks);
		request.setAttribute("middleValue", new DecimalFormat("#0.00").format(middleValue));
		gotoToJSP("main/student/studentsProgress.jsp", request, response);

	}

}
