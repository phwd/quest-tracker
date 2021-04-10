package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;

public class C6 extends W8 implements Serializable {
    public static final long serialVersionUID = 5345570420394408290L;

    @Override // X.V4, X.W8
    public V4 A04(AbstractC0227Wo wo) {
        if (wo == this._property) {
            return this;
        }
        return new C6(this, wo);
    }

    private final Object A01(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        String A31;
        EnumC0470q2 q2Var;
        boolean A0F = ww.A0F();
        if (!A0F) {
            if (this._defaultImpl == null) {
                throw AbstractC0226Wn.A00(ww, EnumC0470q2.START_ARRAY, AnonymousClass06.A04("need JSON Array to contain As.WRAPPER_ARRAY type information for class ", this._baseType._class.getName()));
            }
            A31 = this._idResolver.A31();
        } else {
            EnumC0470q2 A0a = ww.A0a();
            EnumC0470q2 q2Var2 = EnumC0470q2.VALUE_STRING;
            if (A0a == q2Var2) {
                A31 = ww.A0d();
                ww.A0a();
            } else {
                if (this._defaultImpl == null) {
                    throw AbstractC0226Wn.A00(ww, q2Var2, AnonymousClass06.A05("need JSON String that contains type id (for subtype of ", this._baseType._class.getName(), ")"));
                }
                A31 = this._idResolver.A31();
            }
        }
        JsonDeserializer<Object> A0D = A0D(wn, A31);
        if (this._typeIdVisible && ww.A0Z() == EnumC0470q2.START_OBJECT) {
            Br br = new Br(null);
            br.A07();
            br.A09(this._typePropertyName);
            br.A0A(A31);
            ww = AnonymousClass8N.A00(br.A03(ww), ww);
            ww.A0a();
        }
        Object A09 = A0D.A09(ww, wn);
        if (!A0F || ww.A0a() == (q2Var = EnumC0470q2.END_ARRAY)) {
            return A09;
        }
        throw AbstractC0226Wn.A00(ww, q2Var, "expected closing END_ARRAY after type information and deserialized value");
    }

    @Override // X.V4
    public Object A07(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return A01(ww, wn);
    }

    @Override // X.V4
    public final Object A08(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return A01(ww, wn);
    }

    @Override // X.V4
    public Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return A01(ww, wn);
    }

    @Override // X.V4
    public final Object A0A(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return A01(ww, wn);
    }

    @Override // X.V4, X.W8
    public EnumC0463pg A03() {
        return EnumC0463pg.WRAPPER_ARRAY;
    }

    public C6(AbstractC0224Wl wl, V3 v3, String str, boolean z, Class<?> cls) {
        super(wl, v3, str, z, cls);
    }

    public C6(C6 c6, AbstractC0227Wo wo) {
        super(c6, wo);
    }
}
