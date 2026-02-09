/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filters;

import entities.Korisnik;
import entities.Uloga;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Mihajlo
 */
@Provider
public class BasicAuthFilter implements ContainerRequestFilter {
    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        context.getHeaders().getFirst("Authorization");
        MultivaluedMap<String, String> headers = context.getHeaders();
        if (!headers.containsKey("Authorization")) {
            context.abortWith(
                Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("Korisničko ime i lozinka nisu prosleđeni.")
                    .build()
            );
            return;
        }
        List<String> authHeaders = context.getHeaders().get("Authorization");
        if (authHeaders.isEmpty()) {
            context.abortWith(
                Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("Korisničko ime i lozinka nisu prosleđeni.")
                    .build()
            );
            return;
        }
        String[] authorization = new String(Base64.getDecoder().decode(authHeaders.get(0).replace("Basic ", "")), StandardCharsets.UTF_8).split(":");
        if (authorization.length != 2) {
            context.abortWith(
                Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Pogrešno prosleđeno korisničko ime ili lozinka.")
                    .build()
            );
            return;            
        }
        String username = authorization[0];
        String password = authorization[1];
        List<Korisnik> korisnici = em.createNamedQuery("Korisnik.findByKorisnickoIme", Korisnik.class)
            .setParameter("korisnickoIme", username)
            .getResultList();
        if (korisnici.isEmpty() || !korisnici.get(0).getLozinka().equals(password)) {
            context.abortWith(
                Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Pogrešno korisničko ime ili lozinka.")
                    .build()
            );
            return;
        }
        // Pass headers to resources.
        String roles = "";
        Korisnik korisnik = korisnici.get(0);
        for (Uloga u: korisnik.getUlogaList()) roles += u.getNaziv() + ",";
        if (!roles.isEmpty()) roles = roles.substring(0, roles.length() - 1);
        
        context.getHeaders().add("X-User-ID", korisnik.getIdK().toString());
        context.getHeaders().add("X-User-Roles", roles);
    }
}
