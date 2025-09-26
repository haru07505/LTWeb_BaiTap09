package haru.kieu.Controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import haru.kieu.Entity.User;
import haru.kieu.dto.CreateUserInput;
import haru.kieu.dto.UpdateUserInput;
import haru.kieu.repository.UserRepository;

@Controller
public class UserController {
	private final UserRepository userRepo;

	public UserController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	// Queries
	@QueryMapping
	public List<User> users() {
		return userRepo.findAll();
	}

	@QueryMapping
	public User user(@Argument Long id) {
		return userRepo.findById(id).orElse(null);
	}

	// Mutations
	@MutationMapping
	public User createUser(@Argument CreateUserInput input) {
		User u = new User();
		u.setFullname(input.getFullname());
		u.setEmail(input.getEmail());
		u.setPassword(input.getPassword());
		u.setPhone(input.getPhone());
		return userRepo.save(u);
	}

	@MutationMapping
	public User updateUser(@Argument Long id, @Argument UpdateUserInput input) {
		User u = userRepo.findById(id).orElseThrow();
		if (input.getFullname() != null)
			u.setFullname(input.getFullname());
		if (input.getPassword() != null)
			u.setPassword(input.getPassword());
		if (input.getPhone() != null)
			u.setPhone(input.getPhone());
		return userRepo.save(u);
	}

	@MutationMapping
	public Boolean deleteUser(@Argument Long id) {
		userRepo.deleteById(id);
		return true;
	}
}
