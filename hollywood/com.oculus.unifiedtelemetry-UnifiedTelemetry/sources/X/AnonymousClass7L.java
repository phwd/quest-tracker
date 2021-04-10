package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

/* renamed from: X.7L  reason: invalid class name */
public final class AnonymousClass7L extends C6 {
    public static final long serialVersionUID = 1;

    @Override // X.C6, X.V4, X.W8
    public final V4 A04(AbstractC0227Wo wo) {
        if (wo == this._property) {
            return this;
        }
        return new AnonymousClass7L(this, wo);
    }

    private final Object A00(AbstractC0232Ww ww, AbstractC0226Wn wn, Br br) throws IOException, q0 {
        JsonDeserializer<Object> A0C = A0C(wn);
        if (A0C != null) {
            if (br != null) {
                br.A05();
                ww = br.A03(ww);
                ww.A0a();
            }
            return A0C.A09(ww, wn);
        }
        Object A02 = V4.A02(ww, this._baseType);
        if (A02 != null) {
            return A02;
        }
        if (ww.A0Z() == EnumC0470q2.START_ARRAY) {
            return super.A07(ww, wn);
        }
        throw AbstractC0226Wn.A00(ww, EnumC0470q2.FIELD_NAME, AnonymousClass06.A07("missing property '", this._typePropertyName, "' that is to contain type id  (for class ", this._baseType._class.getName(), ")"));
    }

    @Override // X.C6, X.V4
    public final Object A07(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        if (ww.A0Z() == EnumC0470q2.START_ARRAY) {
            return A08(ww, wn);
        }
        return A09(ww, wn);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004e, code lost:
        if (r3 != null) goto L_0x003e;
     */
    @Override // X.C6, X.V4
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object A09(X.AbstractC0232Ww r6, X.AbstractC0226Wn r7) throws java.io.IOException, X.q0 {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass7L.A09(X.Ww, X.Wn):java.lang.Object");
    }

    @Override // X.C6, X.V4, X.W8
    public final EnumC0463pg A03() {
        return EnumC0463pg.PROPERTY;
    }

    public AnonymousClass7L(AbstractC0224Wl wl, V3 v3, String str, boolean z, Class<?> cls) {
        super(wl, v3, str, z, cls);
    }

    public AnonymousClass7L(AnonymousClass7L r1, AbstractC0227Wo wo) {
        super(r1, wo);
    }
}
