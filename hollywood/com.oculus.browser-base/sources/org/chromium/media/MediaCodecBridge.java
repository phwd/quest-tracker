package org.chromium.media;

import J.N;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaCodecBridge {

    /* renamed from: a  reason: collision with root package name */
    public static HandlerThread f10974a;
    public static Handler b;
    public MediaCodec c;
    public int d;
    public boolean e;
    public Queue f;
    public GetOutputFormatResult g;
    public boolean h;
    public boolean i;
    public long j;
    public int k;
    public Queue l;
    public Queue m;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class DequeueInputResult {

        /* renamed from: a  reason: collision with root package name */
        public final int f10975a;
        public final int b;

        public DequeueInputResult(int i, int i2, AbstractC5111ud0 ud0) {
            this.f10975a = i;
            this.b = i2;
        }

        public final int index() {
            return this.b;
        }

        public final int status() {
            return this.f10975a;
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class DequeueOutputResult {

        /* renamed from: a  reason: collision with root package name */
        public final int f10976a;
        public final int b;
        public final int c;
        public final int d;
        public final long e;
        public final int f;

        public DequeueOutputResult(int i, int i2, int i3, int i4, long j, int i5, AbstractC5111ud0 ud0) {
            this.f10976a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = j;
            this.f = i5;
        }

        public final int flags() {
            return this.c;
        }

        public final int index() {
            return this.b;
        }

        public final int numBytes() {
            return this.f;
        }

        public final int offset() {
            return this.d;
        }

        public final long presentationTimeMicroseconds() {
            return this.e;
        }

        public final int status() {
            return this.f10976a;
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class GetOutputFormatResult {

        /* renamed from: a  reason: collision with root package name */
        public final int f10977a;
        public final MediaFormat b;

        public GetOutputFormatResult(int i, MediaFormat mediaFormat, AbstractC5111ud0 ud0) {
            this.f10977a = i;
            this.b = mediaFormat;
        }

        public final boolean a() {
            return this.b.containsKey("crop-right") && this.b.containsKey("crop-left") && this.b.containsKey("crop-bottom") && this.b.containsKey("crop-top");
        }

        public final int channelCount() {
            return this.b.getInteger("channel-count");
        }

        public final int height() {
            if (a()) {
                return (this.b.getInteger("crop-bottom") - this.b.getInteger("crop-top")) + 1;
            }
            return this.b.getInteger("height");
        }

        public final int sampleRate() {
            return this.b.getInteger("sample-rate");
        }

        public final int status() {
            return this.f10977a;
        }

        public final int width() {
            if (a()) {
                return (this.b.getInteger("crop-right") - this.b.getInteger("crop-left")) + 1;
            }
            return this.b.getInteger("width");
        }
    }

    public MediaCodecBridge(MediaCodec mediaCodec, int i2, boolean z) {
        this.c = mediaCodec;
        this.d = i2;
        this.e = z;
        if (z) {
            this.h = false;
            this.f = new LinkedList();
            this.l = new LinkedList();
            this.m = new LinkedList();
            this.c.setCallback(new C5451wd0(this, this), b);
            e();
        }
    }

    public static void createCallbackHandlerForTesting() {
        if (f10974a == null) {
            HandlerThread handlerThread = new HandlerThread("TestCallbackThread");
            f10974a = handlerThread;
            handlerThread.start();
            b = new Handler(f10974a.getLooper());
        }
    }

    public boolean a(MediaFormat mediaFormat, Surface surface, MediaCrypto mediaCrypto, int i2) {
        try {
            this.c.configure(mediaFormat, surface, mediaCrypto, i2);
            return true;
        } catch (IllegalArgumentException e2) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Cannot configure the video codec, wrong format or surface", e2);
            return false;
        } catch (IllegalStateException e3) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Cannot configure the video codec", e3);
            return false;
        } catch (MediaCodec.CryptoException e4) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Cannot configure the video codec: DRM error", e4);
            return false;
        } catch (Exception e5) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Cannot configure the video codec", e5);
            return false;
        }
    }

    public int b(MediaCodec.BufferInfo bufferInfo, long j2) {
        return this.c.dequeueOutputBuffer(bufferInfo, j2);
    }

    public final synchronized void c() {
        long j2 = this.j;
        if (j2 != 0) {
            N.MNqjfk23(j2, this);
        }
    }

    public synchronized void d() {
        this.h = true;
        this.l.clear();
        this.m.clear();
        c();
    }

    public final DequeueInputResult dequeueInputBuffer(long j2) {
        int i2 = 5;
        int i3 = -1;
        if (this.e) {
            synchronized (this) {
                if (this.h) {
                    return new DequeueInputResult(5, -1, null);
                }
                if (!this.i) {
                    if (!this.l.isEmpty()) {
                        return (DequeueInputResult) this.l.remove();
                    }
                }
                return new DequeueInputResult(1, -1, null);
            }
        }
        try {
            int dequeueInputBuffer = this.c.dequeueInputBuffer(j2);
            if (dequeueInputBuffer >= 0) {
                i3 = dequeueInputBuffer;
                i2 = 0;
            } else if (dequeueInputBuffer == -1) {
                i2 = 1;
            } else {
                AbstractC1220Ua0.a("MediaCodecBridge", "Unexpected index_or_status: %d", Integer.valueOf(dequeueInputBuffer));
            }
        } catch (Exception e2) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Failed to dequeue input buffer", e2);
        }
        return new DequeueInputResult(i2, i3, null);
    }

    public final DequeueOutputResult dequeueOutputBuffer(long j2) {
        int i2;
        int i3;
        int i4 = 3;
        if (this.e) {
            synchronized (this) {
                if (this.h) {
                    return new DequeueOutputResult(5, -1, 0, 0, 0, 0, null);
                } else if (this.m.isEmpty()) {
                    return new DequeueOutputResult(1, -1, 0, 0, 0, 0, null);
                } else {
                    if (((DequeueOutputResult) this.m.peek()).status() == 3) {
                        this.g = (GetOutputFormatResult) this.f.remove();
                    }
                    return (DequeueOutputResult) this.m.remove();
                }
            }
        } else {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int i5 = -1;
            try {
                int b2 = b(bufferInfo, j2);
                if (b2 >= 0) {
                    i5 = b2;
                    i4 = 0;
                } else if (b2 == -3) {
                    i4 = 2;
                } else if (b2 == -2) {
                    this.c.getOutputFormat();
                } else if (b2 == -1) {
                    i4 = 1;
                } else {
                    AbstractC1220Ua0.a("MediaCodecBridge", "Unexpected index_or_status: %d", Integer.valueOf(b2));
                    i4 = 5;
                }
                i3 = i4;
                i2 = i5;
            } catch (IllegalStateException e2) {
                AbstractC1220Ua0.a("MediaCodecBridge", "Failed to dequeue output buffer", e2);
                i2 = -1;
                i3 = 5;
            }
            return new DequeueOutputResult(i3, i2, bufferInfo.flags, bufferInfo.offset, bufferInfo.presentationTimeUs, bufferInfo.size, null);
        }
    }

    public final synchronized void e() {
        this.f.clear();
        this.l.clear();
        this.m.clear();
        this.i = true;
        this.g = null;
        this.k++;
    }

    public boolean f() {
        try {
            if (this.e) {
                synchronized (this) {
                    if (this.h) {
                        return false;
                    }
                    Handler handler = b;
                    if (handler == null) {
                        handler = new Handler(Looper.getMainLooper());
                    }
                    handler.post(new RunnableC5281vd0(this, this.k));
                }
            }
            this.c.start();
            return true;
        } catch (IllegalStateException e2) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Cannot start the media codec", e2);
            return false;
        } catch (IllegalArgumentException e3) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Cannot start the media codec", e3);
            return false;
        }
    }

    public final int flush() {
        try {
            this.c.flush();
            if (this.e) {
                e();
                if (!f()) {
                    return 5;
                }
            }
            return 0;
        } catch (Exception e2) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Failed to flush MediaCodec", e2);
            return 5;
        }
    }

    public final int g(int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (i2 == 1) {
            return 1;
        }
        if (i2 == 2) {
            return 2;
        }
        AbstractC1220Ua0.a("MediaCodecBridge", "Unsupported cipher mode: %d", Integer.valueOf(i2));
        return -1;
    }

    public final ByteBuffer getInputBuffer(int i2) {
        try {
            return this.c.getInputBuffer(i2);
        } catch (IllegalStateException e2) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Failed to get input buffer", e2);
            return null;
        }
    }

    public final String getName() {
        try {
            return this.c.getName();
        } catch (IllegalStateException e2) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Cannot get codec name", e2);
            return "unknown";
        }
    }

    public ByteBuffer getOutputBuffer(int i2) {
        try {
            return this.c.getOutputBuffer(i2);
        } catch (IllegalStateException e2) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Failed to get output buffer", e2);
            return null;
        }
    }

    public final GetOutputFormatResult getOutputFormat() {
        MediaFormat mediaFormat;
        GetOutputFormatResult getOutputFormatResult;
        if (this.e && (getOutputFormatResult = this.g) != null) {
            return getOutputFormatResult;
        }
        int i2 = 0;
        try {
            mediaFormat = this.c.getOutputFormat();
        } catch (IllegalStateException e2) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Failed to get output format", e2);
            i2 = 5;
            mediaFormat = null;
        }
        return new GetOutputFormatResult(i2, mediaFormat, null);
    }

    public final int queueInputBuffer(int i2, int i3, int i4, long j2, int i5) {
        try {
            this.c.queueInputBuffer(i2, i3, i4, j2, i5);
            return 0;
        } catch (Exception e2) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Failed to queue input buffer", e2);
            return 5;
        }
    }

    public final int queueSecureInputBuffer(int i2, int i3, byte[] bArr, byte[] bArr2, int[] iArr, int[] iArr2, int i4, int i5, int i6, int i7, long j2) {
        try {
            int g2 = g(i5);
            if (g2 == -1) {
                return 5;
            }
            boolean z = g2 == 2;
            if (!z || MediaCodecUtil.platformSupportsCbcsEncryption(Build.VERSION.SDK_INT)) {
                MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
                cryptoInfo.set(i4, iArr, iArr2, bArr2, bArr, g2);
                if (!(i6 == 0 || i7 == 0)) {
                    if (z) {
                        cryptoInfo.setPattern(new MediaCodec.CryptoInfo.Pattern(i6, i7));
                    } else {
                        AbstractC1220Ua0.a("MediaCodecBridge", "Pattern encryption only supported for 'cbcs' scheme (CBC mode).", new Object[0]);
                        return 5;
                    }
                }
                this.c.queueSecureInputBuffer(i2, i3, cryptoInfo, j2, 0);
                return 0;
            }
            AbstractC1220Ua0.a("MediaCodecBridge", "Encryption scheme 'cbcs' not supported on this platform.", new Object[0]);
            return 5;
        } catch (MediaCodec.CryptoException e2) {
            if (e2.getErrorCode() == 1) {
                return 4;
            }
            AbstractC1220Ua0.a("MediaCodecBridge", "Failed to queue secure input buffer. Error code %d", Integer.valueOf(e2.getErrorCode()), e2);
            return 5;
        } catch (IllegalArgumentException e3) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Failed to queue secure input buffer.", e3);
            return 5;
        } catch (IllegalStateException e4) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Failed to queue secure input buffer.", e4);
            return 5;
        }
    }

    public void release() {
        if (this.e) {
            synchronized (this) {
                this.j = 0;
            }
        }
        try {
            AbstractC1220Ua0.f("MediaCodecBridge", "Releasing: %s", this.c.getName());
            this.c.release();
            AbstractC1220Ua0.f("MediaCodecBridge", "Codec released", new Object[0]);
        } catch (IllegalStateException e2) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Cannot release media codec", e2);
        }
        this.c = null;
    }

    public void releaseOutputBuffer(int i2, boolean z) {
        try {
            this.c.releaseOutputBuffer(i2, z);
        } catch (IllegalStateException e2) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Failed to release output buffer", e2);
        }
    }

    public final void requestKeyFrameSoon() {
        Bundle bundle = new Bundle();
        bundle.putInt("request-sync", 0);
        try {
            this.c.setParameters(bundle);
        } catch (IllegalStateException e2) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Failed to set MediaCodec parameters", e2);
        }
    }

    public final synchronized void setBuffersAvailableListener(long j2) {
        this.j = j2;
        if (!this.l.isEmpty() || !this.m.isEmpty() || this.h) {
            c();
        }
    }

    public final boolean setSurface(Surface surface) {
        try {
            this.c.setOutputSurface(surface);
            return true;
        } catch (IllegalArgumentException | IllegalStateException e2) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Cannot set output surface", e2);
            return false;
        }
    }

    public final void setVideoBitrate(int i2, int i3) {
        int i4 = this.d;
        if (i4 != 0) {
            if (i4 != 1) {
                i2 = 0;
            } else if (i3 != 0) {
                i2 = (i2 * 30) / i3;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt("video-bitrate", i2);
        try {
            this.c.setParameters(bundle);
        } catch (IllegalStateException e2) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Failed to set MediaCodec parameters", e2);
        }
    }

    public final void stop() {
        try {
            this.c.stop();
            if (this.e) {
                e();
            }
        } catch (IllegalStateException e2) {
            AbstractC1220Ua0.a("MediaCodecBridge", "Failed to stop MediaCodec", e2);
        }
    }
}
