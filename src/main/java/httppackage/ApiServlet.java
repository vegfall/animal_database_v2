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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiServlet extends HttpServlet {
    private final static Logger logger = LoggerFactory.getLogger(ApiServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder response = new StringBuilder();

        if (AnimalRepository.getAnimals().isEmpty()) {
            response.append("No animals here :(");
        } else {
            AnimalRepository.getAnimals().forEach(animal -> {
                    response.append(String.format("%s named %s\r\n", animal.getType(), animal.getName()));
                }
            );
        }

        logger.info(response.toString());

        resp.getWriter().write(new ObjectMapper().writeValueAsString(response.toString()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String payload = req.getReader().lines().collect(Collectors.joining());
        Animal animal = new ObjectMapper().readValue(payload, Animal.class);

        AnimalRepository.addAnimal(animal);
    }
}