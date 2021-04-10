package X;

import java.io.Serializable;

/* renamed from: X.1hS  reason: invalid class name */
public class AnonymousClass1hS implements Serializable {
    public static final long serialVersionUID = -2488473066578201069L;
    public final String accessTokenString;
    public final String appId;

    private Object readResolve() {
        return new AnonymousClass1gV(this.accessTokenString, this.appId);
    }

    public AnonymousClass1hS(String str, String str2) {
        this.accessTokenString = str;
        this.appId = str2;
    }
}
