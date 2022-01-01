package Spring.backend.chat.friends;

import Spring.backend.Authentication.appuser.AppUser;
import Spring.backend.Authentication.appuser.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendsService {
    private final FriendsRepository friendsRepository;
    private final AppUserRepository appUserRepository;

    public FriendsService(FriendsRepository friendsRepository, AppUserRepository appUserRepository) {
        this.friendsRepository = friendsRepository;
        this.appUserRepository = appUserRepository;
    }

    public Friends sendRequestFriendship(FriendsKey friendsKey){
        if(isValidInvitation(friendsKey)){
            Friends pendingRelation = new Friends(friendsKey, Status.PENDING);
            return friendsRepository.save(pendingRelation);
        }else{
            throw new IllegalStateException("request is not valid");
        }
    }

    public Friends acceptRequestFriendship(FriendsKey friendsKey){
        if(isValidFriendship(friendsKey)){
            //TODO: check before accept request if auth user is the one who has receiver_id
            if(isPendingInvitation(friendsKey)){
                updateFriendsList(friendsKey, "ADD");
                Friends friends = new Friends(friendsKey, Status.ACCEPTED);
                return friendsRepository.save(friends);
            }
        }
        throw new IllegalStateException("request is not valid");

    }

    public String deleteRequestFriendship(FriendsKey friendsKey){
        if(isValidFriendship(friendsKey)){
            updateFriendsList(friendsKey, "REMOVE");
            friendsRepository.deleteById(friendsKey);
            return "friendship deleted";
        }else {
            return "request is not valid";
        }
    }

    private boolean isPendingInvitation(FriendsKey friendsKey){
        return friendsRepository.findById(friendsKey).get().getStatus() == Status.PENDING;
    }

    private boolean isFriendsExistsAsUsers(FriendsKey friendsKey){
        return appUserRepository.existsById(friendsKey.getSenderId()) &&
                appUserRepository.existsById(friendsKey.getReceiverId());
    }

    private boolean isAlreadyFriends(FriendsKey friendsKey){
        FriendsKey friendsKey1 = new FriendsKey(friendsKey.getSenderId(), friendsKey.getReceiverId());
        FriendsKey friendsKey2 = new FriendsKey(friendsKey.getReceiverId(), friendsKey.getSenderId());
        return friendsRepository.existsById(friendsKey1) || friendsRepository.existsById(friendsKey2);
    }

    private boolean isDifferentFriends(FriendsKey friendsKey){
        return friendsKey.getSenderId() != friendsKey.getReceiverId();
    }

    private boolean isValidInvitation(FriendsKey friendsKey){
        if(isDifferentFriends(friendsKey)){
            if(isFriendsExistsAsUsers(friendsKey)){
                if(!isAlreadyFriends(friendsKey)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidFriendship(FriendsKey friendsKey){
        if(isDifferentFriends(friendsKey)){
            if(isFriendsExistsAsUsers(friendsKey)){
                if(isAlreadyFriends(friendsKey)){
                    return true;
                }
            }
        }
        return false;
    }

    private void removeFriend(AppUser user1, AppUser user2){
        user1.setTotalFriends(user1.getTotalFriends()-1);
        user2.setTotalFriends(user2.getTotalFriends()-1);
    }
    private void addFriend(AppUser user1, AppUser user2){
        user1.setTotalFriends(user1.getTotalFriends()+1);
        user2.setTotalFriends(user2.getTotalFriends()+1);
    }

    private void updateFriendsList(FriendsKey friendsKey, String operation){
        AppUser user1 = appUserRepository.findById(friendsKey.getSenderId()).get();
        AppUser user2 = appUserRepository.findById(friendsKey.getReceiverId()).get();
        if(operation.equals("ADD")){
            addFriend(user1, user2);
        }else if(operation.equals("REMOVE")){
            removeFriend(user1, user2);
        }
    }
}
