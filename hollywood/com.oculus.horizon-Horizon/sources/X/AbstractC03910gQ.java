package X;

import X.AbstractC03910gQ;
import java.io.Serializable;

/* renamed from: X.0gQ  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03910gQ<T extends AbstractC03910gQ<T>> implements AbstractC05730ll, Serializable {
    public static final long serialVersionUID = 8891625428805876137L;
    public final C05350kv _base;
    public final int _mapperFeatures;

    public abstract AbstractC05180kU A02(AbstractC04000gb v);

    public AbstractC04040gi A01() {
        return this._base._annotationIntrospector;
    }

    public final AbstractC04000gb A03(Class<?> cls) {
        return this._base._typeFactory.A09(cls, null);
    }

    public AbstractC05820lu<?> A04() {
        return this._base._visibilityChecker;
    }

    public final boolean A05(EnumC03960gV r3) {
        if ((r3.getMask() & this._mapperFeatures) != 0) {
            return true;
        }
        return false;
    }

    public AbstractC03910gQ(C05350kv r1, int i) {
        this._base = r1;
        this._mapperFeatures = i;
    }

    public static <F extends Enum<F> & AbstractC05360kw> int A00(Class<F> cls) {
        int i = 0;
        for (Enum r1 : (Enum[]) cls.getEnumConstants()) {
            AbstractC05360kw r12 = (AbstractC05360kw) r1;
            if (r12.enabledByDefault()) {
                i |= r12.getMask();
            }
        }
        return i;
    }
}
