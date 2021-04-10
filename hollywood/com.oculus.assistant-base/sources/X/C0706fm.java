package X;

import java.util.ArrayList;
import java.util.List;

/* renamed from: X.fm  reason: case insensitive filesystem */
public final class C0706fm implements AnonymousClass8c {
    public final /* synthetic */ C0740gP A00;

    public C0706fm(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // X.AnonymousClass8c
    public final void A3q(List list) {
        List list2;
        ArrayList arrayList = new ArrayList();
        C0740gP gPVar = this.A00;
        for (AnonymousClass8J r1 : gPVar.A0y.values()) {
            if (r1.A04()) {
                synchronized (r1) {
                    list2 = r1.A03;
                }
                arrayList.addAll(list2);
            }
        }
        gPVar.A0L.A3q(arrayList);
    }
}
