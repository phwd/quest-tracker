package defpackage;

import android.view.ViewTreeObserver;
import org.chromium.base.task.PostTask;

/* renamed from: MQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MQ implements ViewTreeObserver.OnDrawListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ NQ f8475a;

    public MQ(NQ nq) {
        this.f8475a = nq;
    }

    public void onDraw() {
        NQ nq = this.f8475a;
        if (!nq.c) {
            nq.c = true;
            Runnable runnable = nq.b;
            if (runnable != null) {
                runnable.run();
                nq.b = null;
            }
            PostTask.b(Zo1.b, new LQ(this), 0);
        }
    }
}
