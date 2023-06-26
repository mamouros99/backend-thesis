package com.mamouros.backend.ecoIsland;

import jakarta.persistence.*;

@Entity
@Table(name = "ECO_ISLANDS")
public class EcoIsland {
    @Id
    private String id;

    @Column(name = "building_name", nullable = false)
    private String building;

    @Column(name = "building_id", nullable = false)
    private String buildingId;

    @Column(name = "floor", nullable = false)
    private String floor;
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "bins",length = 2, nullable = false)
    private String bins;

    @Column(name = "identifier", nullable = false)
    private String identifier;
    private Integer xPos;
    private Integer yPos;

    public EcoIsland() {
    }

    public EcoIsland(String id, String building, String buildingId, String floor, String description, String bins, Integer xPos, Integer yPos, String identifier ) {
        this.id = id;
        this.building = building;
        this.floor = floor;
        this.description = description;
        this.bins = bins;
        this.buildingId = buildingId;
        this.xPos = xPos;
        this.yPos = yPos;
        this.identifier = identifier;
    }

    public EcoIsland(String id, String building, String buildingId, String floor, String description, String bins, String identifier ) {
        this.id = id;
        this.building = building;
        this.floor = floor;
        this.description = description;
        this.bins = bins;
        this.buildingId = buildingId;
        this.identifier = identifier;
    }

    public String getId() {
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
    public void setId(String id) {
        this.id = id;
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

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getxPos() {
        return xPos;
    }

    public void setxPos(Integer xPos) {
        this.xPos = xPos;
    }

    public Integer getyPos() {
        return yPos;
    }

    public void setyPos(Integer yPos) {
        this.yPos = yPos;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "EcoIsland{" +
                "id=" + id +
                ", building='" + building + '\'' +
                ", buildingId='" + buildingId + '\'' +
                ", floor='" + floor + '\'' +
                ", description='" + description + '\'' +
                ", bins='" + bins + '\'' +
                '}';
    }
}
