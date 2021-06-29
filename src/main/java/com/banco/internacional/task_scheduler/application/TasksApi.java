package com.banco.internacional.task_scheduler.application;

import com.banco.internacional.task_scheduler.domain.tasks.model.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/tasks")
public interface TasksApi {

    @Operation(summary = "Get PoHeaders enrich.", description = "Get PoHeaders enrich.", tags = { "" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PoHeaders information requested", content = @Content(schema = @Schema(implementation = Boolean.class))),

            @ApiResponse(responseCode = "400", description = "Bad Request"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found"),

            @ApiResponse(responseCode = "500", description = "Internal server error"),

            @ApiResponse(responseCode = "501", description = "Not Implemented") })
    @GetMapping
    ResponseEntity<Task> getTasks();

}
