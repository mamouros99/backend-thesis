package com.mamouros.backend.reports;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mamouros.backend.ecoIsland.EcoIsland;
import jakarta.persistence.*;


@Entity
@Table(name = "REPORTS")
public class Report{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ecoisland_id", nullable = false)
    private EcoIsland ecoIsland;
    private  String separation;
    private  String full;

    private String dirty;

    @Column(nullable = false)
    private String time;

    private String message;

    @Column(nullable = false)
    private Boolean archived;

    public Report() {
    }

    public Report(EcoIsland ecoIsland, String separation, String full, String dirty, String time, String message) {
        this.ecoIsland = ecoIsland;
        this.separation = separation;
        this.full = full;
        this.dirty = dirty;
        this.time = time;
        this.message = message;
        this.archived = false;
    }

    public Long getId() {
        return id;
    }

    public EcoIsland getEcoIsland() {
        return ecoIsland;
    }

    public void setEcoIsland(EcoIsland ecoIsland) {
        this.ecoIsland = ecoIsland;
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


    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", ecoIsland=" + ecoIsland +
                ", separation='" + separation + '\'' +
                ", full='" + full + '\'' +
                ", dirty='" + dirty + '\'' +
                ", time='" + time + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }
}
