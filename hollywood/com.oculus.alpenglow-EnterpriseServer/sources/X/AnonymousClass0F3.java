package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/* renamed from: X.0F3  reason: invalid class name */
public final class AnonymousClass0F3 extends AbstractC01680Ku {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0KB _annotated;
    public final int _creatorIndex;
    public final Object _injectableValueId;

    @Override // X.AbstractC01680Ku
    public final Object A07(Object obj, Object obj2) throws IOException {
        return obj;
    }

    @Override // X.AbstractC01680Ku
    public final int A01() {
        return this._creatorIndex;
    }

    @Override // X.AbstractC01680Ku
    public final /* bridge */ /* synthetic */ AbstractC01680Ku A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass0F3(this, jsonDeserializer);
    }

    @Override // X.AbstractC01680Ku
    public final AbstractC01680Ku A03(String str) {
        return new AnonymousClass0F3(this, str);
    }

    @Override // X.AbstractC01680Ku
    public final Object A04() {
        return this._injectableValueId;
    }

    @Override // X.AbstractC01680Ku
    public final void A0A(Object obj, Object obj2) throws IOException {
        throw new IllegalStateException(AnonymousClass006.A05("Method should never be called on a ", getClass().getName()));
    }

    @Override // X.AbstractC02580aL, X.AbstractC01680Ku
    public final AbstractC02450Zr A41() {
        return this._annotated;
    }

    @Override // X.AbstractC01680Ku
    public final String toString() {
        return "[creator property, name '" + this._propName + "'; inject id '" + this._injectableValueId + "']";
    }

    @Override // X.AbstractC01680Ku
    public final void A08(AnonymousClass0aT r3, AbstractC02570aK r4, Object obj) throws IOException, C05910ld {
        A0A(obj, A05(r3, r4));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC01680Ku
    public final Object A06(AnonymousClass0aT r1, AbstractC02570aK r2, Object obj) throws IOException, C05910ld {
        A05(r1, r2);
        return obj;
    }

    public AnonymousClass0F3(AnonymousClass0F3 r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this._creatorIndex = r2._creatorIndex;
        this._injectableValueId = r2._injectableValueId;
    }

    public AnonymousClass0F3(AnonymousClass0F3 r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this._creatorIndex = r2._creatorIndex;
        this._injectableValueId = r2._injectableValueId;
    }

    public AnonymousClass0F3(String str, AnonymousClass0aI r9, C06350mc r10, AnonymousClass0o3 r11, AbstractC07080oh r12, AnonymousClass0KB r13, int i, Object obj, boolean z) {
        super(str, r9, r10, r11, r12, z);
        this._annotated = r13;
        this._creatorIndex = i;
        this._injectableValueId = obj;
    }
}
