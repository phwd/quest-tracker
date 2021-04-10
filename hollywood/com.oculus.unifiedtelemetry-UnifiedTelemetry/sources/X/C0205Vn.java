package X;

import java.io.Serializable;

/* renamed from: X.Vn  reason: case insensitive filesystem */
public final class C0205Vn implements Serializable {
    public static final long serialVersionUID = 1;
    public final boolean _isPrimitive;
    public final Object _nullValue;
    public final Class<?> _rawType;

    public final Object A00(AbstractC0226Wn wn) throws q0 {
        if (!this._isPrimitive || !wn.A0L(EnumC0225Wm.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            return this._nullValue;
        }
        throw C0223Wj.A00(wn.A00, AnonymousClass06.A05("Can not map JSON null into type ", this._rawType.getName(), " (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)"));
    }

    public C0205Vn(AbstractC0224Wl wl, Object obj) {
        this._nullValue = obj;
        this._isPrimitive = wl._class.isPrimitive();
        this._rawType = wl._class;
    }
}
