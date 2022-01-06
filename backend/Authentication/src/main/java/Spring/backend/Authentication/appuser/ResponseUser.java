package Spring.backend.Authentication.appuser;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUser {
    private String userId;
    private String name;
    private String email;
    private int totalFriends = 0;
}
