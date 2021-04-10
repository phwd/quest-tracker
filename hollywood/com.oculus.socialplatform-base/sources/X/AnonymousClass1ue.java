package X;

import android.view.Choreographer;

/* renamed from: X.1ue  reason: invalid class name */
public class AnonymousClass1ue implements Choreographer.FrameCallback {
    public final /* synthetic */ AnonymousClass1uW A00;

    public AnonymousClass1ue(AnonymousClass1uW r1) {
        this.A00 = r1;
    }

    public final void doFrame(long j) {
        this.A00.mRebindRunnable.run();
    }
}
