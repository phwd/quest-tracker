package X;

import java.io.Serializable;

/* renamed from: X.0lP  reason: invalid class name and case insensitive filesystem */
public final class C05520lP implements Serializable {
    public static final long serialVersionUID = 1;
    public final boolean _isPrimitive;
    public final Object _nullValue;
    public final Class<?> _rawType;

    public final Object A00(AbstractC04020gg r4) throws AnonymousClass0jg {
        if (!this._isPrimitive || !r4.A0I(EnumC04010gf.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            return this._nullValue;
        }
        throw C03990gZ.A00(null, AnonymousClass006.A07("Can not map JSON null into type ", this._rawType.getName(), " (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)"));
    }

    public C05520lP(AbstractC04000gb r2, Object obj) {
        this._nullValue = obj;
        this._isPrimitive = r2._class.isPrimitive();
        this._rawType = r2._class;
    }
}
