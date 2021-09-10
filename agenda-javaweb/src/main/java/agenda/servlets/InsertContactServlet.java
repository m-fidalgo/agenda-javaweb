package agenda.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agenda.entities.Contact;
import agenda.repositories.impl.JdbcContactRepository;
import agenda.repositories.interfaces.AgendaRepository;

@WebServlet(urlPatterns = {"/agenda/inserir"})
public class InsertContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/pages/inserirContato.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AgendaRepository<Contact> repAgenda = new JdbcContactRepository();
		Contact contato = new Contact();
		contato.setNome(req.getParameter("nome"));
		contato.setIdade(Integer.parseInt(req.getParameter("idade")));
		contato.setTel(req.getParameter("tel"));
		try {
			repAgenda.insert(contato);
		} catch (SQLException | IOException e) {
			req.getSession().setAttribute("errorMsg", e.getMessage());
		}
		resp.sendRedirect(req.getContextPath()+"/agenda/listar");
	}

}
