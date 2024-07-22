package backend.gamification.service.security;

import backend.gamification.entity.user.User;
import backend.gamification.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("Required login information is empty");
        }
        if (username.isEmpty()) {
            throw new UsernameNotFoundException("Required login information is empty");
        }
        User user = userRepository.findUserByUsername(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return new CustomUserDetails(user);
    }
}
