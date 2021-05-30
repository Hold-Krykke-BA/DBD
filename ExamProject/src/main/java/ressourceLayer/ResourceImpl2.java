package ressourceLayer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/api3")
public class ResourceImpl2 implements Resource {
    @Override
    public String hello(String name) {
        return "Hello" + name;
    }

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}
