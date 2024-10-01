package testmvn.bi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import testmvn.bi.web.dto.UserDto;
import testmvn.bi.web.dto.request.GetUserCriteria;

public interface IUserService {
  Page<UserDto> getAllUsers(GetUserCriteria request, Pageable pageable);
}
