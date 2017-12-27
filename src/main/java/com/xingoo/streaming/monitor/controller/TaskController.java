package com.xingoo.streaming.monitor.controller;

import com.xingoo.streaming.monitor.manager.ProcessManager;
import com.xingoo.streaming.monitor.manager.ResourceManager;
import com.xingoo.streaming.monitor.manager.Task;
import com.xingoo.streaming.monitor.service.TaskService;
import com.xingoo.streaming.monitor.utils.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private ProcessManager processManager;

    @Autowired
    private ResourceManager resourceManager;

    @Autowired
    private TaskService taskService;

    @RequestMapping("start")
    public Response start(String name, String clazz, String jar, String settings, String[] args){

        String jars = StringUtils.join(resourceManager.listJars(),",");

        // 封装命令
        Task task = new Task(
                name,
                clazz,
                resourceManager.getJar(jar),
                resourceManager.getCommandJars(jar),
                settings,
                args
        );

        // 保存并启动进程
        taskService.saveAndStart(task);

        return Response.SUCCESS(taskService.listAll());
    }

    @RequestMapping("delete")
    public Response delete(String id){
        return Response.SUCCESS(taskService.delete(id));
    }

    @RequestMapping("list")
    public Response list(){
        return Response.SUCCESS(taskService.listAll());
    }
}
