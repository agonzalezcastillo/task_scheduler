package com.banco.internacional.task_scheduler.domain.tasks.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TasksDto implements Serializable {

    @JsonProperty("task_id")
    private String taskId;
    @JsonProperty("task_name")
    private String taskName;
    private int duration;


}
