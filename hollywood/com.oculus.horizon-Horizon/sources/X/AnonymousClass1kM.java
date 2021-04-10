package X;

import javax.annotation.concurrent.Immutable;

@Immutable
/* renamed from: X.1kM  reason: invalid class name */
public enum AnonymousClass1kM {
    SHA256("SHA-256", "sha256");
    
    public final String mDigestInstanceString;
    public final String mHeaderPrefix;

    /* access modifiers changed from: public */
    AnonymousClass1kM(String str, String str2) {
        this.mDigestInstanceString = str;
        this.mHeaderPrefix = str2;
    }

    public String getDigestInstanceString() {
        return this.mDigestInstanceString;
    }

    public String getHeaderPrefix() {
        return this.mHeaderPrefix;
    }
}
