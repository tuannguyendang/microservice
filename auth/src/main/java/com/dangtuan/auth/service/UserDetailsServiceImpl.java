package com.dangtuan.auth.service;

import com.dangtuan.auth.dto.CustomGrantedAuthority;
import com.dangtuan.auth.dto.CustomUserDetails;
import com.dangtuan.auth.entity.Authorities;
import com.dangtuan.auth.entity.User;
import com.dangtuan.auth.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user != null) {
      CustomUserDetails customUserDetails = new CustomUserDetails();
      customUserDetails.setUserName(user.getUserName());
      customUserDetails.setPassword(user.getPassword());
      customUserDetails.setAccountExpired(user.getAccountExpired());
      customUserDetails.setAccountLocked(user.getAccountLocked());
      customUserDetails.setCredentialsExpired(user.getCredentialsExpired());
      customUserDetails.setEnabled(user.getEnabled());
      Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
      for (Authorities authority : user.getUserAuthorities()) {
//        authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        authorities.add(new CustomGrantedAuthority(authority.getAuthority()));
      }
      customUserDetails.setGrantedAuthorities(authorities);
      return customUserDetails;
    }
    throw new UsernameNotFoundException(username);
  }

}