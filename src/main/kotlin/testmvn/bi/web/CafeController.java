package testmvn.bi.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import testmvn.bi.service.ICafeService;
import testmvn.bi.service.IUserService;
import testmvn.bi.web.dto.CafeDto;
import testmvn.bi.web.dto.UserDto;
import testmvn.bi.web.dto.request.GetCafeCriteria;
import testmvn.bi.web.dto.request.GetUserCriteria;

@RestController
@RequestMapping("/api/v1/cafes")
@RequiredArgsConstructor
@Tag(name = "cafe-v1-controller")
public class CafeController {
    private final ICafeService iCafeService;

    @GetMapping
    ResponseEntity<Page<CafeDto>> getAllUsers(
            @RequestParam(required = false) GetCafeCriteria query,
            @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok(iCafeService.getAllCafeList(query, pageable));
    }


}
