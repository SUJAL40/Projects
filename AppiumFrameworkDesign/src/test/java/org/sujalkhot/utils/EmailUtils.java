package org.sujalkhot.utils;

import java.io.File;
import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.Session; //this two library provide email functionality

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtils {

	public static void sendEmail() throws Exception {

		String from = ConfigReader.get("senderEmail");

		String password = ConfigReader.get("senderPassword");

		String to = ConfigReader.get("receiverEmail");

		Properties prop = new Properties(); // Creates container to store Gmail configuration.

		prop.put("mail.smtp.host", "smtp.gmail.com"); // SMTP = Simple Mail Transfer Protocol
														// "Use Gmail's SMTP server to send the email."

		prop.put("mail.smtp.port", "587"); // Port number (587 for TLS)

		prop.put("mail.smtp.auth", "true"); // Gmail username/password required

		prop.put("mail.smtp.starttls.enable", "true"); // Secure connection

		Session session = Session.getInstance( // Creates email session.

				prop,

				new Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() { // Called automatically by Gmail.

						return new PasswordAuthentication(from, password);

					}

				});

		Message message = new MimeMessage(session); // Creates actual email.

		message.setFrom(new InternetAddress(from)); // Set Sender

		message.setRecipients( 					   // Set Receiver

				Message.RecipientType.TO,

				InternetAddress.parse(to)

		);

		message.setSubject("Appium Automation Report"); // Add Subject

		Multipart multipart = new MimeMultipart(); // Because email contains multiple parts:

		MimeBodyPart body = new MimeBodyPart(); // Creates text section container.

		body.setText("Automation execution completed."); // Set Text

		multipart.addBodyPart(body); // Add Text

		addAttachment(multipart, "test-output/emailable-report.html"); // Attach File

		addAttachment(multipart, "test-output/index.html"); // Attach File

		addAttachment(multipart, "reports/ExtentReport.html"); // Attach File

		File folder = new File("videos");

		File[] files = folder.listFiles();

		if (files != null) {

			for (File file : files) {

				if (file.getName().endsWith(".mp4")) {

					addAttachment(multipart, file.getPath());

				}

			}

		}

		message.setContent(multipart); // Set Email Content

		Transport.send(message); // Send Email

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

//	EmailUtil.sendEmail() → Load Sender Email → Load App Password → Load Receiver Email
//	→ Create SMTP Properties → Set Gmail Server → Set Port 587 → Enable Authentication 
//	→ Enable TLS Security → Create Session → Create Authenticator → Provide Credentials 
//	→ Create Empty Email → Set Sender → Set Receiver → Set Subject → Create Body
//	→ Load Report File → Create Multipart → Add Body → Add Attachment → Build Final Email
//	→ Transport.send() → Gmail SMTP Server → Authenticate User → Accept Email → Deliver Email → Print "Email sent"







