package uz.pdp.appwarehouse.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CategoryRepository;
import uz.pdp.appwarehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategory(CategoryDto categoryDto){
        Category category=new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getCategoryParentId()!=null){
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryParentId());
            if (!optionalCategory.isPresent()){
                return new Result("Such Parent category doesn't exist!!!",false);
            }
            category.setParentCategory(optionalCategory.get());
        }

        categoryRepository.save(category);
        return new Result("Category successfully added!!!",true);
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    public Result deleteCategory(Integer id){
        categoryRepository.deleteById(id);
        return new Result("Category deleted",true);
    }

    public Result editCategory(Integer id,CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new Result("Such category doesn't exist!",false);
        }
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        if (categoryDto.getCategoryParentId()!=null){
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getCategoryParentId());
            if (!optionalParentCategory.isPresent()){
                return new Result("Such Parent category doesn't exist!!!",false);
            }
            category.setParentCategory(optionalParentCategory.get());
        }

        categoryRepository.save(category);
        return new Result("Category successfully edited!!!",true);
    }





}
