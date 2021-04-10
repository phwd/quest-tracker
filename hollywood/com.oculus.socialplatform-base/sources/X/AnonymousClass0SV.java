package X;

import X.AbstractC04150pR;
import X.AnonymousClass0SV;
import java.io.Serializable;
import java.util.Map;

/* renamed from: X.0SV  reason: invalid class name */
public abstract class AnonymousClass0SV<CFG extends AbstractC04150pR, T extends AnonymousClass0SV<CFG, T>> extends AbstractC02110i2<T> implements Serializable {
    public static final int A00 = AbstractC02110i2.A00(EnumC02160i9.class);
    public static final long serialVersionUID = -8378230381628000111L;
    public final Map<C04720r6, Class<?>> _mixInAnnotations;
    public final String _rootName;
    public final AbstractC04510qY _subtypeResolver;
    public final Class<?> _view;

    @Override // X.AnonymousClass0qF
    public final Class<?> A3A(Class<?> cls) {
        Map<C04720r6, Class<?>> map = this._mixInAnnotations;
        if (map == null) {
            return null;
        }
        return map.get(new C04720r6(cls));
    }

    public AnonymousClass0SV(C04140pQ r2, AbstractC04510qY r3, Map<C04720r6, Class<?>> map) {
        super(r2, A00);
        this._mixInAnnotations = map;
        this._subtypeResolver = r3;
        this._rootName = null;
        this._view = null;
    }

    public AnonymousClass0SV(AnonymousClass0SV<CFG, T> r2) {
        super(r2);
        this._mixInAnnotations = r2._mixInAnnotations;
        this._subtypeResolver = r2._subtypeResolver;
        this._rootName = r2._rootName;
        this._view = r2._view;
    }

    public AnonymousClass0SV(AnonymousClass0SV<CFG, T> r2, int i) {
        super(r2._base, i);
        this._mixInAnnotations = r2._mixInAnnotations;
        this._subtypeResolver = r2._subtypeResolver;
        this._rootName = r2._rootName;
        this._view = r2._view;
    }

    public AnonymousClass0SV(AnonymousClass0SV<CFG, T> r2, C04140pQ r3) {
        super(r3, r2._mapperFeatures);
        this._mixInAnnotations = r2._mixInAnnotations;
        this._subtypeResolver = r2._subtypeResolver;
        this._rootName = r2._rootName;
        this._view = r2._view;
    }
}
