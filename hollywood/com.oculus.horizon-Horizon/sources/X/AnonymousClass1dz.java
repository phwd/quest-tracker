package X;

import android.content.Context;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/* renamed from: X.1dz  reason: invalid class name */
public class AnonymousClass1dz implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.appevents.AppEventsLogger$5";
    public final /* synthetic */ Context A00;
    public final /* synthetic */ AnonymousClass1gV A01;
    public final /* synthetic */ C09401dq A02;

    public AnonymousClass1dz(Context context, AnonymousClass1gV r2, C09401dq r3) {
        this.A00 = context;
        this.A01 = r2;
        this.A02 = r3;
    }

    public final void run() {
        Throwable th;
        int i;
        int size;
        AnonymousClass1fx A002 = AppEventsLogger.A00(this.A00, this.A01);
        C09401dq r2 = this.A02;
        synchronized (A002) {
            if (A002.A03.size() + A002.A04.size() >= 1000) {
                A002.A00++;
            } else {
                A002.A03.add(r2);
            }
        }
        synchronized (AppEventsLogger.A03) {
            synchronized (AppEventsLogger.A03) {
                try {
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            }
            synchronized (AppEventsLogger.A03) {
                i = 0;
                try {
                    for (AnonymousClass1fx r1 : AppEventsLogger.A04.values()) {
                        synchronized (r1) {
                            size = r1.A03.size();
                        }
                        i += size;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    throw th;
                }
            }
            if (i > 100) {
                FacebookSdk.getExecutor().execute(new AnonymousClass1hC(AnonymousClass007.A0E));
            }
        }
    }
}
