package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/* renamed from: X.0E6  reason: invalid class name */
public final class AnonymousClass0E6 extends AnonymousClass0K6 {
    public static final long serialVersionUID = 1;

    @Override // X.AnonymousClass0o3, X.AnonymousClass0K6, X.AnonymousClass0Zf
    public final EnumC05770lI A03() {
        return EnumC05770lI.PROPERTY;
    }

    @Override // X.AnonymousClass0o3, X.AnonymousClass0K6, X.AnonymousClass0Zf
    public final AnonymousClass0o3 A04(AbstractC02580aL r2) {
        if (r2 == this._property) {
            return this;
        }
        return new AnonymousClass0E6(this, r2);
    }

    private final Object A00(AnonymousClass0aT r7, AbstractC02570aK r8, C01570Jk r9) throws IOException, C05910ld {
        JsonDeserializer<Object> A0C = A0C(r8);
        if (A0C != null) {
            if (r9 != null) {
                r9.A0C();
                r7 = r9.A0Z(r7);
                r7.A0a();
            }
            return A0C.A09(r7, r8);
        }
        Object A02 = AnonymousClass0o3.A02(r7, this._baseType);
        if (A02 != null) {
            return A02;
        }
        if (r7.A0G() == EnumC05930lf.START_ARRAY) {
            return super.A07(r7, r8);
        }
        throw AbstractC02570aK.A00(r7, EnumC05930lf.FIELD_NAME, AnonymousClass006.A09("missing property '", this._typePropertyName, "' that is to contain type id  (for class ", this._baseType._class.getName(), ")"));
    }

    @Override // X.AnonymousClass0o3, X.AnonymousClass0K6
    public final Object A07(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
        if (r3.A0G() == EnumC05930lf.START_ARRAY) {
            return A08(r3, r4);
        }
        return A09(r3, r4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004e, code lost:
        if (r3 != null) goto L_0x003e;
     */
    @Override // X.AnonymousClass0o3, X.AnonymousClass0K6
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object A09(X.AnonymousClass0aT r6, X.AbstractC02570aK r7) throws java.io.IOException, X.C05910ld {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0E6.A09(X.0aT, X.0aK):java.lang.Object");
    }

    public AnonymousClass0E6(AnonymousClass0aI r1, AnonymousClass0o4 r2, String str, boolean z, Class<?> cls) {
        super(r1, r2, str, z, cls);
    }

    public AnonymousClass0E6(AnonymousClass0E6 r1, AbstractC02580aL r2) {
        super(r1, r2);
    }
}
