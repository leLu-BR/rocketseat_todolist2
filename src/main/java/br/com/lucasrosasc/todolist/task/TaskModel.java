package br.com.lucasrosasc.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

 /**
     * ID
     * Usuario (ID_usuario)
     * Descricao
     * Titulo
     * Data de inicio
     * Data de termino
     * Prioridade
     */
    
@Data
@Entity(name = "tb_tasks")
public class TaskModel {
    
   
     @Id
     @GeneratedValue(generator = "UUID")
     private UUID id;
     private String description;

     @Column(length = 50)
     private String title;
     private LocalDateTime startAt;
     private LocalDateTime endAt;
     private String priority;

     private UUID idUser;

     @CreationTimestamp
     private LocalDateTime createdAt;

     /*{método} throws Exception faz com que a responsabilidade de tratar a exceção seja repassada
      * pra quem chama o método/pra camada acima.
     */
     public void setTitle(String title) throws Exception{
          if (title.length() > 50) {
               /*Exception é a exceção mais genérica do Java.
                * Existem exceções tratáveis(ocasionadas pelo user, erro de dados, erro de validação) e 
                as não tratáveis(ocasionadas pelo sistema, e.g. sistema fora do ar)
                Exception é do tipo tratável.*/
               throw new Exception("O campo title deve conter no máximo 50 caracteres");
          }
          this.title = title;
     }
     
}
