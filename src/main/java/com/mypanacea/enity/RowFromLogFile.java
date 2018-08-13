package com.mypanacea.enity;

import java.time.LocalDateTime;

public class RowFromLogFile {
    private LocalDateTime datetime;
    private String type;
    private String method;
    private int id;

    public RowFromLogFile(LocalDateTime datetime, String type, String method, int id) {
        this.datetime = datetime;
        this.type = type;
        this.method = method;
        this.id = id;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RowFromLogFile{" +
                "datetime=" + datetime +
                ", type='" + type + '\'' +
                ", method='" + method + '\'' +
                ", id=" + id +
                '}';
    }
}
