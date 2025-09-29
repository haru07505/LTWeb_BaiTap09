package haru.kieu.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    
    @OneToMany(mappedBy = "owner")
    private List<Product> products = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
      name = "user_category",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    
    private Set<Category> categories = new HashSet<>();
}
