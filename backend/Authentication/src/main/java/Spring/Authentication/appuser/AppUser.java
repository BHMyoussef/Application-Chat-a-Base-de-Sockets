package Spring.Authentication.appuser;

import lombok.*;

import javax.persistence.*;

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

    public AppUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
