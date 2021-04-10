package X;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Handler;
import android.view.Surface;
import androidx.annotation.RequiresApi;
import com.oculus.horizon.fbconnect.FBConnectHelper;
import javax.annotation.Nullable;

@RequiresApi(21)
/* renamed from: X.1zt  reason: invalid class name */
public final class AnonymousClass1zt implements AbstractC11501yz {
    @Nullable
    public MediaCodec A00;
    @Nullable
    public MediaFormat A01;
    @Nullable
    public Handler A02;
    @Nullable
    public AnonymousClass1zF A03;
    public StringBuilder A04;
    @Nullable
    public Surface A05;
    public final AnonymousClass1yW A06;
    public final int A07;
    public final MediaCodec.Callback A08 = new C11541zw(this);
    public final Handler A09;
    public final AnonymousClass1xF A0A;
    public volatile Integer A0B;

    @Override // X.AbstractC11501yz
    public final synchronized void A9P(AnonymousClass1zF r5, Handler handler) {
        this.A04.append("stop, ");
        this.A0B = AnonymousClass007.A0C;
        this.A09.post(new AnonymousClass203(this, new C11471yw(r5, handler, this.A07, new AnonymousClass205("Timeout while stopping"))));
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

    public static void A01(AnonymousClass1zt r2, AnonymousClass1lF r3, Exception exc) {
        r3.A00("current_state", AnonymousClass1z2.A00(r2.A0B));
        r3.A00("method_invocation", r2.A04.toString());
        AnonymousClass1xF r22 = r2.A0A;
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

    public static void A02(@Nullable AnonymousClass1zt r3, @Nullable AnonymousClass1zF r4, Handler handler) {
        r3.A04.append("handleFinishedEncoding, ");
        r3.A03 = null;
        r3.A02 = null;
        if (r4 != null && handler != null) {
            try {
                Surface surface = r3.A05;
                if (surface != null) {
                    surface.release();
                }
                MediaCodec mediaCodec = r3.A00;
                if (mediaCodec != null) {
                    mediaCodec.flush();
                    r3.A00.stop();
                    r3.A00.release();
                }
                r3.A0B = AnonymousClass007.A0D;
                r3.A00 = null;
                r3.A05 = null;
                r3.A01 = null;
                r3.A04.append("asyncStop end, ");
                AnonymousClass1z7.A00(r4, handler);
            } catch (Exception e) {
                AnonymousClass205 r1 = new AnonymousClass205(e);
                A01(r3, r1, e);
                MediaCodec mediaCodec2 = r3.A00;
                if (mediaCodec2 != null) {
                    try {
                        mediaCodec2.release();
                    } catch (Exception unused) {
                    }
                }
                r3.A0B = AnonymousClass007.A0D;
                r3.A00 = null;
                r3.A05 = null;
                r3.A01 = null;
                AnonymousClass1z7.A01(r4, handler, r1);
            }
        }
    }

    public static void A03(AnonymousClass1zt r6, AnonymousClass1zF r7, Handler handler, boolean z) {
        AnonymousClass205 r2;
        MediaCodec mediaCodec;
        String str;
        StringBuilder sb = r6.A04;
        sb.append("(");
        sb.append(z);
        sb.append(")");
        sb.append("asyncPrepare, ");
        if (r6.A0B != AnonymousClass007.A0D) {
            Integer num = r6.A0B;
            if (num != null) {
                str = AnonymousClass1z2.A00(num);
            } else {
                str = "null";
            }
            r2 = new AnonymousClass205(AnonymousClass006.A05("Must only call prepare() on a stopped SurfaceVideoEncoder. Current state is: ", str));
            r2.A00("current_state", AnonymousClass1z2.A00(r6.A0B));
            r2.A00("method_invocation", r6.A04.toString());
        } else {
            try {
                AnonymousClass1xF r5 = r6.A0A;
                MediaCodec.Callback callback = r6.A08;
                if ("high".equalsIgnoreCase(r5.A05)) {
                    try {
                        mediaCodec = AnonymousClass1x5.A00("video/avc", A00(r5, true), callback);
                    } catch (Exception e) {
                        AnonymousClass0NO.A0C("LegacyAsyncSurfaceVideoEncoderImpl", "Error getting video encoder for high profile. Fall back to baseline", e);
                    }
                    r6.A00 = mediaCodec;
                    r6.A05 = mediaCodec.createInputSurface();
                    r6.A0B = AnonymousClass007.A00;
                    r6.A04.append("asyncPrepare end, ");
                    AnonymousClass1z7.A00(r7, handler);
                    return;
                }
                mediaCodec = AnonymousClass1x5.A00("video/avc", A00(r5, false), callback);
                r6.A00 = mediaCodec;
                r6.A05 = mediaCodec.createInputSurface();
                r6.A0B = AnonymousClass007.A00;
                r6.A04.append("asyncPrepare end, ");
                AnonymousClass1z7.A00(r7, handler);
                return;
            } catch (Exception e2) {
                if (z) {
                    A03(r6, r7, handler, false);
                    return;
                } else {
                    r2 = new AnonymousClass205(e2);
                    A01(r6, r2, e2);
                }
            }
        }
        AnonymousClass1z7.A01(r7, handler, r2);
    }

    @Override // X.AbstractC11501yz
    public final void A7S(AnonymousClass1zF r3, Handler handler) {
        this.A04.append("prepare, ");
        this.A09.post(new AnonymousClass207(this, r3, handler));
    }

    @Override // X.AbstractC11501yz
    public final void A9E(AnonymousClass1zF r3, Handler handler) {
        this.A04.append("start, ");
        this.A09.post(new AnonymousClass201(this, r3, handler));
    }

    public AnonymousClass1zt(AnonymousClass1xF r3, AnonymousClass1yW r4, Handler handler, int i) {
        this.A0A = r3;
        this.A06 = r4;
        this.A09 = handler;
        this.A07 = i;
        this.A0B = AnonymousClass007.A0D;
        StringBuilder sb = new StringBuilder();
        this.A04 = sb;
        sb.append(hashCode());
        this.A04.append(" ctor, ");
    }

    @Override // X.AbstractC11501yz
    @Nullable
    public final Surface A3c() {
        return this.A05;
    }

    @Override // X.AnonymousClass1yQ
    @Nullable
    public final MediaFormat A41() {
        return this.A01;
    }
}
