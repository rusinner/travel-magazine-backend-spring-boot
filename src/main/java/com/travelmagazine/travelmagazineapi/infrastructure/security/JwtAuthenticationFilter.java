package com.travelmagazine.travelmagazineapi.infrastructure.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;

        // 1. Έλεγχος αν υπάρχει το Bearer Token στο Header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Εξαγωγή του Token
        jwt = authHeader.substring(7);

        // Ένα κατεστραμμένο/ληγμένο/πειραγμένο token δεν πρέπει να ρίχνει το filter chain·
        // απλώς συνεχίζουμε το request ως μη αυθεντικοποιημένο.
        try {
            String userEmail = jwtService.extractUsername(jwt);

            // 3. Αν υπάρχει email και ο χρήστης δεν είναι ήδη authenticated
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                // 4. Αν το token είναι έγκυρο, ενημερώνουμε το SecurityContext
                if (jwtService.isTokenValid(jwt, userDetails)) {

                    // --- ΕΔΩ ΕΙΝΑΙ Η ΔΙΟΡΘΩΣΗ ΓΙΑ ΤΟ ROLE ---
                    String role = jwtService.extractClaim(jwt, claims -> claims.get("role", String.class));
                    String authorityName = role.startsWith("ROLE_") ? role : "ROLE_" + role;

                    // Δημιουργούμε τη λίστα με το ROLE_ADMIN
                    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(authorityName));

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            authorities
                    );
                    // ---------------------------------------

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (JwtException | IllegalArgumentException | UsernameNotFoundException ex) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return path.startsWith("/api/auth/") || path.equals("/api/users/register");
    }
}