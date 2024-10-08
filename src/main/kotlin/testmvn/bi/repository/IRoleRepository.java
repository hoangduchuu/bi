package testmvn.bi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testmvn.bi.domain.Role;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}