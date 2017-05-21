package bookmarks;

/**
 * Created by Me on 21/05/2017.
 */
class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userId) {
        super("could not find user '" + userId + "'.");
    }
}
