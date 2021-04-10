package org.chromium.chrome.browser.partnercustomizations;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import org.chromium.base.task.PostTask;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PartnerBrowserCustomizations {

    /* renamed from: a  reason: collision with root package name */
    public static volatile PartnerBrowserCustomizations f10729a;
    public volatile String b;
    public volatile boolean c;
    public volatile boolean d;
    public boolean e;
    public final List f = new ArrayList();
    public QX g;

    public static Uri a(String str) {
        return new Uri.Builder().scheme("content").authority("com.android.partnerbrowsercustomizations").appendPath(str).build();
    }

    public static PartnerBrowserCustomizations c() {
        if (f10729a == null) {
            f10729a = new PartnerBrowserCustomizations();
        }
        return f10729a;
    }

    public static boolean isIncognitoDisabled() {
        return c().c;
    }

    public String b() {
        AbstractC1575Zv e2 = AbstractC1575Zv.e();
        if (e2.g("partner-homepage-for-testing")) {
            return e2.f("partner-homepage-for-testing");
        }
        return this.b;
    }

    public void d(Context context) {
        this.e = false;
        C1029Qw0 qw0 = new C1029Qw0(this, context);
        Executor executor = AbstractC2032cb.f9616a;
        qw0.f();
        ((ExecutorC1463Ya) executor).execute(qw0.e);
        PostTask.b(Zo1.f9374a, new RunnableC0907Ow0(qw0), 10000);
    }

    public boolean e() {
        return !TextUtils.isEmpty(b());
    }

    public void f(Runnable runnable) {
        if (this.e) {
            PostTask.b(Zo1.f9374a, runnable, 0);
        } else {
            this.f.add(runnable);
        }
    }
}
