package httppackage;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import userpackage.User;
import userpackage.UserRepository;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class LoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining());
        ObjectMapper objectMapper = new ObjectMapper();

        if (body.isEmpty()) {
            return;
        }

        User user = objectMapper.readValue(body, User.class);
        Map<String, String> users = UserRepository.getUsers();

        if (users.get(user.getUsername()) != null) {
            if (users.get(user.getUsername()).equals(user.getPassword())) {
                logger.info("User " + user.getUsername() + " authenticated");
                resp.getWriter().write(objectMapper.writeValueAsString("Login successful."));
                resp.addCookie(new Cookie("authorized", "true"));
            } else {
                resp.getWriter().write(objectMapper.writeValueAsString("Incorrect password..."));
            }
        } else {
            resp.getWriter().write(objectMapper.writeValueAsString("User not found..."));
        }
    }
}