package net.uun.java.planner.service;

import net.uun.java.planner.entity.Equipment;
import net.uun.java.planner.repository.IEquipmentRepository;
import net.uun.java.planner.requests.EquipmentRequest;
import net.uun.java.planner.utils.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EquipmentService implements IEquipmentService{

    @Autowired
    IEquipmentRepository equipmentRepository;

    @Override
    public List<Equipment> getEquipmentList() {
        return equipmentRepository.findAll();
    }

    @Override
    public Optional<Equipment> showSpecificEquipment(int id) {
        return equipmentRepository.findById(id);
    }

    @Override
    public Equipment createEquipment(EquipmentRequest equipmentRequest) {
        Equipment entity = new Equipment();
        setDataFromRequestAndSave(entity, equipmentRequest);
        return entity;
    }

    @Override
    public void deleteEquipment(int id) throws EntityNotFound {
        if(!equipmentRepository.existsById(id)){
            throw new EntityNotFound();
        }
        equipmentRepository.deleteById(id);
    }

    @Override
    public Optional<Equipment> updateEquipment(int id, EquipmentRequest equipmentRequest) {
        Optional<Equipment> equipmentById = equipmentRepository.findById(id);
        equipmentById.ifPresent(c -> setDataFromRequestAndSave(c, equipmentRequest));
        return equipmentById;
    }
    private void setDataFromRequestAndSave(Equipment entity, EquipmentRequest request){
        entity.setName(request.name);
        equipmentRepository.save(entity);
    }
}
