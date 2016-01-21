package controllers.discipline;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.AbstractWebtasksServletHandler;
import entity.Discipline;


public class DisciplinesListServletHandler extends
		AbstractWebtasksServletHandler {
	private static final long serialVersionUID = -5434763642087569107L;

	@Override
	protected void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			List<Discipline> result = getDataService().listDisciplines();
			request.setAttribute("disciplines", result);
			gotoToJSP("main/discipline/disciplinesList.jsp", request, response);
		} else {
			Integer idDiscipline = Integer.parseInt(request.getParameter("id"));
			getDataService().deleteDiscipline(idDiscipline);
			List<Discipline> result = getDataService().listDisciplines();
			request.setAttribute("disciplines", result);
			gotoToJSP("main/discipline/disciplinesList.jsp", request, response);

		}
	}

}
