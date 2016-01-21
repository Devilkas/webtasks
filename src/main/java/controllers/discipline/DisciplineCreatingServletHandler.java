package controllers.discipline;

import constants.WebtasksConstants;
import controllers.AbstractWebtasksServletHandler;
import entity.Discipline;
import exeptions.InvalidDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class DisciplineCreatingServletHandler extends  AbstractWebtasksServletHandler {
	private static final long serialVersionUID = 3078933605369461510L;

	@Override
	protected void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			request.setAttribute(CURRENT_BUTTON, "1");
			gotoToJSP("main/discipline/disciplinesCreatingModifying.jsp",
					request, response);
		} else {
			String name = request.getParameter("name");
			try {
				validateRequestDiscipline(name);
				Discipline dcp = new Discipline(name);
				getDataService().addDiscipline(dcp);
				String mapping = request.getSession()
						.getAttribute("CURRENT_MAPPING").toString();
				redirectRequest( mapping + "/disciplinesList.php", request, response);

			} catch (InvalidDataException e) {
				request.setAttribute(WebtasksConstants.VALIDATION_MESSAGE,
						e.getMessage());
				request.setAttribute(CURRENT_BUTTON, "1");
				gotoToJSP("main/discipline/disciplinesCreatingModifying.jsp",
						request, response);
			}

		}

	}

}
