package bobr.blps_lab.exceptions.user;

public class AlreadyHaveSuperuserPermissionsException extends RuntimeException {
    public AlreadyHaveSuperuserPermissionsException() {
        super("You already have all permissions, provided by superuser");
    }
}
