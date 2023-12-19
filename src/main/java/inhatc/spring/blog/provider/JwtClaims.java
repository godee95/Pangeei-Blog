package inhatc.spring.blog.provider;

import java.math.BigInteger;

public class JwtClaims {
        private final BigInteger userId;
    private final String email;

    public JwtClaims(BigInteger userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }
}
