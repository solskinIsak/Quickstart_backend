package rest;

import com.google.gson.Gson;
import dtos.DadJokeDTO;
import facades.DadJokeFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Path("dadjoke")
public class DadJokeResource {

    private EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    Gson GSON = new Gson().newBuilder().setPrettyPrinting().create();
    private DadJokeFacade FACADE = DadJokeFacade.getDadJokeFacade(EMF);

@GET
@Produces({MediaType.APPLICATION_JSON})
public Response getDadJoke() throws Exception {
    DadJokeDTO dadJokeDTO = FACADE.createDadJokeDTO(FACADE.getHttpResponse("https://icanhazdadjoke.com"));
    return Response.ok().entity(dadJokeDTO).build();
}


}
