/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.dpe.siigpe.ca.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import mg.dpe.siigpe.ca.model.SiigpeRole;
import mg.dpe.siigpe.ca.model.SiigpeUser;
import mg.dpe.siigpe.ca.service.SiigpeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author BasilisseN
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    SiigpeUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SiigpeUser user = userService.findByUsername(username);
        System.out.println("Username :" + username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(
                user.getUserName(), user.getUserPass(), getGrantedAuthorities(user.getSiigpeRoleList()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Collection<SiigpeRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleLib()));
        });
        return authorities;
    }

}
