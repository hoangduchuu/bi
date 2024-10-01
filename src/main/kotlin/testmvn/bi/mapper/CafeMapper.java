package testmvn.bi.mapper;

import org.mapstruct.Mapper;
import testmvn.bi.domain.Cafe;
import testmvn.bi.domain.User;
import testmvn.bi.web.dto.CafeDto;
import testmvn.bi.web.dto.UserDto;

@Mapper(componentModel = "spring")
public interface CafeMapper extends EntityMapper<CafeDto, Cafe> {

}
