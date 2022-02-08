package Spring.backend.Authentication.appuser;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", columnDefinition = "VARCHAR(255)")
    private String userId;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private int totalFriends = 0;
    private boolean is_connected = false;

    @OneToMany
    Set<AppUser> friends = new HashSet<AppUser>();

    public AppUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AppUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
