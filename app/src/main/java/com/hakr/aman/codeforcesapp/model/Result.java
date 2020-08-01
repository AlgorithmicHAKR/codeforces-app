package com.hakr.aman.codeforcesapp.model;

import java.util.List;

public class Result {

    private String status;
    private List<userinfo> result = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<userinfo> getResult() {
        return result;
    }

    public void setUserInfo(List<userinfo> result) {
        this.result = result;
    }

}
