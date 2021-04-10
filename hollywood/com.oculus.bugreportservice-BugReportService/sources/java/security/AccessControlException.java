package java.security;

public class AccessControlException extends SecurityException {
    private static final long serialVersionUID = 5138225684096988535L;
    private Permission perm;

    public AccessControlException(String str) {
        super(str);
    }
}
