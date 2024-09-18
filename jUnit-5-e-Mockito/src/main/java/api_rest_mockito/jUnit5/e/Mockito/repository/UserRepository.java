package api_rest_mockito.jUnit5.e.Mockito.repository;

import api_rest_mockito.jUnit5.e.Mockito.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
