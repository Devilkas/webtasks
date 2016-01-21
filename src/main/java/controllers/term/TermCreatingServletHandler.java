package controllers.term;

import constants.WebtasksConstants;
import controllers.AbstractWebtasksServletHandler;
import entity.Discipline;
import entity.Term;
import exeptions.InvalidDataException;
import exeptions.WebtasksException;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TermCreatingServletHandler extends AbstractWebtasksServletHandler {
	private static final long serialVersionUID = 5885851480909281354L;

	@Override
	protected void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			gotoToJSPDisciplineCreate(request, response);
		} else {
			String durationStr = request.getParameter("duration");
			String[] dsp = request.getParameterValues("multi_list");
			try {
				validateRequestTerm(durationStr, dsp);
				Integer duration = null;
				try {
					duration = Integer.parseInt(durationStr);
				} catch (Exception e) {
					request.setAttribute(WebtasksConstants.VALIDATION_MESSAGE,
							"char");
					gotoToJSPDisciplineCreate(request, response);
				}
				Term term = new Term();
				term.setDuration(duration);
				getDataService().addTerm(term, dsp);
				
				String mapping = request.getSession()
						.getAttribute("CURRENT_MAPPING").toString();
				redirectRequest(mapping + "/termsList.php", request, response);
			} catch (InvalidDataException e) {
				request.setAttribute(WebtasksConstants.VALIDATION_MESSAGE,
						e.getMessage());
				gotoToJSPDisciplineCreate(request, response);
			}
		}

	}

	private void gotoToJSPDisciplineCreate(HttpServletRequest request,
			HttpServletResponse response) throws WebtasksException,
			ServletException, IOException {
		List<Discipline> disciplines = getDataService().listDisciplines();
		request.setAttribute(CURRENT_BUTTON, "1");
		request.setAttribute("disciplines", disciplines);
		gotoToJSP("main/term/termCreatingModifying.jsp", request, response);
	}

}
