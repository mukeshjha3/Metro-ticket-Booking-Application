package com.metro.Metro.service;

public interface MailSender {

	public boolean sendEmailOnLogin(String to, String username);
	public boolean sendEmailOnTicketbooking(String to, String username);
}
