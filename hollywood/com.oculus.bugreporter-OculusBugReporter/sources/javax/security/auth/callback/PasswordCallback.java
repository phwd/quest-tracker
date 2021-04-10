package javax.security.auth.callback;

import java.io.Serializable;

public class PasswordCallback implements Callback, Serializable {
    private static final long serialVersionUID = 2267422647454909926L;
    private boolean echoOn;
    private char[] inputPassword;
    private String prompt;

    public PasswordCallback(String prompt2, boolean echoOn2) {
        if (prompt2 == null || prompt2.length() == 0) {
            throw new IllegalArgumentException();
        }
        this.prompt = prompt2;
        this.echoOn = echoOn2;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public boolean isEchoOn() {
        return this.echoOn;
    }

    public void setPassword(char[] password) {
        this.inputPassword = password == null ? null : (char[]) password.clone();
    }

    public char[] getPassword() {
        char[] cArr = this.inputPassword;
        if (cArr == null) {
            return null;
        }
        return (char[]) cArr.clone();
    }

    public void clearPassword() {
        if (this.inputPassword != null) {
            int i = 0;
            while (true) {
                char[] cArr = this.inputPassword;
                if (i < cArr.length) {
                    cArr[i] = ' ';
                    i++;
                } else {
                    return;
                }
            }
        }
    }
}
