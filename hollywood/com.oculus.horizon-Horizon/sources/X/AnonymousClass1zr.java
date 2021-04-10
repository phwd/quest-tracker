package X;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Handler;
import android.view.Surface;
import com.oculus.horizon.fbconnect.FBConnectHelper;
import javax.annotation.Nullable;

@TargetApi(18)
/* renamed from: X.1zr  reason: invalid class name */
public final class AnonymousClass1zr implements AbstractC11501yz {
    public MediaCodec A00;
    public MediaFormat A01;
    public Surface A02;
    public StringBuilder A03;
    public boolean A04 = true;
    public final Handler A05;
    public final int A06;
    public final AnonymousClass1yW A07;
    public final AnonymousClass1xF A08;
    public volatile Integer A09 = AnonymousClass007.A0D;
    public volatile boolean A0A;

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00bb, code lost:
        r3.A00(r1, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00be, code lost:
        return;
     */
    @androidx.annotation.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A03(X.AnonymousClass1zr r11, boolean r12) {
        /*
        // Method dump skipped, instructions count: 288
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1zr.A03(X.1zr, boolean):void");
    }

    @Override // X.AbstractC11501yz
    public final synchronized void A9P(AnonymousClass1zF r5, Handler handler) {
        this.A03.append("stop, ");
        boolean z = false;
        if (this.A09 == AnonymousClass007.A01) {
            z = true;
        }
        this.A0A = z;
        this.A09 = AnonymousClass007.A0C;
        this.A05.post(new RunnableC11551zx(this, new C11471yw(r5, handler, this.A06, new AnonymousClass205("Timeout while stopping"))));
    }

    public static MediaFormat A00(AnonymousClass1xF r3, boolean z) {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", r3.A04, r3.A02);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", r3.A00);
        createVideoFormat.setInteger("frame-rate", r3.A01);
        createVideoFormat.setInteger("i-frame-interval", r3.A03);
        createVideoFormat.setInteger("channel-count", 1);
        createVideoFormat.setInteger("max-input-size", 0);
        if (z) {
            createVideoFormat.setInteger(FBConnectHelper.FACEBOOK_DATA_PROFILE_KEY, 8);
            createVideoFormat.setInteger("level", 256);
        }
        return createVideoFormat;
    }

    public static void A01(AnonymousClass1zr r2, AnonymousClass1lF r3, Exception exc) {
        r3.A00("current_state", AnonymousClass1z2.A00(r2.A09));
        r3.A00("method_invocation", r2.A03.toString());
        AnonymousClass1xF r22 = r2.A08;
        r3.A00(FBConnectHelper.FACEBOOK_DATA_PROFILE_KEY, r22.A05);
        String valueOf = String.valueOf(false);
        r3.A00("b_frames", valueOf);
        r3.A00("explicitly_set_baseline", valueOf);
        StringBuilder sb = new StringBuilder();
        sb.append(r22.A04);
        sb.append("x");
        sb.append(r22.A02);
        r3.A00("size", sb.toString());
        r3.A00("bitrate", String.valueOf(r22.A00));
        r3.A00("frameRate", String.valueOf(r22.A01));
        r3.A00("iFrameIntervalS", String.valueOf(r22.A03));
        if (C11161xo.A00() >= 21 && (exc instanceof MediaCodec.CodecException)) {
            MediaCodec.CodecException codecException = (MediaCodec.CodecException) exc;
            r3.A00("isRecoverable", String.valueOf(codecException.isRecoverable()));
            r3.A00("isTransient", String.valueOf(codecException.isTransient()));
        }
    }

    public static void A02(AnonymousClass1zr r5, AnonymousClass1zF r6, Handler handler, boolean z) {
        AnonymousClass205 r2;
        MediaCodec mediaCodec;
        String str;
        StringBuilder sb = r5.A03;
        sb.append("(");
        sb.append(z);
        sb.append(")");
        sb.append("asyncPrepare, ");
        if (r5.A09 != AnonymousClass007.A0D) {
            Integer num = r5.A09;
            if (num != null) {
                str = AnonymousClass1z2.A00(num);
            } else {
                str = "null";
            }
            r2 = new AnonymousClass205(AnonymousClass006.A05("Must only call prepare() on a stopped SurfaceVideoEncoder. Current state is: ", str));
            r2.A00("current_state", AnonymousClass1z2.A00(r5.A09));
            r2.A00("method_invocation", r5.A03.toString());
        } else {
            try {
                AnonymousClass1xF r4 = r5.A08;
                if ("high".equalsIgnoreCase(r4.A05)) {
                    try {
                        mediaCodec = AnonymousClass1x5.A00("video/avc", A00(r4, true), null);
                    } catch (Exception e) {
                        AnonymousClass0NO.A0C("SurfaceVideoEncoderImpl", "Error getting video encoder for high profile. Fall back to baseline", e);
                    }
                    r5.A00 = mediaCodec;
                    r5.A02 = mediaCodec.createInputSurface();
                    r5.A04 = true;
                    r5.A09 = AnonymousClass007.A00;
                    r5.A03.append("asyncPrepare end, ");
                    AnonymousClass1z7.A00(r6, handler);
                    return;
                }
                mediaCodec = AnonymousClass1x5.A00("video/avc", A00(r4, false), null);
                r5.A00 = mediaCodec;
                r5.A02 = mediaCodec.createInputSurface();
                r5.A04 = true;
                r5.A09 = AnonymousClass007.A00;
                r5.A03.append("asyncPrepare end, ");
                AnonymousClass1z7.A00(r6, handler);
                return;
            } catch (Exception e2) {
                if (z) {
                    A02(r5, r6, handler, false);
                    return;
                } else {
                    r2 = new AnonymousClass205(e2);
                    A01(r5, r2, e2);
                }
            }
        }
        AnonymousClass1z7.A01(r6, handler, r2);
    }

    @Override // X.AbstractC11501yz
    public final void A7S(AnonymousClass1zF r3, Handler handler) {
        this.A03.append("prepare, ");
        this.A05.post(new AnonymousClass209(this, r3, handler));
    }

    @Override // X.AbstractC11501yz
    public final void A9E(AnonymousClass1zF r3, Handler handler) {
        this.A03.append("start, ");
        this.A05.post(new RunnableC11561zy(this, r3, handler));
    }

    public AnonymousClass1zr(AnonymousClass1xF r3, AnonymousClass1yW r4, Handler handler, int i) {
        this.A08 = r3;
        this.A07 = r4;
        this.A05 = handler;
        this.A06 = i;
        StringBuilder sb = new StringBuilder();
        this.A03 = sb;
        sb.append(hashCode());
        this.A03.append(" ctor, ");
    }

    @Override // X.AbstractC11501yz
    @Nullable
    public final Surface A3c() {
        return this.A02;
    }

    @Override // X.AnonymousClass1yQ
    @Nullable
    public final MediaFormat A41() {
        return this.A01;
    }
}
