package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import org.chromium.content.browser.TracingControllerAndroidImpl;

/* renamed from: Nm1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Nm1 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TracingControllerAndroidImpl f8575a;

    public Nm1(TracingControllerAndroidImpl tracingControllerAndroidImpl) {
        this.f8575a = tracingControllerAndroidImpl;
    }

    public void onReceive(Context context, Intent intent) {
        String str;
        if (intent.getAction().endsWith("GPU_PROFILER_START")) {
            String stringExtra = intent.getStringExtra("categories");
            if (TextUtils.isEmpty(stringExtra)) {
                str = N.My9pNx9O(this.f8575a);
            } else {
                str = stringExtra.replaceFirst("_DEFAULT_CHROME_CATEGORIES", N.My9pNx9O(this.f8575a));
            }
            String str2 = intent.getStringExtra("continuous") == null ? "record-until-full" : "record-continuously";
            String stringExtra2 = intent.getStringExtra("file");
            if (stringExtra2 != null) {
                this.f8575a.c(stringExtra2, true, str, str2, false, false);
            } else {
                this.f8575a.c(null, true, str, str2, false, false);
            }
        } else if (intent.getAction().endsWith("GPU_PROFILER_STOP")) {
            TracingControllerAndroidImpl tracingControllerAndroidImpl = this.f8575a;
            if (tracingControllerAndroidImpl.d) {
                N.M$HKWu8q(tracingControllerAndroidImpl.i, tracingControllerAndroidImpl, tracingControllerAndroidImpl.f, tracingControllerAndroidImpl.g, tracingControllerAndroidImpl.h, null);
            }
        } else if (intent.getAction().endsWith("GPU_PROFILER_LIST_CATEGORIES")) {
            TracingControllerAndroidImpl tracingControllerAndroidImpl2 = this.f8575a;
            tracingControllerAndroidImpl2.a();
            if (!N.MdRNuqnW(tracingControllerAndroidImpl2.i, tracingControllerAndroidImpl2, null)) {
                AbstractC1220Ua0.a("TracingController", "Unable to fetch tracing category list.", new Object[0]);
            }
        } else {
            AbstractC1220Ua0.a("TracingController", "Unexpected intent: %s", intent);
        }
    }
}
