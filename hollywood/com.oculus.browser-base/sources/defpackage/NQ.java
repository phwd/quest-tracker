package defpackage;

import android.view.View;
import java.lang.ref.WeakReference;

/* renamed from: NQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NQ {

    /* renamed from: a  reason: collision with root package name */
    public WeakReference f8545a;
    public Runnable b;
    public boolean c;

    public NQ(View view, Runnable runnable) {
        this.f8545a = new WeakReference(view);
        this.b = runnable;
    }

    public static void a(View view, Runnable runnable) {
        NQ nq = new NQ(view, runnable);
        KQ kq = new KQ(nq);
        MQ mq = new MQ(nq);
        ((View) nq.f8545a.get()).getViewTreeObserver().addOnPreDrawListener(kq);
        ((View) nq.f8545a.get()).getViewTreeObserver().addOnDrawListener(mq);
    }
}
