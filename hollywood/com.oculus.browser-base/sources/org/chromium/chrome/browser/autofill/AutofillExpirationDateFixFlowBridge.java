package org.chromium.chrome.browser.autofill;

import android.app.Activity;
import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class AutofillExpirationDateFixFlowBridge {

    /* renamed from: a  reason: collision with root package name */
    public long f10607a;
    public final String b;
    public final String c;
    public final int d;
    public final String e;
    public C0740Md f;

    public AutofillExpirationDateFixFlowBridge(long j, String str, String str2, int i, String str3) {
        this.f10607a = j;
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = str3;
    }

    public static AutofillExpirationDateFixFlowBridge create(long j, String str, String str2, int i, String str3) {
        return new AutofillExpirationDateFixFlowBridge(j, str, str2, i, str3);
    }

    public final void dismiss() {
        C0740Md md = this.f;
        if (md != null) {
            md.M.b(md.G, 4);
        }
    }

    public final void show(WindowAndroid windowAndroid) {
        Activity activity = (Activity) windowAndroid.s0().get();
        if (activity == null) {
            PostTask.b(Zo1.f9374a, new RunnableC0558Jd(this), 0);
            return;
        }
        C0740Md md = new C0740Md(activity, this, this.b, this.c, this.d, this.e);
        this.f = md;
        ChromeActivity chromeActivity = (ChromeActivity) activity;
        Objects.requireNonNull(md);
        md.N = chromeActivity;
        C2746gl0 l0 = chromeActivity.l0();
        md.M = l0;
        l0.i(md.G, 0, false);
    }
}
