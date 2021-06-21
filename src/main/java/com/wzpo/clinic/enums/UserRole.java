package com.wzpo.clinic.enums;

public enum UserRole {

    DOCTOR("DOCTOR"),
    RECEPTION("RECEPTION"),
    ADMIN("ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
