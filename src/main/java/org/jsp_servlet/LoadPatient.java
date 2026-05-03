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

@WebServlet("/loadpatient")
public class LoadPatient extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println(id);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/hospital?user=root&&password=root");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM patients WHERE ID=?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			PrintWriter out = resp.getWriter();
			out.println("<html>");

			out.println("<head>");
			out.println("<link rel='stylesheet' href='LoadPatientProject.css'>");
			out.println("</head>");

			out.println("<body>");
			out.println("<form action='updatepatient' method='post'>");

			out.println("<h1>Update Patient</h1>");
			if (rs.next()) {
				String email = rs.getString("email");
				String password = rs.getString("password");
				String name = rs.getString("name");
				long number = rs.getLong("number");
				out.println("<label>ID</label>");
				out.println("<input type='number' name='id' value='" + id + "'required>");
				out.println("<label>Email</label>");
				out.println("<input type='email' name='email' value='" + email + "'required>");

				out.println("<label>Password</label>");
				out.println("<input type='password' name='password' value='" + password + "'required>");

				out.println("<label>Name</label>");
				out.println("<input type='text' name='name' value='" + name + "'required>");

				out.println("<label>Number</label>");
				out.println("<input type='tel' name='number' value='" + number + "'required>");
				out.println("<button type='submit'>UPDATE</button>");
				out.println("</form>");
			} else {
				out.println("<h3>Failed to Update the record</h3>");
			}
			out.println("</body></html>");
		} catch (ClassNotFoundException | SQLException e) {
			resp.sendRedirect("Error.html");
		}
	}

}
