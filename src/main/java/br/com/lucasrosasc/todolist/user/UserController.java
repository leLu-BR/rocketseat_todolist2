package br.com.lucasrosasc.todolist.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Modificador
 * Public
 * Private
 * Protected
 */

@RestController
@RequestMapping("/users")
public class UserController {

    /*A notação @Autowired coloca o gerenciamento do repositorio a cargo do Spring */
    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    /* o tipo ResponseEntity permite diferentes tipos de retorno */
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if (user != null) {
            // Mensagem de erro
            // Status Code
            /* 400 - erro de requisição que não */
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        //hashToString aceita chars, então o password foi convertido em array de chars com toCharArray
        var passwordHashed = BCrypt.withDefaults()
        .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashed);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}
