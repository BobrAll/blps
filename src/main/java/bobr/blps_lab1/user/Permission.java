package bobr.blps_lab1.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    FLAT_ADD("flat.add"),
    FLAT_DELETE("flat.delete"),
    FLAT_BOOST("flat.boost"),

    USER_BlOCK("user.block"),
    USER_UNBLOCK("user.unblock"),
    USER_DELETE("user.delete");

    @Getter
    private final String permission;
}
