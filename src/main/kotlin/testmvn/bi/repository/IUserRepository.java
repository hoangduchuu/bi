package testmvn.bi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testmvn.bi.domain.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}
