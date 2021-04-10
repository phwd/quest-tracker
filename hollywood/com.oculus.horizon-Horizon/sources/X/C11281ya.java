package X;

import android.annotation.TargetApi;
import android.os.Handler;
import androidx.annotation.RequiresApi;
import com.oculus.horizon.capture.VideoCapture;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@RequiresApi(18)
@TargetApi(18)
/* renamed from: X.1ya  reason: invalid class name and case insensitive filesystem */
public final class C11281ya implements AbstractC11261xz {
    public static final AbstractC11131xk A0G = new AnonymousClass1zE();
    @Nullable
    public Handler A00;
    @Nullable
    public AnonymousClass1zG A01;
    @Nullable
    public AbstractC11491yy A02;
    @Nullable
    public AnonymousClass1yZ A03;
    @Nullable
    public C11311yd A04;
    @Nullable
    public C11241xx A05;
    @Nullable
    public AnonymousClass1xu A06;
    public boolean A07;
    public boolean A08;
    public final Handler A09;
    public final AbstractC11041xS A0A = new C11481yx(this);
    public final AbstractC11091xe A0B;
    public final AnonymousClass1z3 A0C;
    public final VideoCapture.AnonymousClass7 A0D;
    public final Runnable A0E = new AnonymousClass1zC(this);
    public volatile boolean A0F;

    @Override // X.AbstractC11261xz
    @Nullable
    public final Map<String, String> A3H() {
        HashMap hashMap = new HashMap(2);
        String str = "True";
        String str2 = "False";
        if (this.A05 == null) {
            str2 = str;
        }
        hashMap.put("recording_audio_received_data", str2);
        if (!this.A0F) {
            str = "False";
        }
        hashMap.put("recording_audio_encoding_enabled", str);
        return hashMap;
    }

    @Override // X.AbstractC11261xz
    public final synchronized void A8r(@Nullable AnonymousClass1xu r2) {
        this.A06 = r2;
    }

    @Override // X.AbstractC11261xz
    public final void release() {
        this.A04 = null;
        this.A07 = false;
        AnonymousClass1yZ r1 = this.A03;
        if (r1 != null) {
            if (!this.A0C.A92()) {
                r1.A01 = true;
            }
            this.A03 = null;
        }
        AbstractC11491yy r2 = this.A02;
        if (r2 != null) {
            r2.A9N(A0G, this.A09);
            this.A02 = null;
        }
        C09541mY.A01(this.A00, true, false);
        this.A00 = null;
        this.A08 = true;
    }

    @Override // X.AbstractC11261xz
    @Nullable
    public final Map<String, String> A48() {
        HashMap hashMap;
        if (this.A01 != null) {
            hashMap = new HashMap(3);
            hashMap.put("recording_audio_avg_processing_time_ms", String.valueOf(0.0f));
            hashMap.put("recording_audio_was_effect_on", String.valueOf(false));
            hashMap.put("recording_audio_frame_size_ms", String.valueOf(this.A01.A00));
        } else {
            hashMap = null;
        }
        this.A01 = null;
        return hashMap;
    }

