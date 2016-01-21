package controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.WebtasksConstants;
import dao.DataService;
import dao.WebtasksServiceManager;

import exeptions.InvalidDataException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


public abstract class AbstractWebtasksServletHandler extends HttpServlet
		implements WebtasksConstants {
	private static final long serialVersionUID = 2616056221299712890L;
	protected final Logger LOGGER = Logger.getLogger(getClass());
	private String contextName;
	private DataService dataService;

	@Override
	public final void init(ServletConfig config) throws ServletException {
		contextName = config.getServletContext().getContextPath();
		dataService = WebtasksServiceManager.getInstance(
				config.getServletContext()).getDataService();
		super.init(config);
	}

	@Override
	protected final void service(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
	}

	@Override
	public final void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
	}

	@Override
	protected final void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest0(req, resp);
	}

	@Override
	protected final void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest0(req, resp);
	}

	@Override
	protected final void doDelete(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		handleRequest0(req, resp);
	}

	@Override
	protected final void doOptions(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		handleRequest0(req, resp);
	}

	@Override
	protected final void doHead(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest0(req, resp);
	}

	@Override
	protected final void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest0(req, resp);
	}

	@Override
	protected final void doTrace(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		handleRequest0(req, resp);
	}

	private void handleRequest0(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			preHandleRequest(request, response);
			handleRequest(request, response);
		} catch (Exception e) {
			LOGGER.error("Application can't fulfil this request", e);
			handleError(e, request, response);
		}
	}

	protected abstract void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	protected void preHandleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	}

	protected final DataService getDataService() {
		return dataService;
	}

	protected final String getContextName() {
		return contextName;
	}

	protected final void sendTextResponse(String message,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setContentLength(message.length());
		response.getWriter().write(message);
	}

	protected final void handleError(Exception ex, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("javax.servlet.error.exception", ex);
		gotoToJSP("error.jsp", request, response);
	}

	protected final void gotoToJSP(String page, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("currentPage", "../JSP/" + page);
		request.getRequestDispatcher("/WEB-INF/templates/page-template.jsp")
				.forward(request, response);
	}

	protected final void forwardRequest(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected final void redirectRequest(String path,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(contextName + path);
	}

	protected void validateRequestStudent(String lastName, String firstName,
			String group, String date) throws InvalidDataException {
		if (StringUtils.isBlank(lastName)) {
			throw new InvalidDataException("lastName");
		}
		if (StringUtils.isBlank(firstName)) {
			throw new InvalidDataException("firstName");
		}
		if (StringUtils.isBlank(group)) {
			throw new InvalidDataException("group");
		}
		if (StringUtils.isBlank(date)) {
			throw new InvalidDataException("date");
		}
	}

	protected void validateRequestDiscipline(String name)
			throws InvalidDataException {
		if (StringUtils.isBlank(name)) {
			throw new InvalidDataException("name");
		}
	}

	protected void validateRequestTerm(String duration, String[] disciplines)
			throws InvalidDataException {
		if (disciplines == null || disciplines.length == 0) {
			throw new InvalidDataException("disciplines");
		}

		if (StringUtils.isBlank(duration)) {
			throw new InvalidDataException("duration");
		}
	}
}
