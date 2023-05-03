package net.codejava.user.api;

import javax.transaction.Transactional;

import net.codejava.user.Role;
import net.codejava.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.codejava.user.User;
import net.codejava.user.UserRepository;

import java.util.Set;

@Service
@Transactional
public class UserService {
	@Autowired private UserRepository repo;
	@Autowired private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;
	
	public User save(User user,Integer id) {
		String rawPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);

		if(id==1){
			//String name ="admin";
			Role admin =new Role(1);
		user.setRoles(Set.of(admin));
		} else if (id==2) {
			String name ="manager";
			Role manager =new Role(id,name);
			user.setRoles(Set.of(manager));
		}else if(id==3){
			String name ="worker";
			Role worker = new Role(id,name);
			user.setRoles(Set.of(worker));
		}else {
			throw new IllegalArgumentException("Role Not Found");
		}


		return repo.save(user);
	}
}
