package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.0Ew  reason: invalid class name */
public final class AnonymousClass0Ew extends AbstractC01680Ku {
    public static final long serialVersionUID = 1;
    public final transient Method A00;
    public final AnonymousClass0EC _annotated;

    @Override // X.AbstractC01680Ku
    public final AbstractC01680Ku A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass0Ew(this, jsonDeserializer);
    }

    @Override // X.AbstractC01680Ku
    public final AbstractC01680Ku A03(String str) {
        return new AnonymousClass0Ew(this, str);
    }

    @Override // X.AbstractC01680Ku
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

    @Override // X.AbstractC01680Ku
    public final void A0A(Object obj, Object obj2) throws IOException {
        try {
            this.A00.invoke(obj, obj2);
        } catch (Exception e) {
            A09(e, obj2);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // X.AbstractC02580aL, X.AbstractC01680Ku
    public final AbstractC02450Zr A41() {
        return this._annotated;
    }

    public Object readResolve() {
        return new AnonymousClass0Ew(this, this._annotated.A00);
    }

    @Override // X.AbstractC01680Ku
    public final Object A06(AnonymousClass0aT r2, AbstractC02570aK r3, Object obj) throws IOException, C05910ld {
        return A07(obj, A05(r2, r3));
    }

    @Override // X.AbstractC01680Ku
    public final void A08(AnonymousClass0aT r2, AbstractC02570aK r3, Object obj) throws IOException, C05910ld {
        A0A(obj, A05(r2, r3));
    }

    public AnonymousClass0Ew(AnonymousClass0Ew r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass0Ew(AnonymousClass0Ew r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass0Ew(AnonymousClass0Ew r2, Method method) {
        super(r2);
        this._annotated = r2._annotated;
        this.A00 = method;
    }

    public AnonymousClass0Ew(AbstractC02410Zn r2, AnonymousClass0aI r3, AnonymousClass0o3 r4, AbstractC07080oh r5, AnonymousClass0EC r6) {
        super(r2, r3, r4, r5);
        this._annotated = r6;
        this.A00 = r6.A00;
    }
}
