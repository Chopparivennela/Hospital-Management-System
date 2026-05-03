package org.jsp_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/removepatient")
public class RemovePatient extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		out.println("<html>");

		out.println("<head>");
		out.println("<link rel='stylesheet' href='RemovePatientProject.css'>");
		out.println("</head>");
		out.println("<body><form action='removepatient' method='post'>");
		out.println("Enter the ID : <input type='number' name='id' required><br>");
		out.println("<button type='submit'>Remove</button>");
		out.println("</form></body></html>");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/hospital?user=root&&password=root");
			PreparedStatement ps = con.prepareStatement("DELETE FROM patients WHERE id=?;");
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			if (result > 0) {
				resp.sendRedirect("homepagepro?msg=remove_success");
			} else {
				resp.sendRedirect("invalidId.html");
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			resp.sendRedirect("Error.html");
		}
	}

}
