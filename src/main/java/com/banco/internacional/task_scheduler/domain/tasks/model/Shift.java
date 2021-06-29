package com.banco.internacional.task_scheduler.domain.tasks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shift implements Serializable {

    @JsonProperty("duracion")
    private int duration;

    @JsonIgnore
    private boolean assigned;

    @JsonProperty("tareas")
    private List<String> tasks;

}
