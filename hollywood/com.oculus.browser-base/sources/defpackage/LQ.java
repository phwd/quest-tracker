package defpackage;

import android.view.View;

/* renamed from: LQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class LQ implements Runnable {
    public final MQ F;

    public LQ(MQ mq) {
        this.F = mq;
    }

    public void run() {
        MQ mq = this.F;
        if (mq.f8475a.f8545a.get() != null) {
            ((View) mq.f8475a.f8545a.get()).getViewTreeObserver().removeOnDrawListener(mq);
        }
    }
}
