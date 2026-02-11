package com.kubecloudpipeline.task.controller;

import com.kubecloudpipeline.task.model.Task;
import com.kubecloudpipeline.task.service.TaskService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing Tasks.
 * Provides CRUD endpoints.
 */
@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Task API", description = "Operations related to Task management")
public class TaskResource {

    @Inject
    TaskService taskService;

    @GET
    @Operation(summary = "List all tasks", description = "Returns a list of all available tasks")
    @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)))
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get a task by ID", description = "Returns a single task matching the specific ID")
    @APIResponse(responseCode = "200", description = "Task found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)))
    @APIResponse(responseCode = "404", description = "Task not found")
    public Response getTaskById(@PathParam("id") String id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            return Response.ok(task.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Operation(summary = "Create a new task", description = "Creates a new task and returns the created entity")
    @APIResponse(responseCode = "201", description = "Task created",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)))
    public Response createTask(@Valid Task task) {
        Task createdTask = taskService.createTask(task);
        return Response.status(Response.Status.CREATED).entity(createdTask).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a task", description = "Updates an existing task by ID")
    @APIResponse(responseCode = "200", description = "Task updated",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)))
    @APIResponse(responseCode = "404", description = "Task not found")
    public Response updateTask(@PathParam("id") String id, @Valid Task task) {
        Optional<Task> updatedTask = taskService.updateTask(id, task);
        if (updatedTask.isPresent()) {
            return Response.ok(updatedTask.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a task", description = "Deletes a task by ID")
    @APIResponse(responseCode = "204", description = "Task deleted")
    @APIResponse(responseCode = "404", description = "Task not found")
    public Response deleteTask(@PathParam("id") String id) {
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
