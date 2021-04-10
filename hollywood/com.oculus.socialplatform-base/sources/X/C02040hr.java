package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0hr  reason: invalid class name and case insensitive filesystem */
public final class C02040hr extends AnonymousClass0p6 implements Serializable {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer<?> _delegate;
    public final Class<?> _keyClass;

    @Override // X.AnonymousClass0p6
    public final Object A00(String str, AbstractC02210iH r5) throws IOException, C03620oC {
        if (str == null) {
            return null;
        }
        try {
            Object A0A = this._delegate.A0A(r5.A00, r5);
            if (A0A != null) {
                return A0A;
            }
            throw r5.A0E(this._keyClass, str, "not a valid representation");
        } catch (Exception e) {
            throw r5.A0E(this._keyClass, str, AnonymousClass006.A07("not a valid representation: ", e.getMessage()));
        }
    }

    public C02040hr(Class<?> cls, JsonDeserializer<?> jsonDeserializer) {
        this._keyClass = cls;
        this._delegate = jsonDeserializer;
    }
}
