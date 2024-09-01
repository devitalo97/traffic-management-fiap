package br.com.fiap.traffic_management_spring_boot.dto.account;

import br.com.fiap.traffic_management_spring_boot.model.account.Account;
import br.com.fiap.traffic_management_spring_boot.model.account.Role;

import java.util.Date;

public record AccountViewDto(
        Long id,
        String name,
        String email,
        Role role,
        Date createdAt,
        Date updatedAt
) {

    public AccountViewDto(Account account){
        this(
                account.getId(),
                account.getName(),
                account.getEmail(),
                account.getRole(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }
}
