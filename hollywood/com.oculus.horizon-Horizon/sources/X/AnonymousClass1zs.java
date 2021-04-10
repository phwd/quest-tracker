package X;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Handler;
import android.view.Surface;
import com.oculus.horizon.fbconnect.FBConnectHelper;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import javax.annotation.Nullable;

@TargetApi(18)
/* renamed from: X.1zs  reason: invalid class name */
public final class AnonymousClass1zs implements AbstractC11501yz {
    public MediaCodec A00;
    public MediaFormat A01;
    public Surface A02;
    public StringBuilder A03;
    public final Handler A04;
    public final int A05;
    public final AnonymousClass1yW A06;
    public final AnonymousClass1xF A07;
    public volatile Integer A08 = AnonymousClass007.A0D;
    public volatile boolean A09;

    @Override // X.AbstractC11501yz
    public final synchronized void A9P(AnonymousClass1zF r5, Handler handler) {
        this.A03.append("stop, ");
        boolean z = false;
        if (this.A08 == AnonymousClass007.A01) {
            z = true;
        }
        this.A09 = z;
        this.A08 = AnonymousClass007.A0C;
        this.A04.post(new AnonymousClass202(this, new C11471yw(r5, handler, this.A05, new AnonymousClass205("Timeout while stopping"))));
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

    public static void A01(AnonymousClass1zs r2, AnonymousClass1lF r3, Exception exc) {
        r3.A00("current_state", AnonymousClass1z2.A00(r2.A08));
        r3.A00("method_invocation", r2.A03.toString());
        AnonymousClass1xF r22 = r2.A07;
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

    public static void A02(AnonymousClass1zs r5, AnonymousClass1zF r6, Handler handler, boolean z) {
        AnonymousClass205 r2;
        MediaCodec mediaCodec;
        String str;
        StringBuilder sb = r5.A03;
        sb.append("(");
        sb.append(z);
        sb.append(")");
        sb.append("asyncPrepare, ");
        if (r5.A08 != AnonymousClass007.A0D) {
            Integer num = r5.A08;
            if (num != null) {
                str = AnonymousClass1z2.A00(num);
            } else {
                str = "null";
            }
            r2 = new AnonymousClass205(AnonymousClass006.A05("Must only call prepare() on a stopped SurfaceVideoEncoder. Current state is: ", str));
            r2.A00("current_state", AnonymousClass1z2.A00(r5.A08));
            r2.A00("method_invocation", r5.A03.toString());
        } else {
            try {
                AnonymousClass1xF r4 = r5.A07;
                if ("high".equalsIgnoreCase(r4.A05)) {
                    try {
                        mediaCodec = AnonymousClass1x5.A00("video/avc", A00(r4, true), null);
                    } catch (Exception e) {
                        AnonymousClass0NO.A0C("LegacySurfaceVideoEncoderImpl", "Error getting video encoder for high profile. Fall back to baseline", e);
                    }
                    r5.A00 = mediaCodec;
                    r5.A02 = mediaCodec.createInputSurface();
                    r5.A08 = AnonymousClass007.A00;
                    r5.A03.append("asyncPrepare end, ");
                    AnonymousClass1z7.A00(r6, handler);
                    return;
                }
                mediaCodec = AnonymousClass1x5.A00("video/avc", A00(r4, false), null);
                r5.A00 = mediaCodec;
                r5.A02 = mediaCodec.createInputSurface();
                r5.A08 = AnonymousClass007.A00;
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

    public static void A03(AnonymousClass1zs r10, boolean z) {
        AnonymousClass1yW r5;
        IOException iOException;
        long j = 0;
        try {
            ByteBuffer[] outputBuffers = r10.A00.getOutputBuffers();
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            while (true) {
                if (r10.A08 == AnonymousClass007.A01 || z) {
                    int dequeueOutputBuffer = r10.A00.dequeueOutputBuffer(bufferInfo, 1000);
                    if (dequeueOutputBuffer == -1) {
                        if (z) {
                            return;
                        }
                    } else if (dequeueOutputBuffer == -3) {
                        outputBuffers = r10.A00.getOutputBuffers();
                    } else if (dequeueOutputBuffer == -2) {
                        r10.A01 = r10.A00.getOutputFormat();
                    } else if (dequeueOutputBuffer < 0) {
                        r5 = r10.A06;
                        iOException = new IOException(String.format(null, "Unexpected result from encoder.dequeueOutputBuffer: %d", Integer.valueOf(dequeueOutputBuffer)));
                        break;
                    } else {
                        ByteBuffer byteBuffer = outputBuffers[dequeueOutputBuffer];
                        if (byteBuffer == null) {
                            r5 = r10.A06;
                            iOException = new IOException(String.format(null, "encoderOutputBuffer %d was null", Integer.valueOf(dequeueOutputBuffer)));
                            break;
                        }
                        byteBuffer.position(bufferInfo.offset).limit(bufferInfo.size);
                        if ((bufferInfo.flags & 2) != 0) {
                            bufferInfo.flags = 2;
                        }
                        if (bufferInfo.size > 0) {
                            r10.A06.A01(byteBuffer, bufferInfo);
                        }
                        r10.A00.releaseOutputBuffer(dequeueOutputBuffer, false);
                        if ((bufferInfo.flags & 4) == 0) {
                            j++;
                        } else {
                            return;
                        }
                    }
                } else {
                    return;
                }
            }
            r5.A00(iOException, null);
        } catch (Exception e) {
            HashMap hashMap = new HashMap();
            hashMap.put("current_state", AnonymousClass1z2.A00(r10.A08));
            hashMap.put("is_end_of_stream", String.valueOf(z));
            hashMap.put("frames_processed", String.valueOf(0L));
            hashMap.put("method_invocation", r10.A03.toString());
            if (C11161xo.A00() >= 21 && (e instanceof MediaCodec.CodecException)) {
                MediaCodec.CodecException codecException = (MediaCodec.CodecException) e;
                hashMap.put("isRecoverable", String.valueOf(codecException.isRecoverable()));
                hashMap.put("isTransient", String.valueOf(codecException.isTransient()));
            }
            r10.A06.A00(e, hashMap);
        }
    }

    @Override // X.AbstractC11501yz
    public final void A7S(AnonymousClass1zF r3, Handler handler) {
        this.A03.append("prepare, ");
        this.A04.post(new AnonymousClass208(this, r3, handler));
    }

    @Override // X.AbstractC11501yz
    public final void A9E(AnonymousClass1zF r3, Handler handler) {
        this.A03.append("start, ");
        this.A04.post(new RunnableC11571zz(this, r3, handler));
    }

    public AnonymousClass1zs(AnonymousClass1xF r3, AnonymousClass1yW r4, Handler handler, int i) {
        this.A07 = r3;
        this.A06 = r4;
        this.A04 = handler;
        this.A05 = i;
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
