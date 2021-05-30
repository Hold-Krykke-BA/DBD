package ressourceLayer;

import javax.ws.rs.*;

@Path("/api2")
public class ResourceImpl {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}