package br.com.fiap.traffic_management_spring_boot.model.account;

public enum Role {
    ADMIN("ADMIN"),
    ACCOUNT("ACCOUNT");

    private final String role;

    Role(String role){
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
