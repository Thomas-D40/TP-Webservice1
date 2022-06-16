
package fr.m2i.demoapi.api;


import fr.m2i.demoapi.business.Annuaire;
import fr.m2i.demoapi.business.Personne;
import java.util.ArrayList;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Tom
 */

@Path("/personnes")
public class AnnuaireAPI {
    
    @GET()
    @Produces({MediaType.APPLICATION_JSON})    
    public ArrayList<Personne> getPersons(@Context HttpServletRequest request ) {
        Annuaire annuaire = (Annuaire) request.getSession().getAttribute("annuaire");
        if (annuaire == null) {
            annuaire = new Annuaire();
        }
        
        return annuaire.getPersonnes();
    }
    
    @GET() @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})    
    public Response getPersonneById(@Context HttpServletRequest request, @PathParam("id") long id) {
        Annuaire annuaire = (Annuaire) request.getSession().getAttribute("annuaire");
        if (annuaire == null) {
            annuaire = new Annuaire();
        }
        
        Optional<Personne> option = annuaire.getPersonne(id);
        
        if (option.isPresent()) {            
            return Response.ok(option.get()).build();
        }     
        
        
        return Response.status(404).entity("Personne not found").build();
        // Design Pattern: Builder
    }
    
    
    @POST()
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Personne postPersonne(Personne newPersonne, @Context HttpServletRequest request) {
        Annuaire annuaire = (Annuaire) request.getSession().getAttribute("annuaire");
        if (annuaire == null) {
            annuaire = new Annuaire();
        }
        
        Personne personne = annuaire.addPersonne(newPersonne);
        request.getSession().setAttribute("annuaire", annuaire);
        
        return personne;
        
        
    }
    
    @PUT() @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Personne updatePersonne(Personne personne, @Context HttpServletRequest request) {
        Annuaire annuaire = (Annuaire) request.getSession().getAttribute("annuaire");
        if (annuaire == null) {
            annuaire = new Annuaire();
        }
        annuaire.updatePersonne(personne);
        
        request.getSession().setAttribute("annuaire", annuaire);
        
        return personne;
        
    }
    
    @DELETE() @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deletePersonne(@PathParam("id") long id, @Context HttpServletRequest request) {
        Annuaire annuaire = (Annuaire) request.getSession().getAttribute("annuaire");
        if (annuaire == null) {
            annuaire = new Annuaire();
        }
        annuaire.deletePersonne(id);
        request.getSession().setAttribute("annuaire", annuaire);
        
        
        
        return Response.ok().entity("Suppression effectuée").build();
    }
    
    
}
