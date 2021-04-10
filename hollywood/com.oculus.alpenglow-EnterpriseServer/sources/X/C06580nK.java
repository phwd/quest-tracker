package X;

import java.io.Serializable;

/* renamed from: X.0nK  reason: invalid class name and case insensitive filesystem */
public final class C06580nK implements Serializable {
    public static final long serialVersionUID = 1;
    public final boolean _isPrimitive;
    public final Object _nullValue;
    public final Class<?> _rawType;

    public final Object A00(AbstractC02570aK r4) throws C05910ld {
        if (!this._isPrimitive || !r4.A0O(EnumC02560aJ.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            return this._nullValue;
        }
        throw AnonymousClass0aG.A00(r4.A00, AnonymousClass006.A07("Can not map JSON null into type ", this._rawType.getName(), " (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)"));
    }

    public C06580nK(AnonymousClass0aI r2, Object obj) {
        this._nullValue = obj;
        this._isPrimitive = r2._class.isPrimitive();
        this._rawType = r2._class;
    }
}
