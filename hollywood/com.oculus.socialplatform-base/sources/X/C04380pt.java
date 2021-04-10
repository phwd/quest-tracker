package X;

import java.io.Serializable;

/* renamed from: X.0pt  reason: invalid class name and case insensitive filesystem */
public final class C04380pt implements Serializable {
    public static final long serialVersionUID = 1;
    public final boolean _isPrimitive;
    public final Object _nullValue;
    public final Class<?> _rawType;

    public final Object A00(AbstractC02210iH r4) throws C03620oC {
        if (!this._isPrimitive || !r4.A0P(EnumC02200iG.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            return this._nullValue;
        }
        throw C02180iD.A00(r4.A00, AnonymousClass006.A09("Can not map JSON null into type ", this._rawType.getName(), " (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)"));
    }

    public C04380pt(AbstractC02190iF r2, Object obj) {
        this._nullValue = obj;
        this._isPrimitive = r2._class.isPrimitive();
        this._rawType = r2._class;
    }
}
