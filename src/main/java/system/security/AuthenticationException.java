package system.security;

public class AuthenticationException extends RuntimeException {

    private String realm = null;
	private static final long serialVersionUID = 1344181060071633478L;

	public AuthenticationException(String message, String realm) {
        super(message);
        this.realm = realm;
    }

    public String getRealm() {
        return this.realm;
    }
}