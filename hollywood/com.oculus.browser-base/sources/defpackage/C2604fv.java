package defpackage;

import android.content.SharedPreferences;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;

/* renamed from: fv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2604fv {

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f9964a;

    public C2604fv() {
        P21 f0 = P21.f0();
        try {
            this.f9964a = ContextUtils.getApplicationContext().getSharedPreferences("trusted_web_activity_client_apps", 0);
            f0.close();
            PostTask.b(C3070if1.f10154a, new RunnableC2433ev(this), 0);
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
