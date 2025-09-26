package haru.kieu.Controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import haru.kieu.Entity.Category;
import haru.kieu.dto.CreateCategoryInput;
import haru.kieu.dto.UpdateCategoryInput;
import haru.kieu.repository.CategoryRepository;

import java.util.List;

@Controller
public class CategoryController {
	private final CategoryRepository categoryRepo;

	public CategoryController(CategoryRepository categoryRepo) {
		this.categoryRepo = categoryRepo;
	}

	// Queries
	@QueryMapping
	public List<Category> categories() {
		return categoryRepo.findAll();
	}

	@QueryMapping
	public Category category(@Argument Long id) {
		return categoryRepo.findById(id).orElse(null);
	}

	// Mutations
	@MutationMapping
	public Category createCategory(@Argument CreateCategoryInput input) {
		Category c = new Category();
		c.setName(input.getName());
		c.setImages(input.getImages());
		return categoryRepo.save(c);
	}

	@MutationMapping
	public Category updateCategory(@Argument Long id, @Argument UpdateCategoryInput input) {
		Category c = categoryRepo.findById(id).orElseThrow();
		if (input.getName() != null)
			c.setName(input.getName());
		if (input.getImages() != null)
			c.setImages(input.getImages());
		return categoryRepo.save(c);
	}

	@MutationMapping
	public Boolean deleteCategory(@Argument Long id) {
		categoryRepo.deleteById(id);
		return true;
	}

}
