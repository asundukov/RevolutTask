package ru.my2i.wallet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import ru.my2i.wallet.rs.exception.mapper.BadRequestWebExceptionMapper;

public class App {

    private static final int DEFAULT_PORT = 8080;

    private App() throws Exception {
        Server server = configureServer();
        server.start();
        server.join();
    }

    private Server configureServer() {
        return configureServer(DEFAULT_PORT);
    }

    private Server configureServer(int port) {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages("ru.my2i.wallet.rs");
        resourceConfig.register(BadRequestWebExceptionMapper.class);
        resourceConfig.register(JacksonFeature.class);

        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        ServletHolder sh = new ServletHolder(servletContainer);

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(sh, "/*");

        Server server = new Server(port);
        server.setHandler(context);
        return server;
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}
