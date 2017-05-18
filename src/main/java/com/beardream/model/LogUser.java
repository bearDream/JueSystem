package com.beardream.model;

import java.util.Date;

/**
 * Created by bearDream on 2017/5/18.
 *
 */
public class LogUser {


    private Integer logId;

    private Date logAddtime;

    private String actionkey;

    private String logContent;

    private String controllerkey;

    private String logIp;

    private String params;

    private Integer userId;

    private String username;

    private String tel;

    private String realName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Date getLogAddtime() {
        return logAddtime;
    }

    public void setLogAddtime(Date logAddtime) {
        this.logAddtime = logAddtime;
    }

    public String getActionkey() {
        return actionkey;
    }

    public void setActionkey(String actionkey) {
        this.actionkey = actionkey;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getControllerkey() {
        return controllerkey;
    }

    public void setControllerkey(String controllerkey) {
        this.controllerkey = controllerkey;
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
