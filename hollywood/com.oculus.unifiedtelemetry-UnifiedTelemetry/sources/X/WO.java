package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;

public final class WO extends AbstractC0420hV implements Serializable {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer<?> _delegate;
    public final Class<?> _keyClass;

    @Override // X.AbstractC0420hV
    public final Object A00(String str, AbstractC0226Wn wn) throws IOException, q0 {
        if (str == null) {
            return null;
        }
        try {
            Object A09 = this._delegate.A09(wn.A00, wn);
            if (A09 != null) {
                return A09;
            }
            throw wn.A0B(this._keyClass, str, "not a valid representation");
        } catch (Exception e) {
            throw wn.A0B(this._keyClass, str, AnonymousClass06.A04("not a valid representation: ", e.getMessage()));
        }
    }

    public WO(Class<?> cls, JsonDeserializer<?> jsonDeserializer) {
        this._keyClass = cls;
        this._delegate = jsonDeserializer;
    }
}
