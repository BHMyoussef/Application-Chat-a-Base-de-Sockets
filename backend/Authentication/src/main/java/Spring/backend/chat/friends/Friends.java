package Spring.backend.chat.friends;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Friends {
    @EmbeddedId
    private FriendsKey friendsKey;

    @Enumerated(value = EnumType.STRING)
    private Status status;

}
