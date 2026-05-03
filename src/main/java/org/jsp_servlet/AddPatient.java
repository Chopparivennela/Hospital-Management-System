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

@WebServlet("/addpatient")
public class AddPatient extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		out.println("<html><head>");
		out.println("<link rel='stylesheet' href='AddPatientProject.css'>");
		out.println("</head>");
		out.println("<body><form action='addpatient' method='post'>");
		out.println("<h1>Add Patient</h1>");
		out.println("ID: <input type='number' name='id' required><br>");
		out.println("Email: <input type='email' name='email' required><br>");
		out.println("Password: <input type='password' name='password' required><br>");
		out.println("Name: <input type='name' name='name' required><br>");
		out.println("Number: <input type='number' name='number' required><br>");
		out.println("<button type='submit'>Add</button>");
		out.println("</form></body></html>");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int ID = Integer.parseInt(req.getParameter("id"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		long number = Long.parseLong(req.getParameter("number"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/hospital?user=root&&password=root");
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO patients (ID,email,password,name,number) VALUES(?,?,?,?,?);");
			ps.setInt(1, ID);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, name);
			ps.setLong(5, number);
			int result = ps.executeUpdate();
			if (result == 1) {
				resp.sendRedirect("homepagepro?msg=add_success");
			} else {
				resp.sendRedirect("homepagepro?msg=add_failed");
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			resp.sendRedirect("Error.html");
		} catch (SQLException e) {
			resp.sendRedirect("duplicateId.html");
		}
	}
}
