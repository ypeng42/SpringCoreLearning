package com.reflectionLearn;

public class TestBean {
    String job = "lll";

    public TestBean() {

    }

    public TestBean(String input, String aaa) {
        this.job = input;
    }

    public String getJob() {
        return job;
    }

    private void setJob(String job) {
        this.job = job;
    }
}
