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

@WebServlet("/searchpatient")
public class SearchPatient extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		out.println("<html>");

		out.println("<head>");
		out.println("<link rel='stylesheet' href='SearchPatientProject.css'>");
		out.println("</head>");
		out.println("<body><form action='searchpatient' method='post'>");
		out.println("Enter the ID : <input type='number' name='ID' required><br><br>");
		out.println("<button>Search</button>");
		out.println("</form></body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(req.getParameter("ID"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/hospital?user=root&&password=root");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM patients WHERE ID=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			PrintWriter out = resp.getWriter();
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='SearchPatientResult.css'>");
			out.println("</head><body>");
			out.println("<div class='result-container'>");
			out.println("<h3>Patient data fetched Successfully</h3>");
			out.println("<table");
			if (rs.next()) {
				do {
					String email = rs.getString("email");
					String password = rs.getString("password");
					String name = rs.getString("name");
					String number = rs.getString("number");
					out.println("<tr><td>ID : </td><td>" + id + "</td></tr>");
					out.println("<tr><td>Email : </td><td>" + email + "</td></tr>");
					out.println("<tr><td>Password : </td><td>" + password + "</td></tr>");
					out.println("<tr><td>Name : </td><td>" + name + "</td></tr>");
					out.println("<tr><td>Number : </td><td>" + number + "</td></tr>");
				} while (rs.next());
				out.println("</div>");
				out.println("</table>");
				out.println("<a href='homepagepro'><button>Home Page</button></a>");
				out.println("</body</html>");
			} else {
				resp.sendRedirect("invalidSearch.html");
			}
			rs.close();
			ps.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			resp.sendRedirect("Error.html");
		}
	}

}
