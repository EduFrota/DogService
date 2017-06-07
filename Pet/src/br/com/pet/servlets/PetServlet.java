package br.com.pet.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.pet.domain.Dog;
import br.com.pet.domain.DogService;
import br.com.pet.domain.Response;
import br.com.pet.utils.RegexUtil;
import br.com.pet.utils.ServletUtil;

/**
 * Servlet implementation class PetServlet
 */
@WebServlet("/pet/*")
public class PetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DogService dogService = new DogService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		Long id = RegexUtil.matchId(requestURI);
		if (id != null) {
			Dog dog = dogService.getDog(id);
			if (dog != null) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(dog);
				ServletUtil.writeJSON(response, json);
			} else {
				response.sendError(404, "Cão não encontrado");

			}
		} else {
			List<Dog> dogs = dogService.getDogs();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(dogs);
			ServletUtil.writeJSON(response, json);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Dog dog = new Dog();
		String id = request.getParameter("id");
		if (id != null) {
			dog = dogService.getDog(Long.parseLong(id));
		}
		dog.setNome(request.getParameter("nome"));
		dog.setIdade(request.getParameter("idade"));
		dog.setRaca(request.getParameter("raca"));
		dog.setTemperamento(request.getParameter("temperamento"));
		dogService.save(dog);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(dog);
		ServletUtil.writeJSON(response, json);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		if (id != null) {
			dogService.delete(id);
			Response r = Response.Ok("Carro Excluido com sucesso");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(r);
			ServletUtil.writeJSON(response, json);
		} else {
			response.sendError(404, "URl invalida");
		}
	}
}
