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

@WebServlet("/loginPro")
public class LoginPro extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = req.getParameter("email");
		HttpSession session = req.getSession();
		session.setAttribute("email", email);
		String password = req.getParameter("password");
		PrintWriter out = resp.getWriter();
		out.println("<html><head>");
		out.println("<link rel='stylesheet' href='LoginPageProject.css'>");
		out.println("</head><body>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hospital?user=root&&password=root";
			Connection con = DriverManager.getConnection(url);
			PreparedStatement ps = con.prepareStatement("SELECT * FROM patients WHERE email=?;");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String dbPassword = rs.getString("password");
				if (dbPassword.equals(password)) {
					resp.sendRedirect("homepagepro?msg=login_success");
				} else {
					resp.sendRedirect("LoginError.html");
				}
			} else {
				resp.sendRedirect("LoginError.html");
			}

		} catch (ClassNotFoundException | SQLException e) {
			resp.sendRedirect("Error.html");
		}
		out.println("</html></body>");
	}

}
