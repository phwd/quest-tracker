package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Field;

/* renamed from: X.8E  reason: invalid class name */
public final class AnonymousClass8E extends AbstractC0073Cr {
    public static final long serialVersionUID = 1;
    public final transient Field A00;
    public final CD _annotated;

    @Override // X.AbstractC0073Cr
    public final AbstractC0073Cr A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass8E(this, jsonDeserializer);
    }

    @Override // X.AbstractC0073Cr
    public final AbstractC0073Cr A03(String str) {
        return new AnonymousClass8E(this, str);
    }

    @Override // X.AbstractC0073Cr
    public final Object A07(Object obj, Object obj2) throws IOException {
        try {
            this.A00.set(obj, obj2);
            return obj;
        } catch (Exception e) {
            A09(e, obj2);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // X.AbstractC0073Cr
    public final void A0A(Object obj, Object obj2) throws IOException {
        try {
            this.A00.set(obj, obj2);
        } catch (Exception e) {
            A09(e, obj2);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public Object readResolve() {
        return new AnonymousClass8E(this, this._annotated.A00);
    }

    @Override // X.AbstractC0073Cr
    public final Object A06(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        A07(obj, A05(ww, wn));
        return obj;
    }

    @Override // X.AbstractC0073Cr
    public final void A08(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        A0A(obj, A05(ww, wn));
    }

    @Override // X.AbstractC0227Wo, X.AbstractC0073Cr
    public final WJ A2d() {
        return this._annotated;
    }

    public AnonymousClass8E(AnonymousClass8E r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass8E(AnonymousClass8E r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass8E(AnonymousClass8E r6, Field field) {
        super(r6);
        this._annotated = r6._annotated;
        if (field != null) {
            this.A00 = field;
            return;
        }
        throw new IllegalArgumentException(AnonymousClass06.A07("No Field passed for property '", r6._propName, "' (class ", r6.A2d().A0O().getName(), ")"));
    }

    public AnonymousClass8E(WF wf, AbstractC0224Wl wl, V4 v4, N6 n6, CD cd) {
        super(wf, wl, v4, n6);
        this._annotated = cd;
        this.A00 = cd.A00;
    }
}
