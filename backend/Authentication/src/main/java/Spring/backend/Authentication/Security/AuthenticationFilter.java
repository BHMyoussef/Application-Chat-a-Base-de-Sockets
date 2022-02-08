package Spring.backend.Authentication.Security;

import Spring.backend.Authentication.appuser.AppUser;
import Spring.backend.Authentication.appuser.AppUserService;
import Spring.backend.Authentication.appuser.ResponseUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;

    public AuthenticationFilter(AuthenticationManager authenticationManager, AppUserService appUserService) {
        this.authenticationManager = authenticationManager;
        this.appUserService = appUserService;
        setFilterProcessesUrl("/api/v1/signin");
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            AppUser appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            appUser.getEmail(), appUser.getPassword(), new ArrayList<>()
                    );
            return authenticationManager.authenticate(authenticationToken);
        }catch (Exception e){
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("path", request.getServletPath());
            responseBody.put("message", "email or password invalid");
            responseBody.put("error", "Forbidden");
            responseBody.put("status", response.getStatus()+"");
            responseBody.put("timestamp", new Date().toString());
            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getOutputStream(), responseBody);
            throw new RuntimeException(e.getCause());

        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User springUser = (User) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.TOKEN_SECRET.getBytes());
        String email = springUser.getUsername();
        Optional<AppUser> user = appUserService.getByEmail(email);
        String name = user.get().getName();
        String userID = user.get().getUserId();
        boolean is_connected = user.get().is_connected();
        Set<AppUser> friends = user.get().getFriends();

        String jwtToken = JWT.create().withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
                .sign(algorithm);

        response.addHeader(SecurityConstants.HEADER_NAME, SecurityConstants.TOKEN_PREFIX + jwtToken);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("token", jwtToken);
        responseBody.put("message", "success");
        responseBody.put("name", name);
        responseBody.put("email", email);
        responseBody.put("userID", userID);
        responseBody.put("friends", friends);

        responseBody.put("is_connected", is_connected);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), responseBody);
    }

}