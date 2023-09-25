package animalpackage;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

public class AnimalFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Cookie cookie = Arrays.stream(req.getCookies()).filter(c -> c.getName().equals("authorized")).findFirst().orElse(null);

        if (cookie != null && cookie.getValue().equals("true")) {
            chain.doFilter(req, res);
        } else {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
