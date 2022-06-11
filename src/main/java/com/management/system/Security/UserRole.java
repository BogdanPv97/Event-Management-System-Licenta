package com.management.system.Security;

import com.google.common.collect.Sets;
import com.management.system.Security.UserPermissions.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.management.system.Security.UserPermissions.*;


public enum UserRole {
    ADMIN(Sets.newHashSet(EVENT_READ, EVENT_WRITE, TICKET_READ, TICKET_WRITE, BILL_READ, BILL_WRITE)),
    USER(Sets.newHashSet(EVENT_READ,TICKET_READ, BILL_READ));


    private final Set<UserPermissions> permissions;

    UserRole(Set<UserPermissions> permissions){
        this.permissions = permissions;
    }

    public Set<UserPermissions> getPermissions(){
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> collect = getPermissions()
                .stream()
                .map(e -> new SimpleGrantedAuthority(e.getPermission()))
                .collect(Collectors.toSet());
        collect.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return collect;
    }
}
