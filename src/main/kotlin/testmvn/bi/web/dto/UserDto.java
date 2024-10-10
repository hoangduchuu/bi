package testmvn.bi.web.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Data
public class UserDto {
    private Long userId;
    private String username;
    private String email;
    private String fullname;
    private String phone;
    private Set<String> roles;  // Changed from String to Set<String>
    private BigDecimal balance;
    private Instant createdAt;
    private Instant updatedAt;

    // You might want to add some convenience methods

    public boolean hasRole(String roleName) {
        return roles != null && roles.contains(roleName);
    }

    public String getPrimaryRole() {
        return roles != null && !roles.isEmpty() ? roles.iterator().next() : null;
    }
}