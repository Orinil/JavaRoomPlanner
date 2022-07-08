package net.uun.java.planner.controller;

import net.uun.java.planner.entity.EquipmentCategory;
import net.uun.java.planner.requests.CategoryRequest;
import net.uun.java.planner.service.CategoryService;
import net.uun.java.planner.service.ICategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Transactional
    @RequestMapping("/name")
    public List<EquipmentCategory> getCategoryNames() {
        return categoryService.getCategories();
    }

    @Transactional
    @RequestMapping("/name/{id}")
    public Optional<EquipmentCategory> showCategory(@PathVariable int id) {
        return categoryService.showCategory(id);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteCategory(@PathVariable int id) throws EntityNotFound {
        categoryService.deleteCategory(id);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Optional<EquipmentCategory> updateCategory(@PathVariable int id, CategoryRequest categoryRequest){
        return categoryService.updateCategory(id, categoryRequest);
    }
}
