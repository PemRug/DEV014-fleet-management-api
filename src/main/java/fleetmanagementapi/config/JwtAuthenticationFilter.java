package fleetmanagementapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fleetmanagementapi.exception.GlobalExceptionHandler;
import fleetmanagementapi.service.impl.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ProblemDetail;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtServiceImpl jwtService;
    private final UserDetailsService userDetailsService;
    private GlobalExceptionHandler globalExceptionHandler;

    public JwtAuthenticationFilter(JwtServiceImpl jwtService, UserDetailsService userDetailsService, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver, GlobalExceptionHandler globalExceptionHandler) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.globalExceptionHandler = globalExceptionHandler;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (userEmail != null && authentication == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities() != null ? userDetails.getAuthorities() : Collections.emptyList()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (AuthenticationServiceException ex) {
            handleAuthenticationException(ex, request, response);
        } catch (Exception ex) {
            handleException(ex, request, response);
        }
    }

    private void handleAuthenticationException(AuthenticationServiceException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", "Unauthorized: " + ex.getMessage());
        body.put("path", request.getRequestURI());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getWriter(), body);
    }

    private void handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProblemDetail problemDetail = globalExceptionHandler.handleSecurityException(ex);
        if (problemDetail != null) {
            response.setStatus(problemDetail.getStatus());
            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getWriter(), problemDetail);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            errorBody.put("error", "Internal Server Error");
            errorBody.put("message", "An internal server error occurred.");
            errorBody.put("path", request.getRequestURI());
            new ObjectMapper().writeValue(response.getWriter(), errorBody);
        }
    }
}
