package com.menekse.airlines.security;

import com.menekse.airlines.model.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {
    private final Long userId;
    private final String firstName;
    private final String lastName;

    public CustomUserDetails(UserEntity userEntity, Collection<? extends GrantedAuthority> authorities) {
        super(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isActive(),
                true,
                true,
                true,
                authorities);
        this.userId = userEntity.getId();
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
    }

    public CustomUserDetails(UserEntity userEntity, String password, Collection<? extends GrantedAuthority> authorities) {
        super(userEntity.getUsername(),
                password,
                userEntity.isActive(),
                true,
                true,
                true,
                authorities);
        this.userId = userEntity.getId();
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
    }
}