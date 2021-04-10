package X;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Collections;

/* renamed from: X.0Ve  reason: invalid class name and case insensitive filesystem */
public class C01220Ve extends C01970hj {
    @Override // X.C01970hj
    /* renamed from: A07 */
    public final C01980hk A03(AnonymousClass0HU r4, AbstractC02190iF r5, AnonymousClass0qF r6) {
        C01980hk A00 = C01970hj.A00(r5);
        if (A00 != null) {
            return A00;
        }
        JsonDeserialize jsonDeserialize = (JsonDeserialize) r5._class.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || jsonDeserialize.using() == null) {
            return super.A03(r4, r5, r6);
        }
        boolean A05 = r4.A05(EnumC02160i9.USE_ANNOTATIONS);
        AbstractC02230iJ A01 = r4.A01();
        Class<?> cls = r5._class;
        if (!A05) {
            A01 = null;
        }
        return new C01980hk(r4, r5, C02000hn.A01(cls, A01, r6), Collections.emptyList());
    }

    @Override // X.C01970hj
    /* renamed from: A08 */
    public final C01980hk A05(AnonymousClass0HM r4, AbstractC02190iF r5, AnonymousClass0qF r6) {
        C01980hk A00 = C01970hj.A00(r5);
        if (A00 != null) {
            return A00;
        }
        JsonSerialize jsonSerialize = (JsonSerialize) r5._class.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || jsonSerialize.using() == null) {
            return super.A05(r4, r5, r6);
        }
        boolean A05 = r4.A05(EnumC02160i9.USE_ANNOTATIONS);
        AbstractC02230iJ A01 = r4.A01();
        Class<?> cls = r5._class;
        if (!A05) {
            A01 = null;
        }
        return new C01980hk(r4, r5, C02000hn.A01(cls, A01, r6), Collections.emptyList());
    }
}
