package com.ebcr.services;


import com.ebcr.exceptions.BadRequestException;
import com.ebcr.models.User;
import com.ebcr.utils.types.Mail;
import javassist.tools.web.BadHttpRequest;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@Slf4j
public class MailService {
    @Value("${client.host}")
    private String clientHost;

    private JavaMailSender


    @Async
    public  void sendEmail(Mail mail){
        try{

        }catch (MessagingException exception){
               throw new BadRequestException("Failed to send mail");
        }
    }
  public void sendAccountVerificationEmail(User user){
     String link = clientHost + "/verify-email?email=" + user.getEmail() + "&code=" + user.getActivationCode();
      Mail mail = new Mail("Welcome to EBCR management portal", user.getFullName(), user.getEmail(), "verify-email ", link);
      log.info("Email : {}", mail);
  }

}
