package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "trainee")
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int traineeId;

    private String traineeName;
    private String traineeDomain;
    private String traineeLocation;

    public Trainee() {}

    public Trainee(int traineeId, String traineeName, String traineeDomain, String traineeLocation) {
        this.traineeId = traineeId;
        this.traineeName = traineeName;
        this.traineeDomain = traineeDomain;
        this.traineeLocation = traineeLocation;
    }

    public int getTraineeId() { return traineeId; }
    public void setTraineeId(int traineeId) { this.traineeId = traineeId; }

    public String getTraineeName() { return traineeName; }
    public void setTraineeName(String traineeName) { this.traineeName = traineeName; }

    public String getTraineeDomain() { return traineeDomain; }
    public void setTraineeDomain(String traineeDomain) { this.traineeDomain = traineeDomain; }

    public String getTraineeLocation() { return traineeLocation; }
    public void setTraineeLocation(String traineeLocation) { this.traineeLocation = traineeLocation; }
    
    @Override
    public String toString() {
        return "Trainee [id=" + traineeId + 
               ", name=" + traineeName + 
               ", domain=" + traineeDomain + 
               ", location=" + traineeLocation + "]";
    }
}
