package ru.my2i.wallet.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/api")
public class MainRs {

    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getParam(@PathParam("param") String param, @QueryParam("p") String p) {
        return Arrays.asList(param, p);
    }
}
