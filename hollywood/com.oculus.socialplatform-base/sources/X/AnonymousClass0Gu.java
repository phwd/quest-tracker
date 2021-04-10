package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/* renamed from: X.0Gu  reason: invalid class name */
public final class AnonymousClass0Gu extends AbstractC01170Rz {
    public static final long serialVersionUID = 1;
    public final C04390pu _objectIdReader;

    @Override // X.AbstractC02220iI, X.AbstractC01170Rz
    public final AbstractC01990hm A4N() {
        return null;
    }

    @Override // X.AbstractC01170Rz
    public final AbstractC01170Rz A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass0Gu(this, jsonDeserializer);
    }

    @Override // X.AbstractC01170Rz
    public final AbstractC01170Rz A03(String str) {
        return new AnonymousClass0Gu(this, str);
    }

    @Override // X.AbstractC01170Rz
    public final Object A06(AbstractC02280iQ r3, AbstractC02210iH r4, Object obj) throws IOException, C03620oC {
        Object A0A = this._valueDeserializer.A0A(r3, r4);
        r4.A0J(A0A, this._objectIdReader.generator).A00(obj);
        AbstractC01170Rz r0 = this._objectIdReader.idProperty;
        if (r0 != null) {
            return r0.A07(obj, A0A);
        }
        return obj;
    }

    @Override // X.AbstractC01170Rz
    public final Object A07(Object obj, Object obj2) throws IOException {
        AbstractC01170Rz r0 = this._objectIdReader.idProperty;
        if (r0 != null) {
            return r0.A07(obj, obj2);
        }
        throw new UnsupportedOperationException("Should not call set() on ObjectIdProperty that has no SettableBeanProperty");
    }

    @Override // X.AbstractC01170Rz
    public final void A0A(Object obj, Object obj2) throws IOException {
        A07(obj, obj2);
    }

    @Override // X.AbstractC01170Rz
    public final void A08(AbstractC02280iQ r1, AbstractC02210iH r2, Object obj) throws IOException, C03620oC {
        A06(r1, r2, obj);
    }

    public AnonymousClass0Gu(C04390pu r9) {
        super(r9.propertyName, r9.idType, null, null, null, true);
        this._objectIdReader = r9;
        this._valueDeserializer = r9.deserializer;
    }

    public AnonymousClass0Gu(AnonymousClass0Gu r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._objectIdReader = r2._objectIdReader;
    }

    public AnonymousClass0Gu(AnonymousClass0Gu r2, String str) {
        super(r2, str);
        this._objectIdReader = r2._objectIdReader;
    }
}
