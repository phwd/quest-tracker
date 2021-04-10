package X;

import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.0S  reason: invalid class name */
public final class AnonymousClass0S extends PV {
    public static final AnonymousClass0S A01 = new AnonymousClass0S(OacrConstants.AUTO_SPEECH_DOMAIN);
    public final String A00;

    public static AnonymousClass0S A00(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return A01;
        }
        return new AnonymousClass0S(str);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ((AnonymousClass0S) obj).A00.equals(this.A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.OA, X.PV
    public final String toString() {
        String str = this.A00;
        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 2 + (length >> 4));
        sb.append('\"');
        C0245Ne.A00(sb, str);
        sb.append('\"');
        return sb.toString();
    }

    public AnonymousClass0S(String str) {
        this.A00 = str;
    }
}
