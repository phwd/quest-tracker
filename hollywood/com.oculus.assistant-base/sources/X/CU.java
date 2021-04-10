package X;

import com.facebook.assistant.oacr.OacrConstants;

public final class CU {
    public static final CU A01 = new CU(OacrConstants.AUTO_SPEECH_DOMAIN);
    public final String A00;

    public final boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && this.A00.equals(((CU) obj).A00));
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public CU(String str) {
        if (str == null || str.contains(":")) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.A00 = str;
    }

    public final String toString() {
        return this.A00;
    }
}
