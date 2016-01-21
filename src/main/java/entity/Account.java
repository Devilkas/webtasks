package entity;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.log4j.Logger;


public class Account extends AbstractModelBean implements HttpSessionBindingListener {
	private static final Logger LOGGER = Logger.getLogger(Account.class);
	private static final long serialVersionUID = -5787220666753301113L;
	private int id;
	private String username;
	private String password;
	private List<Role> roles;
	public Account() {
		super();
	}
	public Account(String username, String password, List<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	public Account(int id, String username, String password, List<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	protected Object getIdModel() {
		return getUsername();
	}
	@Override
	public void valueBound(HttpSessionBindingEvent se) {
		if(LOGGER.isDebugEnabled()) {
			StringBuilder message = new StringBuilder("Account has been added to session with id='");
			message.append(se.getSession().getId());
			message.append("' and been bound to name='");
			message.append(se.getName());
			message.append("'");
			LOGGER.debug(message);
		}
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent se) {
		if(LOGGER.isDebugEnabled()) {
			StringBuilder message = new StringBuilder("Account has been removed from session with id='");
			message.append(se.getSession().getId());
			message.append("'");
			LOGGER.debug(message);
		}
	}
}
