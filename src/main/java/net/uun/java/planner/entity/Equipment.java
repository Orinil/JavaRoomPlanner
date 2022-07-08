package net.uun.java.planner.entity;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Table
@Entity
public class Equipment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int Id;

  @Column(length = 50)
  private String name;

  @ManyToOne
  //@RestResource(path = "libraryAddress", rel="address")
  private EquipmentCategory category;

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

  public EquipmentCategory getCategory() {
    return category;
  }

  public void setCategory(EquipmentCategory category) {
    this.category = category;
  }

  //endregion
}
