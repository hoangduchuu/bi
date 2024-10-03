package testmvn.bi.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import testmvn.bi.service.IUserService;
import testmvn.bi.web.dto.UserDto;
import testmvn.bi.web.dto.request.CreateUserRequest;
import testmvn.bi.web.dto.request.GetUserCriteria;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "user-v1-controller")
public class UserController {
    private final IUserService userService;

    @GetMapping
    ResponseEntity<Page<UserDto>> getAllUsers(@RequestParam(required = false) GetUserCriteria query, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(query, pageable));
    }


    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody CreateUserRequest request) {
        try {
            UserDto savedUser = userService.saveUser(request);
            return ResponseEntity.ok(savedUser);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "An unexpected error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
