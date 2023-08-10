package com.example.oauth.vo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.thymeleaf.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Collections;

@Data
@Entity
public class User implements UserDetails {

    @Id
    @Column(length = 20)
    private String id;

    @Column(length = 100)
    private String password;

    @Column(length = 100)
    private String nickname;

    private String state; // Y : 정상 회원, L : 잠긴 계정, P : 패스워드 만료, A : 계정 만료

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    // 계정 만료
    @Override
    public boolean isAccountNonExpired() {
        return !StringUtils.equalsIgnoreCase(state, "A");
    }

    // 잠긴 계정
    @Override
    public boolean isAccountNonLocked() {
        return !StringUtils.equalsIgnoreCase(state, "L");
    }

    // 패스워드 만료
    @Override
    public boolean isCredentialsNonExpired() {
        return !StringUtils.equalsIgnoreCase(state, "P");
    }

    @Override
    public boolean isEnabled() {
        return isCredentialsNonExpired() && isAccountNonExpired() && isAccountNonLocked();
    }

}
