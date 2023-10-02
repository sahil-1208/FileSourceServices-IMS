package com.multiple.datasource.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multiple.datasource.entity.user.UserDetails;

public interface UserRepo extends JpaRepository<UserDetails, Long>{

}
