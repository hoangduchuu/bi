package testmvn.bi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import testmvn.bi.domain.User;
import testmvn.bi.mapper.UserMapper;
import testmvn.bi.repository.ICafeRepository;
import testmvn.bi.repository.IUserRepository;
import testmvn.bi.web.dto.CafeDto;
import testmvn.bi.web.dto.UserDto;
import testmvn.bi.web.dto.request.GetCafeCriteria;
import testmvn.bi.web.dto.request.GetUserCriteria;

import java.util.Arrays;

//bean

@Service
@RequiredArgsConstructor
public class CafeServiceImpl implements ICafeService {

    private final ICafeRepository cafeRepository;
    private final UserMapper userMapper;

    @Override
    public Page<CafeDto> getAllCafeList(GetCafeCriteria request, Pageable pageable) {

        CafeDto cafeDto1 = new CafeDto();
        cafeDto1.setUsername("username1");
        // create 15 mock
        CafeDto cafeDto2 = new CafeDto();
        cafeDto2.setUsername("username2");
        CafeDto cafeDto3 = new CafeDto();
        cafeDto3.setUsername("username3");
        CafeDto cafeDto4 = new CafeDto();
        cafeDto4.setUsername("username4");
        CafeDto cafeDto5 = new CafeDto();
        cafeDto5.setUsername("username5");
        CafeDto cafeDto6 = new CafeDto();
        cafeDto6.setUsername("username6");
        CafeDto cafeDto7 = new CafeDto();
        cafeDto7.setUsername("username7");
        CafeDto cafeDto8 = new CafeDto();
        cafeDto8.setUsername("username8");
        CafeDto cafeDto9 = new CafeDto();
        cafeDto9.setUsername("username9");
        CafeDto cafeDto10 = new CafeDto();
        cafeDto10.setUsername("username10");
        CafeDto cafeDto11 = new CafeDto();
        cafeDto11.setUsername("username11");
        CafeDto cafeDto12 = new CafeDto();
        cafeDto12.setUsername("username12");
        CafeDto cafeDto13 = new CafeDto();
        cafeDto13.setUsername("username13");
        CafeDto cafeDto14 = new CafeDto();
        cafeDto14.setUsername("username14");
        CafeDto cafeDto15 = new CafeDto();
        cafeDto15.setUsername("username15");

        return new PageImpl<>(Arrays.asList(cafeDto1, cafeDto2, cafeDto3, cafeDto4, cafeDto5, cafeDto6, cafeDto7, cafeDto8, cafeDto9, cafeDto10, cafeDto11, cafeDto12, cafeDto13, cafeDto14, cafeDto15), pageable, 15);
    }
}
