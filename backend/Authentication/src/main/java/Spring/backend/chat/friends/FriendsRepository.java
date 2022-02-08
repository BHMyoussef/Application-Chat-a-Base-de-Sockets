package Spring.backend.chat.friends;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends CrudRepository<Friends, FriendsKey> {
    @Query("SELECT f.friendsKey.receiverId FROM Friends f WHERE f.status = 'PENDING' AND f.friendsKey.senderId = ?1")
    List<String> findReceiverIds(String senderId);
}
