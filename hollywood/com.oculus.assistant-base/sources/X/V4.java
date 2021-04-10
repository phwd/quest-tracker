package X;

import java.io.Serializable;
import java.util.Map;

public abstract class V4 extends AbstractC1032r3 implements Serializable {
    public static final int A00 = AbstractC1032r3.A00(EnumC1027qy.class);
    public static final long serialVersionUID = -8378230381628000111L;
    public final Map _mixInAnnotations;
    public final String _rootName = null;
    public final PP _subtypeResolver;
    public final Class _view = null;

    public V4(OS os, PP pp, Map map) {
        super(os, A00);
        this._mixInAnnotations = map;
        this._subtypeResolver = pp;
    }

    @Override // X.PF
    public final Class A25(Class cls) {
        Map map = this._mixInAnnotations;
        if (map == null) {
            return null;
        }
        return (Class) map.get(new C0297Pt(cls));
    }
}
