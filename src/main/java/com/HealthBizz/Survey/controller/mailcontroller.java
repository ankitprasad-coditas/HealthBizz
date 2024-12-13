package com.HealthBizz.Survey.controller;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hi")
public class mailcontroller {

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/sendEmail")
    public String email(@RequestParam("mail") String email) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setTo(email);
        helper.setSubject("");
        helper.setText("Hi from Ankit?");

        javaMailSender.send(mimeMessage);

        return "MAil Sent";
    }
}
