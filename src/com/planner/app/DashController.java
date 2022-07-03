package com.planner.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planner.dao.DashDao;
import com.planner.model.DashEventsBean;

@WebServlet("/dashboard")
public class DashController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DashDao dashDao;

	public void init() {
		dashDao = new DashDao();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getParameter("action");
		String locid = req.getParameter("lid");
		Integer lid = null;
		if(locid != null) {
			lid = Integer.parseInt(locid);
		}
		HttpSession session = req.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		if(userid != null) {
			if(action != null) {
				switch(action) {
				case "delete":
					Integer id = Integer.parseInt(req.getParameter("id"));
					if(id != null && dashDao.deleteEventById(id) != 0) {
						session.setAttribute("toastSuccess", "Event deleted successfully!");
						session.setAttribute("dashEvents", dashDao.getAllDashboardEventsByUser(userid));
						RequestDispatcher dispatcher = req.getRequestDispatcher("components/dashboard/dashboard.jsp");
						dispatcher.forward(req, resp);
					} else {
						session.setAttribute("toast", "Could not delete event, user id is not set");
						RequestDispatcher dispatcher = req.getRequestDispatcher("components/dashboard/dashboard.jsp");
						dispatcher.forward(req, resp);
					}
				break;
				case "alleve":
					session.setAttribute("dashEvents", dashDao.getAllDashboardEventsByUser(userid));
					RequestDispatcher dispatcher1 = req.getRequestDispatcher("components/events/all-events.jsp");
					dispatcher1.forward(req, resp);
					break;
				case "nearby":
					List<DashEventsBean> dils = dashDao.getAllNearByEventsByLocation(userid, lid);
					session.setAttribute("nearbyevents", dils);
					RequestDispatcher dispatcher2 = req.getRequestDispatcher("components/dashboard/dashboard.jsp");
					dispatcher2.forward(req, resp);
					break;
				default:
					session.setAttribute("dashEvents", dashDao.getAllDashboardEventsByUser(userid));
					HashMap<Integer, String> alllocs =(HashMap<Integer, String>) session.getAttribute("allLocations");
					session.setAttribute("nearbyevents", dashDao.getAllNearByEventsByLocation(userid, alllocs.keySet().stream().findFirst().get()));
					RequestDispatcher dispatcher = req.getRequestDispatcher("components/dashboard/dashboard.jsp");
					dispatcher.forward(req, resp);
					break;
				}
			} else {
				session.setAttribute("dashEvents", dashDao.getAllDashboardEventsByUser(userid));
				HashMap<Integer, String> alllocs =(HashMap<Integer, String>) session.getAttribute("allLocations");
				session.setAttribute("nearbyevents", dashDao.getAllNearByEventsByLocation(userid, alllocs.keySet().stream().findFirst().get()));
				RequestDispatcher dispatcher = req.getRequestDispatcher("components/dashboard/dashboard.jsp");
				dispatcher.forward(req, resp);
			}
		
		} else {
			session.setAttribute("toast", "User id is not set, please log in");
			RequestDispatcher dispatcher = req.getRequestDispatcher("components/dashboard/dashboard.jsp");
			dispatcher.forward(req, resp);
		}

	}
	
}
