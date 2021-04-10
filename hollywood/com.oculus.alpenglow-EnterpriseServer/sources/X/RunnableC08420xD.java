package X;

import android.util.Pair;
import java.util.List;

/* renamed from: X.0xD  reason: invalid class name and case insensitive filesystem */
public class RunnableC08420xD implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$7";
    public final /* synthetic */ Pair A00;
    public final /* synthetic */ C08290wy A01;

    public RunnableC08420xD(C08290wy r1, Pair pair) {
        this.A01 = r1;
        this.A00 = pair;
    }

    public final void run() {
        C08300wz r5;
        C08300wz r6;
        C08290wy r2 = this.A01;
        Pair pair = this.A00;
        List list = (List) pair.first;
        if (list != null && !list.isEmpty() && (r6 = r2.A0l) != null && r6.A06()) {
            try {
                int andIncrement = C08300wz.A0d.getAndIncrement() & 65535;
                r2.A0I.A00(r6, "callSub", EnumC08830xt.SUBACK, andIncrement, ((AnonymousClass0v5) r2.A0C).A01.A0I);
                synchronized (r6) {
                    if (r6.A06()) {
                        r6.A0G.execute(new RunnableC08680xe(r6, list, andIncrement));
                    } else {
                        throw new C09540zd(AnonymousClass007.A00);
                    }
                }
            } catch (C09540zd e) {
                AnonymousClass0NK.A0A("FbnsConnectionManager", e, "exception/subscribe");
                r2.A09(r6, EnumC08720xi.SEND_FAILURE, AnonymousClass007.A01);
            }
        }
        List list2 = (List) pair.second;
        if (list2 != null && !list2.isEmpty() && (r5 = r2.A0l) != null && r5.A06()) {
            try {
                int andIncrement2 = C08300wz.A0d.getAndIncrement() & 65535;
                r2.A0I.A00(r5, "callUnSub", EnumC08830xt.UNSUBACK, andIncrement2, ((AnonymousClass0v5) r2.A0C).A01.A0I);
                synchronized (r5) {
                    if (r5.A06()) {
                        r5.A0G.execute(new RunnableC08660xc(r5, list2, andIncrement2));
                    } else {
                        throw new C09540zd(AnonymousClass007.A00);
                    }
                }
            } catch (C09540zd e2) {
                AnonymousClass0NK.A0A("FbnsConnectionManager", e2, "exception/unsubscribe");
                r2.A09(r5, EnumC08720xi.SEND_FAILURE, AnonymousClass007.A01);
            }
        }
    }
}
