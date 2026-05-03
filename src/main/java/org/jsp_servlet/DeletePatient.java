package org.jsp_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deletepatient")
public class DeletePatient extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/hospital?user=root&&password=root");
			PreparedStatement ps = con.prepareStatement("DELETE FROM patients WHERE id=?;");
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			if (result > 0) {
				resp.sendRedirect("displayallpatients?msg=delete_success");
			} else {
				resp.sendRedirect("displayallpatients?msg=delete_failed");
			}
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			resp.sendRedirect("Error.html");
		}
	}

}
