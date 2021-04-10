package X;

import com.facebook.appevents.AppEventsLogger;

/* renamed from: X.1hC  reason: invalid class name */
public class AnonymousClass1hC implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.appevents.AppEventsLogger$6";
    public final /* synthetic */ Integer A00;

    public AnonymousClass1hC(Integer num) {
        this.A00 = num;
    }

    public final void run() {
        AppEventsLogger.A03(this.A00);
    }
}
