package br.com.fiap.traffic_management_spring_boot.controller;

import br.com.fiap.traffic_management_spring_boot.dto.account.AccountCreateDto;
import br.com.fiap.traffic_management_spring_boot.dto.account.AccountViewDto;
import br.com.fiap.traffic_management_spring_boot.dto.auth.AuthSignInInputDto;
import br.com.fiap.traffic_management_spring_boot.dto.auth.AuthSignInOutputDto;
import br.com.fiap.traffic_management_spring_boot.model.account.Account;
import br.com.fiap.traffic_management_spring_boot.service.AccountService;
import br.com.fiap.traffic_management_spring_boot.config.security.JWTTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JWTTokenService jwtTokenService;

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthSignInOutputDto> signIn(@RequestBody @Valid AuthSignInInputDto authSignInDto) {
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                authSignInDto.email(),
                authSignInDto.password()
        );
        Authentication authentication = authenticationManager.authenticate(credentials);
        String token = jwtTokenService.sign((Account) authentication.getPrincipal());
        AuthSignInOutputDto authSignInOutputDto = new AuthSignInOutputDto(token);
        return ResponseEntity.ok(authSignInOutputDto);
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountViewDto signUp(@RequestBody @Valid AccountCreateDto accountCreateDto) {
        return this.accountService.insertOne(accountCreateDto);
    }
}
