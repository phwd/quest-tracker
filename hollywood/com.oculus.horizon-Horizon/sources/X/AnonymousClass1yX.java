package X;

import android.annotation.TargetApi;
import android.os.Handler;
import android.view.Surface;
import androidx.annotation.RequiresApi;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@RequiresApi(18)
@TargetApi(18)
/* renamed from: X.1yX  reason: invalid class name */
public abstract class AnonymousClass1yX<T> implements AbstractC11261xz {
    public static final AnonymousClass1zF A0E = new AnonymousClass1zD();
    @Nullable
    public Handler A00;
    @Nullable
    public Surface A01;
    @Nullable
    public C11241xx A02;
    @Nullable
    public AnonymousClass1xu A03;
    @Nullable
    public AnonymousClass1yX<T>.VideoEncoderCallback A04;
    @Nullable
    public AnonymousClass1y4 A05;
    @Nullable
    public AbstractC11501yz A06;
    @Nullable
    public T A07;
    public boolean A08;
    public boolean A09 = true;
    public final Handler A0A;
    public final AbstractC11091xe A0B;
    public final AnonymousClass1z3 A0C;
    public final WeakReference<AbstractC11031xR<T>> A0D;

    @Override // X.AbstractC11261xz
    @Nullable
    public final Map<String, String> A3H() {
        HashMap hashMap = new HashMap(2);
        String str = "True";
        hashMap.put("recording_video_received_data", "False");
        T t = this.A07;
        if (t == null || !t.A1e()) {
            str = "False";
        }
        hashMap.put("recording_video_encoding_enabled", str);
        return hashMap;
    }

    @Override // X.AbstractC11261xz
    @Nullable
    public final Map<String, String> A48() {
        return null;
    }

    @Override // X.AbstractC11261xz
    public final synchronized void A8r(@Nullable AnonymousClass1xu r2) {
        this.A03 = r2;
    }

    @Override // X.AbstractC11261xz
    public final void release() {
        this.A05 = null;
        this.A08 = false;
        this.A0D.get();
        this.A01 = null;
        this.A07 = null;
        AnonymousClass1yX<T>.VideoEncoderCallback videoEncoderCallback = this.A04;
        if (videoEncoderCallback != null) {
            if (!this.A0C.A92()) {
                videoEncoderCallback.A01 = true;
            }
            this.A04 = null;
        }
        AbstractC11501yz r2 = this.A06;
        if (r2 != null) {
            r2.A9P(A0E, this.A0A);
            this.A06 = null;
        }
        C09541mY.A01(this.A00, true, false);
        this.A00 = null;
        this.A09 = true;
    }

    @Override // X.AbstractC11261xz
    public final void A7T(AnonymousClass1yR r12, AbstractC11131xk r13) {
        String str;
        AbstractC11501yz r2;
        HashMap hashMap = new HashMap();
        if (r12.equals(this.A05)) {
            str = "true";
        } else {
            str = "false";
        }
        hashMap.put("recording_prepare_with_same_config", str);
        AbstractC11091xe r22 = this.A0B;
        r22.A5Q("prepare_recording_video_started", "AbstractVideoRecordingTrack", (long) hashCode(), "", null, null, hashMap);
        if (r12.equals(this.A05)) {
            AnonymousClass1z6.A00(r13, this.A0A);
            return;
        }
        r22.A5O(19, "recording_prepare_video_started");
        release();
        this.A09 = false;
        this.A05 = (AnonymousClass1y4) r12;
        this.A00 = C09541mY.A00(C09541mY.A03, "VideoRecordingThread", null);
        AnonymousClass1y4 r1 = this.A05;
        AnonymousClass1yW r5 = new AnonymousClass1yW(this);
        this.A04 = r5;
        AnonymousClass1z3 r0 = this.A0C;
        boolean A91 = r0.A91();
        AnonymousClass1xF r4 = r1.A01;
        Handler handler = this.A00;
        int A4U = r0.A4U();
        boolean A92 = r0.A92();
        if (A91) {
            if (A92) {
                r2 = new AnonymousClass1zu(r4, r5, handler, A4U);
            } else {
                r2 = new AnonymousClass1zt(r4, r5, handler, A4U);
            }
        } else if (A92) {
            r2 = new AnonymousClass1zr(r4, r5, handler, A4U);
        } else {
            r2 = new AnonymousClass1zs(r4, r5, handler, A4U);
        }
        this.A06 = r2;
        r2.A7S(new C11371yj(this, r13), this.A0A);
    }

    @Override // X.AbstractC11261xz
    public final void A9D(AbstractC11131xk r22, C11241xx r23) {
        AbstractC11091xe r5 = this.A0B;
        r5.A5O(19, "recording_start_video_started");
        r5.A5Q("start_recording_video_started", "AbstractVideoRecordingTrack", (long) hashCode(), "", null, null, null);
        this.A02 = r23;
        AbstractC11501yz r2 = this.A06;
        if (r2 != null) {
            r2.A9E(new AnonymousClass1yY(this, r22), this.A0A);
            return;
        }
        C11081xd r1 = new C11081xd(23000, "mVideoEncoder is null while starting");
        r5.A5Q("start_recording_video_failed", "AbstractVideoRecordingTrack", (long) hashCode(), "", r1, "start", null);
        release();
        r22.A62(r1);
    }

    @Override // X.AbstractC11261xz
    public final void A9G(AnonymousClass1yU r3) {
        AnonymousClass1yX<T>.VideoEncoderCallback videoEncoderCallback = this.A04;
        if (videoEncoderCallback != null) {
            videoEncoderCallback.A00 = r3;
        }
        T t = this.A07;
        if (t != null) {
            t.A8f(true);
        }
    }

    @Override // X.AbstractC11261xz
    public final void A9O(AnonymousClass1zF r12) {
        if (!this.A09) {
            AbstractC11091xe r2 = this.A0B;
            r2.A5O(19, "recording_stop_video_started");
            r2.A5Q("stop_recording_video_started", "AbstractVideoRecordingTrack", (long) hashCode(), "", null, null, null);
        }
        T t = this.A07;
        if (t != null) {
            t.A8f(false);
        }
        this.A0D.get();
        this.A01 = null;
        this.A07 = null;
        AbstractC11501yz r22 = this.A06;
        if (r22 != null) {
            r22.A9P(new AnonymousClass1yt(this, r12), this.A0A);
            return;
        }
        C11081xd r7 = null;
        if (!this.A09) {
            r7 = new C11081xd(23000, "mVideoEncoder is null while stopping");
            this.A0B.A5Q("stop_recording_video_failed", "AbstractVideoRecordingTrack", (long) hashCode(), "", r7, "stop", null);
        }
        release();
        if (r7 != null) {
            r12.A5y(r7);
        } else {
            r12.onSuccess();
        }
    }

    public AnonymousClass1yX(Handler handler, AbstractC11031xR<T> r3, AbstractC11091xe r4, AnonymousClass1z3 r5) {
        this.A0A = handler;
        this.A0D = new WeakReference<>(r3);
        this.A0B = r4;
        this.A0C = r5;
    }

    @Override // X.AbstractC11261xz
    @Nullable
    public final AnonymousClass1yQ A42() {
        return this.A06;
    }

    @Override // X.AbstractC11261xz
    public final AnonymousClass1yA A4Z() {
        return AnonymousClass1yA.VIDEO;
    }

    @Override // X.AbstractC11261xz
    public final boolean A4v() {
        return this.A08;
    }
}
