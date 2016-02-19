package com.eis.cloud.service;

import com.eis.cloud.model.TodoModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

public class TodoService {
    @HystrixCommand(fallbackMethod = "onFailedGetToDo")
    public TodoModel getTodo() {

        TodoModel t = new TodoModel();
        t.setTitle("Test");
        t.setFinished(false);
        return t;
    }

    private TodoModel onFailedGetToDo()
    {
        TodoModel t = new TodoModel();
        t.setTitle("Failed");
        t.setFinished(false);
        return t;
    }
}