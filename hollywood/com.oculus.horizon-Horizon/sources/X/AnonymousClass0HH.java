package X;

import X.AbstractC05360kw;
import X.AnonymousClass0HH;
import java.io.Serializable;
import java.util.Map;

/* renamed from: X.0HH  reason: invalid class name */
public abstract class AnonymousClass0HH<CFG extends AbstractC05360kw, T extends AnonymousClass0HH<CFG, T>> extends AbstractC03910gQ<T> implements Serializable {
    public static final int A00 = AbstractC03910gQ.A00(EnumC03960gV.class);
    public static final long serialVersionUID = -8378230381628000111L;
    public final Map<C06210mi, Class<?>> _mixInAnnotations;
    public final String _rootName = null;
    public final AbstractC05920m7 _subtypeResolver;
    public final Class<?> _view = null;

    public AnonymousClass0HH(C05350kv r2, AbstractC05920m7 r3, Map<C06210mi, Class<?>> map) {
        super(r2, A00);
        this._mixInAnnotations = map;
        this._subtypeResolver = r3;
    }

    @Override // X.AbstractC05730ll
    public final Class<?> A2n(Class<?> cls) {
        Map<C06210mi, Class<?>> map = this._mixInAnnotations;
        if (map == null) {
            return null;
        }
        return map.get(new C06210mi(cls));
    }
}
