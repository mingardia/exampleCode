package com.eis.cloud.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients

public class Application {

    public static void main(String[] args) {

        new SpringApplicationBuilder(Application.class)
                .web(false)
                .run(args);
    }
}

@Component
class DiscoveryClientExample implements CommandLineRunner {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public void run(String... strings) throws Exception {

        discoveryClient.getInstances("TODO-SERVICE").forEach((ServiceInstance s) -> {

            System.out.println("$$$$$>>>>>TODO-SERVICE::HOST::" + s.getHost());
            System.out.println("$$$$$>>>>>TODO-SERVICE::PORT::" + s.getPort());
            System.out.println("$$$$$>>>>>TODO-SERVICE::URI::" + s.getUri().toString());
            System.out.println("$$$$$>>>>>TODO-SERVICE::URI::" + s.getServiceId());

        });

       /* discoveryClient.getInstances("todo-service").forEach((ServiceInstance s) -> {
            System.out.println("$$$$$>>>>>todo-service::HOST::" + s.getHost() );
            System.out.println("$$$$$>>>>>todo-service::PORT::" + s.getPort() );
            System.out.println("$$$$$>>>>>todo-service::URI:: " + s.getUri().toString());
            System.out.println("@@@@>>>>>>todo-service::ServiceId:" + s.getServiceId());
        }); */

    }
}


@Component
class FeignExample implements CommandLineRunner {

    @Autowired
    private ToDoClient toDoClient;

    @Override
    public void run(String... strings) throws Exception {

        System.out.println(" ----- Running Feign Service Call -----");
        System.out.println(">>> Retrieved:" + this.toDoClient.getToDo().toString());
        System.out.println("------ Done Feign Service Call");
    }
}

@FeignClient("todo-service")
interface ToDoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/services/todo")
    TodoModel getToDo();
}


class TodoModel {
    protected String title;
    protected boolean finished;

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public boolean isFinished() {

        return finished;
    }

    public void setFinished(boolean finished) {

        this.finished = finished;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TodoModel todoModel = (TodoModel) o;

        if (finished != todoModel.finished) return false;
        return title != null ? title.equals(todoModel.title) : todoModel.title == null;

    }

    @Override
    public int hashCode() {

        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (finished ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {

        return "TodoModel{" +
                "title='" + title + '\'' +
                ", finished=" + finished +
                '}';
    }
}

