package bobr.blps_lab1.exceptions.jwt;

public class JwtNotFoundException extends RuntimeException{
    public JwtNotFoundException() {
        super("Token not found");
    }
}
