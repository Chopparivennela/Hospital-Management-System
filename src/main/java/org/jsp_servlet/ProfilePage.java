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
import javax.servlet.http.HttpSession;

@WebServlet("/profilepagepro")
public class ProfilePage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		PrintWriter out = resp.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/hospital?user=root&&password=root");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM patients WHERE email=?;");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			out.println("<html><head>");
			out.println("<link rel='stylesheet' href='ProfilePageProject.css'>");
			out.println("</head>");
			out.println("<body><div class='container'><h1>Profile page</h1>");
			out.println("<table>");
			if (rs.next()) {
				int id = rs.getInt("ID");
				String password = rs.getString("password");
				String name = rs.getString("name");
				long number = rs.getLong("number");
				out.println("<tr><td>ID</td><td>" + id + "</td></tr>");
				out.println("<tr><td>EMAIl</td><td>" + email + "</td></tr>");
				out.println("<tr><td>NAME</td><td>" + name + "</td></tr>");
				out.println("<tr><td>NUMBER</td><td>" + number + "</td></tr>");
				out.println("<tr><td>PASSWORD</td><td>" + password + "</td></tr>");
				out.println("<tr><td><a href='loadpatient?id=" + id
						+ "'><button class='update-btn'>UPDATE</button></a></td><td><a href='deletepatient?id=" + id
						+ "'><button class='delete-btn'>DELETE</button></a></td></tr>");
			}
			out.println("</table><br><br>");
			out.println("<a href='homepagepro'><button class='home-btn'>Home Page</button></a>");
			out.println("</div>");
			out.println("</body></html>");
			rs.close();
			ps.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			resp.sendRedirect("Error.html");
		}
	}

}
