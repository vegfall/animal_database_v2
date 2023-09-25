package httppackage;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

public class HttpServer {
    Server server = new Server(8181);

    public void start() throws Exception {
        Resource resource = Resource.newClassPathResource("/website");
        WebAppContext webAppContext = new WebAppContext(resource, "/");

        webAppContext.addServlet(new ServletHolder(new ServerServlet()), "/api");
        server.setHandler(webAppContext);

        server.start();
    }
}
