package io.otakuoracle.repositories;

import io.otakuoracle.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo,String> {

    @Query(value = "SELECT * FROM otakuoracle.user_table u WHERE u.user_name=:username",nativeQuery = true)
    Optional<UserInfo> getUserInfoByName(String username);
}
