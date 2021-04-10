package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/* renamed from: X.0Eu  reason: invalid class name */
public final class AnonymousClass0Eu extends AbstractC01680Ku {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0nL _objectIdReader;

    @Override // X.AbstractC02580aL, X.AbstractC01680Ku
    public final AbstractC02450Zr A41() {
        return null;
    }

    @Override // X.AbstractC01680Ku
    public final AbstractC01680Ku A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass0Eu(this, jsonDeserializer);
    }

    @Override // X.AbstractC01680Ku
    public final AbstractC01680Ku A03(String str) {
        return new AnonymousClass0Eu(this, str);
    }

    @Override // X.AbstractC01680Ku
    public final Object A06(AnonymousClass0aT r3, AbstractC02570aK r4, Object obj) throws IOException, C05910ld {
        Object A09 = this._valueDeserializer.A09(r3, r4);
        r4.A0I(A09, this._objectIdReader.generator).A00(obj);
        AbstractC01680Ku r0 = this._objectIdReader.idProperty;
        if (r0 != null) {
            return r0.A07(obj, A09);
        }
        return obj;
    }

    @Override // X.AbstractC01680Ku
    public final Object A07(Object obj, Object obj2) throws IOException {
        AbstractC01680Ku r0 = this._objectIdReader.idProperty;
        if (r0 != null) {
            return r0.A07(obj, obj2);
        }
        throw new UnsupportedOperationException("Should not call set() on ObjectIdProperty that has no SettableBeanProperty");
    }

    @Override // X.AbstractC01680Ku
    public final void A0A(Object obj, Object obj2) throws IOException {
        A07(obj, obj2);
    }

    @Override // X.AbstractC01680Ku
    public final void A08(AnonymousClass0aT r1, AbstractC02570aK r2, Object obj) throws IOException, C05910ld {
        A06(r1, r2, obj);
    }

    public AnonymousClass0Eu(AnonymousClass0nL r9) {
        super(r9.propertyName, r9.idType, null, null, null, true);
        this._objectIdReader = r9;
        this._valueDeserializer = r9.deserializer;
    }

    public AnonymousClass0Eu(AnonymousClass0Eu r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._objectIdReader = r2._objectIdReader;
    }

    public AnonymousClass0Eu(AnonymousClass0Eu r2, String str) {
        super(r2, str);
        this._objectIdReader = r2._objectIdReader;
    }
}
