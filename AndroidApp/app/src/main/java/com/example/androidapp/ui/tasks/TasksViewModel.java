package com.example.androidapp.ui.tasks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidapp.data.model.Task;
import com.example.androidapp.repository.TaskRepository;

import java.util.List;

public class TasksViewModel extends ViewModel {

    private TaskRepository taskRepository;

    public TasksViewModel() {
        taskRepository = TaskRepository.getInstance();
    }

    public LiveData<List<Task>> getTasks() {
        taskRepository.requestTasks();
        return taskRepository.getTasks();
    }

    public void addTask(Task newTask) {
        taskRepository.addTasks(newTask);
    }

    public void deleteTask(int id) {
        taskRepository.deleteTasks(id);
    }
}