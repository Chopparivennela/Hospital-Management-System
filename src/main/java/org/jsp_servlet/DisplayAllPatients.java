package org.jsp_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayallpatients")
public class DisplayAllPatients extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='DisplayAllPatientsProject.css'>");
		out.println("</head><body>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hospital?user=root&&password=root";
			Connection con = DriverManager.getConnection(url);
			PreparedStatement ps = con.prepareStatement("SELECT * FROM patients;");
			ResultSet rs = ps.executeQuery();
			out.println(
					"<table border='' cellspacing='0' cellpadding='30'><tr><th>ID</th><th>Email</th><th>Password</th><th>Name</th><th>Number</th><th>Operations</th></tr>");
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("ID"));
				String email = rs.getString("email");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String number = rs.getString("number");
				out.println("<tr><td>" + id + "</td>");
				out.println("<td>" + email + "</td>");
				out.println("<td>" + password + "</td>");
				out.println("<td>" + name + "</td>");
				out.println("<td>" + number + "</td>");
				out.println("<td><a href='loadpatient?id=" + id
						+ "'><button>Update</button></a>&nbsp<a href='deletepatient?id=" + id
						+ "'><button>Delete</button></a></td></tr>");
			}
			out.println("</table><br><br>");
			String msg = req.getParameter("msg");
			if ("delete_success".equals(msg)) {
				out.println("<h3>Patient record deleted successfully</h3>");
			} else if ("delete_failed".equals(msg)) {
				out.println("<h3>Failed to delete patient record</h3>");
			}
			out.println("<a href='homepagepro'><button>Home Page</button></a>");
			out.println("</body></html>");
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			resp.sendRedirect("Error.html");
		}

	}

}
