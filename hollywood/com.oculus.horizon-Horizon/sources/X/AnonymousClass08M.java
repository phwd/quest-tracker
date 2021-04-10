package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.08M  reason: invalid class name */
public final class AnonymousClass08M extends AnonymousClass0HD {
    public static final long serialVersionUID = 1;
    public final transient Method A00;
    public final AnonymousClass07O _annotated;

    @Override // X.AnonymousClass0HD
    public final AnonymousClass0HD A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass08M(this, jsonDeserializer);
    }

    @Override // X.AnonymousClass0HD
    public final AnonymousClass0HD A03(String str) {
        return new AnonymousClass08M(this, str);
    }

    @Override // X.AnonymousClass0HD
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

    @Override // X.AnonymousClass0HD
    public final void A0A(Object obj, Object obj2) throws IOException {
        try {
            this.A00.invoke(obj, obj2);
        } catch (Exception e) {
            A09(e, obj2);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public Object readResolve() {
        return new AnonymousClass08M(this, this._annotated.A00);
    }

    @Override // X.AnonymousClass0HD
    public final Object A06(AbstractC04100gp r2, AbstractC04020gg r3, Object obj) throws IOException, AnonymousClass0jg {
        return A07(obj, A05(r2, r3));
    }

    @Override // X.AnonymousClass0HD
    public final void A08(AbstractC04100gp r2, AbstractC04020gg r3, Object obj) throws IOException, AnonymousClass0jg {
        A0A(obj, A05(r2, r3));
    }

    @Override // X.AbstractC04030gh, X.AnonymousClass0HD
    public final AnonymousClass0g9 A3p() {
        return this._annotated;
    }

    public AnonymousClass08M(AnonymousClass08M r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass08M(AnonymousClass08M r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass08M(AnonymousClass08M r2, Method method) {
        super(r2);
        this._annotated = r2._annotated;
        this.A00 = method;
    }

    public AnonymousClass08M(AnonymousClass0g5 r2, AbstractC04000gb r3, AnonymousClass0m9 r4, AbstractC06280mp r5, AnonymousClass07O r6) {
        super(r2, r3, r4, r5);
        this._annotated = r6;
        this.A00 = r6.A00;
    }
}
