package haru.kieu.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name ="users")
@Data
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullname;
    @Column(unique = true)
    private String email;
    private String password;
    private String phone;

    @ManyToMany
    @JoinTable(
      name = "user_category",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();
}
