package org.wildcodeschool.myblog.service;

import org.springframework.stereotype.Service;
import org.wildcodeschool.myblog.dto.CategoryDTO;
import org.wildcodeschool.myblog.exception.NoContentException;
import org.wildcodeschool.myblog.exception.ResourceNotFoundException;
import org.wildcodeschool.myblog.mapper.CategoryMapper;
import org.wildcodeschool.myblog.model.*;
import org.wildcodeschool.myblog.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(
            CategoryRepository categoryRepository,
            CategoryMapper categoryMapper
    ) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            throw new NoContentException("Aucune categorie trouvée.");
        }
        return categories.stream().map(categoryMapper::convertToDTO).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La catégorie avec l'id " + id + " n'a pas été trouvée."));

        return categoryMapper.convertToDTO(category);
    }

    public CategoryDTO createCategory(Category category) {

        category.setName(category.getName());

        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.convertToDTO(savedCategory);
    }

    public CategoryDTO updateCategory(Long id, Category categoryDetails) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La catégorie avec l'id " + id + " n'a pas été trouvée."));

        if (category == null) {
            return null;
        }

        category.setName(categoryDetails.getName());

        Category updatedCategory = categoryRepository.save(category);

        return categoryMapper.convertToDTO(updatedCategory);
    }

    public boolean deleteCategory(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La catégorie avec l'id " + id + " n'a pas été trouvée."));

        if (category == null) {
            return false;
        }

        categoryRepository.delete(category);

        return true;
    }
}
