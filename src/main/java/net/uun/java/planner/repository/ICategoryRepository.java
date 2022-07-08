package net.uun.java.planner.repository;

import net.uun.java.planner.entity.EquipmentCategory;
import net.uun.java.planner.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<EquipmentCategory, Integer> {
}
