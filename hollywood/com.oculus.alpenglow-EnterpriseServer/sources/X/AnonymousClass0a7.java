package X;

import X.AnonymousClass0a7;
import java.io.Serializable;

/* renamed from: X.0a7  reason: invalid class name */
public abstract class AnonymousClass0a7<T extends AnonymousClass0a7<T>> implements AbstractC06690ng, Serializable {
    public static final long serialVersionUID = 8891625428805876137L;
    public final C06420mr _base;
    public final int _mapperFeatures;

    public abstract AbstractC06260mR A02(AnonymousClass0aI v);

    public AbstractC02590aM A01() {
        return this._base._annotationIntrospector;
    }

    public final AnonymousClass0aI A03(Class<?> cls) {
        return this._base._typeFactory.A09(cls, null);
    }

    public AbstractC06760no<?> A04() {
        return this._base._visibilityChecker;
    }

    public final boolean A05(EnumC02540aC r3) {
        if ((r3.getMask() & this._mapperFeatures) != 0) {
            return true;
        }
        return false;
    }

    public AnonymousClass0a7(C06420mr r1, int i) {
        this._base = r1;
        this._mapperFeatures = i;
    }

    public static <F extends Enum<F> & AbstractC06430ms> int A00(Class<F> cls) {
        int i = 0;
        for (Enum r1 : (Enum[]) cls.getEnumConstants()) {
            AbstractC06430ms r12 = (AbstractC06430ms) r1;
            if (r12.enabledByDefault()) {
                i |= r12.getMask();
            }
        }
        return i;
    }
}
