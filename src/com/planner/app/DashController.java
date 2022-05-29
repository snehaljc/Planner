package com.planner.app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planner.dao.DashDao;
import com.planner.dao.LoginDao;
import com.planner.dao.RegisterDao;

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
		Integer id = Integer.parseInt(req.getParameter("id"));
		HttpSession session = req.getSession();
		Integer userid = (Integer) session.getAttribute("userid");
		if(userid != null) {
			switch(action) {
			case "delete":
				if(id != null && dashDao.deleteEventById(id) != 0) {
					session.setAttribute("dashEvents", dashDao.getAllDashboardEventsByUser(userid));
					RequestDispatcher dispatcher = req.getRequestDispatcher("components/dashboard/dashboard.jsp");
					dispatcher.forward(req, resp);
				}
			break;
			default:
				RequestDispatcher dispatcher = req.getRequestDispatcher("components/dashboard/dashboard.jsp");
				dispatcher.forward(req, resp);
				break;
			}
		} else {
			session.setAttribute("toast", "Could not delete event, user id is not set");
			RequestDispatcher dispatcher = req.getRequestDispatcher("components/dashboard/dashboard.jsp");
			dispatcher.forward(req, resp);
		}

	}
}
