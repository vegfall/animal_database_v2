package httppackage;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import userpackage.User;
import userpackage.UserRepository;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining());

        if (!body.isEmpty()) {
            User user = new ObjectMapper().readValue(body, User.class);
            Map<String, String> users = UserRepository.getUsers();

            if (users.get(user.getUsername()) != null) {
                if (users.get(user.getUsername()).equals(user.getPassword())) {
                    resp.addCookie(new Cookie("authorized", "true"));
                }
            }
        }
    }
}