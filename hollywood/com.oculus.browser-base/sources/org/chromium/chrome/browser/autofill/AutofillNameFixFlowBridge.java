package org.chromium.chrome.browser.autofill;

import android.app.Activity;
import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class AutofillNameFixFlowBridge {

    /* renamed from: a  reason: collision with root package name */
    public final long f10608a;
    public final Activity b;
    public final String c;
    public final String d;
    public final String e;
    public final int f;
    public C1228Ud g;

    public AutofillNameFixFlowBridge(long j, String str, String str2, String str3, int i, WindowAndroid windowAndroid) {
        this.f10608a = j;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = i;
        Activity activity = (Activity) windowAndroid.s0().get();
        this.b = activity;
        if (activity == null) {
            this.g = null;
            PostTask.b(Zo1.f9374a, new RunnableC0923Pd(this), 0);
        }
    }

    public static AutofillNameFixFlowBridge create(long j, String str, String str2, String str3, int i, WindowAndroid windowAndroid) {
        return new AutofillNameFixFlowBridge(j, str, str2, str3, i, windowAndroid);
    }

    public final void dismiss() {
        C1228Ud ud = this.g;
        if (ud != null) {
            ud.L.b(ud.G, 4);
        }
    }

    public final void show(WindowAndroid windowAndroid) {
        C1228Ud ud = new C1228Ud(this.b, this, this.c, this.d, this.e, this.f);
        this.g = ud;
        if (ud != null) {
            ChromeActivity chromeActivity = (ChromeActivity) windowAndroid.s0().get();
            Objects.requireNonNull(ud);
            if (chromeActivity != null) {
                ud.M = chromeActivity;
                C2746gl0 l0 = chromeActivity.l0();
                ud.L = l0;
                l0.i(ud.G, 0, false);
                ud.I.addTextChangedListener(ud);
            }
        }
    }
}
