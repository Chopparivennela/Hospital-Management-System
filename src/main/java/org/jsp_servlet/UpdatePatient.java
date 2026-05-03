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

@WebServlet("/updatepatient")
public class UpdatePatient extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		out.println("<html><body><form action='updatepatient' method='post'>");
		out.println("ID: <input type='number' name='id'><br>");
		out.println("Email: <input type='email' name='email'><br>");
		out.println("Password: <input type='password' name='password'><br>");
		out.println("Name: <input type='name' name='name'><br>");
		out.println("Number: <input type='number' name='number'><br>");
		out.println("<button type='submit'>Update</button>");
		out.println("</form></body></html>");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		long number = Long.parseLong(req.getParameter("number"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/hospital?user=root&&password=root");
			PreparedStatement ps = con
					.prepareStatement("UPDATE patients SET password=?, name=?, number=? WHERE email=?;");
			ps.setString(1, password);
			ps.setString(2, name);
			ps.setLong(3, number);
			ps.setString(4, email);
			int result = ps.executeUpdate();
			if (result > 0) {
				resp.sendRedirect("homepagepro?msg=update_success");
			} else {
				resp.sendRedirect("homepagepro?msg=update_failed");
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			resp.sendRedirect("Error.html");
		}
	}

}
