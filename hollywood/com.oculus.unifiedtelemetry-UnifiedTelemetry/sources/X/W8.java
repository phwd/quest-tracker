package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.NullifyingDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

public abstract class W8 extends V4 implements Serializable {
    public static final long serialVersionUID = 278445030337366675L;
    public final AbstractC0224Wl _baseType;
    public final AbstractC0224Wl _defaultImpl;
    public JsonDeserializer<Object> _defaultImplDeserializer;
    public final HashMap<String, JsonDeserializer<Object>> _deserializers;
    public final V3 _idResolver;
    public final AbstractC0227Wo _property;
    public final boolean _typeIdVisible;
    public final String _typePropertyName;

    @Override // X.V4
    public abstract EnumC0463pg A03();

    @Override // X.V4
    public abstract V4 A04(AbstractC0227Wo wo);

    @Override // X.V4
    public final Class<?> A06() {
        AbstractC0224Wl wl = this._defaultImpl;
        if (wl == null) {
            return null;
        }
        return wl._class;
    }

    public final JsonDeserializer<Object> A0C(AbstractC0226Wn wn) throws IOException, q0 {
        JsonDeserializer<Object> jsonDeserializer;
        AbstractC0224Wl wl = this._defaultImpl;
        if (wl == null) {
            if (wn.A0L(EnumC0225Wm.FAIL_ON_INVALID_SUBTYPE)) {
                return null;
            }
        } else if (wl._class != dY.class) {
            synchronized (wl) {
                jsonDeserializer = this._defaultImplDeserializer;
                if (jsonDeserializer == null) {
                    jsonDeserializer = wn.A06(this._defaultImpl, this._property);
                    this._defaultImplDeserializer = jsonDeserializer;
                }
            }
            return jsonDeserializer;
        }
        return NullifyingDeserializer.A00;
    }

    public final JsonDeserializer<Object> A0D(AbstractC0226Wn wn, String str) throws IOException, q0 {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this._deserializers) {
            jsonDeserializer = this._deserializers.get(str);
            if (jsonDeserializer == null) {
                AbstractC0224Wl A5W = this._idResolver.A5W(str);
                if (A5W != null) {
                    AbstractC0224Wl wl = this._baseType;
                    if (wl != null && wl.getClass() == A5W.getClass()) {
                        A5W = wl.A06(A5W._class);
                    }
                    jsonDeserializer = wn.A06(A5W, this._property);
                } else if (this._defaultImpl != null) {
                    jsonDeserializer = A0C(wn);
                } else {
                    AbstractC0224Wl wl2 = this._baseType;
                    AbstractC0232Ww ww = wn.A00;
                    StringBuilder sb = new StringBuilder("Could not resolve type id '");
                    sb.append(str);
                    sb.append("' into a subtype of ");
                    sb.append(wl2);
                    throw C0223Wj.A00(ww, sb.toString());
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

    @Override // X.V4
    public final V3 A05() {
        return this._idResolver;
    }

    @Override // X.V4
    public final String A0B() {
        return this._typePropertyName;
    }

    public W8(AbstractC0224Wl wl, V3 v3, String str, boolean z, Class<?> cls) {
        this._baseType = wl;
        this._idResolver = v3;
        this._typePropertyName = str;
        this._typeIdVisible = z;
        this._deserializers = new HashMap<>();
        if (cls == null) {
            this._defaultImpl = null;
        } else {
            if (cls != wl._class) {
                AbstractC0224Wl A07 = wl.A07(cls);
                Object obj = wl._valueHandler;
                A07 = obj != A07._valueHandler ? A07.A0C(obj) : A07;
                Object obj2 = wl._typeHandler;
                wl = obj2 != A07._typeHandler ? A07.A0B(obj2) : A07;
            }
            this._defaultImpl = wl;
        }
        this._property = null;
    }

    public W8(W8 w8, AbstractC0227Wo wo) {
        this._baseType = w8._baseType;
        this._idResolver = w8._idResolver;
        this._typePropertyName = w8._typePropertyName;
        this._typeIdVisible = w8._typeIdVisible;
        this._deserializers = w8._deserializers;
        this._defaultImpl = w8._defaultImpl;
        this._defaultImplDeserializer = w8._defaultImplDeserializer;
        this._property = wo;
    }
}
