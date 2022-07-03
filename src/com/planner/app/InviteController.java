package com.planner.app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planner.dao.InvitesDao;

@WebServlet("/invites")
public class InviteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private InvitesDao inviteDao;
	@Override
	public void init() throws ServletException {
		inviteDao = new InvitesDao();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action != null && action.equals("acceptinvite")) {
			Integer uid = (Integer) req.getSession().getAttribute("userid");
			Integer eid = Integer.parseInt(req.getParameter("evid"));
			acceptInvites(req, resp,uid,eid);
		} else {
			Integer uid = (Integer) req.getSession().getAttribute("userid");
			viewInvites(req, resp, uid);
		}
	}
	
	public void viewInvites(HttpServletRequest req, HttpServletResponse resp, Integer uid) throws IOException, ServletException {
		//TODO: IF uid is null redirect to login page
				if(uid == null) {
					req.getSession().setAttribute("toast", "Session expired, please login again");
					RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
					dispatcher.forward(req, resp);
				}
		req.getSession().setAttribute("invitedeve", inviteDao.getAllInvitedEventsByUser(uid));
		RequestDispatcher dispatcher = req.getRequestDispatcher("components/events/invited-event.jsp");
		dispatcher.forward(req, resp);
	}
	
	public void acceptInvites(HttpServletRequest req, HttpServletResponse resp, Integer uid, Integer eid) throws IOException, ServletException {
		//TODO: IF uid is null redirect to login page
				if(uid == null) {
					req.getSession().setAttribute("toast", "Session expired, please login again");
					RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
					dispatcher.forward(req, resp);
				}
		int rs = inviteDao.accepteInvite(uid, eid);	
		if(rs != 0) {
			req.getSession().setAttribute("toastSuccess", "Event invitation accepted successfully!");
			req.getSession().setAttribute("invitedeve", inviteDao.getAllInvitedEventsByUser(uid));
			RequestDispatcher dispatcher = req.getRequestDispatcher("components/events/invited-event.jsp");
			dispatcher.forward(req, resp);
		} else {
			req.getSession().setAttribute("toast", "Something went wrong, please try again sometime");
			RequestDispatcher dispatcher = req.getRequestDispatcher("components/events/invited-event.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
