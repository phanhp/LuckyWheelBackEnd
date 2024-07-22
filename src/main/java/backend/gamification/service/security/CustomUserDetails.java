package backend.gamification.service.security;

import backend.gamification.entity.user.Role;
import backend.gamification.entity.user.User;
import backend.gamification.service.management.user.RoleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@Data
@SessionScope
public class CustomUserDetails implements UserDetails {
    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Autowired
    RoleService roleService;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authoritySet = new HashSet<>();
        List<Role> roleList = roleService.findRoleListByUserId(user.getId());
        for (int i = 0; i < roleList.size(); i++) {
            authoritySet.add(new SimpleGrantedAuthority(roleList.get(i).getName()));
        }
        return authoritySet;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        String username = user.getUsername();
        if (username != null) {
            if (!username.isEmpty()) {
                return username;
            }
        }

        return "";
    }

    public Map<String, String> getUsernameMap() {
        Map<String, String> usernameMap = new HashMap<>();
        usernameMap.put("username", user.getUsername());
        return usernameMap;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isActive();
    }

    @Override
    public boolean isEnabled() {
        return user.isAvailable();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
}
