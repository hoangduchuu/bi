package testmvn.bi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import testmvn.bi.web.dto.UserDto;
import testmvn.bi.web.dto.request.CreateUserRequest;
import testmvn.bi.web.dto.request.GetUserCriteria;

public interface IUserService {
    // save user to database
    UserDto saveUser(@RequestBody CreateUserRequest request);

    Page<UserDto> getAllUsers(@RequestBody GetUserCriteria request, Pageable pageable);
}
