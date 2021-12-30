package Spring.backend.chat.friends;

import Spring.backend.Authentication.appuser.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class FriendsService {
    private final FriendsRepository friendsRepository;
    private final AppUserRepository appUserRepository;

    public FriendsService(FriendsRepository friendsRepository, AppUserRepository appUserRepository) {
        this.friendsRepository = friendsRepository;
        this.appUserRepository = appUserRepository;
    }

    public Friends sendRequestFriendship(FriendsKey friendsKey){
        if(appUserRepository.existsById(friendsKey.getSenderId()) && appUserRepository.existsById(friendsKey.getReceiverId())){
            if(!friendsRepository.existsById(friendsKey)){
                if(friendsKey.getSenderId() != friendsKey.getReceiverId()){
                    Friends pendingRelation = new Friends(friendsKey, Status.PENDING);
                    return friendsRepository.save(pendingRelation);
                }else {
                    throw new IllegalStateException("receiver id and sender id must be different");
                }
            }else {
                throw new IllegalStateException("friendship already exists");
            }
        }else {
            throw new IllegalStateException("there is no such users");
        }
    }

    public Friends acceptRequestFriendship(FriendsKey friendsKey){
        Friends friends = new Friends(friendsKey, Status.ACCEPTED);
        return friendsRepository.save(friends);
    }

    public String deleteRequestFriendship(FriendsKey friendsKey){
        if(friendsRepository.existsById(friendsKey)){
            //TODO: check before deleting if auth user is the one who has receiver_id
            friendsRepository.deleteById(friendsKey);
            return "friendship deleted";
        }else {
            return "friendship doesn't exist previously";
        }
    }
}
