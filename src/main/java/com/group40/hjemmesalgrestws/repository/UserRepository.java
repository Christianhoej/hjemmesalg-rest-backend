package com.group40.hjemmesalgrestws.repository;

import com.group40.hjemmesalgrestws.entitiy.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, String> { //String pga. email er id

    //@Query("select user from UserEntity user where user.email=:email")
    UserEntity findByEmail(String email);
    UserEntity findUserEntityByEmail(String email);
}
