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
    private static final Logger logger = LoggerFactory.getLogger(AnimalFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Cookie cookie = null;

        if (req.getCookies() != null) {
            cookie = Arrays.stream(req.getCookies()).filter(c -> c.getName().equals("authorized")).findFirst().orElse(null);
        }

        if (cookie != null && cookie.getValue().equals("true")) {
            logger.info("Authentication cookie accepted");
            chain.doFilter(req, res);
        } else {
            logger.info("Authentication cookie not accepted");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}