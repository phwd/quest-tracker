package X;

import com.fasterxml.jackson.databind.JsonDeserializer;

/* renamed from: X.0c  reason: invalid class name */
public final class AnonymousClass0c extends RU {
    public static final long serialVersionUID = 1;

    public static final Object A00(AnonymousClass0c r6, AbstractC1014qi qiVar, AbstractC1022qr qrVar, JD jd) {
        JsonDeserializer A07 = r6.A07(qrVar);
        if (A07 != null) {
            if (jd != null) {
                jd.A09();
                qiVar = jd.A0W(qiVar);
                qiVar.A0o();
            }
            return A07.A0C(qiVar, qrVar);
        }
        Object A01 = PR.A01(qiVar, r6._baseType);
        if (A01 != null) {
            return A01;
        }
        if (qiVar.A0U() == NX.START_ARRAY) {
            return super.A06(qiVar, qrVar);
        }
        throw AbstractC1022qr.A00(qiVar, NX.FIELD_NAME, AnonymousClass08.A07("missing property '", r6._typePropertyName, "' that is to contain type id  (for class ", r6._baseType._class.getName(), ")"));
    }

    @Override // X.PR, X.RU
    public final Object A06(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        if (qiVar.A0U() == NX.START_ARRAY) {
            return A04(qiVar, qrVar);
        }
        return A05(qiVar, qrVar);
    }

    public AnonymousClass0c(AbstractC1024qt qtVar, PS ps, String str, boolean z, Class cls) {
        super(qtVar, ps, str, z, cls);
    }

    public AnonymousClass0c(AnonymousClass0c r1, O5 o5) {
        super(r1, o5);
    }
}
