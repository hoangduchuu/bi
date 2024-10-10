package testmvn.bi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import testmvn.bi.domain.User;
import testmvn.bi.domain.Role;
import testmvn.bi.web.dto.UserDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", source = "roles")
    UserDto toDto(User user);

    List<UserDto> toDto(List<User> users);

    default Set<String> mapRoles(Set<Role> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}