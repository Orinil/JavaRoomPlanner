package net.uun.java.planner.service;

import net.uun.java.planner.entity.Equipment;
import net.uun.java.planner.entity.EquipmentCategory;
import net.uun.java.planner.requests.CategoryRequest;
import net.uun.java.planner.requests.EquipmentRequest;
import net.uun.java.planner.utils.EntityNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface IEquipmentService {

    List<Equipment> getEquipmentList();

    Optional<Equipment> showSpecificEquipment(int id);

    Equipment createEquipment(EquipmentRequest equipmentRequest);

    void deleteEquipment(int id) throws EntityNotFound;

    Optional<Equipment> updateEquipment(int id, EquipmentRequest equipmentRequest);

}
