package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;

/* renamed from: X.Qp  reason: case insensitive filesystem */
public final class C0317Qp extends AbstractC1055rV implements Serializable {
    public static final long serialVersionUID = 5345570420394408290L;

    public static final Object A00(C0317Qp qp, AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0U = qiVar.A0U();
        NX nx = NX.START_OBJECT;
        if (A0U == nx) {
            NX A0o = qiVar.A0o();
            NX nx2 = NX.FIELD_NAME;
            if (A0o == nx2) {
                String A0p = qiVar.A0p();
                JsonDeserializer A08 = qp.A08(qrVar, A0p);
                qiVar.A0o();
                if (qp._typeIdVisible && qiVar.A0U() == nx) {
                    JD jd = new JD(null);
                    jd.A0C();
                    jd.A0M(qp._typePropertyName);
                    jd.A0P(A0p);
                    qiVar = AnonymousClass2J.A00(jd.A0W(qiVar), qiVar);
                    qiVar.A0o();
                }
                Object A0C = A08.A0C(qiVar, qrVar);
                NX A0o2 = qiVar.A0o();
                NX nx3 = NX.END_OBJECT;
                if (A0o2 == nx3) {
                    return A0C;
                }
                throw AbstractC1022qr.A00(qiVar, nx3, "expected closing END_OBJECT after type information and deserialized value");
            }
            throw AbstractC1022qr.A00(qiVar, nx2, AnonymousClass08.A05("need JSON String that contains type id (for subtype of ", qp._baseType._class.getName(), ")"));
        }
        throw AbstractC1022qr.A00(qiVar, nx, AnonymousClass08.A04("need JSON Object to contain As.WRAPPER_OBJECT type information for class ", qp._baseType._class.getName()));
    }

    @Override // X.PR
    public final Object A06(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        return A00(this, qiVar, qrVar);
    }

    public C0317Qp(AbstractC1024qt qtVar, PS ps, String str, boolean z) {
        super(qtVar, ps, str, z, null);
    }

    public C0317Qp(C0317Qp qp, O5 o5) {
        super(qp, o5);
    }
}
