package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.NullifyingDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

/* renamed from: X.0Zf  reason: invalid class name */
public abstract class AnonymousClass0Zf extends AnonymousClass0o3 implements Serializable {
    public static final long serialVersionUID = 278445030337366675L;
    public final AnonymousClass0aI _baseType;
    public final AnonymousClass0aI _defaultImpl;
    public JsonDeserializer<Object> _defaultImplDeserializer;
    public final HashMap<String, JsonDeserializer<Object>> _deserializers;
    public final AnonymousClass0o4 _idResolver;
    public final AbstractC02580aL _property;
    public final boolean _typeIdVisible;
    public final String _typePropertyName;

    @Override // X.AnonymousClass0o3
    public abstract EnumC05770lI A03();

    @Override // X.AnonymousClass0o3
    public abstract AnonymousClass0o3 A04(AbstractC02580aL v);

    @Override // X.AnonymousClass0o3
    public final AnonymousClass0o4 A05() {
        return this._idResolver;
    }

    @Override // X.AnonymousClass0o3
    public final Class<?> A06() {
        AnonymousClass0aI r0 = this._defaultImpl;
        if (r0 == null) {
            return null;
        }
        return r0._class;
    }

    @Override // X.AnonymousClass0o3
    public final String A0B() {
        return this._typePropertyName;
    }

    public final JsonDeserializer<Object> A0C(AbstractC02570aK r4) throws IOException, C05910ld {
        JsonDeserializer<Object> jsonDeserializer;
        AnonymousClass0aI r2 = this._defaultImpl;
        if (r2 == null) {
            if (r4.A0O(EnumC02560aJ.FAIL_ON_INVALID_SUBTYPE)) {
                return null;
            }
        } else if (r2._class != C06410mq.class) {
            synchronized (r2) {
                jsonDeserializer = this._defaultImplDeserializer;
                if (jsonDeserializer == null) {
                    jsonDeserializer = r4.A09(this._defaultImpl, this._property);
                    this._defaultImplDeserializer = jsonDeserializer;
                }
            }
            return jsonDeserializer;
        }
        return NullifyingDeserializer.A00;
    }

    public final JsonDeserializer<Object> A0D(AbstractC02570aK r6, String str) throws IOException, C05910ld {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this._deserializers) {
            jsonDeserializer = this._deserializers.get(str);
            if (jsonDeserializer == null) {
                AnonymousClass0aI A8c = this._idResolver.A8c(str);
                if (A8c != null) {
                    AnonymousClass0aI r2 = this._baseType;
                    if (r2 != null && r2.getClass() == A8c.getClass()) {
                        A8c = r2.A07(A8c._class);
                    }
                    jsonDeserializer = r6.A09(A8c, this._property);
                } else if (this._defaultImpl != null) {
                    jsonDeserializer = A0C(r6);
                } else {
                    AnonymousClass0aI r3 = this._baseType;
                    AnonymousClass0aT r22 = r6.A00;
                    throw AnonymousClass0aG.A00(r22, "Could not resolve type id '" + str + "' into a subtype of " + r3);
                }
                this._deserializers.put(str, jsonDeserializer);
            }
        }
        return jsonDeserializer;
    }

    public final String toString() {
        return "[" + getClass().getName() + "; base-type:" + this._baseType + "; id-resolver: " + this._idResolver + ']';
    }

    public AnonymousClass0Zf(AnonymousClass0aI r5, AnonymousClass0o4 r6, String str, boolean z, Class<?> cls) {
        this._baseType = r5;
        this._idResolver = r6;
        this._typePropertyName = str;
        this._typeIdVisible = z;
        this._deserializers = new HashMap<>();
        if (cls == null) {
            this._defaultImpl = null;
        } else {
            if (cls != r5._class) {
                AnonymousClass0aI A09 = r5.A09(cls);
                Object obj = r5._valueHandler;
                A09 = obj != A09._valueHandler ? A09.A0F(obj) : A09;
                Object obj2 = r5._typeHandler;
                r5 = obj2 != A09._typeHandler ? A09.A0E(obj2) : A09;
            }
            this._defaultImpl = r5;
        }
        this._property = null;
    }

    public AnonymousClass0Zf(AnonymousClass0Zf r2, AbstractC02580aL r3) {
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
