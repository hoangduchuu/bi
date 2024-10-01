package testmvn.bi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testmvn.bi.domain.Cafe;
import testmvn.bi.domain.User;

import java.util.List;

@Repository
public interface ICafeRepository extends JpaRepository<Cafe, Long> {
    List<Cafe> findAllByBrand(String brand);
}
