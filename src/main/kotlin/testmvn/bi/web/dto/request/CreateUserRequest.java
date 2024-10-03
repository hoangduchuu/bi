package testmvn.bi.web.dto.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String email;
    private String fullname;
    private String phone;
    private String role;
    private String balance;
    private String password;
}