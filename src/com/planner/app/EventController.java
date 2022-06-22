package com.planner.app;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.planner.dao.DashDao;
import com.planner.dao.eventsDao;
import com.planner.model.EventsBean;
import com.planner.model.NewEventParamBean;

@WebServlet("/events")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class EventController extends HttpServlet{
	private eventsDao evedao;
	private DashDao dashDao;
	public void init() {
		 evedao = new eventsDao();
		 dashDao = new DashDao();
	}
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getParameter("action");
		if(action.equals("newevent")) {
			NewEventParamBean neweventdata = new NewEventParamBean(evedao.getAllLocations(), evedao.getAllUsers());
			req.getSession().setAttribute("neweventdata", neweventdata);
			RequestDispatcher dispatcher = req.getRequestDispatcher("components/events/events.jsp");
			dispatcher.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			submitEvent(req, resp);
		} catch (IOException | ServletException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void submitEvent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
		EventsBean event = new EventsBean();
		event.setName(request.getParameter("eventname"));
		event.setCity(Integer.parseInt(request.getParameter("eventcity")));
		event.setAddress(request.getParameter("eventaddress"));
		event.setFee(Integer.parseInt(request.getParameter("eventfee")));
		event.setDescription(request.getParameter("eventdesc"));
		String evetype = request.getParameter("eventtype");
		if(evetype.equals("publictype")) {
		event.setIspublic(true);
		} else {
		event.setIspublic(false);
		}
		event.setDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(request.getParameter("eventdate").replace("T", " ")));
		Integer uid = (Integer) request.getSession().getAttribute("userid");
		event.setCreatedBy(uid);
		InputStream inputStream = null; // input stream of the upload file
		// obtains the upload file part in this multipart request
        Part filePart = request.getPart("eventimage");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        String users = request.getParameter("eventinvite");
        List<String> userslist =null;
        if(users != null) {
        	userslist = Arrays.asList(users.split("\\s*,\\s*"));
        }
        int rs = evedao.addEvent(event, inputStream, userslist);
        if(rs == 1) {
        	Integer userid = (Integer) request.getSession().getAttribute("userid");
        	request.getSession().setAttribute("toast", "Event added sccuessfully!");
        	request.getSession().setAttribute("dashEvents", dashDao.getAllDashboardEventsByUser(userid));
			RequestDispatcher dispatcher = request.getRequestDispatcher("components/dashboard/dashboard.jsp");
			dispatcher.forward(request, response);
        } else {
        	request.getSession().setAttribute("toast", "Error occured while adding event");
        }
		
	}
}
