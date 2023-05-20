package com.mamouros.backend.reports;

import java.io.Serializable;

public class ReportDto implements Serializable {
    private Integer id;

    private Integer ecoIslandId;
    private  String separation;
    private  String full;

    private String dirty;

    private String time;
    private String message;

    public ReportDto() {
    }

    public ReportDto(Report report) {
        this.ecoIslandId = report.getEcoIsland().getId();
        this.separation = report.getSeparation();
        this.full = report.getFull();
        this.dirty = report.getDirty();
        this.time = report.getTime();
        this.message = report.getMessage();
    }

    public Integer getId() {
        return id;
    }

    public Integer getEcoIslandId() {
        return ecoIslandId;
    }

    public void setEcoIslandId(Integer ecoIslandId) {
        this.ecoIslandId = ecoIslandId;
    }

    public String getSeparation() {
        return separation;
    }

    public void setSeparation(String separation) {
        this.separation = separation;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getDirty() {
        return dirty;
    }

    public void setDirty(String dirty) {
        this.dirty = dirty;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ReportDto{" +
                "id=" + id +
                ", ecoIsland=" + ecoIslandId +
                ", separation='" + separation + '\'' +
                ", full='" + full + '\'' +
                ", dirty='" + dirty + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }
}
