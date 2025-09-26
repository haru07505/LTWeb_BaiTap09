package haru.kieu.dto;

import java.util.List;

import lombok.Data;

@Data
public class UpdateProductInput {
	private String title;
	private Integer quantity;
	private String description;
	private Double price;
	private List<String> categoryIds;
}
