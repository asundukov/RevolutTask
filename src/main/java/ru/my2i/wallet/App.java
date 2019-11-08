package ru.my2i.wallet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import ru.my2i.wallet.service.currency.CurrencyServiceFactoryImpl;
import ru.my2i.wallet.service.paymentagent.PaymentAgentServiceFactoryImpl;
import ru.my2i.wallet.web.CurrencyRs;
import ru.my2i.wallet.web.PaymentAgentRs;
import ru.my2i.wallet.web.exception.mapper.BadRequestWebExceptionMapper;
import ru.my2i.wallet.web.exception.mapper.ConflictWebExceptionMapper;
import ru.my2i.wallet.web.exception.mapper.ConstraintViolationMapper;
import ru.my2i.wallet.web.exception.mapper.GeneralWebExceptionMapper;
import ru.my2i.wallet.web.exception.mapper.NotAllowedExceptionMapper;
import ru.my2i.wallet.web.exception.mapper.NotFoundWebExceptionMapper;

public class App {

    private static final int DEFAULT_PORT = 8090;

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
        resourceConfig.register(BadRequestWebExceptionMapper.class);
        resourceConfig.register(NotFoundWebExceptionMapper.class);
        resourceConfig.register(ConflictWebExceptionMapper.class);
        resourceConfig.register(ConstraintViolationMapper.class);
        resourceConfig.register(NotAllowedExceptionMapper.class);
        resourceConfig.register(GeneralWebExceptionMapper.class);
        resourceConfig.register(JacksonFeature.class);

        resourceConfig.register(new CurrencyRs(new CurrencyServiceFactoryImpl()));
        resourceConfig.register(new PaymentAgentRs(new PaymentAgentServiceFactoryImpl()));

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
