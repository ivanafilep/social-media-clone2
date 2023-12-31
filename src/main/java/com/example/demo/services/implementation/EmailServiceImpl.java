package com.example.demo.services.implementation;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Email;
import com.example.demo.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	
	public final JavaMailSender emailSender;

	public EmailServiceImpl(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	@Override
	public void sendSimpleMessage(Email email) {
		System.out.println("email je" + email.getTo() + email.getSubject() + email.getText());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email.getTo());
		message.setSubject(email.getSubject());
		message.setText(email.getText());
		emailSender.send(message);
		System.out.println("email je poslat");
	}
	
}
