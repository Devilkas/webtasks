package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ErrorServletHandler extends AbstractWebtasksServletHandler {
	private static final long serialVersionUID = 4544737546336836686L;

	@Override
	protected void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String notfound = request.getParameter("notfound");
		request.setAttribute("notfound", notfound);
		gotoToJSP("/error.jsp", request, response);
	}
}
