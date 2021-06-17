package go.api;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;


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

    var gameGo = new GoImpl(5);

    var go= new Go(gameGo, namePlayer1, namePlayer2);
    
    HttpSession session = request.getSession(true);
    session.setAttribute("go", go);
    session.setAttribute("player1", namePlayer1);
    session.setAttribute("player2", namePlayer2);
    session.setAttribute("gameGo", gameGo);
    //session.setAttribute("playerObject", firstPlayer);

    return Response.status(200).entity(go).build();
}
    
}
