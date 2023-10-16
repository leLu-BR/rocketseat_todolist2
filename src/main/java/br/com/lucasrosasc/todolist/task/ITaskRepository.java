package br.com.lucasrosasc.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ITaskRepository extends JpaRepository<TaskModel, UUID>{
    List<TaskModel> findByIdUser(UUID idUser);
    /*Outra forma de conferir se user é o dono da tarefa. 
    TaskModel findByIdAndByIdUser(UUID id, UUID idUser);
    A declaração do método ocorre na interface e a implementação no controller.*/
}
