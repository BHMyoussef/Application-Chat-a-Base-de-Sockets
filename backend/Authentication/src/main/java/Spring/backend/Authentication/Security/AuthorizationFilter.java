package Spring.backend.Authentication.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/v1/signin") ||  request.getServletPath().equals("/api/v1/registration")){
            // go to next filter
            filterChain.doFilter(request, response);
        }else {
            String authorizationHeader = request.getHeader(SecurityConstants.HEADER_NAME);
            if(authorizationHeader != null && authorizationHeader.startsWith(SecurityConstants.TOKEN_PREFIX)){
                try{
                    String token = authorizationHeader.split(" ")[1];
                    Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.TOKEN_SECRET.getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);

                    String email = decodedJWT.getSubject();

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                }catch (Exception e){
                    System.out.println(e.getCause());
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType("application/json");
                    Map<String, String> err = new HashMap();
                    err.put("error_message", e.getMessage());
                    new ObjectMapper().writeValue(response.getOutputStream(), err);
                }

            }else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
