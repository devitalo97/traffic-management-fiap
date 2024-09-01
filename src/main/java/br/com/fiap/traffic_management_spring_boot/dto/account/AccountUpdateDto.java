package br.com.fiap.traffic_management_spring_boot.dto.account;

public record AccountUpdateDto(
        Long id,
        String name,
        String email,
        String role,
        String password
) {

}
