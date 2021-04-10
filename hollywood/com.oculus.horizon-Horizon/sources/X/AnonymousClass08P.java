package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Field;

/* renamed from: X.08P  reason: invalid class name */
public final class AnonymousClass08P extends AnonymousClass0HD {
    public static final long serialVersionUID = 1;
    public final transient Field A00;
    public final AnonymousClass0GX _annotated;

    @Override // X.AnonymousClass0HD
    public final AnonymousClass0HD A02(JsonDeserializer jsonDeserializer) {
        return new AnonymousClass08P(this, jsonDeserializer);
    }

    @Override // X.AnonymousClass0HD
    public final AnonymousClass0HD A03(String str) {
        return new AnonymousClass08P(this, str);
    }

    @Override // X.AnonymousClass0HD
    public final Object A07(Object obj, Object obj2) throws IOException {
        try {
            this.A00.set(obj, obj2);
            return obj;
        } catch (Exception e) {
            A09(e, obj2);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // X.AnonymousClass0HD
    public final void A0A(Object obj, Object obj2) throws IOException {
        try {
            this.A00.set(obj, obj2);
        } catch (Exception e) {
            A09(e, obj2);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public Object readResolve() {
        return new AnonymousClass08P(this, this._annotated.A00);
    }

    @Override // X.AnonymousClass0HD
    public final Object A06(AbstractC04100gp r2, AbstractC04020gg r3, Object obj) throws IOException, AnonymousClass0jg {
        A07(obj, A05(r2, r3));
        return obj;
    }

    @Override // X.AnonymousClass0HD
    public final void A08(AbstractC04100gp r2, AbstractC04020gg r3, Object obj) throws IOException, AnonymousClass0jg {
        A0A(obj, A05(r2, r3));
    }

    @Override // X.AbstractC04030gh, X.AnonymousClass0HD
    public final AnonymousClass0g9 A3p() {
        return this._annotated;
    }

    public AnonymousClass08P(AnonymousClass08P r2, JsonDeserializer<?> jsonDeserializer) {
        super(r2, jsonDeserializer);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass08P(AnonymousClass08P r2, String str) {
        super(r2, str);
        this._annotated = r2._annotated;
        this.A00 = r2.A00;
    }

    public AnonymousClass08P(AnonymousClass08P r6, Field field) {
        super(r6);
        this._annotated = r6._annotated;
        if (field != null) {
            this.A00 = field;
            return;
        }
        throw new IllegalArgumentException(AnonymousClass006.A09("No Field passed for property '", r6._propName, "' (class ", r6.A3p().A0O().getName(), ")"));
    }

    public AnonymousClass08P(AnonymousClass0g5 r2, AbstractC04000gb r3, AnonymousClass0m9 r4, AbstractC06280mp r5, AnonymousClass0GX r6) {
        super(r2, r3, r4, r5);
        this._annotated = r6;
        this.A00 = r6.A00;
    }
}
