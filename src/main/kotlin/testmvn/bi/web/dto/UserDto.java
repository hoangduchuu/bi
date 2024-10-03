package testmvn.bi.web.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class UserDto {
    private Long userId;
    private String username;
    private String email;
    private String fullname;
    private String phone;
    private String role;
    private BigDecimal balance;
    private Instant createdAt;
    private Instant updatedAt;
}