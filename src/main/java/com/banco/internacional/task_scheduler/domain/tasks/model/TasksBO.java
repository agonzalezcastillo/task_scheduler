package com.banco.internacional.task_scheduler.domain.tasks.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TasksBO{

    @JsonProperty("task_id")
    private String taskId;
    @JsonProperty("task_name")
    private String taskName;
    private int duration;
    private boolean assigned;

}
