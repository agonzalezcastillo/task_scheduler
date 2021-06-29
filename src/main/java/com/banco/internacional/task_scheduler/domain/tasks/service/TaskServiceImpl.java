package com.banco.internacional.task_scheduler.domain.tasks.service;

import com.banco.internacional.task_scheduler.domain.tasks.model.Shift;
import com.banco.internacional.task_scheduler.domain.tasks.model.Task;
import com.banco.internacional.task_scheduler.domain.tasks.model.TasksBO;
import com.banco.internacional.task_scheduler.domain.tasks.model.TasksDto;
import com.banco.internacional.task_scheduler.infraestructure.TaskClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final static int MAX_SHIFT_DURATION= 8;

    private TaskClient taskClient;

    @Override
    public Task getTasks() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        int id = 0;
        List<Shift> shifts =  new ArrayList<>();
        Task task = Task.builder()
                .neededDays(0)
                .shifts(shifts)
                .build();
        try {
            log.info("TaskServiceImpl.getTasks -> attempt call to external service");
            List<TasksDto> tasksDto = taskClient.getTasks().getBody();
            log.info("TaskServiceImpl.getTasks -> call to external service successful");
            List<TasksBO> tasksBo = om.convertValue(tasksDto, new TypeReference<List<TasksBO>>() {});

            tasksBo.stream()
                    .filter(t -> t.getDuration() == MAX_SHIFT_DURATION)
                    .forEach(t2 -> {
                        Shift shift = Shift.builder()
                                .tasks(new ArrayList<>())
                                .build();
                        shift.getTasks().add(t2.getTaskId());
                        shift.setDuration(t2.getDuration());
                        shifts.add(shift);
                        task.setNeededDays(task.getNeededDays()+1);
                    });

         List<TasksBO> tasksLesserThanEight = tasksBo.stream()
                 .filter(t -> t.getDuration() < MAX_SHIFT_DURATION)
                 .collect(Collectors.toList());

         this.assignShifts(tasksLesserThanEight, task);
         task.setWorkingDayHours(MAX_SHIFT_DURATION);
         task.setNeededDays(task.getShifts().size());

        }catch(Exception e){
            log.error(e.getMessage(), e);
        }
        return task;
    }

    private void assignShifts(List<TasksBO> tasksBOList, Task task){
            List<TasksBO> localList = tasksBOList.stream().filter(t->!t.isAssigned()).collect(Collectors.toList());
            if(localList.isEmpty())
                return;
            for(TasksBO tBo: localList){
                Shift shift = task.getShifts().stream().filter(
                    s -> s.getDuration() + tBo.getDuration() <= MAX_SHIFT_DURATION &&
                    !tBo.isAssigned()
                ).findFirst().orElse(Shift.builder().build());
                if(shift.getTasks() == null)
                    shift.setTasks(new ArrayList<>());
                if (shift.getDuration() + tBo.getDuration() <= MAX_SHIFT_DURATION) {
                    shift.getTasks().add(tBo.getTaskId());
                    shift.setDuration(shift.getDuration() + tBo.getDuration());
                    if(!shift.isAssigned())
                        task.getShifts().add(shift);
                    tBo.setAssigned(Boolean.TRUE);
                    shift.setAssigned(Boolean.TRUE);
                }
            }
            assignShifts(tasksBOList, task);
    }

}
