package testmvn.bi.web.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String fullname;
    private String phone;
    private String role;
    private BigDecimal balance;
}