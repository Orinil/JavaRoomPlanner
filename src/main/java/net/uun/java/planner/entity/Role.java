package net.uun.java.planner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Table
@Entity
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(length = 50)
  private String name;


  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "role_rooms",
    joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id",
      referencedColumnName = "id"))
  @JsonIgnore
  private List<Room> rooms;

  //region Getters & Setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  //endregion
}