    @Override // X.AbstractC11261xz
    public final void A7T(AnonymousClass1yR r13, AbstractC11131xk r14) {
        String str;
        AbstractC11491yy r1;
        HashMap hashMap = new HashMap();
        if (r13.equals(this.A04)) {
            str = "true";
        } else {
            str = "false";
        }
        hashMap.put("recording_prepare_with_same_config", str);
        AbstractC11091xe r3 = this.A0B;
        r3.A5Q("prepare_recording_audio_started", "AudioRecordingTrack", (long) hashCode(), "", null, null, hashMap);
        if (r13.equals(this.A04)) {
            AnonymousClass1z6.A00(r14, this.A09);
            return;
        }
        r3.A5O(19, "recording_prepare_audio_started");
        release();
        this.A08 = false;
        C11311yd r132 = (C11311yd) r13;
        this.A04 = r132;
        AnonymousClass1zG r7 = new AnonymousClass1zG();
        this.A01 = r7;
        r7.A00 = Math.round(((float) ((((4096 / ((long) 2)) / ((long) 1)) * 1000000) / ((long) r132.A01.A05))) / 1000.0f);
        this.A00 = C09541mY.A00(C09541mY.A03, "AudioRecordingThread", null);
        C11381yk r0 = new C11381yk(this, hashMap, r14);
        Handler handler = this.A09;
        AnonymousClass1y7 r72 = new AnonymousClass1y7(r0, handler);
        C11311yd r02 = this.A04;
        Runnable runnable = this.A0E;
        AbstractC11131xk A002 = r72.A00(runnable);
        if (r02 != null) {
            VideoCapture.AnonymousClass7 r03 = this.A0D;
            Handler handler2 = this.A00;
            C11321ye r12 = new C11321ye(this, A002);
            VideoCapture.this.mAudioHandler = handler2;
            r12.onSuccess();
        }
        C11311yd r04 = this.A04;
        AbstractC11131xk A003 = r72.A00(runnable);
        if (r04 != null) {
            AnonymousClass1yZ r4 = new AnonymousClass1yZ(this);
            this.A03 = r4;
            C11351yh r32 = r04.A01;
            Handler handler3 = this.A00;
            if (this.A0C.A92()) {
                r1 = new C11391yl(r32, r4, handler3);
            } else {
                r1 = new C11401ym(r32, r4, handler3);
            }
            this.A02 = r1;
            r1.A7R(new C11301yc(this, A003), handler);
        }
        r72.A01();
        this.A0F = false;
    }

    @Override // X.AbstractC11261xz
    public final void A9D(AbstractC11131xk r22, C11241xx r23) {
        AbstractC11091xe r5 = this.A0B;
        r5.A5O(19, "recording_start_audio_started");
        r5.A5Q("start_recording_audio_started", "AudioRecordingTrack", (long) hashCode(), "", null, null, null);
        this.A05 = r23;
        this.A0F = false;
        AbstractC11491yy r2 = this.A02;
        if (r2 != null) {
            r2.A9C(new C11291yb(this, r22), this.A09);
            return;
        }
        release();
        C11081xd r1 = new C11081xd(22000, "mAudioEncoder is null while starting");
        r5.A5Q("start_recording_audio_failed", "AudioRecordingTrack", (long) hashCode(), "", r1, "start", null);
        r22.A62(r1);
    }

    @Override // X.AbstractC11261xz
    public final void A9G(AnonymousClass1yU r2) {
        AnonymousClass1yZ r0 = this.A03;
        if (r0 != null) {
            r0.A00 = r2;
        }
        this.A0F = true;
    }

    @Override // X.AbstractC11261xz
    public final void A9O(AnonymousClass1zF r12) {
        if (!this.A08) {
            AbstractC11091xe r2 = this.A0B;
            r2.A5O(19, "recording_stop_audio_started");
            r2.A5Q("stop_recording_audio_started", "AudioRecordingTrack", (long) hashCode(), "", null, null, null);
        }
        this.A0F = false;
        C11471yw r22 = new C11471yw(new C11331yf(this, r12), this.A09, this.A0C.A32(), new C11081xd("Timeout while removeOutput from AudioPipelineRecorder"));
        VideoCapture.this.mAudioPipelineRecorderOutput = null;
        r22.onSuccess();
    }

    public C11281ya(Handler handler, VideoCapture.AnonymousClass7 r3, AbstractC11091xe r4, AnonymousClass1z3 r5) {
        this.A09 = handler;
        this.A0D = r3;
        this.A0B = r4;
        this.A0C = r5;
        this.A08 = true;
    }

    @Override // X.AbstractC11261xz
    @Nullable
    public final AnonymousClass1yQ A42() {
        return this.A02;
    }

    @Override // X.AbstractC11261xz
    public final AnonymousClass1yA A4Z() {
        return AnonymousClass1yA.AUDIO;
    }

    @Override // X.AbstractC11261xz
    public final boolean A4v() {
        return this.A07;
    }
}
