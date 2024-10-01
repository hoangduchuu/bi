package testmvn.bi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import testmvn.bi.web.dto.CafeDto;
import testmvn.bi.web.dto.request.GetCafeCriteria;

public interface ICafeService {
  Page<CafeDto> getAllCafeList(GetCafeCriteria request, Pageable pageable);
}
