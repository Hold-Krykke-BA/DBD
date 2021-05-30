package ressourceLayer;

import javax.ws.rs.*;

@Path("/hello-world")
public class Resource implements IResource {

    @Override
    public String hello(String name) {
        return null;
    }
}