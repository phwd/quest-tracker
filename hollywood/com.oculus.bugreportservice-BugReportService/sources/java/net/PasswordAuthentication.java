package java.net;

public final class PasswordAuthentication {
    private char[] password;
    private String userName;

    public String getUserName() {
        return this.userName;
    }

    public char[] getPassword() {
        return this.password;
    }
}
