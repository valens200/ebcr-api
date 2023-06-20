package com.ebcr.services;


import com.ebcr.exceptions.BadRequestException;
import com.ebcr.models.Admin;
import com.ebcr.utils.types.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class IMailService {
  @Value("${client.host}")
  private String clientHost;

  private final JavaMailSender mailSender;
  private final SpringTemplateEngine templateEngine;
    @Value("${spring.mail.username}")
  private String appEmail;

  @Autowired
  public IMailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
    this.mailSender = mailSender;
    this.templateEngine = templateEngine;
  }


  public void sendAccountVerificationEmail(Admin user){
      String link = clientHost + "/verify-email?email=" + user.getEmail() + "&code=" + user.getActivationCode();
      Mail mail = new Mail("Welcome to EBCR management portal", user.getFullName(), user.getEmail(), "verify-email", link);
      log.info("Email : {}", mail);
      sendMessage(mail);
  }

  @Async
public void sendMessage(Mail mail){
     try{
       MimeMessage message = mailSender.createMimeMessage();
       MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
       Context context = new Context();
       context.setVariable("data", mail.getData());
       context.setVariable("name", mail.getFullNames());
       context.setVariable("otherData", mail.getOtherData());
       context.setVariable("additionalData", mail.getAdditionalData());
       context.setVariable("additional", mail.getAdditional());

       String html = templateEngine.process(mail.getTemplate(), context);
       helper.setTo(mail.getToEmail());

       helper.setFrom(appEmail);
       helper.setSubject(mail.getSubject());
       helper.setText(html, true);
       mailSender.send(message);
       log.info("Message sent successfully");
     }catch (Exception e){
           throw  new BadRequestException("Failed to send the message" + e.getMessage());
     }
  }
}
