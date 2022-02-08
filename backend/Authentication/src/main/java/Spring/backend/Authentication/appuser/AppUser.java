package Spring.backend.Authentication.appuser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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
    @JsonIgnore
    private boolean is_connected = false;

    @OneToMany
    List<AppUser> friends = new ArrayList<>();

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
