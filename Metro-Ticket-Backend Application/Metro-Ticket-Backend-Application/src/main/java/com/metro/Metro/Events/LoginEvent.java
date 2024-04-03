package com.metro.Metro.Events;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

// This will send the Email to user, everytime the user is logged-into the application

@Component
public class LoginEvent {

	@EventListener
	public void AuthenticationSuccessEventListener(AuthenticationSuccessEvent e) {
		System.out.println("User Logged in Successfully with token... ");
	}
}
