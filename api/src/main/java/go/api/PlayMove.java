package go.api;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.ArrayList;

import go.api.models.*;
import go.domain.*;
import go.domain.Player;
import go.domain.Intersection.Occupation;

@Path("/play")
public class PlayMove {
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response processmove(
        @Context HttpServletRequest request, 
        PlayerMove playerMove) {
    HttpSession session = request.getSession(false);

    System.out.println(playerMove.getPlayerName());
    System.out.println(playerMove.getPlayIntersection());

    GoImpl gameGo = (GoImpl) session.getAttribute("gameGo");
    ArrayList<Integer> playedIntersectionArrayList = playerMove.getPlayIntersection();
    int[] playedIntersection = new int[] {playedIntersectionArrayList.get(0),playedIntersectionArrayList.get(1)};
    Occupation colour;
    if (gameGo.getFirstPlayer().getTurn()) {
        colour = Occupation.BLACK;
    } else if (gameGo.getSecondPlayer().getTurn()) {
        colour = Occupation.WHITE;
    } else{
        colour = Occupation.EMPTY;
    }
    System.out.println("Has the player passed?" + String.valueOf(playerMove.getDidPass()));
    gameGo.playIntersection(playedIntersection, colour,playerMove.getDidPass());
    Go go = new Go(gameGo, ((String) session.getAttribute("player1")), ((String) session.getAttribute("player2")));
    return Response.status(200).entity(go).build(); 

}
}
    

