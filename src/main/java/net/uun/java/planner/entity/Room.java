package net.uun.java.planner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Table
@Entity
@SQLDelete(sql = "UPDATE room SET state = 'DELETED' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "state != 'DELETED'")
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int Id;

  @Column(length = 50)
  private String name;

  private int capacity;

  @Enumerated(EnumType.STRING)
  @JsonIgnore
  private State state = State.ACTIVE;

  @OneToMany(mappedBy = "room")
  @JsonIgnore
  private List<RoomReservation> reservations;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(name = "location_id", updatable = false, insertable = false)
  private Location location;

  @Column(name = "location_id")
  private int locationId;

  //region Getters & Setters
  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public List<RoomReservation> getReservations() {
    return reservations;
  }

  public void setReservations(List<RoomReservation> reservations) {
    this.reservations = reservations;
  }


  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public int getLocationId() {
    return locationId;
  }

  public void setLocationId(int locationId) {
    this.locationId = locationId;
  }

  public Location getLocation() {
    return location;
  }

  //endregion

  enum State {
    DELETED, ACTIVE
  }
}
