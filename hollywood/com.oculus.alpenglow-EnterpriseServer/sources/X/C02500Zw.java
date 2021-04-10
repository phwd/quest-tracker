package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0Zw  reason: invalid class name and case insensitive filesystem */
public final class C02500Zw extends AnonymousClass0mY implements Serializable {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer<?> _delegate;
    public final Class<?> _keyClass;

    @Override // X.AnonymousClass0mY
    public final Object A00(String str, AbstractC02570aK r5) throws IOException, C05910ld {
        if (str == null) {
            return null;
        }
        try {
            Object A09 = this._delegate.A09(r5.A00, r5);
            if (A09 != null) {
                return A09;
            }
            throw r5.A0E(this._keyClass, str, "not a valid representation");
        } catch (Exception e) {
            throw r5.A0E(this._keyClass, str, AnonymousClass006.A05("not a valid representation: ", e.getMessage()));
        }
    }

    public C02500Zw(Class<?> cls, JsonDeserializer<?> jsonDeserializer) {
        this._keyClass = cls;
        this._delegate = jsonDeserializer;
    }
}
