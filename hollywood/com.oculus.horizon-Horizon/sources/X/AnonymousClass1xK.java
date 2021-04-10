package X;

import android.view.Surface;
import com.facebook.cameracore.mediapipeline.filterlib.output.listeners.VideoOutputFrameRenderingListener;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.horizon.capture.VideoCapture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1xK  reason: invalid class name */
public abstract class AnonymousClass1xK implements AbstractC11051xT {
    @Nullable
    public Surface A00;
    @Nullable
    public VideoCapture.AnonymousClass5.AnonymousClass1 A01 = null;
    public final AtomicBoolean A02 = new AtomicBoolean(false);
    public final AtomicReference<VideoOutputFrameRenderingListener> A03 = new AtomicReference<>(null);
    public volatile boolean A04 = true;

    @Override // X.AbstractC11051xT
    public synchronized boolean A1e() {
        return false;
    }

    @Override // X.AbstractC11051xT
    public final synchronized void A8f(boolean z) {
        this.A04 = z;
        VideoCapture.AnonymousClass5.AnonymousClass1 r2 = this.A01;
        if (r2 != null && this.A04) {
            VideoCapture.this.mVideoStateListener.A6z(((AnonymousClass1xK) ((AnonymousClass1xN) r3)).A00);
        }
    }
}
