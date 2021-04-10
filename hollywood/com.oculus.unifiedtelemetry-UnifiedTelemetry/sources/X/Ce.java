package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class Ce extends WN {
    public static final long serialVersionUID = 1;

    public Ce() {
        super(Character.class);
    }

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws Exception {
        if (str.length() == 1) {
            return Character.valueOf(str.charAt(0));
        }
        throw wn.A0B(this._keyClass, str, "can only convert 1-character Strings");
    }
}
