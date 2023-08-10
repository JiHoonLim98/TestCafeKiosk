package sample.cafekiosk.spring.api.service.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @since       2023.08.09
 * @author      jerry
 * @description mail service
 **********************************************************************************************************************/
@Service
@RequiredArgsConstructor
public class MailService {


    public boolean sendMail(String fromEmail, String toEmail, String subject, String content) {
    }
}
