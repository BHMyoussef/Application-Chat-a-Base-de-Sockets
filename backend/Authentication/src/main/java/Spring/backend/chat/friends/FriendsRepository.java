package Spring.backend.chat.friends;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends CrudRepository<Friends, FriendsKey> {
    @Query("SELECT f.friendsKey.senderId FROM Friends f WHERE f.status = 'PENDING' AND f.friendsKey.receiverId = ?1")
    List<String> findSenderIds(String receiverId);
}
