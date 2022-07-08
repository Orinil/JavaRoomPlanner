package net.uun.java.planner.service;

import net.uun.java.planner.entity.EquipmentCategory;
import net.uun.java.planner.repository.ICategoryRepository;
import net.uun.java.planner.requests.CategoryRequest;
import net.uun.java.planner.utils.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public List<EquipmentCategory> getCategories(){
        return categoryRepository.findAll();
    }

    @Override
    public Optional<EquipmentCategory> showCategory(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public EquipmentCategory createCategory(CategoryRequest categoryRequest) {
        EquipmentCategory entity = new EquipmentCategory();
        setDataFromRequestAndSave(entity, categoryRequest);
        return entity;
    }

    @Override
    public void deleteCategory(int id) throws EntityNotFound {
        if(!categoryRepository.existsById(id)){
            throw new EntityNotFound();
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<EquipmentCategory> updateCategory(int id, CategoryRequest categoryRequest){
        Optional<EquipmentCategory> categoryById = categoryRepository.findById(id);
        categoryById.ifPresent(c -> setDataFromRequestAndSave(c, categoryRequest));
        return categoryById;
    }
    private void setDataFromRequestAndSave(EquipmentCategory entity, CategoryRequest request){
        entity.setName(request.name);
        categoryRepository.save(entity);
    }
}
