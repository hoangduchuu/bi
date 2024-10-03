package testmvn.bi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import testmvn.bi.domain.User;
import testmvn.bi.web.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

    @Override
    @Mapping(target = "userId", ignore = true)
    User toEntity(UserDto dto);

    @Override
    @Mapping(target = "userId", ignore = true)
    void updateEntity(@MappingTarget User entity, UserDto dto);

    @Override
    @Mapping(target = "userId", ignore = true)
    void partialUpdateEntity(@MappingTarget User entity, UserDto dto);


}