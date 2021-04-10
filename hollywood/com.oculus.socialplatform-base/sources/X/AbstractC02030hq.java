package X;

import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0hq  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02030hq extends AnonymousClass0p6 implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _keyClass;

    @Override // X.AnonymousClass0p6
    public final Object A00(String str, AbstractC02210iH r5) throws IOException, C03620oC {
        if (str == null) {
            return null;
        }
        try {
            Object A01 = A01(str, r5);
            if (A01 != null) {
                return A01;
            }
            if (this._keyClass.isEnum()) {
                AnonymousClass0HU r0 = r5._config;
                EnumC02200iG r1 = EnumC02200iG.READ_UNKNOWN_ENUM_VALUES_AS_NULL;
                int mask = r1.getMask() & r0._deserFeatures;
                boolean z = false;
                if (mask != 0) {
                    z = true;
                }
                if (z) {
                    return null;
                }
            }
            throw r5.A0E(this._keyClass, str, "not a valid representation");
        } catch (Exception e) {
            throw r5.A0E(this._keyClass, str, AnonymousClass006.A07("not a valid representation: ", e.getMessage()));
        }
    }

    public abstract Object A01(String str, AbstractC02210iH v) throws Exception;

    public AbstractC02030hq(Class<?> cls) {
        this._keyClass = cls;
    }
}
