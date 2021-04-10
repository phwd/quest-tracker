package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.NullifyingDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

/* renamed from: X.0fx  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03720fx extends AnonymousClass0m9 implements Serializable {
    public static final long serialVersionUID = 278445030337366675L;
    public final AbstractC04000gb _baseType;
    public final AbstractC04000gb _defaultImpl;
    public JsonDeserializer<Object> _defaultImplDeserializer;
    public final HashMap<String, JsonDeserializer<Object>> _deserializers;
    public final AbstractC05940mA _idResolver;
    public final AbstractC04030gh _property;
    public final boolean _typeIdVisible;
    public final String _typePropertyName;

    @Override // X.AnonymousClass0m9
    public abstract EnumC04710jK A03();

    @Override // X.AnonymousClass0m9
    public abstract AnonymousClass0m9 A04(AbstractC04030gh v);

    @Override // X.AnonymousClass0m9
    public final Class<?> A06() {
        AbstractC04000gb r0 = this._defaultImpl;
        if (r0 == null) {
            return null;
        }
        return r0._class;
    }

    public final JsonDeserializer<Object> A0C(AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        JsonDeserializer<Object> jsonDeserializer;
        AbstractC04000gb r2 = this._defaultImpl;
        if (r2 == null) {
            if (r4.A0I(EnumC04010gf.FAIL_ON_INVALID_SUBTYPE)) {
                return null;
            }
        } else if (r2._class != C05340ku.class) {
            synchronized (r2) {
                jsonDeserializer = this._defaultImplDeserializer;
                if (jsonDeserializer == null) {
                    jsonDeserializer = r4.A05(this._defaultImpl, this._property);
                    this._defaultImplDeserializer = jsonDeserializer;
                }
            }
            return jsonDeserializer;
        }
        return NullifyingDeserializer.A00;
    }

    public final JsonDeserializer<Object> A0D(AbstractC04020gg r6, String str) throws IOException, AnonymousClass0jg {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this._deserializers) {
            jsonDeserializer = this._deserializers.get(str);
            if (jsonDeserializer == null) {
                AbstractC04000gb A9e = this._idResolver.A9e(str);
                if (A9e != null) {
                    AbstractC04000gb r2 = this._baseType;
                    if (r2 != null && r2.getClass() == A9e.getClass()) {
                        A9e = r2.A06(A9e._class);
                    }
                    jsonDeserializer = r6.A05(A9e, this._property);
                } else if (this._defaultImpl != null) {
                    jsonDeserializer = A0C(r6);
                } else {
                    AbstractC04000gb r3 = this._baseType;
                    StringBuilder sb = new StringBuilder("Could not resolve type id '");
                    sb.append(str);
                    sb.append("' into a subtype of ");
                    sb.append(r3);
                    throw C03990gZ.A00(null, sb.toString());
                }
                this._deserializers.put(str, jsonDeserializer);
            }
        }
        return jsonDeserializer;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(getClass().getName());
        sb.append("; base-type:");
        sb.append(this._baseType);
        sb.append("; id-resolver: ");
        sb.append(this._idResolver);
        sb.append(']');
        return sb.toString();
    }

    @Override // X.AnonymousClass0m9
    public final AbstractC05940mA A05() {
        return this._idResolver;
    }

    @Override // X.AnonymousClass0m9
    public final String A0B() {
        return this._typePropertyName;
    }

    public AbstractC03720fx(AbstractC04000gb r5, AbstractC05940mA r6, String str, boolean z, Class<?> cls) {
        this._baseType = r5;
        this._idResolver = r6;
        this._typePropertyName = str;
        this._typeIdVisible = z;
        this._deserializers = new HashMap<>();
        if (cls == null) {
            this._defaultImpl = null;
        } else {
            if (cls != r5._class) {
                AbstractC04000gb A07 = r5.A07(cls);
                Object obj = r5._valueHandler;
                A07 = obj != A07._valueHandler ? A07.A0C(obj) : A07;
                Object obj2 = r5._typeHandler;
                r5 = obj2 != A07._typeHandler ? A07.A0B(obj2) : A07;
            }
            this._defaultImpl = r5;
        }
        this._property = null;
    }

    public AbstractC03720fx(AbstractC03720fx r2, AbstractC04030gh r3) {
        this._baseType = r2._baseType;
        this._idResolver = r2._idResolver;
        this._typePropertyName = r2._typePropertyName;
        this._typeIdVisible = r2._typeIdVisible;
        this._deserializers = r2._deserializers;
        this._defaultImpl = r2._defaultImpl;
        this._defaultImplDeserializer = r2._defaultImplDeserializer;
        this._property = r3;
    }
}
