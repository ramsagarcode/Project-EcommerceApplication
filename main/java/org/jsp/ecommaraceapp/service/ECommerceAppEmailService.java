package org.jsp.ecommaraceapp.service;

import org.jsp.ecommaraceapp.model.Merchant;
import org.jsp.ecommaraceapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.Address;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import static org.jsp.ecommaraceapp.util.ApplicationConstants.VERIFY_LINK;//import from util package

@Service
public class ECommerceAppEmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public String sendWelcomeMail(Merchant merchant, HttpServletRequest request) {
		String siteUrl = request.getRequestURI().toString();
		String url = siteUrl.replace(request.getServletPath(), "");
		String actual_url = url + VERIFY_LINK + merchant.getToken();
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(merchant.getEmail());
			helper.setSubject("Active your account");
			helper.setText(actual_url);
			javaMailSender.send(message);

			return "verification mail has been sent";
		} catch (Exception e) {
			return "cannot send verification";
		}

	}

	public String sendWelcomeMail(User user, HttpServletRequest request) {
		String siteUrl = request.getRequestURI().toString();
		String url = siteUrl.replace(request.getServletPath(), "");
		String actual_url = url + VERIFY_LINK + user.getToken();
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(user.getEmail());
			helper.setSubject("Active your account");
			helper.setText(actual_url);
			javaMailSender.send(message);

			return "verification mail has been sent";
		} catch (Exception e) {
			return "cannot send verification";
		}

	}
	
}
