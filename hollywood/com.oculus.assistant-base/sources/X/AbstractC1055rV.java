package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.NullifyingDeserializer;
import java.io.Serializable;
import java.util.HashMap;

/* renamed from: X.rV  reason: case insensitive filesystem */
public abstract class AbstractC1055rV extends PR implements Serializable {
    public static final long serialVersionUID = 278445030337366675L;
    public final AbstractC1024qt _baseType;
    public final AbstractC1024qt _defaultImpl;
    public JsonDeserializer _defaultImplDeserializer;
    public final HashMap _deserializers;
    public final PS _idResolver;
    public final O5 _property;
    public final boolean _typeIdVisible;
    public final String _typePropertyName;

    public final JsonDeserializer A07(AbstractC1022qr qrVar) {
        JsonDeserializer jsonDeserializer;
        AbstractC1024qt qtVar = this._defaultImpl;
        if (qtVar == null) {
            if (qrVar.A0O(EnumC1023qs.FAIL_ON_INVALID_SUBTYPE)) {
                return null;
            }
        } else if (qtVar._class != OR.class) {
            synchronized (qtVar) {
                jsonDeserializer = this._defaultImplDeserializer;
                if (jsonDeserializer == null) {
                    jsonDeserializer = qrVar.A08(this._defaultImpl, this._property);
                    this._defaultImplDeserializer = jsonDeserializer;
                }
            }
            return jsonDeserializer;
        }
        return NullifyingDeserializer.A00;
    }

    public final JsonDeserializer A08(AbstractC1022qr qrVar, String str) {
        JsonDeserializer jsonDeserializer;
        synchronized (this._deserializers) {
            jsonDeserializer = (JsonDeserializer) this._deserializers.get(str);
            if (jsonDeserializer == null) {
                AbstractC1024qt A5H = this._idResolver.A5H(str);
                if (A5H != null) {
                    AbstractC1024qt qtVar = this._baseType;
                    if (qtVar != null && qtVar.getClass() == A5H.getClass()) {
                        A5H = qtVar.A09(A5H._class);
                    }
                    jsonDeserializer = qrVar.A08(A5H, this._property);
                } else if (this._defaultImpl != null) {
                    jsonDeserializer = A07(qrVar);
                } else {
                    AbstractC1024qt qtVar2 = this._baseType;
                    StringBuilder sb = new StringBuilder("Could not resolve type id '");
                    sb.append(str);
                    sb.append("' into a subtype of ");
                    sb.append(qtVar2);
                    throw C1025qv.A00(null, sb.toString());
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

    public AbstractC1055rV(AbstractC1024qt qtVar, PS ps, String str, boolean z, Class cls) {
        this._baseType = qtVar;
        this._idResolver = ps;
        this._typePropertyName = str;
        this._typeIdVisible = z;
        this._deserializers = new HashMap();
        if (cls == null) {
            this._defaultImpl = null;
        } else {
            if (cls != qtVar._class) {
                AbstractC1024qt A07 = qtVar.A07(cls);
                Object obj = qtVar._valueHandler;
                A07 = obj != A07._valueHandler ? A07.A0E(obj) : A07;
                Object obj2 = qtVar._typeHandler;
                qtVar = obj2 != A07._typeHandler ? A07.A0D(obj2) : A07;
            }
            this._defaultImpl = qtVar;
        }
        this._property = null;
    }

    public AbstractC1055rV(AbstractC1055rV rVVar, O5 o5) {
        this._baseType = rVVar._baseType;
        this._idResolver = rVVar._idResolver;
        this._typePropertyName = rVVar._typePropertyName;
        this._typeIdVisible = rVVar._typeIdVisible;
        this._deserializers = rVVar._deserializers;
        this._defaultImpl = rVVar._defaultImpl;
        this._defaultImplDeserializer = rVVar._defaultImplDeserializer;
        this._property = o5;
    }
}
