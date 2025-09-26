package haru.kieu.dto;
import lombok.*;

@Data
public class CreateUserInput {
	private String fullname;
	private String email;
	private String password;
	private String phone;
}
