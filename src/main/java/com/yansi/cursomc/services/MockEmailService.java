package com.yansi.cursomc.services;

import com.yansi.cursomc.services.abstracts.AbstractEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Simulando o Envio de email");
        LOG.info(msg.toString());
        LOG.info("Email enviado");
    }

}
