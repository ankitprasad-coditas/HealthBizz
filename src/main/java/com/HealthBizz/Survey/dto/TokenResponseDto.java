package com.HealthBizz.Survey.dto;

public class TokenResponseDto {

    private String accessToken;
    private String refreshToken;
    private String role;
    private String name;

    // Private constructor to prevent direct instantiation
    private TokenResponseDto(Builder builder) {
        this.accessToken = builder.accessToken;
        this.refreshToken = builder.refreshToken;
        this.role = builder.role;
        this.name = builder.name;
    }

    // Static builder method to start building the object
    public static Builder builder() {
        return new Builder();
    }

    // Getter methods for the fields
    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    // The Builder class
    public static class Builder {
        private String accessToken;
        private String refreshToken;
        private String role;
        private String name;

        // Setter methods for each field, returning the builder itself for chaining
        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        // Build method to return the final TokenResponseDto object
        public TokenResponseDto build() {
            return new TokenResponseDto(this);
        }
    }
}