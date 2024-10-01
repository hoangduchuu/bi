package testmvn.bi.mapper;

import org.mapstruct.Mapper;
import testmvn.bi.domain.User;
import testmvn.bi.web.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

}
