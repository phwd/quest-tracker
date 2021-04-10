package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/* renamed from: X.07L  reason: invalid class name */
public final class AnonymousClass07L extends AnonymousClass0GQ {
    public static final long serialVersionUID = 1;

    @Override // X.AbstractC03720fx, X.AnonymousClass0GQ, X.AnonymousClass0m9
    public final AnonymousClass0m9 A04(AbstractC04030gh r2) {
        if (r2 == this._property) {
            return this;
        }
        return new AnonymousClass07L(this, r2);
    }

    private final Object A00(AbstractC04100gp r7, AbstractC04020gg r8, AnonymousClass0Fv r9) throws IOException, AnonymousClass0jg {
        JsonDeserializer<Object> A0C = A0C(r8);
        if (A0C != null) {
            if (r9 != null) {
                r9.A05();
                r7 = r9.A03(r7);
                r7.A0b();
            }
            return A0C.A09(r7, r8);
        }
        Object A02 = AnonymousClass0m9.A02(r7, this._baseType);
        if (A02 != null) {
            return A02;
        }
        if (r7.A0a() == EnumC04820ji.START_ARRAY) {
            return super.A07(r7, r8);
        }
        throw AbstractC04020gg.A00(r7, EnumC04820ji.FIELD_NAME, AnonymousClass006.A09("missing property '", this._typePropertyName, "' that is to contain type id  (for class ", this._baseType._class.getName(), ")"));
    }

    @Override // X.AbstractC03720fx, X.AnonymousClass0GQ, X.AnonymousClass0m9
    public final EnumC04710jK A03() {
        return EnumC04710jK.PROPERTY;
    }

    @Override // X.AnonymousClass0GQ, X.AnonymousClass0m9
    public final Object A07(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        if (r3.A0a() == EnumC04820ji.START_ARRAY) {
            return A08(r3, r4);
        }
        return A09(r3, r4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004e, code lost:
        if (r3 != null) goto L_0x003e;
     */
    @Override // X.AnonymousClass0GQ, X.AnonymousClass0m9
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object A09(X.AbstractC04100gp r6, X.AbstractC04020gg r7) throws java.io.IOException, X.AnonymousClass0jg {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass07L.A09(X.0gp, X.0gg):java.lang.Object");
    }

    public AnonymousClass07L(AbstractC04000gb r1, AbstractC05940mA r2, String str, boolean z, Class<?> cls) {
        super(r1, r2, str, z, cls);
    }

    public AnonymousClass07L(AnonymousClass07L r1, AbstractC04030gh r2) {
        super(r1, r2);
    }
}
