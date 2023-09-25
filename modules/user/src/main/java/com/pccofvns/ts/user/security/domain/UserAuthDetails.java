package com.pccofvns.ts.user.security.domain;

import com.pccofvns.ts.domain.tables.pojos.Permission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserAuthDetails implements UserDetails {

    private final com.pccofvns.ts.domain.tables.pojos.User user;
    private final Set<GrantedAuthority> permissions;

    public UserAuthDetails(com.pccofvns.ts.domain.tables.pojos.User user, Set<String> permissions) {
        this.user = user;
        this.permissions = asGrantedAuthorityFromString(permissions);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }

    @Override
    public String getPassword() {
        return user.password();
    }

    @Override
    public String getUsername() {
        return user.username();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.nonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.nonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.credentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.enabled();
    }

    private Set<GrantedAuthority> asGrantedAuthorityFromString(Set<String> permissions) {
        return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toUnmodifiableSet());
    }
}
