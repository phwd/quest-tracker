package X;

import X.AbstractC06430ms;
import X.AnonymousClass0L1;
import java.io.Serializable;
import java.util.Map;

/* renamed from: X.0L1  reason: invalid class name */
public abstract class AnonymousClass0L1<CFG extends AbstractC06430ms, T extends AnonymousClass0L1<CFG, T>> extends AnonymousClass0a7<T> implements Serializable {
    public static final int A00 = AnonymousClass0a7.A00(EnumC02540aC.class);
    public static final long serialVersionUID = -8378230381628000111L;
    public final Map<C07010oa, Class<?>> _mixInAnnotations;
    public final String _rootName = null;
    public final AnonymousClass0o1 _subtypeResolver;
    public final Class<?> _view = null;

    public AnonymousClass0L1(C06420mr r2, AnonymousClass0o1 r3, Map<C07010oa, Class<?>> map) {
        super(r2, A00);
        this._mixInAnnotations = map;
        this._subtypeResolver = r3;
    }

    @Override // X.AbstractC06690ng
    public final Class<?> A2l(Class<?> cls) {
        Map<C07010oa, Class<?>> map = this._mixInAnnotations;
        if (map == null) {
            return null;
        }
        return map.get(new C07010oa(cls));
    }
}
