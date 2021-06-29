package com.banco.internacional.task_scheduler.application;

import com.banco.internacional.task_scheduler.domain.tasks.model.Task;
import com.banco.internacional.task_scheduler.domain.tasks.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class TasksController implements TasksApi{

    private TaskService taskService;

    @Override
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Task> getTasks() {
        try {
            return new ResponseEntity<Task>(taskService.getTasks(), HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
