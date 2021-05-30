package ressourceLayer;

import javax.ws.rs.*;

@Path("/hello-world")
public interface IResource {
    @GET
    @Produces("text/plain")
    String hello(@QueryParam("name") String name);
}