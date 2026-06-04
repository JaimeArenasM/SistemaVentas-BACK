package group1.HD.Back.Security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
    
    private final Jwt jwtT;
    private final UserDetailsService userDetailsService;


    public JwtFilter(Jwt jwtT, UserDetailsService userDetailsService) {
        this.jwtT = jwtT;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    throws ServletException, IOException{

        final String authorizationHeader = request.getHeader("Authorizacion");
        String username = null;
        String jwt = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            username= jwtT.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() ==null) {
            UserDetails  userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtT.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken aToken = new UsernamePasswordAuthenticationToken(
                    userDetails,null,userDetails.getAuthorities());
                    aToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(aToken);
            }
        }
        chain.doFilter(request, response);
    }
}
