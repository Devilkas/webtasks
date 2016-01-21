package controllers.discipline;

import constants.WebtasksConstants;
import controllers.AbstractWebtasksServletHandler;
import entity.Discipline;
import exeptions.InvalidDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DisciplineModifyingServletHandler extends
        AbstractWebtasksServletHandler {
	private static final long serialVersionUID = 2100408825310825179L;

	@Override
	protected void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			request.setAttribute(CURRENT_BUTTON, "2");
			int idDiscipline = Integer.parseInt(request.getParameter("id"));
			Discipline dcp = getDataService().getDiscipline(idDiscipline);
			request.setAttribute("discipline", dcp);
			gotoToJSP("main/discipline/disciplinesCreatingModifying.jsp",
					request, response);
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			try {
				validateRequestDiscipline(name);
				Discipline dcp = new Discipline(id, name);
				getDataService().updateDiscipline(dcp);
				String mapping = request.getSession()
						.getAttribute("CURRENT_MAPPING").toString();
				redirectRequest(mapping + "/disciplinesList.php", request, response);
			} catch (InvalidDataException e) {
				request.setAttribute(WebtasksConstants.VALIDATION_MESSAGE,
						e.getMessage());
				request.setAttribute(CURRENT_BUTTON, "2");
				gotoToJSP("main/discipline/disciplinesCreatingModifying.jsp",
						request, response);
			}
		}
	}
}