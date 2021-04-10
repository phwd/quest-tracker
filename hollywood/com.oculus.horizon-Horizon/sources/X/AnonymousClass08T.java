package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/* renamed from: X.08T  reason: invalid class name */
public final class AnonymousClass08T extends AnonymousClass0HD {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0GW _annotated;
    public final int _creatorIndex;
    public final Object _injectableValueId;

    @Override // X.AnonymousClass0HD
    public final Object A07(Object obj, Object obj2) throws IOException {
        return obj;
    }

    @Override // X.AnonymousClass0HD
    public final /* bridge */ /* synthetic */ AnonymousClass0HD A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass08T(this, jsonDeserializer);
    }

    @Override // X.AnonymousClass0HD
    public final AnonymousClass0HD A03(String str) {
        return new AnonymousClass08T(this, str);
    }

    @Override // X.AnonymousClass0HD
    public final void A0A(Object obj, Object obj2) throws IOException {
        throw new IllegalStateException(AnonymousClass006.A05("Method should never be called on a ", getClass().getName()));
    }

    @Override // X.AnonymousClass0HD
    public final String toString() {
        StringBuilder sb = new StringBuilder("[creator property, name '");
        sb.append(this._propName);
        sb.append("'; inject id '");
        sb.append(this._injectableValueId);
        sb.append("']");
        return sb.toString();
    }

    @Override // X.AnonymousClass0HD
    public final int A01() {
        return this._creatorIndex;
    }

    @Override // X.AnonymousClass0HD
    public final Object A04() {
        return this._injectableValueId;
    }

    @Override // X.AnonymousClass0HD
    public final void A08(AbstractC04100gp r3, AbstractC04020gg r4, Object obj) throws IOException, AnonymousClass0jg {
        A0A(obj, A05(r3, r4));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC04030gh, X.AnonymousClass0HD
    public final AnonymousClass0g9 A3p() {
        return this._annotated;
    }

    @Override // X.AnonymousClass0HD
    public final Object A06(AbstractC04100gp r1, AbstractC04020gg r2, Object obj) throws IOException, AnonymousClass0jg {
        A05(r1, r2);
        return obj;
    }

    public AnonymousClass08T(AnonymousClass08T r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this._creatorIndex = r2._creatorIndex;
        this._injectableValueId = r2._injectableValueId;
    }

    public AnonymousClass08T(AnonymousClass08T r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this._creatorIndex = r2._creatorIndex;
        this._injectableValueId = r2._injectableValueId;
    }

    public AnonymousClass08T(String str, AbstractC04000gb r9, C05280kg r10, AnonymousClass0m9 r11, AbstractC06280mp r12, AnonymousClass0GW r13, int i, Object obj, boolean z) {
        super(str, r9, r10, r11, r12, z);
        this._annotated = r13;
        this._creatorIndex = i;
        this._injectableValueId = obj;
    }
}
