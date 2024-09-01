package br.com.fiap.traffic_management_spring_boot.service;

import br.com.fiap.traffic_management_spring_boot.exception.NotFoundException;
import br.com.fiap.traffic_management_spring_boot.model.account.Account;
import br.com.fiap.traffic_management_spring_boot.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<Account> account = this.accountRepository.findByEmail(email);
        return account.orElseThrow(() -> new NotFoundException("Account not found"));
    }
}
