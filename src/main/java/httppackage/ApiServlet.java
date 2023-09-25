package httppackage;

import animalpackage.Animal;
import animalpackage.AnimalRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder response = new StringBuilder();

        if (AnimalRepository.getAnimals().isEmpty()) {
            response.equals("No animals here :(");
        } else {
            AnimalRepository.getAnimals().forEach(animal -> {
                    response.append(String.format("%s named %s\r\n", animal.getType(), animal.getName()));
                }
            );
        }

        resp.getWriter().write(response.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String payload = req.getReader().lines().collect(Collectors.joining());
        Animal animal = new ObjectMapper().readValue(payload, Animal.class);

        AnimalRepository.addAnimal(animal);
    }
}