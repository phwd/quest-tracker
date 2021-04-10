package X;

import com.fasterxml.jackson.databind.JsonDeserializer;

/* renamed from: X.Td  reason: case insensitive filesystem */
public final class C0353Td extends AbstractC1034r7 {
    public static final long serialVersionUID = 1;
    public final AbstractC1034r7 _backProperty;
    public final boolean _isContainer;
    public final AbstractC1034r7 _managedProperty;
    public final String _referenceName;

    public C0353Td(AbstractC1034r7 r7Var, String str, AbstractC1034r7 r7Var2, Q0 q0, boolean z) {
        super(r7Var._propName, r7Var.A34(), r7Var._wrapperName, r7Var._valueTypeDeserializer, q0, r7Var._isRequired);
        this._referenceName = str;
        this._managedProperty = r7Var;
        this._backProperty = r7Var2;
        this._isContainer = z;
    }

    public C0353Td(C0353Td td, JsonDeserializer jsonDeserializer) {
        super(td, jsonDeserializer);
        this._referenceName = td._referenceName;
        this._isContainer = td._isContainer;
        this._managedProperty = td._managedProperty;
        this._backProperty = td._backProperty;
    }

    public C0353Td(C0353Td td, String str) {
        super(td, str);
        this._referenceName = td._referenceName;
        this._isContainer = td._isContainer;
        this._managedProperty = td._managedProperty;
        this._backProperty = td._backProperty;
    }
}
