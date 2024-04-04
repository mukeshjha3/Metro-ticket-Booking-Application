package com.metro.Metro.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.metro.Metro.model.User;
import com.metro.Metro.service.MailSender;
import com.metro.Metro.service.UserService;

// This will send the Email to user, everytime the user is logged-into the application

@Component
public class LoginEvent {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private UserService userService;
	
	
	//this event will be triggered on successfull login by user
	
//	@Async
	@EventListener
	public void AuthenticationSuccessEventListener(AuthenticationSuccessEvent e) {
		Authentication authentication = e.getAuthentication();
		String email = authentication.getName();
		System.out.println("Email is :" +email);
		User user = userService.getUser(email);
		mailSender.sendEmailOnLogin(email, user.getFirstName());
		
	}
}
