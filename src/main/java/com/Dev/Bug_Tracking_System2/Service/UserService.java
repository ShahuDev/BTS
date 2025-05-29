package com.Dev.Bug_Tracking_System2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.Dev.Bug_Tracking_System2.Repository.UserRepository;
import com.Dev.Bug_Tracking_System2.Model.User;
import com.Dev.Bug_Tracking_System2.Model.UserPrincipal;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	UserRepository userRepo;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public User saveUser(User user)
	{
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("User 404");
			
		}
		//return new org.springframework.security.core.user()
		return new UserPrincipal(user);
	}
	public User findByName(String name)
	{
		return userRepo.findByUsername(name);
	}

}
