package net.uun.java.planner.service;

import net.uun.java.planner.entity.EquipmentCategory;
import net.uun.java.planner.requests.CategoryRequest;
import net.uun.java.planner.utils.EntityNotFound;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    List<EquipmentCategory> getCategories();

    Optional<EquipmentCategory> showCategory(int id);

    EquipmentCategory createCategory(CategoryRequest categoryRequest);

    void deleteCategory(int id) throws EntityNotFound;

    Optional<EquipmentCategory> updateCategory(int id, CategoryRequest categoryRequest);

}
