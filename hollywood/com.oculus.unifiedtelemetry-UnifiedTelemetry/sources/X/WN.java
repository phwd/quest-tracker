package X;

import java.io.IOException;
import java.io.Serializable;

public abstract class WN extends AbstractC0420hV implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _keyClass;

    @Override // X.AbstractC0420hV
    public final Object A00(String str, AbstractC0226Wn wn) throws IOException, q0 {
        if (str == null) {
            return null;
        }
        try {
            Object A01 = A01(str, wn);
            if (A01 != null) {
                return A01;
            }
            if (this._keyClass.isEnum() && wn._config.A06(EnumC0225Wm.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return null;
            }
            throw wn.A0B(this._keyClass, str, "not a valid representation");
        } catch (Exception e) {
            throw wn.A0B(this._keyClass, str, AnonymousClass06.A04("not a valid representation: ", e.getMessage()));
        }
    }

    public abstract Object A01(String str, AbstractC0226Wn wn) throws Exception;

    public WN(Class<?> cls) {
        this._keyClass = cls;
    }
}
