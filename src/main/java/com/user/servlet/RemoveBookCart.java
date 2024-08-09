package com.user.servlet;

import java.io.IOException;

import com.DAO.CartDAOImpl;
import com.DB.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/remove_book")
public class RemoveBookCart extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int cid = Integer.parseInt(req.getParameter("cid"));
		int bid = Integer.parseInt(req.getParameter("bid"));
		int uid = Integer.parseInt(req.getParameter("uid"));
		CartDAOImpl cartDao = new CartDAOImpl(DBConnect.getConn());
		boolean f = cartDao.deleteBook(bid, uid, cid);
		HttpSession session = req.getSession();
		if(f) {
			session.setAttribute("successMsg", "Book removed from cart");
		}else {
			session.setAttribute("failedMsg", "Something wrong on server");
		}
		resp.sendRedirect("checkout.jsp");
	}
}
