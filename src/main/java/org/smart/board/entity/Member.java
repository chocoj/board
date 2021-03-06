package org.smart.board.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member implements UserDetails {
    private String usrid;
    private String usrpwd;
    private String usrname;
    private String email;
    private boolean enabled;
    private String rolename;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return usrpwd;
    }

    @Override
    public String getUsername() {
        return usrid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }


    /* 시큐리티에서는 username이 id와 같은 역할을 수행하도록 설계되었음*/

}
