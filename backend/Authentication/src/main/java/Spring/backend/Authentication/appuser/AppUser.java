package Spring.backend.Authentication.appuser;

import Spring.backend.chat.friends.Friends;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Friends> friends = new HashSet<>();

    public AppUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AppUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public AppUser(String name, String email, String password, Set<Friends> friends) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.friends = friends;
    }
}
