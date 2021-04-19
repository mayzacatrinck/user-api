package challenge.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import challenge.api.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByDocumentOrEmail(String document, String email);

}
