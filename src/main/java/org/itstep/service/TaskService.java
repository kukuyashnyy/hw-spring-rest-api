package org.itstep.service;

import org.itstep.entity.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TaskService {
    List<Task> getAllTasks();

    Optional<Task> getById(String id);

    Optional<Task> create(Task toDo);

    Optional<Task> update(Task toDo);

    void delete(String id);

    List<Task> getAllTasks(Map<String, ?> params);
}
