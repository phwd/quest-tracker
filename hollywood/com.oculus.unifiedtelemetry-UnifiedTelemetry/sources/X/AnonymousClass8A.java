package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/* renamed from: X.8A  reason: invalid class name */
public final class AnonymousClass8A extends AbstractC0073Cr {
    public static final long serialVersionUID = 1;
    public final C0204Vm _objectIdReader;

    @Override // X.AbstractC0227Wo, X.AbstractC0073Cr
    public final WJ A2d() {
        return null;
    }

    @Override // X.AbstractC0073Cr
    public final AbstractC0073Cr A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass8A(this, jsonDeserializer);
    }

    @Override // X.AbstractC0073Cr
    public final AbstractC0073Cr A03(String str) {
        return new AnonymousClass8A(this, str);
    }

    @Override // X.AbstractC0073Cr
    public final Object A06(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        Object A09 = this._valueDeserializer.A09(ww, wn);
        wn.A0F(A09, this._objectIdReader.generator).A00(obj);
        AbstractC0073Cr cr = this._objectIdReader.idProperty;
        if (cr != null) {
            return cr.A07(obj, A09);
        }
        return obj;
    }

    @Override // X.AbstractC0073Cr
    public final Object A07(Object obj, Object obj2) throws IOException {
        AbstractC0073Cr cr = this._objectIdReader.idProperty;
        if (cr != null) {
            return cr.A07(obj, obj2);
        }
        throw new UnsupportedOperationException("Should not call set() on ObjectIdProperty that has no SettableBeanProperty");
    }

    @Override // X.AbstractC0073Cr
    public final void A0A(Object obj, Object obj2) throws IOException {
        A07(obj, obj2);
    }

    @Override // X.AbstractC0073Cr
    public final void A08(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        A06(ww, wn, obj);
    }

    public AnonymousClass8A(C0204Vm vm) {
        super(vm.propertyName, vm.idType, null, null, null, true);
        this._objectIdReader = vm;
        this._valueDeserializer = vm.deserializer;
    }

    public AnonymousClass8A(AnonymousClass8A r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._objectIdReader = r2._objectIdReader;
    }

    public AnonymousClass8A(AnonymousClass8A r2, String str) {
        super(r2, str);
        this._objectIdReader = r2._objectIdReader;
    }
}
