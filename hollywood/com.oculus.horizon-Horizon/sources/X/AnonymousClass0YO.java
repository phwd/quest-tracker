package X;

import android.util.Pair;
import java.util.List;

/* renamed from: X.0YO  reason: invalid class name */
public class AnonymousClass0YO implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$7";
    public final /* synthetic */ Pair A00;
    public final /* synthetic */ AnonymousClass0YZ A01;

    public AnonymousClass0YO(AnonymousClass0YZ r1, Pair pair) {
        this.A01 = r1;
        this.A00 = pair;
    }

    public final void run() {
        AnonymousClass0ZF r5;
        AnonymousClass0ZF r6;
        AnonymousClass0YZ r2 = this.A01;
        Pair pair = this.A00;
        List list = (List) pair.first;
        if (list != null && !list.isEmpty() && (r6 = r2.A0l) != null && r6.A06()) {
            try {
                int andIncrement = AnonymousClass0ZF.A0d.getAndIncrement() & 65535;
                r2.A0I.A00(r6, "callSub", EnumC02120Zg.SUBACK, andIncrement, r2.A0C.A00().A0I);
                synchronized (r6) {
                    if (r6.A06()) {
                        r6.A0G.execute(new AnonymousClass0Z8(r6, list, andIncrement));
                    } else {
                        throw new AnonymousClass0ZK(AnonymousClass007.A00);
                    }
                }
            } catch (AnonymousClass0ZK e) {
                AnonymousClass0NO.A0I("FbnsConnectionManager", e, "exception/subscribe");
                r2.A09(r6, EnumC01660Wk.SEND_FAILURE, AnonymousClass007.A01);
            }
        }
        List list2 = (List) pair.second;
        if (list2 != null && !list2.isEmpty() && (r5 = r2.A0l) != null && r5.A06()) {
            try {
                int andIncrement2 = AnonymousClass0ZF.A0d.getAndIncrement() & 65535;
                r2.A0I.A00(r5, "callUnSub", EnumC02120Zg.UNSUBACK, andIncrement2, r2.A0C.A00().A0I);
                synchronized (r5) {
                    if (r5.A06()) {
                        r5.A0G.execute(new AnonymousClass0Z9(r5, list2, andIncrement2));
                    } else {
                        throw new AnonymousClass0ZK(AnonymousClass007.A00);
                    }
                }
            } catch (AnonymousClass0ZK e2) {
                AnonymousClass0NO.A0I("FbnsConnectionManager", e2, "exception/unsubscribe");
                r2.A09(r5, EnumC01660Wk.SEND_FAILURE, AnonymousClass007.A01);
            }
        }
    }
}
