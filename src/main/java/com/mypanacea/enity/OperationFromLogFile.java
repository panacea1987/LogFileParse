package com.mypanacea.enity;

public class OperationFromLogFile {
    private String methodName;
    private long methodDuration;
    private int id;

    public OperationFromLogFile(String methodName, long methodDuration, int id) {
        this.methodName = methodName;
        this.methodDuration = methodDuration;
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public long getMethodDuration() {
        return methodDuration;
    }

    public void setMethodDuration(long methodDuration) {
        this.methodDuration = methodDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OperationFromLogFile{" +
                "methodName='" + methodName + '\'' +
                ", methodDuration=" + methodDuration +
                ", id=" + id +
                '}';
    }
}
