package X;

import X.D2;
import X.dU;
import java.io.Serializable;
import java.util.Map;

public abstract class D2<CFG extends dU, T extends D2<CFG, T>> extends WZ<T> implements Serializable {
    public static final int A00 = WZ.A00(EnumC0220Wf.class);
    public static final long serialVersionUID = -8378230381628000111L;
    public final Map<ON, Class<?>> _mixInAnnotations;
    public final String _rootName = null;
    public final V6 _subtypeResolver;
    public final Class<?> _view = null;

    public D2(dV dVVar, V6 v6, Map<ON, Class<?>> map) {
        super(dVVar, A00);
        this._mixInAnnotations = map;
        this._subtypeResolver = v6;
    }

    @Override // X.VQ
    public final Class<?> A2A(Class<?> cls) {
        Map<ON, Class<?>> map = this._mixInAnnotations;
        if (map == null) {
            return null;
        }
        return map.get(new ON(cls));
    }
}
