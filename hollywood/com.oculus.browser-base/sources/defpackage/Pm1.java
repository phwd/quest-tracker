package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.util.Objects;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.TracingControllerAndroidImpl;

/* renamed from: Pm1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Pm1 implements Runnable {
    public final Intent F;

    public Pm1(Intent intent) {
        this.F = intent;
    }

    public void run() {
        Intent intent = this.F;
        boolean z = false;
        if (!(Lm1.f8438a == null || Lm1.a().d == 0)) {
            z = true;
        }
        if (!z) {
            Om1.c();
        } else if ("org.chromium.chrome.browser.tracing.STOP_RECORDING".equals(intent.getAction())) {
            Lm1 a2 = Lm1.a();
            a2.b(4);
            Om1.d(Om1.b().H("Chrome trace is stopping").F("Trace data is being collected and compressed.").u(true).c());
            Mm1 mm1 = a2.b;
            Em1 em1 = new Em1(a2);
            TracingControllerAndroidImpl tracingControllerAndroidImpl = (TracingControllerAndroidImpl) mm1;
            if (tracingControllerAndroidImpl.d) {
                N.M$HKWu8q(tracingControllerAndroidImpl.i, tracingControllerAndroidImpl, tracingControllerAndroidImpl.f, tracingControllerAndroidImpl.g, tracingControllerAndroidImpl.h, em1);
            }
        } else if ("org.chromium.chrome.browser.tracing.SHARE_TRACE".equals(intent.getAction())) {
            Lm1 a3 = Lm1.a();
            Objects.requireNonNull(a3);
            Intent intent2 = new Intent("android.intent.action.SEND");
            Uri b = ContentUriUtils.b(a3.f);
            intent2.setType("application/gzip");
            intent2.putExtra("android.intent.extra.STREAM", b);
            intent2.addFlags(1);
            Context applicationContext = ContextUtils.getApplicationContext();
            Intent createChooser = Intent.createChooser(intent2, "Share trace");
            createChooser.addFlags(268435456);
            applicationContext.startActivity(createChooser);
            PostTask.b(Zo1.f9374a, new Fm1(a3), 3600000);
            a3.f = null;
            a3.b(1);
        } else if ("org.chromium.chrome.browser.tracing.DISCARD_TRACE".equals(intent.getAction())) {
            Lm1.a().b(1);
        }
    }
}
