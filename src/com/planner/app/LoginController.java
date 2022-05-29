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
import com.planner.model.LoginBean;
import com.planner.model.LoginRespBean;
import com.planner.model.RegisterBean;
@WebServlet("/login")
public class LoginController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private LoginDao loginDao;
	private RegisterDao registerDao;
	private DashDao dashDao;

	public void init() {
		loginDao = new LoginDao();
		registerDao = new RegisterDao();
		dashDao = new DashDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getParameter("pageName");
		switch(action) {
		case "register":
			register(req, resp);
		break;
		case "login":
			authenticate(req, resp);
		break;
		}
	}
	
	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);

		try {
			LoginRespBean resp = loginDao.validate(loginBean);
			if (resp != null) {
				HttpSession session = request.getSession();
				session.setAttribute("dashEvents", dashDao.getAllDashboardEventsByUser(resp.getUserId()));
				session.setAttribute("user", resp.getUserName());
				session.setAttribute("userid", resp.getUserId());
				session.setAttribute("toast", null);
				RequestDispatcher dispatcher = request.getRequestDispatcher("components/dashboard/dashboard.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				request.getSession().setAttribute("toast", "Email or password is incorrect");
				dispatcher.forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			request.getSession().setAttribute("toast", "Something went wrong, please check server logs");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			e.printStackTrace();
		}

	}
	
	private void register(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException {
		RegisterBean rb = new RegisterBean();
		rb.setEmail(request.getParameter("email"));
		rb.setFname(request.getParameter("fname"));
		rb.setLname(request.getParameter("lname"));
		rb.setPassword(request.getParameter("password"));
		
		try {
			int result = registerDao.RegisterUser(rb);
			if(result == 1) {
				request.getSession().setAttribute("toast", "User Registered Successfully! Login with the created user");
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			} else if(result == 2) {
				request.getSession().setAttribute("toast", "User already exists! Try with another email");
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			} else {
				request.getSession().setAttribute("toast", "Something went wrong, please check server logs");
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.getSession().setAttribute("toast", "Something went wrong, please check server logs");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			e.printStackTrace();
		}

	}
	
}
