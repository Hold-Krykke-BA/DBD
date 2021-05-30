package ressourceLayer;

import javax.ws.rs.*;

@Path("/api")
public interface Resource {
    @GET
    @Produces("text/plain")
    String hello(@QueryParam("name") String name);
}