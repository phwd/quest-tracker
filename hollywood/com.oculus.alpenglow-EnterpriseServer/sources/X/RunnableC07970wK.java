package X;

import android.os.Handler;
import android.text.TextUtils;
import com.oculus.util.constants.OculusConstants;
import com.squareup.okhttp.ConnectionPool;

/* renamed from: X.0wK  reason: invalid class name and case insensitive filesystem */
public class RunnableC07970wK implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.common.analytics.defaultlogger.DefaultAnalyticsLogger$EventRunnable";
    public C07840w6 A00;
    public final /* synthetic */ C07980wL A01;

    public RunnableC07970wK(C07980wL r1, C07840w6 r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void run() {
        C07980wL r4 = this.A01;
        C07840w6 r3 = this.A00;
        String string = r4.A01.getString("user_id", "");
        if (TextUtils.isEmpty(string)) {
            string = OculusConstants.DEFAULT_ENTERPRISE_USER_ID;
        }
        r3.A01.put("pk", string);
        C07960wJ r0 = r4.A00;
        r0.A07.add(this.A00);
        Handler handler = r4.A02;
        handler.removeMessages(1);
        if (r4.A00.A07.size() >= 50) {
            C07980wL.A00(r4);
        } else {
            handler.sendEmptyMessageDelayed(1, ConnectionPool.DEFAULT_KEEP_ALIVE_DURATION_MS);
        }
    }
}
