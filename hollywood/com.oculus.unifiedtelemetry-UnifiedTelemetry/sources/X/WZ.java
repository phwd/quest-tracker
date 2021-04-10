package X;

import X.WZ;
import java.io.Serializable;

public abstract class WZ<T extends WZ<T>> implements VQ, Serializable {
    public static final long serialVersionUID = 8891625428805876137L;
    public final dV _base;
    public final int _mapperFeatures;

    public abstract jm A02(AbstractC0224Wl wl);

    public Wp A01() {
        return this._base._annotationIntrospector;
    }

    public final AbstractC0224Wl A03(Class<?> cls) {
        return this._base._typeFactory.A09(cls, null);
    }

    public VI<?> A04() {
        return this._base._visibilityChecker;
    }

    public final boolean A05(EnumC0220Wf wf) {
        if ((wf.getMask() & this._mapperFeatures) != 0) {
            return true;
        }
        return false;
    }

    public WZ(dV dVVar, int i) {
        this._base = dVVar;
        this._mapperFeatures = i;
    }

    public static <F extends Enum<F> & dU> int A00(Class<F> cls) {
        int i = 0;
        for (Enum r1 : (Enum[]) cls.getEnumConstants()) {
            dU dUVar = (dU) r1;
            if (dUVar.enabledByDefault()) {
                i |= dUVar.getMask();
            }
        }
        return i;
    }
}
