package facades;

import com.google.gson.Gson;
import dtos.DadJokeDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DadJokeFacade {

    private static DadJokeFacade instance;
    private static EntityManagerFactory entityManagerFactory;

    private DadJokeFacade(){}

    // Method returns an instance of the FacadeExample class
    public static DadJokeFacade getDadJokeFacade(EntityManagerFactory entityManagerFactory1){
        if( instance == null) {
            entityManagerFactory = entityManagerFactory1;
            instance = new DadJokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    Gson GSON = new Gson().newBuilder().setPrettyPrinting().create();

    public String getHttpResponse(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public DadJokeDTO createDadJokeDTO(String input){
        return GSON.fromJson(input,DadJokeDTO.class);
    }
}
