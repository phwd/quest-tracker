package X;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.1ee  reason: invalid class name and case insensitive filesystem */
public class C09421ee implements AnonymousClass1hR {
    public long A00;
    public final List<AnonymousClass1eZ> A01 = Collections.synchronizedList(new ArrayList());

    @Override // X.AnonymousClass1hR
    public final void A1j() {
        Iterator it = new ArrayList(this.A01).iterator();
        while (it.hasNext()) {
            AnonymousClass1eZ r1 = (AnonymousClass1eZ) it.next();
            AnonymousClass1ea.A02(r1.A00);
            AnonymousClass1ea.A02(r1.A01);
        }
    }

    @Override // X.AnonymousClass1hR
    public final void A1k(AnonymousClass1eZ r2) {
        this.A01.remove(r2);
    }

    @Override // X.AnonymousClass1hR
    public final void A2Z(AnonymousClass1eZ r5) {
        this.A00++;
        Thread thread = new Thread(r5);
        thread.setDaemon(true);
        StringBuilder sb = new StringBuilder("NanoHttpd Request Processor (#");
        sb.append(this.A00);
        sb.append(")");
        thread.setName(sb.toString());
        this.A01.add(r5);
        thread.start();
    }
}
