package br.com.fiap.traffic_management_spring_boot.config.security;

import br.com.fiap.traffic_management_spring_boot.model.account.Account;
import br.com.fiap.traffic_management_spring_boot.repository.AccountRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class VerifyToken extends OncePerRequestFilter {

    @Autowired
    private JWTTokenService tokenService;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String token = resolveToken(request.getHeader("Authorization"));

        if (token != null && !token.isEmpty()) {
            try {
                String email = tokenService.valid(token);
                Optional<Account> account = accountRepository.findByEmail(email);
                account.ifPresent(this::setAuthentication);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7).trim();
        }
        return null;
    }

    private void setAuthentication(Account account) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                account,
                null,
                account.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
