package com.lbi.mytestapplication.process;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import com.lbi.mytestapplication.domain.RouteDAO;
import com.lbi.mytestapplication.domain.entity.Route;

public class RouteManager {
	
	Logger logger = Logger.getLogger(RouteManager.class.getName());
	
	@Inject
    private UserTransaction utx;

	@Inject
	RouteDAO routeDao;

	@Inject
	CamelManager camelMgr;

	public void addCamelRoute(Route r){
		try {
			camelMgr.addRoute(r);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(),e);
		}
	}

	public List<Route> getAllRoutes() {
		return routeDao.getAllRoutes();
	}

	public void createRoute(Route r) {
		try {
			utx.begin();
			routeDao.createRoute(r);
			addCamelRoute(r);
			utx.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(),e);
			try {
				utx.rollback();
			} catch (Exception e1) {
				logger.log(Level.SEVERE, e.getMessage(),e);
			}
		}
	}

	public Route getRouteById(int id) {
		return routeDao.getRouteById(id);
	}

	public Route performAction(Route route, String action) throws Exception {
		Route r  = route;
		if(action.equalsIgnoreCase("start")){
			startRoute(r);
		} else if(action.equalsIgnoreCase("stop")){
			stopRoute(r);
		} else if(action.equalsIgnoreCase("pause")){
			pauseRoute(r);
		}
		return r;
	}

	private void pauseRoute(Route r) {
		logger.info("pausing route : " + r);
		// TODO Auto-generated method stub
		
	}

	private void stopRoute(Route r) {
		logger.info("stopping route : " + r);
		// TODO Auto-generated method stub
		
	}

	private void startRoute(Route r) throws Exception {
		logger.info("starting route : " + r);
		camelMgr.startRoute(r);
		// TODO update status to started
	}

}
