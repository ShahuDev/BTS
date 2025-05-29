/*
	* the register and login methods are working perfectly.
 */
package com.Dev.Bug_Tracking_System2.Controller;
import com.Dev.Bug_Tracking_System2.Model.Role;
import com.Dev.Bug_Tracking_System2.Model.User;
import com.Dev.Bug_Tracking_System2.Service.EmailService;
import com.Dev.Bug_Tracking_System2.Service.JwtService;
import com.Dev.Bug_Tracking_System2.Service.UserService;
import com.Dev.Bug_Tracking_System2.dtos.UserRequestDTO;
import com.Dev.Bug_Tracking_System2.dtos.UserRequestLoginDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	EmailService emailService;
	
	@Value("${admin.email}") // ✅ Fetch value from properties
    private String adminEmail;
	
	@PostMapping("/register")
	public User register(@RequestBody UserRequestDTO userDto)
	{
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setRole(Role.USER); // the newly registered user will be by default user only.
		User saved = userService.saveUser(user);
		//sendEmailToAdmin(user);  // to send mail to admin.
		return saved;
	}
	
	@GetMapping("/admin/home")
	public String adminHome()
	{
		return "Welcome to home page admin.";
	}
	@GetMapping("/buyer/home")
	public String buyerHome()
	{
		return "Welcome to home page buyer";
	}
	@GetMapping("/seller/home")
	public String sellerHome()
	{
		return "Welcome to home page seller";
	}
	
	@PostMapping("/login")
	public String login(@RequestBody UserRequestLoginDTO userDto)
	{
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.name(),userDto.password()));
		if(authentication.isAuthenticated())
		{
			return jwtService.generateToken(userDto.name());
		}
		else
			return "failure";
	}
	
	@SuppressWarnings("unused")
	private void sendEmailToAdmin(User user) {
		var subject = "New user registered";
		String text = """
				Hey Admin!,
				A new user have been registered!
				user Id : %d
				username : %s
				email : %s
				role : %s
				""".formatted(user.getUserId(),user.getUsername(),user.getEmail(),user.getRole());
		
		emailService.sendEmail(adminEmail, subject, text);
	}
}
