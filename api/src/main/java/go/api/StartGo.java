package go.api;

import java.io.IOException;

import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.ArrayList;


import go.domain.*;
import go.api.models.*;

@Path("/start")
public class StartGo {
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response initialize(
        @Context HttpServletRequest request, 
        PlayerInput players) {
    
    String namePlayer1 = players.getNameplayer1();
    String namePlayer2 = players.getNameplayer2();

    var gameGo = new GoImpl(players.getBoardSize());

    var go= new Go(gameGo, namePlayer1, namePlayer2);
    /*   
    DefaultCommandGateway commandGateWay = DefaultCommandGateway.builder().commandBus(SimpleCommandBus.builder().build()).build();
    commandGateWay.getCommandBus().subscribe("PlayMoveCommand", AnnotationCommandHandler());
    Configurer configurer = DefaultConfigurer.defaultConfiguration()
       .configureAggregate(GoImpl.class);
            */
    
    //build(SimpleCommandBus.builder().build(), new BeanValidationInterceptor<GenericCommandMessage<String>>());
    
    HttpSession session = request.getSession(true);
    session.setAttribute("go", go);
    session.setAttribute("player1", namePlayer1);
    session.setAttribute("player2", namePlayer2);
    session.setAttribute("gameGo", gameGo);
    //session.setAttribute("commandGateway", commandGateWay);
    //session.setAttribute("configurer", configurer);
    //session.setAttribute("playerObject", firstPlayer);

    return Response.status(200).entity(go).build();
}
    
}
