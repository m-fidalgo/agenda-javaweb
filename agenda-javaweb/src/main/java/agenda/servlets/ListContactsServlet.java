package agenda.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agenda.repositories.impl.JdbcContactRepository;
import agenda.repositories.interfaces.AgendaRepository;
import agenda.entities.Contact;

@WebServlet(urlPatterns = {"/agenda/listar"})
public class ListContactsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AgendaRepository<Contact> repAgenda = new JdbcContactRepository();
		try {
			List<Contact> contatos = repAgenda.select();
			req.setAttribute("listaContatos", contatos);
		}  catch (Exception e) {
			req.setAttribute("errorMsg", e.getMessage());
		}
		
		Object errorMsg = req.getSession().getAttribute("errorMsg");
		
		if(errorMsg != null) {
			req.setAttribute("errorMsg", errorMsg.toString());
			req.getSession().removeAttribute("errorMsg");
		}
			
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/pages/listaContatos.jsp");
		dispatcher.forward(req, resp);
	}

}
