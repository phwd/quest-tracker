package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.rT  reason: case insensitive filesystem */
public final class C0482rT {
    public static void A01(AnonymousClass2z r4, YE ye) {
        for (Map.Entry<String, AbstractC0222Wi> entry : r4.A00.entrySet()) {
            String key = entry.getKey();
            AbstractC0222Wi value = entry.getValue();
            Integer A04 = value.A04();
            if (A04 == AnonymousClass07.A04) {
                YE.A00(ye, key, null);
            } else if (A04 == AnonymousClass07.A08) {
                YE.A00(ye, key, value.A05());
            } else if (A04 == AnonymousClass07.A05) {
                YE.A00(ye, key, value.A01());
            } else if (A04 == AnonymousClass07.A02) {
                YE.A00(ye, key, Boolean.valueOf(value.A03()));
            } else if (A04 == AnonymousClass07.A06) {
                YE A02 = ((MF) ye).A01.A02();
                ye.A0C(key, A02);
                A01((AnonymousClass2z) value, A02);
            } else if (A04 == AnonymousClass07.A00) {
                YF A01 = ((MF) ye).A01.A01();
                ye.A0C(key, A01);
                A00((AnonymousClass38) value, key, A01);
            } else {
                throw new IllegalArgumentException(AnonymousClass06.A06("Unsupported JSON type for '", key, "': ", Uz.A00(A04)));
            }
        }
    }

    public static void A00(AnonymousClass38 r3, @Nullable String str, YF yf) {
        Iterator<AbstractC0222Wi> A02 = r3.A02();
        while (A02.hasNext()) {
            AbstractC0222Wi next = A02.next();
            Integer A04 = next.A04();
            if (A04 == AnonymousClass07.A04) {
                YF.A01(yf, null);
            } else if (A04 == AnonymousClass07.A08) {
                YF.A01(yf, next.A05());
            } else if (A04 == AnonymousClass07.A05) {
                YF.A01(yf, next.A01());
            } else if (A04 == AnonymousClass07.A02) {
                YF.A01(yf, Boolean.valueOf(next.A03()));
            } else if (A04 == AnonymousClass07.A06) {
                YE A022 = yf.A01.A02();
                YF.A00(yf, A022);
                A01((AnonymousClass2z) next, A022);
            } else if (A04 == AnonymousClass07.A00) {
                YF A01 = yf.A01.A01();
                YF.A00(yf, A01);
                A00((AnonymousClass38) next, str, A01);
            } else {
                throw new IllegalArgumentException(AnonymousClass06.A06("Unsupported JSON type for '", str, "': ", Uz.A00(A04)));
            }
        }
    }
}
