package com.hakr.aman.codeforcesapp.model;


import java.util.List;

public class usersubmissioninfo {

    private String status;
    private List<Result1> result = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Result1> getResult() {
        return result;
    }

    public void setResult(List<Result1> result) {
        this.result = result;
    }

}