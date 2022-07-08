package net.uun.java.planner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity
public class RoomReservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private String name;

  @ManyToOne
  @JoinColumn(name = "room_id", insertable = false, updatable = false)
  @JsonIgnore
  private Room room;

  @Column(name = "room_id",nullable = false)
  private int roomId;

  @Column(name = "user_id")
  private int userId;

  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  @JsonIgnore
  private User user;

  //region Getters & Setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime from) {
    this.startDate = from;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime to) {
    this.endDate = to;
  }

  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getUserId() {
    return userId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  //endregion
}
