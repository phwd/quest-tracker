package X;

import X.AbstractC02110i2;
import java.io.Serializable;

/* renamed from: X.0i2  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02110i2<T extends AbstractC02110i2<T>> implements AnonymousClass0qF, Serializable {
    public static final long serialVersionUID = 8891625428805876137L;
    public final C04140pQ _base;
    public final int _mapperFeatures;

    public abstract AbstractC04010oz A02(AbstractC02190iF v);

    public AbstractC02230iJ A01() {
        return this._base._annotationIntrospector;
    }

    public final AbstractC02190iF A03(Class<?> cls) {
        return this._base._typeFactory.A09(cls, null);
    }

    public AnonymousClass0qO<?> A04() {
        return this._base._visibilityChecker;
    }

    public final boolean A05(EnumC02160i9 r3) {
        if ((r3.getMask() & this._mapperFeatures) != 0) {
            return true;
        }
        return false;
    }

    public static <F extends Enum<F> & AbstractC04150pR> int A00(Class<F> cls) {
        int i = 0;
        for (Enum r1 : (Enum[]) cls.getEnumConstants()) {
            AbstractC04150pR r12 = (AbstractC04150pR) r1;
            if (r12.enabledByDefault()) {
                i |= r12.getMask();
            }
        }
        return i;
    }

    public AbstractC02110i2(C04140pQ r1, int i) {
        this._base = r1;
        this._mapperFeatures = i;
    }

    public AbstractC02110i2(AbstractC02110i2<T> r2) {
        this._base = r2._base;
        this._mapperFeatures = r2._mapperFeatures;
    }
}
