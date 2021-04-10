package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.1fD  reason: invalid class name and case insensitive filesystem */
public class C08741fD implements AnonymousClass1Ry<Object> {
    public final /* synthetic */ C08661f4 A00;
    public final /* synthetic */ C07091bb A01;

    public C08741fD(C08661f4 r1, C07091bb r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // X.AnonymousClass1Ry
    public final void A6x(@Nullable Object obj) {
        C08661f4 r3 = this.A00;
        C07091bb<?> r2 = this.A01;
        C07091bb<?> r0 = r3.A06;
        if (r0 != null && r0 == r2) {
            AbstractC08841fc r1 = r3.A05.A06;
            if (obj == null || !r1.A00(r2.A01.A3i())) {
                AnonymousClass1fL r4 = r3.A04;
                AbstractC06491aL r5 = r2.A00;
                AbstractC07051bX<Data> r7 = r2.A01;
                r4.A6w(r5, obj, r7, r7.A3i(), r3.A02);
                return;
            }
            r3.A03 = obj;
            r3.A04.A9L();
        }
    }

    @Override // X.AnonymousClass1Ry
    public final void A7F(@NonNull Exception exc) {
        C08661f4 r2 = this.A00;
        C07091bb<?> r1 = this.A01;
        C07091bb<?> r0 = r2.A06;
        if (r0 != null && r0 == r1) {
            AnonymousClass1fL r3 = r2.A04;
            C06501aM r22 = r2.A02;
            AbstractC07051bX<Data> r12 = r1.A01;
            r3.A6v(r22, exc, r12, r12.A3i());
        }
    }
}
