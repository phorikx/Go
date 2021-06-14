package go;

//import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.*;
//import org.eclipse.jetty.server.handler.*;
//import org.eclipse.jetty.webapp.*;
//import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.*;
//import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import go.api.*;

public class App {
    public static void main(String[] args) throws Exception {
        Server server = startServer(7070);
        ServletContextHandler context = createStatefulContext(server);
        registerServlets(context);

        server.start();
        System.out.println("Started server.");
        System.out.println("Listening on http://localhost:7070/");
        System.out.println("Press CTRL+C to exit.");
        server.join();
    }

    private static Server startServer(int port) {
        return new Server(7070);
    }

    private static ServletContextHandler createStatefulContext(Server server) {
        ServletContextHandler context = 
                new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        return context;
    }

    private static void registerServlets(ServletContextHandler context) {
        // Use the Jersey framework to translate the classes in the
        // mancala.api package to server endpoints (servlets).
        // For example, the StartMancala class will become an endpoint at
        // http://localost:7070/mancala/api/start
        ServletHolder serverHolder = context.addServlet(ServletContainer.class, "/go/api/*");
        serverHolder.setInitOrder(1);
        serverHolder.setInitParameter("jersey.config.server.provider.packages", 
                "go.api");
    }
}
