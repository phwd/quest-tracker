package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.8B  reason: invalid class name */
public final class AnonymousClass8B extends AbstractC0073Cr {
    public static final long serialVersionUID = 1;
    public final transient Method A00;
    public final AnonymousClass7P _annotated;

    @Override // X.AbstractC0073Cr
    public final AbstractC0073Cr A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass8B(this, jsonDeserializer);
    }

    @Override // X.AbstractC0073Cr
    public final AbstractC0073Cr A03(String str) {
        return new AnonymousClass8B(this, str);
    }

    @Override // X.AbstractC0073Cr
    public final Object A07(Object obj, Object obj2) throws IOException {
        try {
            Object invoke = this.A00.invoke(obj, obj2);
            if (invoke != null) {
                return invoke;
            }
            return obj;
        } catch (Exception e) {
            A09(e, obj2);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // X.AbstractC0073Cr
    public final void A0A(Object obj, Object obj2) throws IOException {
        try {
            this.A00.invoke(obj, obj2);
        } catch (Exception e) {
            A09(e, obj2);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public Object readResolve() {
        return new AnonymousClass8B(this, this._annotated.A00);
    }

    @Override // X.AbstractC0073Cr
    public final Object A06(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        return A07(obj, A05(ww, wn));
    }

    @Override // X.AbstractC0073Cr
    public final void A08(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        A0A(obj, A05(ww, wn));
    }

    @Override // X.AbstractC0227Wo, X.AbstractC0073Cr
    public final WJ A2d() {
        return this._annotated;
    }

    public AnonymousClass8B(AnonymousClass8B r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass8B(AnonymousClass8B r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass8B(AnonymousClass8B r2, Method method) {
        super(r2);
        this._annotated = r2._annotated;
        this.A00 = method;
    }

    public AnonymousClass8B(WF wf, AbstractC0224Wl wl, V4 v4, N6 n6, AnonymousClass7P r6) {
        super(wf, wl, v4, n6);
        this._annotated = r6;
        this.A00 = r6.A00;
    }
}
