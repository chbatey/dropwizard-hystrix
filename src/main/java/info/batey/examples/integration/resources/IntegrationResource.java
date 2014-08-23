package info.batey.examples.integration.resources;

import com.codahale.metrics.annotation.Timed;
import info.batey.examples.integration.points.auth.PinCheckDependency;
import info.batey.examples.integration.points.device.DeviceServiceDependency;
import info.batey.examples.integration.points.user.UserServiceDependency;
import org.apache.http.client.HttpClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("integrate")
public class IntegrationResource {

    private HttpClient httpClient;

    public IntegrationResource(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @GET
    @Timed
    public String integrate() {
        String user = new UserServiceDependency(httpClient).execute();
        String device = new DeviceServiceDependency(httpClient).execute();
        Boolean pinCheck = new PinCheckDependency(httpClient).execute();
        return String.format("[User info: %s] \n[Device info: %s] \n[Pin check: %s] \n", user, device, pinCheck);
    }

}
