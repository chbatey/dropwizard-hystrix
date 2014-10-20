package info.batey.examples.integration.resources;

import com.codahale.metrics.annotation.Timed;
import info.batey.examples.integration.points.auth.PinCheckDependency;
import info.batey.examples.integration.points.device.DeviceServiceDependency;
import info.batey.examples.integration.points.user.UserServiceDependency;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("integrate")
public class IntegrationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationResource.class);

    private HttpClient userService;
    private HttpClient deviceService;
    private HttpClient pinService;


    public IntegrationResource(HttpClient userService, HttpClient deviceService, HttpClient pinService) {
        this.userService = userService;
        this.deviceService = deviceService;
        this.pinService = pinService;
    }

    @GET
    @Timed
    public String integrate() {
        LOGGER.info("integrate");
        String user = new UserServiceDependency(userService).execute();
        String device = new DeviceServiceDependency(deviceService).execute();
        Boolean pinCheck = new PinCheckDependency(pinService).execute();
        return String.format("[User info: %s] \n[Device info: %s] \n[Pin check: %s] \n", user, device, pinCheck);
    }

}
