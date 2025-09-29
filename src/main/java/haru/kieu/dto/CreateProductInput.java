package haru.kieu.dto;

import lombok.Data;

@Data
public class CreateProductInput {
	private String title;
	private Integer quantity;
	private String description;
	private Double price;
	private String ownerId;
	private Long categoryId;
}
