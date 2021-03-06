package com.xingoo.teddy.entity;


import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 任务类的封装
 *
 * @author xingoo
 */
public class Task implements Serializable{

    private String id;
    private String name;        //job的名字
    private String command;
    private Long create_time = Timestamp.valueOf(LocalDateTime.now()).getTime();    //创建时间
    private String application_id;
    private String web_url;
    private String state;
    private Date modify_time;
    private String email;
    private Integer send;
    private Integer restart;
    private Integer restart_count = 3;

    public Task(){}

    public Task(String name,
                String clazz,
                String jar,
                String jars,
                String settings,
                String args,
                String email,
                Integer send,
                Integer restart){

        this.name = name;
        this.id = this.name+"_"+this.create_time;
        this.email = email;
        this.send = send;
        this.restart = restart;

        //默认配置
        if(StringUtils.isBlank(settings)){
            settings = "--master yarn --deploy-mode cluster --executor-memory 5G --num-executors 5 --executor-cores 3 --driver-memory 5G ";
        }

        this.command = "spark2-submit "
                + settings
                + " --jars "
                + jars
                + " --class "
                + clazz
                + " "
                + jar
                + " "
                + args+ " "+ this.id;
    }

    public Integer getRestart() {
        return restart;
    }

    public void setRestart(Integer restart) {
        this.restart = restart;
    }

    public Integer getRestart_count() {
        return restart_count;
    }

    public void setRestart_count(Integer restart_count) {
        this.restart_count = restart_count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public String getApplication_id() {
        return application_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getModify_time() {
        return new Date(System.currentTimeMillis());
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSend() {
        return send;
    }

    public void setSend(Integer send) {
        this.send = send;
    }
}
