package haru.kieu.Controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import haru.kieu.Entity.Category;
import haru.kieu.Entity.Product;
import haru.kieu.Entity.User;
import haru.kieu.dto.CreateProductInput;
import haru.kieu.dto.UpdateProductInput;
import haru.kieu.repository.CategoryRepository;
import haru.kieu.repository.ProductRepository;
import haru.kieu.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ProductController {
	private final ProductRepository productRepo;
	private final UserRepository userRepo;
	private final CategoryRepository categoryRepo;

	public ProductController(ProductRepository productRepo, UserRepository userRepo, CategoryRepository categoryRepo) {
		this.productRepo = productRepo;
		this.userRepo = userRepo;
		this.categoryRepo = categoryRepo;
	}

	// Queries
	@QueryMapping
	public List<Product> productsSortedByPrice() {
		return productRepo.findAllByOrderByPriceAsc();
	}

	@QueryMapping
	public List<Product> productsByCategory(@Argument Long categoryId) {
		return productRepo.findAllByCategoryId(categoryId);
	}

	@QueryMapping
	public List<Product> products() {
		return productRepo.findAll();
	}

	@QueryMapping
	public Product product(@Argument Long id) {
		return productRepo.findById(id).orElse(null);
	}

	// Mutations
	@MutationMapping
	public Product createProduct(@Argument CreateProductInput input) {
		User owner = userRepo.findById(Long.valueOf(input.getOwnerId()))
				.orElseThrow(() -> new RuntimeException("Owner not found"));
		Product p = new Product();
		p.setTitle(input.getTitle());
		p.setQuantity(input.getQuantity());
		p.setDescription(input.getDescription());
		p.setPrice(BigDecimal.valueOf(input.getPrice()));
		p.setOwner(owner);

		if (input.getCategoryIds() != null) {
			Set<Category> cats = input.getCategoryIds().stream()
					.map(cid -> categoryRepo.findById(Long.valueOf(cid)).orElseThrow()).collect(Collectors.toSet());
			p.setCategories(cats);
		}
		return productRepo.save(p);
	}

	@MutationMapping
	public Product updateProduct(@Argument Long id, @Argument UpdateProductInput input) {
		Product p = productRepo.findById(id).orElseThrow();
		if (input.getTitle() != null)
			p.setTitle(input.getTitle());
		if (input.getQuantity() != null)
			p.setQuantity(input.getQuantity());
		if (input.getDescription() != null)
			p.setDescription(input.getDescription());
		if (input.getPrice() != null)
			p.setPrice(BigDecimal.valueOf(input.getPrice()));
		if (input.getCategoryIds() != null) {
			Set<Category> cats = input.getCategoryIds().stream()
					.map(cid -> categoryRepo.findById(Long.valueOf(cid)).orElseThrow()).collect(Collectors.toSet());
			p.setCategories(cats);
		}
		return productRepo.save(p);
	}

	@MutationMapping
	public Boolean deleteProduct(@Argument Long id) {
		productRepo.deleteById(id);
		return true;
	}
}
