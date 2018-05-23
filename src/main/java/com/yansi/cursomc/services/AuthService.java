package com.yansi.cursomc.services;

import com.yansi.cursomc.domain.Cliente;
import com.yansi.cursomc.error.ObjectNotFoundException;
import com.yansi.cursomc.repositories.ClienteRepository;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    private Random rand = new Random();

    public void sendNewPassword(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);

        if (cliente == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }

        String newPass = newPassword();
        cliente.setSenha(newPass);

        clienteRepository.save(cliente);
        emailService.sendNewPasswordEmail(cliente, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }

        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);

        if (opt == 0) { //gera numero
            return (char) (rand.nextInt(10) + 48);
        } else if (opt == 1) { //Letra maiuscula
            return (char) (rand.nextInt(26) + 65);
        } else { //gera numero
            return (char) (rand.nextInt(26) + 97);
        }
    }

}
