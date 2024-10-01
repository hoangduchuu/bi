package testmvn.bi.service;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import testmvn.bi.domain.User;
import testmvn.bi.mapper.UserMapper;
import testmvn.bi.repository.IUserRepository;
import testmvn.bi.web.dto.UserDto;
import testmvn.bi.web.dto.request.GetUserCriteria;

//bean

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

  private final IUserRepository userRepository;
  private final UserMapper userMapper;
  @Override
  public Page<UserDto> getAllUsers(GetUserCriteria request, Pageable pageable) {
    var anhtop = new User();
    anhtop.setUsername("anhtop");

    return new PageImpl<>(Arrays.asList(userMapper.toDto(anhtop)), pageable, 1L);
  }
}
