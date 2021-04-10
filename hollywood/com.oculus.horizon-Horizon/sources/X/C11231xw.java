package X;

import java.util.HashMap;
import java.util.List;

/* renamed from: X.1xw  reason: invalid class name and case insensitive filesystem */
public class C11231xw implements AbstractC11131xk {
    public final /* synthetic */ AbstractC11131xk A00;
    public final /* synthetic */ AbstractC11131xk A01;
    public final /* synthetic */ C11211xt A02;
    public final /* synthetic */ List A03;

    public C11231xw(C11211xt r1, List list, AbstractC11131xk r3, AbstractC11131xk r4) {
        this.A02 = r1;
        this.A03 = list;
        this.A01 = r3;
        this.A00 = r4;
    }

    @Override // X.AbstractC11131xk
    public final void A62(Throwable th) {
        this.A02.A04(new AnonymousClass1yG(this, th));
    }

    @Override // X.AbstractC11131xk
    public final void onSuccess() {
        C11211xt r4 = this.A02;
        List<AnonymousClass1yR> list = this.A03;
        AbstractC11131xk r5 = this.A01;
        HashMap<AnonymousClass1yA, AnonymousClass1yQ> hashMap = new HashMap<>();
        for (AnonymousClass1yR r0 : list) {
            AbstractC11261xz r6 = r4.A04.get(r0.A4Z());
            if (r6 != null && r6.A4v()) {
                AnonymousClass1yQ A42 = r6.A42();
                if (A42 != null) {
                    hashMap.put(r6.A4Z(), A42);
                } else {
                    r5.A62(new C11081xd(21002, String.format(null, "One of the configured tracks %s has null Output MediaFormatProvider", r6.A4Z().toString())));
                    return;
                }
            }
        }
        AnonymousClass1xu r1 = r4.A03;
        r1.A05 = hashMap;
        r1.A02(AnonymousClass1xu.A0G);
        r1.A0E = false;
        r5.onSuccess();
    }
}
