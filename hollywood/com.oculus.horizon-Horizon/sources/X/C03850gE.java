package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0gE  reason: invalid class name and case insensitive filesystem */
public final class C03850gE extends AbstractC05240kb implements Serializable {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer<?> _delegate;
    public final Class<?> _keyClass;

    @Override // X.AbstractC05240kb
    public final Object A00(String str, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        if (str == null) {
            return null;
        }
        try {
            Object A09 = this._delegate.A09(null, r5);
            if (A09 != null) {
                return A09;
            }
            r5.A0G(this._keyClass, str);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } catch (Exception e) {
            Class<?> cls = this._keyClass;
            AnonymousClass006.A05("not a valid representation: ", e.getMessage());
            r5.A0G(cls, str);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public C03850gE(Class<?> cls, JsonDeserializer<?> jsonDeserializer) {
        this._keyClass = cls;
        this._delegate = jsonDeserializer;
    }
}
