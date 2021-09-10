package agenda.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agenda.entities.Contact;
import agenda.repositories.impl.JdbcContactRepository;
import agenda.repositories.interfaces.AgendaRepository;

@WebServlet(urlPatterns = { "/agenda/excluir" })
public class DeleteContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Contact contato = new Contact();
		contato.setId(id);

		AgendaRepository<Contact> repAgenda = new JdbcContactRepository();
		
		try {
			repAgenda.delete(contato);
		} catch (Exception e) {
			req.getSession().setAttribute("errorMsg", e.getMessage());
		}
		
		resp.sendRedirect(req.getContextPath() + "/agenda/listar");
	}

}
