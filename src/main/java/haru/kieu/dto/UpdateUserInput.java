package haru.kieu.dto;

import lombok.Data;

@Data
public class UpdateUserInput {
	private String fullname;
    private String password;
    private String phone;
}
