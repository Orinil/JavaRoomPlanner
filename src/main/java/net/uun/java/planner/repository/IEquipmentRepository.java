package net.uun.java.planner.repository;

import net.uun.java.planner.entity.Equipment;
import net.uun.java.planner.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEquipmentRepository extends JpaRepository<Equipment, Integer> {

}
