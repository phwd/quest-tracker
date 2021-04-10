package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
/* renamed from: X.0KX  reason: invalid class name */
public final class AnonymousClass0KX extends AbstractC02490Zv {
    public static final long serialVersionUID = 1;

    public AnonymousClass0KX() {
        super(Character.class);
    }

    @Override // X.AbstractC02490Zv
    public final Object A01(String str, AbstractC02570aK r4) throws Exception {
        if (str.length() == 1) {
            return Character.valueOf(str.charAt(0));
        }
        throw r4.A0E(this._keyClass, str, "can only convert 1-character Strings");
    }
}
