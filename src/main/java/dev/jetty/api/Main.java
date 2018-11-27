package dev.jetty.api;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import dev.jetty.model.Topic;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler context =
                new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);

        ServletHolder servletHolder =
                new ServletHolder(new ServletContainer());
        servletHolder.setInitParameter(
                "com.sun.jersey.config.property.resourceConfigClass",
                "com.sun.jersey.api.core.PackagesResourceConfig");
        servletHolder.setInitParameter(
                "com.sun.jersey.config.property.packages",
                "dev.jetty.api");

        servletHolder.setInitOrder(1);
        context.addServlet(servletHolder, "/*");

        server.start();
        server.join();
    }
}