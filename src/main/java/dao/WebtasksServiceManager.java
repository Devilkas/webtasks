package dao;

import javax.servlet.ServletContext;

import dao.impl.DataServiceImpl;


import database.ConfigDatabase;
import org.apache.log4j.Logger;


public final class WebtasksServiceManager {
	private static final Logger LOGGER = Logger.getLogger(WebtasksServiceManager.class);
	private static final String WEBTASKS_SERVICES_MANAGER = "WEBTASKS_SERVICES_MANAGER";
	private static final WebtasksServiceManager INSTANCE = new WebtasksServiceManager();
	private WebtasksServiceManager () {
		init();
	}
	public static WebtasksServiceManager getInstance (ServletContext context) {
		WebtasksServiceManager instance = (WebtasksServiceManager) context.getAttribute(WEBTASKS_SERVICES_MANAGER);
		if(instance == null) {
			context.setAttribute(WEBTASKS_SERVICES_MANAGER, INSTANCE);
			instance = INSTANCE;
		}
		return instance;
	}
	
	private DataService dataService;
	public DataService getDataService() {
		return dataService;
	}
	
	private void init() {
		dataService = new DataServiceImpl();
	}
	
	public void startAllServices(){
		try {
			ConfigDatabase.deleteTables();
			ConfigDatabase.createTables();
			ConfigDatabase.fillTables();
		} catch (Exception e) {
			LOGGER.info("Context can't started. '"+e.getMessage());
			throw new RuntimeException("Context can't started. '"+e.getMessage(), e);
		}
		LOGGER.info("All services have been started");
	}
	
	public void closeAllServices (){
		dataService.close();
		LOGGER.info("All services have been closed");
	}
}
