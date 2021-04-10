package org.chromium.chrome.browser.policy;

import android.os.Handler;
import android.os.Looper;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.RejectedExecutionException;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EnterpriseInfo {

    /* renamed from: a  reason: collision with root package name */
    public static EnterpriseInfo f10747a;
    public final Handler b = new Handler(Looper.myLooper());
    public C4731sL c = null;
    public Queue d = new LinkedList();

    public static EnterpriseInfo b() {
        Object obj = ThreadUtils.f10596a;
        if (f10747a == null) {
            f10747a = new EnterpriseInfo();
        }
        return f10747a;
    }

    public static void getManagedStateForNative() {
        b().a(new C4391qL());
    }

    public void a(Callback callback) {
        Object obj = ThreadUtils.f10596a;
        if (this.c != null) {
            this.b.post(new RunnableC3878nL(this, callback));
            return;
        }
        this.d.add(callback);
        if (this.d.size() <= 1) {
            try {
                C4561rL rLVar = new C4561rL(this);
                C3070if1 if1 = C3070if1.c;
                rLVar.f();
                PostTask.b(if1, rLVar.e, 0);
            } catch (RejectedExecutionException unused) {
                AbstractC1220Ua0.f("EnterpriseInfo", "Thread limit reached, unable to determine managed state.", new Object[0]);
                this.b.post(new RunnableC4049oL((Callback) this.d.remove()));
            }
        }
    }
}
