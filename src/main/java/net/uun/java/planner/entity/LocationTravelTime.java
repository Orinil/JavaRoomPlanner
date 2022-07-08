package net.uun.java.planner.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;

@Entity
@Table
@IdClass(LocationTravelTime.Key.class)
public class LocationTravelTime {
    @Id
    @ManyToOne
    private Location startingLocation;

    @ManyToOne
    @Id
    private Location targetLocation;

    private Duration duration;

    static class Key implements Serializable {
        Location startingLocation;
        Location targetLocation;
    }
}
