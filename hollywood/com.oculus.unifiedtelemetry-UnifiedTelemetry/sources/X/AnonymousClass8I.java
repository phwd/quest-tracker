package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/* renamed from: X.8I  reason: invalid class name */
public final class AnonymousClass8I extends AbstractC0073Cr {
    public static final long serialVersionUID = 1;
    public final CC _annotated;
    public final int _creatorIndex;
    public final Object _injectableValueId;

    @Override // X.AbstractC0073Cr
    public final Object A07(Object obj, Object obj2) throws IOException {
        return obj;
    }

    @Override // X.AbstractC0073Cr
    public final /* bridge */ /* synthetic */ AbstractC0073Cr A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass8I(this, jsonDeserializer);
    }

    @Override // X.AbstractC0073Cr
    public final AbstractC0073Cr A03(String str) {
        return new AnonymousClass8I(this, str);
    }

    @Override // X.AbstractC0073Cr
    public final void A0A(Object obj, Object obj2) throws IOException {
        throw new IllegalStateException(AnonymousClass06.A04("Method should never be called on a ", getClass().getName()));
    }

    @Override // X.AbstractC0073Cr
    public final String toString() {
        StringBuilder sb = new StringBuilder("[creator property, name '");
        sb.append(this._propName);
        sb.append("'; inject id '");
        sb.append(this._injectableValueId);
        sb.append("']");
        return sb.toString();
    }

    @Override // X.AbstractC0073Cr
    public final void A08(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        A0A(obj, A05(ww, wn));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC0073Cr
    public final int A01() {
        return this._creatorIndex;
    }

    @Override // X.AbstractC0073Cr
    public final Object A04() {
        return this._injectableValueId;
    }

    @Override // X.AbstractC0227Wo, X.AbstractC0073Cr
    public final WJ A2d() {
        return this._annotated;
    }

    @Override // X.AbstractC0073Cr
    public final Object A06(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        A05(ww, wn);
        return obj;
    }

    public AnonymousClass8I(AnonymousClass8I r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this._creatorIndex = r2._creatorIndex;
        this._injectableValueId = r2._injectableValueId;
    }

    public AnonymousClass8I(AnonymousClass8I r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this._creatorIndex = r2._creatorIndex;
        this._injectableValueId = r2._injectableValueId;
    }

    public AnonymousClass8I(String str, AbstractC0224Wl wl, C0417hQ hQVar, V4 v4, N6 n6, CC cc, int i, Object obj, boolean z) {
        super(str, wl, hQVar, v4, n6, z);
        this._annotated = cc;
        this._creatorIndex = i;
        this._injectableValueId = obj;
    }
}
