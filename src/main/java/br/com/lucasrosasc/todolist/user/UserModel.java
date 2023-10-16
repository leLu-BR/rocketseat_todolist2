package br.com.lucasrosasc.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

//getters setters via Lombok com o comando @Data
@Data
@Entity(name = "tb_users")
public class UserModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    /*o @Column aqui funciona para mudar o nome da coluna na tabela do BD */
    @Column(name = "usuario", unique = true)
    private String username;
    private String name;
    private String password;

    /*informação(createdat) e automatização da data no BD(@CreationTimeStamp) */
    @CreationTimestamp
    private LocalDateTime createdAt;
       
    
}
