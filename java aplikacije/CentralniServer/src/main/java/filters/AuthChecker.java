/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filters;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 *
 * @author Mihajlo
 */
public class AuthChecker {
    public static Response checkAuth(HttpHeaders headers, String roleName) {
        List<String> roles = Arrays.asList(headers.getRequestHeaders()
                .get("X-User-Roles").get(0).split(","));
            
        if (roles.isEmpty())
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Zaglavlja X-User-ID i X-User-Role su interna i ne smeju se slati u zahtevima.")
                    .build();

        if (!roles.contains(roleName))
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Morate biti " + roleName + ".")
                    .build();

        return null;
    }
}
