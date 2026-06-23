package org.sujalkhot.utils;

import java.io.File;
import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtils {

	public static void sendEmail() throws Exception {

		String from = ConfigReader.get("senderEmail");

		String password = ConfigReader.get("senderPassword");

		String to = ConfigReader.get("receiverEmail");

		Properties prop = new Properties();

		prop.put("mail.smtp.host", "smtp.gmail.com");

		prop.put("mail.smtp.port", "587");

		prop.put("mail.smtp.auth", "true");

		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(

				prop,

				new Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

						return new PasswordAuthentication(from, password);

					}

				});

		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(from));

		message.setRecipients(

				Message.RecipientType.TO,

				InternetAddress.parse(to)

		);

		message.setSubject("Appium Automation Report");

		Multipart multipart = new MimeMultipart();

		MimeBodyPart body = new MimeBodyPart();

		body.setText("Automation execution completed.");

		multipart.addBodyPart(body);

		addAttachment(multipart, "test-output/emailable-report.html");

		addAttachment(multipart, "test-output/index.html");

		addAttachment(multipart, "reports/ExtentReport.html");

		File folder = new File("videos");

		File[] files = folder.listFiles();

		if (files != null) {

			for (File file : files) {

				if (file.getName().endsWith(".mp4")) {

					addAttachment(multipart, file.getPath());

				}

			}

		}

		message.setContent(multipart);

		Transport.send(message);

	}

	public static void addAttachment(

			Multipart multipart,

			String path

	) throws Exception {

		File file = new File(path);

		if (file.exists()) {

			MimeBodyPart part = new MimeBodyPart();

			part.attachFile(file);

			multipart.addBodyPart(part);

		}

	}

}
