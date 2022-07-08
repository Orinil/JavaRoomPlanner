package net.uun.java.planner.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Table
@Entity
public class EquipmentCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int Id;

  @Column(length = 50)
  private String name;

  private boolean canBook = true;

  @OneToMany(mappedBy = "category")
  private List<Equipment> equipments;

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

  public boolean isCanBook() {
    return canBook;
  }

  public void setCanBook(boolean canBook) {
    this.canBook = canBook;
  }

  public List<Equipment> getEquipments() {
    return equipments;
  }

  public void setEquipments(List<Equipment> equipments) {
    this.equipments = equipments;
  }

  //endregion
}
