package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.io.Serializable;

public final class OE implements Serializable {
    public static final OE A00 = new OE(new String("#disabled"));
    public static final OE A01 = new OE(OacrConstants.AUTO_SPEECH_DOMAIN);
    public static final long serialVersionUID = 7930806520033045126L;
    public final String _namespace;
    public final String _simpleName;

    public OE(String str) {
        this._simpleName = str == null ? OacrConstants.AUTO_SPEECH_DOMAIN : str;
        this._namespace = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        if (r5._simpleName != null) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r3 = 1
            if (r5 == r4) goto L_0x0033
            r2 = 0
            if (r5 == 0) goto L_0x001a
            java.lang.Class r1 = r5.getClass()
            java.lang.Class r0 = r4.getClass()
            if (r1 != r0) goto L_0x001a
            X.OE r5 = (X.OE) r5
            java.lang.String r1 = r4._simpleName
            if (r1 != 0) goto L_0x001b
            java.lang.String r0 = r5._simpleName
            if (r0 == 0) goto L_0x0024
        L_0x001a:
            return r2
        L_0x001b:
            java.lang.String r0 = r5._simpleName
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0024
            return r2
        L_0x0024:
            java.lang.String r1 = r4._namespace
            java.lang.String r0 = r5._namespace
            if (r1 != 0) goto L_0x002e
            if (r0 == 0) goto L_0x0033
            r3 = 0
            return r3
        L_0x002e:
            boolean r0 = r1.equals(r0)
            return r0
        L_0x0033:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: X.OE.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        String str = this._namespace;
        if (str == null) {
            return this._simpleName.hashCode();
        }
        return str.hashCode() ^ this._simpleName.hashCode();
    }

    public Object readResolve() {
        String str = this._simpleName;
        if (str == null || OacrConstants.AUTO_SPEECH_DOMAIN.equals(str)) {
            return A01;
        }
        if (str.equals("#disabled")) {
            return A00;
        }
        return this;
    }

    public final String toString() {
        String str = this._namespace;
        if (str == null) {
            return this._simpleName;
        }
        return AnonymousClass08.A06("{", str, "}", this._simpleName);
    }
}
