package org.itstep.service;

import org.itstep.entity.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public Optional<Task> getById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Task> create(Task toDo) {
        return Optional.empty();
    }

    @Override
    public Optional<Task> update(Task toDo) {
        return Optional.empty();
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Task> getAllTasks(Map<String, ?> params) {
        return null;
    }
}
