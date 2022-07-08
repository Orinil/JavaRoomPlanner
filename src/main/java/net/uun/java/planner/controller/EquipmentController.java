package net.uun.java.planner.controller;

import net.uun.java.planner.entity.Equipment;
import net.uun.java.planner.entity.EquipmentCategory;
import net.uun.java.planner.requests.CategoryRequest;
import net.uun.java.planner.requests.EquipmentRequest;
import net.uun.java.planner.service.ICategoryService;
import net.uun.java.planner.service.IEquipmentService;
import net.uun.java.planner.utils.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private IEquipmentService equipmentService;

    @Transactional
    @RequestMapping("/name")
    public List<Equipment> getEquipmentNames() {
        return equipmentService.getEquipmentList();
    }

    @Transactional
    @RequestMapping("/name/{id}")
    public Optional<Equipment> showEqipment(@PathVariable int id) {
        return equipmentService.showSpecificEquipment(id);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteEquipment(@PathVariable int id) throws EntityNotFound {
        equipmentService.deleteEquipment(id);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Optional<Equipment> updateEquipment(@PathVariable int id, EquipmentRequest equipmentRequest){
        return equipmentService.updateEquipment(id, equipmentRequest);
    }
}
