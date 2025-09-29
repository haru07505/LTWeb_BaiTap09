package haru.kieu.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private Integer quantity;
	@Column(name = "description", length = 2000)
	private String description;
	private BigDecimal price;

	@ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    // Khóa ngoại tới Category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
