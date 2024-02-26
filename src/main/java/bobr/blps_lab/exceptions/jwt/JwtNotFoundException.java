package bobr.blps_lab.exceptions.jwt;

public class JwtNotFoundException extends RuntimeException{
    public JwtNotFoundException() {
        super("Token not found");
    }
}
