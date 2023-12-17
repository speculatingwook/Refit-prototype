package synApps.refit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synApps.refit.user.entity.user.User;
import synApps.refit.user.oauth.entity.ProviderType;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findByEmail(String email);

    User findByProviderTypeAndUserId(ProviderType providerType, String userId);

    User findByUserId(String userId);
    Boolean existsByUserId(String userId);
}
