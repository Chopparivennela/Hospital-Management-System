package org.jsp_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/homepagepro")
public class HomePagePro extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String msg = req.getParameter("msg");
		PrintWriter out = resp.getWriter();
		out.println("<html><head>");
		out.println("<link rel='stylesheet' href='HomePageProject.css'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class='container'>");
		out.println("<h1>Hospital Management System</h1>");
		if ("login_success".equals(msg))
			out.println("<h3 class='message'>Login successfull</h3>");
		else if ("add_success".equals(msg))
			out.println("<h3 class='message'>Patient added successfully</h3>");
		else if ("add_failed".equals(msg))
			out.println("<h3 class='message'>Patient Failed to add</h3>");
		else if ("remove_success".equals(msg))
			out.println("<h3 class='message'>Patient removed successfully</h3>");
		else if ("remove_failed".equals(msg))
			out.println("<h3 class='message' >Patient failed to remove</h3>");
		else if ("update_success".equals(msg))
			out.println("<h3 class='message' >Patient updated successfully</h3>");
		else if ("update_failed".equals(msg))
			out.println("<h3 class='message' >Patient failed to update</h3>");
		out.println("<table cellspacing='0' cellpadding='10'>");
		out.println("<tr><th><a href='addpatient'><button>Add Patient</button></a></th></tr>");
		out.println("<tr><th><a href='removepatient'><button>Remove Patient</button></a></th></tr>");
		out.println("<tr><th><a href='searchpatient'><button>Search Patient</button></a></th></tr>");
		out.println("<tr><th><a href='displayallpatients'><button>Display All Patients</button></a></th></tr>");
		out.println("<tr><th><a href='profilepagepro'><button>Profile Page</button></a></th></tr>");
		out.println("<tr><th><a href='logoutpro'><button>Logout</button></a></th></tr></table>");
		out.println("</div>");
		out.println("</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
