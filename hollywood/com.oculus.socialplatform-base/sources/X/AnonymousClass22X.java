package X;

import android.util.Pair;
import java.util.List;
import okhttp3.internal.http2.Settings;

/* renamed from: X.22X  reason: invalid class name */
public class AnonymousClass22X implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.manager.FbnsConnectionManager$7";
    public final /* synthetic */ Pair A00;
    public final /* synthetic */ AnonymousClass22H A01;

    public AnonymousClass22X(AnonymousClass22H r1, Pair pair) {
        this.A01 = r1;
        this.A00 = pair;
    }

    public final void run() {
        AnonymousClass22J r5;
        AnonymousClass22J r6;
        AnonymousClass22H r2 = this.A01;
        Pair pair = this.A00;
        List list = (List) pair.first;
        if (list != null && !list.isEmpty() && (r6 = r2.A0n) != null && r6.A05()) {
            try {
                int andIncrement = AnonymousClass22J.A0d.getAndIncrement() & Settings.DEFAULT_INITIAL_WINDOW_SIZE;
                r2.A0M.A01(r6, "callSub", EnumC142622o.SUBACK, andIncrement, ((AnonymousClass1YE) r2.A0G).A00.A0I);
                synchronized (r6) {
                    if (r6.A05()) {
                        r6.A0H.execute(new AnonymousClass22a(r6, list, andIncrement));
                    } else {
                        throw new AnonymousClass23U(AnonymousClass007.A00);
                    }
                }
            } catch (AnonymousClass23U e) {
                AnonymousClass0MD.A0D(r2.A0R, e, "exception/subscribe");
                r2.A08(r6, EnumC141822g.SEND_FAILURE, AnonymousClass007.A01);
            }
        }
        List list2 = (List) pair.second;
        if (list2 != null && !list2.isEmpty() && (r5 = r2.A0n) != null && r5.A05()) {
            try {
                int andIncrement2 = AnonymousClass22J.A0d.getAndIncrement() & Settings.DEFAULT_INITIAL_WINDOW_SIZE;
                r2.A0M.A01(r5, "callUnSub", EnumC142622o.UNSUBACK, andIncrement2, ((AnonymousClass1YE) r2.A0G).A00.A0I);
                synchronized (r5) {
                    if (r5.A05()) {
                        r5.A0H.execute(new AnonymousClass22W(r5, list2, andIncrement2));
                    } else {
                        throw new AnonymousClass23U(AnonymousClass007.A00);
                    }
                }
            } catch (AnonymousClass23U e2) {
                AnonymousClass0MD.A0D(r2.A0R, e2, "exception/unsubscribe");
                r2.A08(r5, EnumC141822g.SEND_FAILURE, AnonymousClass007.A01);
            }
        }
    }
}
