package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;

/* renamed from: X.rE  reason: case insensitive filesystem */
public final class C1039rE extends OD implements Serializable {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer _delegate;
    public final Class _keyClass;

    public C1039rE(Class cls, JsonDeserializer jsonDeserializer) {
        this._keyClass = cls;
        this._delegate = jsonDeserializer;
    }
}
