package filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.WebtasksConstants;
import entity.Account;


public class SessionDemoFilter extends AbstractWebtasksFilter implements
		WebtasksConstants {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Account currentSession = (Account) request.getSession().getAttribute(
				CURRENT_SESSION_ACCOUNT);

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		String context = req.getContextPath();
		
		if (request.getRequestURI().endsWith(".css")) {
		    chain.doFilter(request, response);
		    return;
		}
		if (uri.endsWith("/logout.php") || uri.endsWith(context)
				|| uri.endsWith("/error.php") || uri.endsWith("/login.php")
				|| uri.endsWith("/pageNotFound.php")) {
			chain.doFilter(request, response);
		}

		else if (currentSession != null) {
			String currentRole = request.getSession()
					.getAttribute(CURRENT_ROLE).toString();
			if (currentRole.equals("1") && (uri.indexOf("/student/") == -1)) {
				chain.doFilter(request, response);

			} else if (currentRole.equals("2")
					&& (uri.indexOf("/admin/") == -1)) {
				chain.doFilter(request, response);
			}else{
				
				res.sendRedirect(context + "/error.php?notfound=true");
			}
		} else {
			res.sendRedirect(context + "/login.php");
		}
	}
}
