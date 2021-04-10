package defpackage;

import android.os.Handler;
import java.util.Iterator;
import org.chromium.base.Callback;

/* renamed from: Rq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1078Rq0 implements AbstractC0956Pq0 {
    public final Thread F = Thread.currentThread();
    public final Handler G = new Handler();
    public Object H;
    public final C1322Vq0 I = new C1322Vq0();

    @Override // defpackage.Q31
    public Object get() {
        return this.H;
    }

    public Object l(Callback callback) {
        this.I.b(callback);
        Object obj = this.H;
        if (obj != null) {
            this.G.post(new RunnableC1017Qq0(this, obj, callback));
        }
        return this.H;
    }

    public void m(Object obj) {
        if (obj != this.H) {
            this.H = obj;
            Iterator it = this.I.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((Callback) uq0.next()).onResult(this.H);
                } else {
                    return;
                }
            }
        }
    }
}
