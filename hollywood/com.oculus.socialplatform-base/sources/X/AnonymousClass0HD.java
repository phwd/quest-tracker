package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/* renamed from: X.0HD  reason: invalid class name */
public final class AnonymousClass0HD extends AbstractC01170Rz {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0Ox _annotated;
    public final int _creatorIndex;
    public final Object _injectableValueId;

    @Override // X.AbstractC01170Rz
    public final Object A07(Object obj, Object obj2) throws IOException {
        return obj;
    }

    @Override // X.AbstractC01170Rz
    public final /* bridge */ /* synthetic */ AbstractC01170Rz A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass0HD(this, jsonDeserializer);
    }

    @Override // X.AbstractC01170Rz
    public final AbstractC01170Rz A03(String str) {
        return new AnonymousClass0HD(this, str);
    }

    @Override // X.AbstractC01170Rz
    public final void A0A(Object obj, Object obj2) throws IOException {
        throw new IllegalStateException(AnonymousClass006.A07("Method should never be called on a ", getClass().getName()));
    }

    @Override // X.AbstractC01170Rz
    public final String toString() {
        StringBuilder sb = new StringBuilder("[creator property, name '");
        sb.append(this._propName);
        sb.append("'; inject id '");
        sb.append(this._injectableValueId);
        sb.append("']");
        return sb.toString();
    }

    @Override // X.AbstractC01170Rz
    public final int A01() {
        return this._creatorIndex;
    }

    @Override // X.AbstractC01170Rz
    public final Object A04() {
        return this._injectableValueId;
    }

    @Override // X.AbstractC01170Rz
    public final void A08(AbstractC02280iQ r3, AbstractC02210iH r4, Object obj) throws IOException, C03620oC {
        A0A(obj, A05(r3, r4));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC02220iI, X.AbstractC01170Rz
    public final AbstractC01990hm A4N() {
        return this._annotated;
    }

    @Override // X.AbstractC01170Rz
    public final Object A06(AbstractC02280iQ r1, AbstractC02210iH r2, Object obj) throws IOException, C03620oC {
        A05(r1, r2);
        return obj;
    }

    public AnonymousClass0HD(AnonymousClass0HD r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this._creatorIndex = r2._creatorIndex;
        this._injectableValueId = r2._injectableValueId;
    }

    public AnonymousClass0HD(AnonymousClass0HD r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this._creatorIndex = r2._creatorIndex;
        this._injectableValueId = r2._injectableValueId;
    }

    public AnonymousClass0HD(String str, AbstractC02190iF r9, C04070pB r10, AbstractC04520qa r11, AbstractC04760rD r12, AnonymousClass0Ox r13, int i, Object obj, boolean z) {
        super(str, r9, r10, r11, r12, z);
        this._annotated = r13;
        this._creatorIndex = i;
        this._injectableValueId = obj;
    }
}
