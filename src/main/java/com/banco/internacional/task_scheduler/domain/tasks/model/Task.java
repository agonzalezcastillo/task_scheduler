package com.banco.internacional.task_scheduler.domain.tasks.model;

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
public class Task implements Serializable {

    @JsonProperty("hrs_jornada")
    private int workingDayHours;

    @JsonProperty("dias_termino")
    private int neededDays;

    @JsonProperty("jornadas")
    private List<Shift> shifts;

}
