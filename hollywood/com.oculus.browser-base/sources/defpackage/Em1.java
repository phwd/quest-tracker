package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.tracing.TracingNotificationService;

/* renamed from: Em1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Em1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Lm1 f7976a;

    public Em1(Lm1 lm1) {
        this.f7976a = lm1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Void r7 = (Void) obj;
        this.f7976a.b(5);
        Context applicationContext = ContextUtils.getApplicationContext();
        AbstractC3615lq0 u = Om1.b().H("Chrome trace is complete").F("The trace is ready to share.").u(false);
        Intent intent = new Intent(applicationContext, TracingNotificationService.class);
        intent.setAction("org.chromium.chrome.browser.tracing.SHARE_TRACE");
        AbstractC3615lq0 l = u.l(R.drawable.f32630_resource_name_obfuscated_RES_2131231303, "Share trace", PendingIntent.getService(applicationContext, 0, intent, 134217728));
        Intent intent2 = new Intent(applicationContext, TracingNotificationService.class);
        intent2.setAction("org.chromium.chrome.browser.tracing.DISCARD_TRACE");
        Om1.d(l.K(PendingIntent.getService(applicationContext, 0, intent2, 134217728)).c());
    }
}
