package com.lbi.mytestapplication.process;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import com.lbi.mytestapplication.domain.RouteDAO;
import com.lbi.mytestapplication.domain.entity.EndPoint;
import com.lbi.mytestapplication.domain.entity.Route;

public class RouteManager {
	
	Logger logger = Logger.getLogger(RouteManager.class.getName());
	
	@Inject
    private UserTransaction utx;

	@Inject
	RouteDAO routeDao;

	@Inject
	CamelManager camelMgr;

	public void addCamelRoute(EndPoint source, EndPoint destination){
		try {
			camelMgr.addRoute(source.getUrl(), destination.getUrl());
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
			addCamelRoute(r.getSource(), r.getDestination());
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

}
