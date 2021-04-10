package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;

public class RU extends AbstractC1055rV implements Serializable {
    public static final long serialVersionUID = 5345570420394408290L;

    public static final Object A02(RU ru, AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        String A3E;
        NX nx;
        boolean A0i = qiVar.A0i();
        if (!A0i) {
            if (ru._defaultImpl == null) {
                throw AbstractC1022qr.A00(qiVar, NX.START_ARRAY, AnonymousClass08.A04("need JSON Array to contain As.WRAPPER_ARRAY type information for class ", ru._baseType._class.getName()));
            }
            A3E = ru._idResolver.A3E();
        } else {
            NX A0o = qiVar.A0o();
            NX nx2 = NX.VALUE_STRING;
            if (A0o == nx2) {
                A3E = qiVar.A0p();
                qiVar.A0o();
            } else {
                if (ru._defaultImpl == null) {
                    throw AbstractC1022qr.A00(qiVar, nx2, AnonymousClass08.A05("need JSON String that contains type id (for subtype of ", ru._baseType._class.getName(), ")"));
                }
                A3E = ru._idResolver.A3E();
            }
        }
        JsonDeserializer A08 = ru.A08(qrVar, A3E);
        if (ru._typeIdVisible && qiVar.A0U() == NX.START_OBJECT) {
            JD jd = new JD(null);
            jd.A0C();
            jd.A0M(ru._typePropertyName);
            jd.A0P(A3E);
            qiVar = AnonymousClass2J.A00(jd.A0W(qiVar), qiVar);
            qiVar.A0o();
        }
        Object A0C = A08.A0C(qiVar, qrVar);
        if (!A0i || qiVar.A0o() == (nx = NX.END_ARRAY)) {
            return A0C;
        }
        throw AbstractC1022qr.A00(qiVar, nx, "expected closing END_ARRAY after type information and deserialized value");
    }

    @Override // X.PR
    public Object A06(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        return A02(this, qiVar, qrVar);
    }

    public RU(AbstractC1024qt qtVar, PS ps, String str, boolean z, Class cls) {
        super(qtVar, ps, str, z, cls);
    }

    public RU(RU ru, O5 o5) {
        super(ru, o5);
    }
}
