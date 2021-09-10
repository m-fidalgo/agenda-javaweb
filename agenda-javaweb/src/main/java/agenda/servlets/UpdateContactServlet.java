package agenda.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agenda.entities.Contact;
import agenda.repositories.impl.JdbcContactRepository;
import agenda.repositories.interfaces.AgendaRepository;

@WebServlet(urlPatterns = { "/agenda/editar" })
public class UpdateContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		AgendaRepository<Contact> repAgenda = new JdbcContactRepository();
		List<Contact> contatos = new ArrayList<Contact>();
		try {
			contatos = repAgenda.select();

			Optional<Contact> original = contatos.stream().filter(contato -> contato.getId() == id).findFirst();

			if (original.isPresent())
				req.setAttribute("contato", original.get());
			else
				throw new Exception("Este contato não existe");

		} catch (Exception e) {
			req.getSession().setAttribute("errorMsg", e.getMessage());
			resp.sendRedirect(req.getContextPath() + "/agenda/listar");
		}

		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/pages/editarContato.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Contact newContact = new Contact(Integer.parseInt(req.getParameter("id")), req.getParameter("nome"),
				Integer.parseInt(req.getParameter("idade")), req.getParameter("tel"));
		
		AgendaRepository<Contact> repAgenda = new JdbcContactRepository();
		try {
			repAgenda.update(newContact);
		} catch (Exception e) {
			req.getSession().setAttribute("errorMsg", e.getMessage());
		}
		
		resp.sendRedirect(req.getContextPath() + "/agenda/listar");
	}

}
