package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0K3  reason: invalid class name */
public final class AnonymousClass0K3 extends AnonymousClass0Zf implements Serializable {
    public static final long serialVersionUID = 5345570420394408290L;

    @Override // X.AnonymousClass0o3, X.AnonymousClass0Zf
    public final EnumC05770lI A03() {
        return EnumC05770lI.WRAPPER_OBJECT;
    }

    @Override // X.AnonymousClass0o3, X.AnonymousClass0Zf
    public final AnonymousClass0o3 A04(AbstractC02580aL r2) {
        if (r2 == this._property) {
            return this;
        }
        return new AnonymousClass0K3(this, r2);
    }

    private final Object A00(AnonymousClass0aT r6, AbstractC02570aK r7) throws IOException, C05910ld {
        EnumC05930lf A0G = r6.A0G();
        EnumC05930lf r4 = EnumC05930lf.START_OBJECT;
        if (A0G == r4) {
            EnumC05930lf A0a = r6.A0a();
            EnumC05930lf r3 = EnumC05930lf.FIELD_NAME;
            if (A0a == r3) {
                String A0P = r6.A0P();
                JsonDeserializer<Object> A0D = A0D(r7, A0P);
                r6.A0a();
                AnonymousClass0Fv r62 = r6;
                if (this._typeIdVisible) {
                    EnumC05930lf A0G2 = r6.A0G();
                    r62 = r6;
                    if (A0G2 == r4) {
                        C01570Jk r1 = new C01570Jk(null);
                        r1.A0F();
                        r1.A0P(this._typePropertyName);
                        r1.A0S(A0P);
                        AnonymousClass0Fv A00 = AnonymousClass0Fv.A00(r1.A0Z(r6), r6);
                        A00.A0a();
                        r62 = A00;
                    }
                }
                Object A09 = A0D.A09(r62, r7);
                EnumC05930lf A0a2 = r62.A0a();
                EnumC05930lf r12 = EnumC05930lf.END_OBJECT;
                if (A0a2 == r12) {
                    return A09;
                }
                throw AbstractC02570aK.A00(r62, r12, "expected closing END_OBJECT after type information and deserialized value");
            }
            throw AbstractC02570aK.A00(r6, r3, AnonymousClass006.A07("need JSON String that contains type id (for subtype of ", this._baseType._class.getName(), ")"));
        }
        throw AbstractC02570aK.A00(r6, r4, AnonymousClass006.A05("need JSON Object to contain As.WRAPPER_OBJECT type information for class ", this._baseType._class.getName()));
    }

    @Override // X.AnonymousClass0o3
    public final Object A07(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        return A00(r2, r3);
    }

    @Override // X.AnonymousClass0o3
    public final Object A08(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        return A00(r2, r3);
    }

    @Override // X.AnonymousClass0o3
    public final Object A09(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        return A00(r2, r3);
    }

    @Override // X.AnonymousClass0o3
    public final Object A0A(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        return A00(r2, r3);
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0aI;LX/0o4;Ljava/lang/String;ZLjava/lang/Class<*>;)V */
    public AnonymousClass0K3(AnonymousClass0aI r7, AnonymousClass0o4 r8, String str, boolean z) {
        super(r7, r8, str, z, null);
    }

    public AnonymousClass0K3(AnonymousClass0K3 r1, AbstractC02580aL r2) {
        super(r1, r2);
    }
}
