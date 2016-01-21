package controllers;

import entity.Account;
import entity.Role;
import exeptions.InvalidDataException;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginServletHandler extends AbstractWebtasksServletHandler {
	private static final long serialVersionUID = 4544737546336836686L;

	private final Map<Integer, String> mappings = new HashMap<Integer, String>();

	public LoginServletHandler() {
		mappings.put(ROLE_ADMIN, "/admin");
		mappings.put(ROLE_STUDENT, "/student");
	}

	@Override
	protected void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			showLoginPage(request, response);
		} else {
			loginHandler(request, response);
		}
	}

	protected void showLoginPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Role> roles = getDataService().listRoles();
		request.setAttribute("roles", roles);
		gotoToJSP("/login.jsp", request, response);
	}

	protected void validateRequest(String username, String password)
			throws InvalidDataException {
		if (StringUtils.isBlank(username)) {
			throw new InvalidDataException("username");
		}
		if (StringUtils.isBlank(password)) {
			throw new InvalidDataException("password");
		}
	}

	protected void loginHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Integer idRole = Integer.parseInt(request.getParameter("role"));
		try {
			validateRequest(username, password);
			Account a = getDataService().login(username, password, idRole);
			String mapping = mappings.get(idRole);
			if (mapping != null) {
				request.getSession().setAttribute(CURRENT_SESSION_ACCOUNT, a);
				request.getSession().setAttribute(CURRENT_ROLE, idRole);
				request.getSession().setAttribute(CURRENT_MAPPING, mapping);
				redirectRequest(mapping + "/home.php", request, response);
			} else {
				throw new InvalidDataException("role");
			}
		} catch (InvalidDataException e) {
			request.setAttribute(VALIDATION_MESSAGE,
					e.getMessage());
			gotoToJSP("login.jsp", request, response);
		}
	}
}
