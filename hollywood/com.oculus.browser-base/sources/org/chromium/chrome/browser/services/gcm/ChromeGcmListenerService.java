package org.chromium.chrome.browser.services.gcm;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChromeGcmListenerService extends UU {
    public String L = "Er";
    public C0287Er M;

    public ChromeGcmListenerService() {
        C5271va vaVar = AbstractC2369eZ0.f9859a;
    }

    public void attachBaseContext(Context context) {
        Context a2 = AbstractC2369eZ0.a(context);
        C0287Er er = (C0287Er) AbstractC2369eZ0.b(a2, this.L);
        this.M = er;
        Objects.requireNonNull(er);
        super.attachBaseContext(a2);
    }

    @Override // defpackage.UU
    public void b(String str, Bundle bundle) {
        Objects.requireNonNull(this.M);
        ContextUtils.getApplicationContext();
        AbstractC3100ip1.f10165a.a("GCM.DataMessageReceivedHasRegisteredApp", true);
        AbstractC3364kK0.d("GCM.DataMessageReceived", 1);
        AbstractC3100ip1.f10165a.a("GCM.DataMessageReceivedHasCollapseKey", !TextUtils.isEmpty(bundle.getString("collapse_key")));
        PostTask.c(Zo1.f9374a, new RunnableC0226Dr(str, bundle));
    }

    @Override // defpackage.UU
    public void onCreate() {
        super.onCreate();
        Objects.requireNonNull(this.M);
        OG0.a().d();
    }
}
