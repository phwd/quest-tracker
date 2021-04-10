package X;

import java.io.Serializable;

/* renamed from: X.Ot  reason: case insensitive filesystem */
public final class C0272Ot implements Serializable {
    public static final long serialVersionUID = 1;
    public final boolean _isPrimitive;
    public final Object _nullValue;
    public final Class _rawType;

    public final Object A00(AbstractC1022qr qrVar) {
        if (!this._isPrimitive || !qrVar.A0O(EnumC1023qs.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            return this._nullValue;
        }
        throw C1025qv.A00(null, AnonymousClass08.A05("Can not map JSON null into type ", this._rawType.getName(), " (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)"));
    }

    public C0272Ot(AbstractC1024qt qtVar, Object obj) {
        this._nullValue = obj;
        this._isPrimitive = qtVar._class.isPrimitive();
        this._rawType = qtVar._class;
    }
}
