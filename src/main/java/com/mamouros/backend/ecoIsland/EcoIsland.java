package com.mamouros.backend.ecoIsland;

import jakarta.persistence.*;

@Entity
@Table(name = "ECO_ISLANDS")
public class EcoIsland {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "building_name", nullable = false)
    private String building;

    @Column(name = "floor", nullable = false)
    private String floor;
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "bins",length = 2, nullable = false)
    private String bins;


    public EcoIsland() {
    }

    public EcoIsland(String building, String floor, String description, String bins) {
        this.building = building;
        this.floor = floor;
        this.description = description;
        this.bins = bins;
    }

    public Integer getId() {
        return id;
    }

    public String getBuilding() {
        return building;
    }

    public String getFloor() {
        return floor;
    }

    public String getDescription() {
        return description;
    }

    public String getBins() {
        return bins;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBins(String bins) {
        this.bins = bins;
    }

    @Override
    public String toString() {
        return "EcoIsland{" +
                "id=" + id +
                ", building='" + building + '\'' +
                ", floor='" + floor + '\'' +
                ", description='" + description + '\'' +
                ", bins='" + bins + '\'' +
                '}';
    }
}
