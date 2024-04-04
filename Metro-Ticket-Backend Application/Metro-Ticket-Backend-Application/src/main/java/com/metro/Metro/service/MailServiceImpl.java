package com.metro.Metro.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailSender{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	public boolean sendEmailOnLogin(String to, String username) {

		LocalDateTime ld = LocalDateTime.now();
		DateTimeFormatter fd =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM");
	
		String formatterDate = ld.format(fd);
		
		final String subject = "Welcome back to Kolkata Metro ticket booking app";

		final String body = "Dear " + username + ",\n\n"
				+ "we are trilled to see you back at kolkata metro ticket booking app.Thank you for choosing us as your platform of choice." +",\n\n" 
				+ "Your login on " + formatterDate + " confirms your interest in kolkata metro booking application and we are excited to continue providing you with exception service and experences. "
				+ "if you have any questions, feedback, or need assistance with anything.Please don't hesitate to reach out to our support team at mukeshjhatest33@gmail.com.\n\n" + 
				"Best Regards,\n" + "Mukesh Jha";

		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("mukeshjhatest33@gmail.com");
			message.setTo(to);
			message.setSubject(subject);
			message.setText(body);
			javaMailSender.send(message);
			System.out.println("Mail sent");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}



	@Override
	public boolean sendEmailOnTicketbooking(String to, String username) {
		// TODO Auto-generated method stub
		return false;
	}
}
