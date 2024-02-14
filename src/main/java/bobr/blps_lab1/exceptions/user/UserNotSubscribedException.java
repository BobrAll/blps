package bobr.blps_lab1.exceptions.user;

public class UserNotSubscribedException extends RuntimeException {
    public UserNotSubscribedException() {
        super("You are don't have a subscription");
    }
}
