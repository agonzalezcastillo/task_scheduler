package com.banco.internacional.task_scheduler.infraestructure;

import com.banco.internacional.task_scheduler.domain.tasks.model.TasksDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name="tasks", url = "http://localhost:8080")
public interface TaskClient {

    @RequestMapping(value ="/generator/schedule/tasks", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<List<TasksDto>> getTasks();

}
