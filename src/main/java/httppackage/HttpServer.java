package httppackage;

import animalpackage.AnimalFilter;
import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.EnumSet;

public class HttpServer {
    Server server = new Server(8181);

    public void start() throws Exception {
        Resource resource = Resource.newClassPathResource("/website");
        WebAppContext webAppContext = new WebAppContext(resource, "/");

        webAppContext.addServlet(new ServletHolder(new ApiServlet()), "/api");
        webAppContext.addServlet(new ServletHolder(new LoginServlet()), "/login");

        webAppContext.addFilter(new FilterHolder(new AnimalFilter()), "/api", EnumSet.of(DispatcherType.REQUEST));
        server.setHandler(webAppContext);

        server.start();
    }
}
