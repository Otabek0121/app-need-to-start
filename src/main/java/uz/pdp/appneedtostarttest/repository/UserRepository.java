package uz.pdp.appneedtostarttest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appneedtostarttest.entity.User;
import uz.pdp.appneedtostarttest.net.ApiResult;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findFirstByEmailAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue( String email);

    boolean existsByEmail(String email);
}
