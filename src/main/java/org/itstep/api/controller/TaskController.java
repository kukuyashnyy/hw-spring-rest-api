package org.itstep.api.controller;

import org.itstep.entity.Task;
import org.itstep.repository.TaskRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/todolist")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public ResponseEntity<?> allTask(@RequestParam(required = false, defaultValue = "20") int size,
                                     @RequestParam(required = false, defaultValue = "1") int page,
                                     @RequestParam(required = false) String sort) {
//        System.out.println("size = " + size + ", page = " + page + ", sort = " + sort);
//
//        try {
//            Pageable pageable;
//            if (sort == null){
//                pageable = PageRequest.of(page, size);
//            } else {
//                pageable = PageRequest.of(page, size, Sort.by(sort));
//            }
//
//            Page<Task> pageTasks = taskRepository.findAll(pageable);
//            System.out.println(pageTasks.toString());
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("tasks", pageTasks.getContent());
//            response.put("currentPage", pageTasks.getNumber());
//            response.put("totalItems", pageTasks.getTotalElements());
//            response.put("totalPages", pageTasks.getTotalPages());
//
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Task> taskById(@PathVariable int id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//???
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Task task) {
        try {
            task = taskRepository.save(task);
            return ResponseEntity.created(URI.create("/api/v1/todolist" + task.getId())).body(task);
        } catch (Throwable throwable) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(throwable.getLocalizedMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Task task) {
        try {
            task.setId(id);
            System.out.println("id = " + id + ", task = " + task);
            task = taskRepository.saveAndFlush(task);
            return ResponseEntity.ok(task); // 200
        } catch (Throwable ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (Throwable ex) {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}
