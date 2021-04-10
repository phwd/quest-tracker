package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.NullifyingDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

/* renamed from: X.0hF  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01880hF extends AbstractC04520qa implements Serializable {
    public static final long serialVersionUID = 278445030337366675L;
    public final AbstractC02190iF _baseType;
    public final AbstractC02190iF _defaultImpl;
    public JsonDeserializer<Object> _defaultImplDeserializer;
    public final HashMap<String, JsonDeserializer<Object>> _deserializers;
    public final AbstractC04530qb _idResolver;
    public final AbstractC02220iI _property;
    public final boolean _typeIdVisible;
    public final String _typePropertyName;

    @Override // X.AbstractC04520qa
    public abstract EnumC03570nq A03();

    @Override // X.AbstractC04520qa
    public abstract AbstractC04520qa A04(AbstractC02220iI v);

    @Override // X.AbstractC04520qa
    public final Class<?> A06() {
        AbstractC02190iF r0 = this._defaultImpl;
        if (r0 == null) {
            return null;
        }
        return r0._class;
    }

    public final JsonDeserializer<Object> A0C(AbstractC02210iH r4) throws IOException, C03620oC {
        JsonDeserializer<Object> jsonDeserializer;
        AbstractC02190iF r2 = this._defaultImpl;
        if (r2 == null) {
            if (r4.A0P(EnumC02200iG.FAIL_ON_INVALID_SUBTYPE)) {
                return null;
            }
        } else if (r2._class != C04130pP.class) {
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

    public final JsonDeserializer<Object> A0D(AbstractC02210iH r6, String str) throws IOException, C03620oC {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this._deserializers) {
            jsonDeserializer = this._deserializers.get(str);
            if (jsonDeserializer == null) {
                AbstractC02190iF AAn = this._idResolver.AAn(str);
                if (AAn != null) {
                    AbstractC02190iF r2 = this._baseType;
                    if (r2 != null && r2.getClass() == AAn.getClass()) {
                        AAn = r2.A07(AAn._class);
                    }
                    jsonDeserializer = r6.A09(AAn, this._property);
                } else if (this._defaultImpl != null) {
                    jsonDeserializer = A0C(r6);
                } else {
                    AbstractC02190iF r3 = this._baseType;
                    AbstractC02280iQ r22 = r6.A00;
                    StringBuilder sb = new StringBuilder("Could not resolve type id '");
                    sb.append(str);
                    sb.append("' into a subtype of ");
                    sb.append(r3);
                    throw C02180iD.A00(r22, sb.toString());
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

    @Override // X.AbstractC04520qa
    public final AbstractC04530qb A05() {
        return this._idResolver;
    }

    @Override // X.AbstractC04520qa
    public final String A0B() {
        return this._typePropertyName;
    }

    public AbstractC01880hF(AbstractC02190iF r5, AbstractC04530qb r6, String str, boolean z, Class<?> cls) {
        this._baseType = r5;
        this._idResolver = r6;
        this._typePropertyName = str;
        this._typeIdVisible = z;
        this._deserializers = new HashMap<>();
        if (cls == null) {
            this._defaultImpl = null;
        } else {
            if (cls != r5._class) {
                AbstractC02190iF A09 = r5.A09(cls);
                Object obj = r5._valueHandler;
                A09 = obj != A09._valueHandler ? A09.A0F(obj) : A09;
                Object obj2 = r5._typeHandler;
                r5 = obj2 != A09._typeHandler ? A09.A0E(obj2) : A09;
            }
            this._defaultImpl = r5;
        }
        this._property = null;
    }

    public AbstractC01880hF(AbstractC01880hF r2, AbstractC02220iI r3) {
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
