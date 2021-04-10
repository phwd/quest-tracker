package X;

import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0Zv  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02490Zv extends AnonymousClass0mY implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _keyClass;

    @Override // X.AnonymousClass0mY
    public final Object A00(String str, AbstractC02570aK r5) throws IOException, C05910ld {
        if (str == null) {
            return null;
        }
        try {
            Object A01 = A01(str, r5);
            if (A01 != null) {
                return A01;
            }
            if (this._keyClass.isEnum()) {
                C01260Fu r0 = r5._config;
                EnumC02560aJ r1 = EnumC02560aJ.READ_UNKNOWN_ENUM_VALUES_AS_NULL;
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
            throw r5.A0E(this._keyClass, str, AnonymousClass006.A05("not a valid representation: ", e.getMessage()));
        }
    }

    public abstract Object A01(String str, AbstractC02570aK v) throws Exception;

    public AbstractC02490Zv(Class<?> cls) {
        this._keyClass = cls;
    }
}
