package X;

import android.os.Handler;
import android.text.TextUtils;
import com.squareup.okhttp.ConnectionPool;

/* renamed from: X.0Vh  reason: invalid class name and case insensitive filesystem */
public class RunnableC01540Vh implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.common.analytics.defaultlogger.DefaultAnalyticsLogger$EventRunnable";
    public AnonymousClass0VX A00;
    public final /* synthetic */ C06570ne A01;

    public RunnableC01540Vh(C06570ne r1, AnonymousClass0VX r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void run() {
        C06570ne r4 = this.A01;
        AnonymousClass0VX r3 = this.A00;
        String string = r4.A01.getString("user_id", "");
        if (TextUtils.isEmpty(string)) {
            string = "0";
        }
        r3.A01.put("pk", string);
        C01500Vc r0 = r4.A00;
        r0.A07.add(this.A00);
        Handler handler = r4.A02;
        handler.removeMessages(1);
        if (r4.A00.A07.size() >= 50) {
            C06570ne.A00(r4);
        } else {
            handler.sendEmptyMessageDelayed(1, ConnectionPool.DEFAULT_KEEP_ALIVE_DURATION_MS);
        }
    }
}
