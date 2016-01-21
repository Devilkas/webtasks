package controllers.term;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.WebtasksConstants;
import controllers.AbstractWebtasksServletHandler;
import exeptions.InvalidDataException;
import exeptions.WebtasksException;
import entity.Discipline;
import entity.Term;

public class TermModifyingServletHandler extends AbstractWebtasksServletHandler {
	private static final long serialVersionUID = 7493594322928057708L;

	@Override
	protected void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			gotoToJSPDisciplineModifying(request, response);
		} else {
			String durationStr = request.getParameter("duration");
			String[] dsp = request.getParameterValues("multi_list");
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				validateRequestTerm(durationStr, dsp);
				Integer duration = null;
				try {
					duration = Integer.parseInt(durationStr);
				} catch (Exception e) {
					request.setAttribute(WebtasksConstants.VALIDATION_MESSAGE,
							"char");
					gotoToJSPDisciplineModifying(request, response);
				}
				Term term = new Term();
				term.setId(id);
				term.setDuration(duration);
				getDataService().updateTerm(term, dsp);
				request.setAttribute("chooser", term.getId());
				String mapping = request.getSession()
						.getAttribute("CURRENT_MAPPING").toString();
				redirectRequest(mapping + "/termsList.php", request, response);
			} catch (InvalidDataException e) {
				request.setAttribute(WebtasksConstants.VALIDATION_MESSAGE,
						e.getMessage());
				gotoToJSPDisciplineModifying(request, response);
			}
		}

	}

	private void gotoToJSPDisciplineModifying(HttpServletRequest request,
			HttpServletResponse response) throws WebtasksException,
			ServletException, IOException {
		request.setAttribute(CURRENT_BUTTON, "2");
		int idTerm = Integer.parseInt(request.getParameter("id"));
		Term term = getDataService().getTerm(idTerm);
		request.setAttribute("term", term);

		List<Discipline> currentDisciplines = getDataService()
				.listTermDisciplines(idTerm);
		request.setAttribute("selectedDisciplines", currentDisciplines);

		List<Discipline> disciplines = getDataService().listDisciplines();
		request.setAttribute("disciplines", disciplines);

		gotoToJSP("main/term/termCreatingModifying.jsp", request, response);
	}

}
