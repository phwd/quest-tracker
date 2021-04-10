package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;

public final class C5 extends W8 implements Serializable {
    public static final long serialVersionUID = 5345570420394408290L;

    @Override // X.V4, X.W8
    public final V4 A04(AbstractC0227Wo wo) {
        if (wo == this._property) {
            return this;
        }
        return new C5(this, wo);
    }

    private final Object A00(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        EnumC0470q2 q2Var = EnumC0470q2.START_OBJECT;
        if (A0Z == q2Var) {
            EnumC0470q2 A0a = ww.A0a();
            EnumC0470q2 q2Var2 = EnumC0470q2.FIELD_NAME;
            if (A0a == q2Var2) {
                String A0d = ww.A0d();
                JsonDeserializer<Object> A0D = A0D(wn, A0d);
                ww.A0a();
                if (this._typeIdVisible && ww.A0Z() == q2Var) {
                    Br br = new Br(null);
                    br.A07();
                    br.A09(this._typePropertyName);
                    br.A0A(A0d);
                    ww = AnonymousClass8N.A00(br.A03(ww), ww);
                    ww.A0a();
                }
                Object A09 = A0D.A09(ww, wn);
                EnumC0470q2 A0a2 = ww.A0a();
                EnumC0470q2 q2Var3 = EnumC0470q2.END_OBJECT;
                if (A0a2 == q2Var3) {
                    return A09;
                }
                throw AbstractC0226Wn.A00(ww, q2Var3, "expected closing END_OBJECT after type information and deserialized value");
            }
            throw AbstractC0226Wn.A00(ww, q2Var2, AnonymousClass06.A05("need JSON String that contains type id (for subtype of ", this._baseType._class.getName(), ")"));
        }
        throw AbstractC0226Wn.A00(ww, q2Var, AnonymousClass06.A04("need JSON Object to contain As.WRAPPER_OBJECT type information for class ", this._baseType._class.getName()));
    }

    @Override // X.V4
    public final Object A07(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return A00(ww, wn);
    }

    @Override // X.V4
    public final Object A08(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return A00(ww, wn);
    }

    @Override // X.V4
    public final Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return A00(ww, wn);
    }

    @Override // X.V4
    public final Object A0A(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return A00(ww, wn);
    }

    @Override // X.V4, X.W8
    public final EnumC0463pg A03() {
        return EnumC0463pg.WRAPPER_OBJECT;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/Wl;LX/V3;Ljava/lang/String;ZLjava/lang/Class<*>;)V */
    public C5(AbstractC0224Wl wl, V3 v3, String str, boolean z) {
        super(wl, v3, str, z, null);
    }

    public C5(C5 c5, AbstractC0227Wo wo) {
        super(c5, wo);
    }
}
