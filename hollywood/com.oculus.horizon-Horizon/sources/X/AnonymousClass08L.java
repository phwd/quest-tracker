package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/* renamed from: X.08L  reason: invalid class name */
public final class AnonymousClass08L extends AnonymousClass0HD {
    public static final long serialVersionUID = 1;
    public final C05530lQ _objectIdReader;

    @Override // X.AbstractC04030gh, X.AnonymousClass0HD
    public final AnonymousClass0g9 A3p() {
        return null;
    }

    @Override // X.AnonymousClass0HD
    public final AnonymousClass0HD A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass08L(this, jsonDeserializer);
    }

    @Override // X.AnonymousClass0HD
    public final AnonymousClass0HD A03(String str) {
        return new AnonymousClass08L(this, str);
    }

    @Override // X.AnonymousClass0HD
    public final Object A06(AbstractC04100gp r3, AbstractC04020gg r4, Object obj) throws IOException, AnonymousClass0jg {
        Object A09 = this._valueDeserializer.A09(r3, r4);
        r4.A0B(A09, this._objectIdReader.generator).A00(obj);
        AnonymousClass0HD r0 = this._objectIdReader.idProperty;
        if (r0 != null) {
            return r0.A07(obj, A09);
        }
        return obj;
    }

    @Override // X.AnonymousClass0HD
    public final Object A07(Object obj, Object obj2) throws IOException {
        AnonymousClass0HD r0 = this._objectIdReader.idProperty;
        if (r0 != null) {
            return r0.A07(obj, obj2);
        }
        throw new UnsupportedOperationException("Should not call set() on ObjectIdProperty that has no SettableBeanProperty");
    }

    @Override // X.AnonymousClass0HD
    public final void A08(AbstractC04100gp r1, AbstractC04020gg r2, Object obj) throws IOException, AnonymousClass0jg {
        A06(r1, r2, obj);
    }

    @Override // X.AnonymousClass0HD
    public final void A0A(Object obj, Object obj2) throws IOException {
        A07(obj, obj2);
    }

    public AnonymousClass08L(C05530lQ r9) {
        super(r9.propertyName, r9.idType, null, null, null, true);
        this._objectIdReader = r9;
        this._valueDeserializer = r9.deserializer;
    }

    public AnonymousClass08L(AnonymousClass08L r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._objectIdReader = r2._objectIdReader;
    }

    public AnonymousClass08L(AnonymousClass08L r2, String str) {
        super(r2, str);
        this._objectIdReader = r2._objectIdReader;
    }
}
