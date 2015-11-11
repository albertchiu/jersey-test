package system;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.ext.RuntimeDelegate;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainer;
import org.glassfish.jersey.server.ResourceConfig;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.ServerConfiguration;

import system.resource.TestImportResource;
import system.security.AuthenticationExceptionMapper;
import system.security.SecurityFilter;

public class App {

    public HttpServer startServer() {
        final HttpServer server = new HttpServer();

        final NetworkListener listener = new NetworkListener("listener1", NetworkListener.DEFAULT_NETWORK_HOST, getPort());
        server.addListener(listener);

        final ServerConfiguration config = server.getServerConfiguration();
        config.addHttpHandler(RuntimeDelegate.getInstance().createEndpoint(createResourceConfig(), GrizzlyHttpContainer.class));

        try {
            server.start();
        }
        catch (Exception ex) {
            throw new ProcessingException(ex);
        }

        return server;
    }

    private int getPort() {
    	int defaultPort = NetworkListener.DEFAULT_NETWORK_PORT;
        final String port = "";//prop.getProperty("app.port") get from config file
        if (port != null && port.length() > 0) {
            try {
            	defaultPort = Integer.parseInt(port);
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultPort;
    }

    private ResourceConfig createResourceConfig() {
        return new ResourceConfig()
                .registerClasses(TestImportResource.class, SecurityFilter.class, AuthenticationExceptionMapper.class);
    }

    public static void main(String[] args) {
    	new App().startServer();

        try {
            while (true) Thread.sleep(Long.MAX_VALUE);
        }
        catch (InterruptedException ie) {
        }
    }
}