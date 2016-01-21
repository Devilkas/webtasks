package controllers.term;

import controllers.AbstractWebtasksServletHandler;
import entity.Discipline;
import entity.Term;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class TermsListServletHandler extends AbstractWebtasksServletHandler {
	private static final long serialVersionUID = -7254464491940189424L;

	protected void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			String[] name = request.getParameterValues("chooser");
			Term term = null;
			List<Discipline> disciplines;
			if (name == null) {
				int j = 1;
				while (term == null) {
					term = getDataService().getTerm(j);
					j++;
				}
				disciplines = getDataService().listTermDisciplines(j - 1);
			} else {
				int id = 0;
				for (int i = 0; i < name.length; i++) {
					id = Integer.parseInt(name[i]);
				}
				term = getDataService().getTerm(id);
				disciplines = getDataService()
						.listTermDisciplines(term.getId());
			}

			List<Term> tems = getDataService().listTerm();
			request.setAttribute("terms", tems);
			request.setAttribute("selectedTerm", term);
			request.setAttribute("disciplines", disciplines);
			gotoToJSP("main/term/termsList.jsp", request, response);
		} else {
			Integer idTerm = Integer.parseInt(request.getParameter("id"));
			getDataService().deleteTerm(idTerm);
			String mapping = request.getSession()
					.getAttribute("CURRENT_MAPPING").toString();
			redirectRequest(mapping + "/termsList.php", request, response);
		}
	}

}
