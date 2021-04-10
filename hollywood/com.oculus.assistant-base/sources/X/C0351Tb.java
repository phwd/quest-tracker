package X;

import com.fasterxml.jackson.databind.JsonDeserializer;

/* renamed from: X.Tb  reason: case insensitive filesystem */
public final class C0351Tb extends AbstractC1034r7 {
    public static final long serialVersionUID = 1;
    public final C0273Ou _objectIdReader;

    public C0351Tb(C0273Ou ou) {
        super(ou.propertyName, ou.idType, null, null, null, true);
        this._objectIdReader = ou;
        this._valueDeserializer = ou.deserializer;
    }

    public C0351Tb(C0351Tb tb, JsonDeserializer jsonDeserializer) {
        super(tb, jsonDeserializer);
        this._objectIdReader = tb._objectIdReader;
    }

    public C0351Tb(C0351Tb tb, String str) {
        super(tb, str);
        this._objectIdReader = tb._objectIdReader;
    }
}
