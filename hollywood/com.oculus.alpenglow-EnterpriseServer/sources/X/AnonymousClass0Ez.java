package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Field;

/* renamed from: X.0Ez  reason: invalid class name */
public final class AnonymousClass0Ez extends AbstractC01680Ku {
    public static final long serialVersionUID = 1;
    public final transient Field A00;
    public final AnonymousClass0KC _annotated;

    @Override // X.AbstractC01680Ku
    public final AbstractC01680Ku A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass0Ez(this, jsonDeserializer);
    }

    @Override // X.AbstractC01680Ku
    public final AbstractC01680Ku A03(String str) {
        return new AnonymousClass0Ez(this, str);
    }

    @Override // X.AbstractC01680Ku
    public final Object A07(Object obj, Object obj2) throws IOException {
        try {
            this.A00.set(obj, obj2);
            return obj;
        } catch (Exception e) {
            A09(e, obj2);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // X.AbstractC01680Ku
    public final void A0A(Object obj, Object obj2) throws IOException {
        try {
            this.A00.set(obj, obj2);
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
        return new AnonymousClass0Ez(this, this._annotated.A00);
    }

    @Override // X.AbstractC01680Ku
    public final Object A06(AnonymousClass0aT r2, AbstractC02570aK r3, Object obj) throws IOException, C05910ld {
        A07(obj, A05(r2, r3));
        return obj;
    }

    @Override // X.AbstractC01680Ku
    public final void A08(AnonymousClass0aT r2, AbstractC02570aK r3, Object obj) throws IOException, C05910ld {
        A0A(obj, A05(r2, r3));
    }

    public AnonymousClass0Ez(AnonymousClass0Ez r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass0Ez(AnonymousClass0Ez r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass0Ez(AnonymousClass0Ez r6, Field field) {
        super(r6);
        this._annotated = r6._annotated;
        if (field != null) {
            this.A00 = field;
            return;
        }
        throw new IllegalArgumentException(AnonymousClass006.A09("No Field passed for property '", r6._propName, "' (class ", r6.A41().A0P().getName(), ")"));
    }

    public AnonymousClass0Ez(AbstractC02410Zn r2, AnonymousClass0aI r3, AnonymousClass0o3 r4, AbstractC07080oh r5, AnonymousClass0KC r6) {
        super(r2, r3, r4, r5);
        this._annotated = r6;
        this.A00 = r6.A00;
    }
}
