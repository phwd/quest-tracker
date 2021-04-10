package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: X.0K6  reason: invalid class name */
public class AnonymousClass0K6 extends AnonymousClass0Zf implements Serializable {
    public static final long serialVersionUID = 5345570420394408290L;

    @Override // X.AnonymousClass0o3, X.AnonymousClass0Zf
    public EnumC05770lI A03() {
        return EnumC05770lI.WRAPPER_ARRAY;
    }

    @Override // X.AnonymousClass0o3, X.AnonymousClass0Zf
    public AnonymousClass0o3 A04(AbstractC02580aL r2) {
        if (r2 == this._property) {
            return this;
        }
        return new AnonymousClass0K6(this, r2);
    }

    private final Object A01(AnonymousClass0aT r6, AbstractC02570aK r7) throws IOException, C05910ld {
        String A55;
        EnumC05930lf r1;
        boolean A0V = r6.A0V();
        if (!A0V) {
            if (this._defaultImpl == null) {
                throw AbstractC02570aK.A00(r6, EnumC05930lf.START_ARRAY, AnonymousClass006.A05("need JSON Array to contain As.WRAPPER_ARRAY type information for class ", this._baseType._class.getName()));
            }
            A55 = this._idResolver.A55();
        } else {
            EnumC05930lf A0a = r6.A0a();
            EnumC05930lf r3 = EnumC05930lf.VALUE_STRING;
            if (A0a == r3) {
                A55 = r6.A0P();
                r6.A0a();
            } else {
                if (this._defaultImpl == null) {
                    throw AbstractC02570aK.A00(r6, r3, AnonymousClass006.A07("need JSON String that contains type id (for subtype of ", this._baseType._class.getName(), ")"));
                }
                A55 = this._idResolver.A55();
            }
        }
        JsonDeserializer<Object> A0D = A0D(r7, A55);
        AnonymousClass0Fv r62 = r6;
        if (this._typeIdVisible) {
            EnumC05930lf A0G = r6.A0G();
            r62 = r6;
            if (A0G == EnumC05930lf.START_OBJECT) {
                C01570Jk r12 = new C01570Jk(null);
                r12.A0F();
                r12.A0P(this._typePropertyName);
                r12.A0S(A55);
                AnonymousClass0Fv A00 = AnonymousClass0Fv.A00(r12.A0Z(r6), r6);
                A00.A0a();
                r62 = A00;
            }
        }
        Object A09 = A0D.A09(r62, r7);
        if (!A0V || r62.A0a() == (r1 = EnumC05930lf.END_ARRAY)) {
            return A09;
        }
        throw AbstractC02570aK.A00(r62, r1, "expected closing END_ARRAY after type information and deserialized value");
    }

    @Override // X.AnonymousClass0o3
    public Object A07(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        return A01(r2, r3);
    }

    @Override // X.AnonymousClass0o3
    public final Object A08(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        return A01(r2, r3);
    }

    @Override // X.AnonymousClass0o3
    public Object A09(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        return A01(r2, r3);
    }

    @Override // X.AnonymousClass0o3
    public final Object A0A(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        return A01(r2, r3);
    }

    public AnonymousClass0K6(AnonymousClass0aI r1, AnonymousClass0o4 r2, String str, boolean z, Class<?> cls) {
        super(r1, r2, str, z, cls);
    }

    public AnonymousClass0K6(AnonymousClass0K6 r1, AbstractC02580aL r2) {
        super(r1, r2);
    }
}
