package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class CO extends WN {
    public static final long serialVersionUID = 1;

    public CO() {
        super(Integer.class);
    }

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws Exception {
        int parseInt = Integer.parseInt(str);
        if (parseInt >= -32768 && parseInt <= 32767) {
            return Short.valueOf((short) parseInt);
        }
        throw wn.A0B(this._keyClass, str, "overflow, value can not be represented as 16-bit value");
    }
}
