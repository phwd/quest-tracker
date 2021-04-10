package org.webrtc;

import X.AnonymousClass006;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.SystemClock;
import android.view.Surface;
import com.facebook.internal.ImageRequest;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.SurfaceTextureHelper;

public class MediaCodecVideoDecoder {
    public static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    public static final int DEQUEUE_INPUT_TIMEOUT = 500000;
    public static final String H264_MIME_TYPE = "video/avc";
    public static final int MAX_QUEUED_OUTPUTBUFFERS = 3;
    public static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    public static final String TAG = "MediaCodecVideoDecoder";
    public static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    public static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    public static int codecErrors;
    public static MediaCodecVideoDecoderErrorCallback errorCallback;
    public static Set<String> hwDecoderDisabledTypes = new HashSet();
    public static MediaCodecVideoDecoder runningInstance;
    public static final List<Integer> supportedColorList = Arrays.asList(19, 21, 2141391872, 2141391876);
    public static final String[] supportedH264HwCodecPrefixes = {"OMX.qcom.", "OMX.Intel.", "OMX.Exynos."};
    public static final String[] supportedVp8HwCodecPrefixes = {"OMX.qcom.", "OMX.Nvidia.", "OMX.Exynos.", "OMX.Intel."};
    public static final String[] supportedVp9HwCodecPrefixes = {"OMX.qcom.", "OMX.Exynos."};
    public int colorFormat;
    public final Queue<TimeStamps> decodeStartTimeMs = new LinkedList();
    public final Queue<DecodedOutputBuffer> dequeuedSurfaceOutputBuffers = new LinkedList();
    public int droppedFrames;
    public boolean hasDecodedFirstFrame;
    public int height;
    public ByteBuffer[] inputBuffers;
    public MediaCodec mediaCodec;
    public Thread mediaCodecThread;
    public ByteBuffer[] outputBuffers;
    public int sliceHeight;
    public int stride;
    public Surface surface = null;
    public TextureListener textureListener;
    public boolean useSurface;
    public int width;

    public interface MediaCodecVideoDecoderErrorCallback {
        void onMediaCodecVideoDecoderCriticalError(int i);
    }

    public static class TextureListener implements SurfaceTextureHelper.OnTextureFrameAvailableListener {
        public DecodedOutputBuffer bufferToRender;
        public final Object newFrameLock = new Object();
        public DecodedTextureBuffer renderedBuffer;
        public final SurfaceTextureHelper surfaceTextureHelper;

        public void addBufferToRender(DecodedOutputBuffer decodedOutputBuffer) {
            if (this.bufferToRender == null) {
                this.bufferToRender = decodedOutputBuffer;
            } else {
                Logging.e(MediaCodecVideoDecoder.TAG, "Unexpected addBufferToRender() called while waiting for a texture.");
                throw new IllegalStateException("Waiting for a texture.");
            }
        }

        public DecodedTextureBuffer dequeueTextureBuffer(int i) {
            DecodedTextureBuffer decodedTextureBuffer;
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer == null && i > 0 && isWaitingForTexture()) {
                    try {
                        this.newFrameLock.wait((long) i);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                decodedTextureBuffer = this.renderedBuffer;
                this.renderedBuffer = null;
            }
            return decodedTextureBuffer;
        }

        public boolean isWaitingForTexture() {
            boolean z;
            synchronized (this.newFrameLock) {
                z = false;
                if (this.bufferToRender != null) {
                    z = true;
                }
            }
            return z;
        }

        @Override // org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener
        public void onTextureFrameAvailable(int i, float[] fArr, long j) {
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer == null) {
                    DecodedOutputBuffer decodedOutputBuffer = this.bufferToRender;
                    this.renderedBuffer = new DecodedTextureBuffer(i, fArr, decodedOutputBuffer.presentationTimeStampMs, decodedOutputBuffer.timeStampMs, decodedOutputBuffer.ntpTimeStampMs, decodedOutputBuffer.decodeTimeMs, SystemClock.elapsedRealtime() - decodedOutputBuffer.endDecodeTimeMs);
                    this.bufferToRender = null;
                    this.newFrameLock.notifyAll();
                } else {
                    Logging.e(MediaCodecVideoDecoder.TAG, "Unexpected onTextureFrameAvailable() called while already holding a texture.");
                    throw new IllegalStateException("Already holding a texture.");
                }
            }
        }

        public void release() {
            this.surfaceTextureHelper.disconnect();
            synchronized (this.newFrameLock) {
                if (this.renderedBuffer != null) {
                    this.surfaceTextureHelper.returnTextureFrame();
                    this.renderedBuffer = null;
                }
            }
        }

        public TextureListener(SurfaceTextureHelper surfaceTextureHelper2) {
            this.surfaceTextureHelper = surfaceTextureHelper2;
            surfaceTextureHelper2.setListener(this);
        }
    }

    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264
    }

    public static DecoderProperties findDecoder(String str, String[] strArr) {
        Logging.d(TAG, AnonymousClass006.A05("Trying to find HW decoder for mime ", str));
        for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (!codecInfoAt.isEncoder()) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                int length = supportedTypes.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        continue;
                        break;
                    } else if (supportedTypes[i2].equals(str)) {
                        String name = codecInfoAt.getName();
                        if (name != null) {
                            Logging.d(TAG, AnonymousClass006.A05("Found candidate decoder ", name));
                            int length2 = strArr.length;
                            int i3 = 0;
                            while (true) {
                                if (i3 >= length2) {
                                    continue;
                                    break;
                                } else if (name.startsWith(strArr[i3])) {
                                    MediaCodecInfo.CodecCapabilities capabilitiesForType = codecInfoAt.getCapabilitiesForType(str);
                                    for (int i4 : capabilitiesForType.colorFormats) {
                                        Logging.v(TAG, AnonymousClass006.A05("   Color: 0x", Integer.toHexString(i4)));
                                    }
                                    for (Integer num : supportedColorList) {
                                        int intValue = num.intValue();
                                        int[] iArr = capabilitiesForType.colorFormats;
                                        int length3 = iArr.length;
                                        int i5 = 0;
                                        while (true) {
                                            if (i5 < length3) {
                                                int i6 = iArr[i5];
                                                if (i6 == intValue) {
                                                    Logging.d(TAG, AnonymousClass006.A08("Found target decoder ", name, ". Color: 0x", Integer.toHexString(i6)));
                                                    return new DecoderProperties(name, i6);
                                                }
                                                i5++;
                                            }
                                        }
                                    }
                                    continue;
                                } else {
                                    i3++;
                                }
                            }
                        } else {
                            continue;
                        }
                    } else {
                        i2++;
                    }
                }
            }
        }
        Logging.d(TAG, AnonymousClass006.A05("No HW decoder found for mime ", str));
        return null;
    }

    public static class DecodedOutputBuffer {
        public final long decodeTimeMs;
        public final long endDecodeTimeMs;
        public final int index;
        public final long ntpTimeStampMs;
        public final int offset;
        public final long presentationTimeStampMs;
        public final int size;
        public final long timeStampMs;

        public DecodedOutputBuffer(int i, int i2, int i3, long j, long j2, long j3, long j4, long j5) {
            this.index = i;
            this.offset = i2;
            this.size = i3;
            this.presentationTimeStampMs = j;
            this.timeStampMs = j2;
            this.ntpTimeStampMs = j3;
            this.decodeTimeMs = j4;
            this.endDecodeTimeMs = j5;
        }
    }

    public static class DecodedTextureBuffer {
        public final long decodeTimeMs;
        public final long frameDelayMs;
        public final long ntpTimeStampMs;
        public final long presentationTimeStampMs;
        public final int textureID;
        public final long timeStampMs;
        public final float[] transformMatrix;

        public DecodedTextureBuffer(int i, float[] fArr, long j, long j2, long j3, long j4, long j5) {
            this.textureID = i;
            this.transformMatrix = fArr;
            this.presentationTimeStampMs = j;
            this.timeStampMs = j2;
            this.ntpTimeStampMs = j3;
            this.decodeTimeMs = j4;
            this.frameDelayMs = j5;
        }
    }

    public static class DecoderProperties {
        public final String codecName;
        public final int colorFormat;

        public DecoderProperties(String str, int i) {
            this.codecName = str;
            this.colorFormat = i;
        }
    }

    public static class TimeStamps {
        public final long decodeStartTimeMs;
        public final long ntpTimeStampMs;
        public final long timeStampMs;

        public TimeStamps(long j, long j2, long j3) {
            this.decodeStartTimeMs = j;
            this.timeStampMs = j2;
            this.ntpTimeStampMs = j3;
        }
    }

    private void MaybeRenderDecodedTextureBuffer() {
        if (!this.dequeuedSurfaceOutputBuffers.isEmpty() && !this.textureListener.isWaitingForTexture()) {
            DecodedOutputBuffer remove = this.dequeuedSurfaceOutputBuffers.remove();
            this.textureListener.addBufferToRender(remove);
            this.mediaCodec.releaseOutputBuffer(remove.index, true);
        }
    }

    private void checkOnMediaCodecThread() throws IllegalStateException {
        long id = this.mediaCodecThread.getId();
        Thread currentThread = Thread.currentThread();
        if (id != currentThread.getId()) {
            StringBuilder sb = new StringBuilder("MediaCodecVideoDecoder previously operated on ");
            sb.append(this.mediaCodecThread);
            sb.append(" but is now called on ");
            sb.append(currentThread);
            throw new IllegalStateException(sb.toString());
        }
    }

    private DecodedOutputBuffer dequeueOutputBuffer(int i) {
        String str;
        int integer;
        int integer2;
        int i2;
        checkOnMediaCodecThread();
        if (!this.decodeStartTimeMs.isEmpty()) {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            while (true) {
                int dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, TimeUnit.MILLISECONDS.toMicros((long) i));
                if (dequeueOutputBuffer == -3) {
                    ByteBuffer[] outputBuffers2 = this.mediaCodec.getOutputBuffers();
                    this.outputBuffers = outputBuffers2;
                    Logging.d(TAG, AnonymousClass006.A01("Decoder output buffers changed: ", outputBuffers2.length));
                    if (this.hasDecodedFirstFrame) {
                        str = "Unexpected output buffer change event.";
                        break;
                    }
                } else if (dequeueOutputBuffer == -2) {
                    MediaFormat outputFormat = this.mediaCodec.getOutputFormat();
                    Logging.d(TAG, AnonymousClass006.A05("Decoder format changed: ", outputFormat.toString()));
                    integer = outputFormat.getInteger(ImageRequest.WIDTH_PARAM);
                    integer2 = outputFormat.getInteger(ImageRequest.HEIGHT_PARAM);
                    if (!this.hasDecodedFirstFrame || (integer == (i2 = this.width) && integer2 == this.height)) {
                        this.width = outputFormat.getInteger(ImageRequest.WIDTH_PARAM);
                        this.height = outputFormat.getInteger(ImageRequest.HEIGHT_PARAM);
                        if (!this.useSurface && outputFormat.containsKey("color-format")) {
                            int integer3 = outputFormat.getInteger("color-format");
                            this.colorFormat = integer3;
                            Logging.d(TAG, AnonymousClass006.A05("Color: 0x", Integer.toHexString(integer3)));
                            if (!supportedColorList.contains(Integer.valueOf(this.colorFormat))) {
                                throw new IllegalStateException(AnonymousClass006.A01("Non supported color format: ", this.colorFormat));
                            }
                        }
                        if (outputFormat.containsKey("stride")) {
                            this.stride = outputFormat.getInteger("stride");
                        }
                        if (outputFormat.containsKey("slice-height")) {
                            this.sliceHeight = outputFormat.getInteger("slice-height");
                        }
                        Logging.d(TAG, AnonymousClass006.A03("Frame stride and slice height: ", this.stride, " x ", this.sliceHeight));
                        this.stride = Math.max(this.width, this.stride);
                        this.sliceHeight = Math.max(this.height, this.sliceHeight);
                    }
                } else if (dequeueOutputBuffer != -1) {
                    this.hasDecodedFirstFrame = true;
                    TimeStamps remove = this.decodeStartTimeMs.remove();
                    return new DecodedOutputBuffer(dequeueOutputBuffer, bufferInfo.offset, bufferInfo.size, TimeUnit.MICROSECONDS.toMillis(bufferInfo.presentationTimeUs), remove.timeStampMs, remove.ntpTimeStampMs, SystemClock.elapsedRealtime() - remove.decodeStartTimeMs, SystemClock.elapsedRealtime());
                }
            }
            StringBuilder sb = new StringBuilder("Unexpected size change. Configured ");
            sb.append(i2);
            sb.append("*");
            sb.append(this.height);
            sb.append(". New ");
            sb.append(integer);
            sb.append("*");
            sb.append(integer2);
            str = sb.toString();
            throw new RuntimeException(str);
        }
        return null;
    }

    public static void disableH264HwCodec() {
        Logging.w(TAG, "H.264 decoding is disabled by application.");
        hwDecoderDisabledTypes.add("video/avc");
    }

    public static void disableVp8HwCodec() {
        Logging.w(TAG, "VP8 decoding is disabled by application.");
        hwDecoderDisabledTypes.add("video/x-vnd.on2.vp8");
    }

    public static void disableVp9HwCodec() {
        Logging.w(TAG, "VP9 decoding is disabled by application.");
        hwDecoderDisabledTypes.add("video/x-vnd.on2.vp9");
    }

    private boolean initDecode(VideoCodecType videoCodecType, int i, int i2, SurfaceTextureHelper surfaceTextureHelper) {
        String str;
        String str2;
        String[] strArr;
        String str3;
        MediaCodec mediaCodec2;
        if (this.mediaCodecThread == null) {
            boolean z = false;
            if (surfaceTextureHelper != null) {
                z = true;
            }
            this.useSurface = z;
            if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP8) {
                strArr = supportedVp8HwCodecPrefixes;
                str3 = "video/x-vnd.on2.vp8";
            } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_VP9) {
                strArr = supportedVp9HwCodecPrefixes;
                str3 = "video/x-vnd.on2.vp9";
            } else if (videoCodecType == VideoCodecType.VIDEO_CODEC_H264) {
                strArr = supportedH264HwCodecPrefixes;
                str3 = "video/avc";
            } else {
                str2 = "Non supported codec ";
                StringBuilder sb = new StringBuilder(str2);
                sb.append(videoCodecType);
                str = sb.toString();
            }
            DecoderProperties findDecoder = findDecoder(str3, strArr);
            if (findDecoder != null) {
                StringBuilder sb2 = new StringBuilder("Java initDecode: ");
                sb2.append(videoCodecType);
                sb2.append(" : ");
                sb2.append(i);
                sb2.append(" x ");
                sb2.append(i2);
                sb2.append(". Color: 0x");
                sb2.append(Integer.toHexString(findDecoder.colorFormat));
                sb2.append(". Use Surface: ");
                sb2.append(this.useSurface);
                Logging.d(TAG, sb2.toString());
                runningInstance = this;
                this.mediaCodecThread = Thread.currentThread();
                try {
                    this.width = i;
                    this.height = i2;
                    this.stride = i;
                    this.sliceHeight = i2;
                    if (this.useSurface) {
                        this.textureListener = new TextureListener(surfaceTextureHelper);
                        this.surface = new Surface(surfaceTextureHelper.surfaceTexture);
                    }
                    MediaFormat createVideoFormat = MediaFormat.createVideoFormat(str3, i, i2);
                    if (!this.useSurface) {
                        createVideoFormat.setInteger("color-format", findDecoder.colorFormat);
                    }
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("  Format: ");
                    sb3.append(createVideoFormat);
                    Logging.d(TAG, sb3.toString());
                    try {
                        mediaCodec2 = MediaCodec.createByCodecName(findDecoder.codecName);
                    } catch (Exception unused) {
                        mediaCodec2 = null;
                    }
                    this.mediaCodec = mediaCodec2;
                    if (mediaCodec2 == null) {
                        Logging.e(TAG, "Can not create media decoder");
                        return false;
                    }
                    mediaCodec2.configure(createVideoFormat, this.surface, (MediaCrypto) null, 0);
                    this.mediaCodec.start();
                    this.colorFormat = findDecoder.colorFormat;
                    this.outputBuffers = this.mediaCodec.getOutputBuffers();
                    this.inputBuffers = this.mediaCodec.getInputBuffers();
                    this.decodeStartTimeMs.clear();
                    this.hasDecodedFirstFrame = false;
                    this.dequeuedSurfaceOutputBuffers.clear();
                    this.droppedFrames = 0;
                    Logging.d(TAG, AnonymousClass006.A03("Input buffers: ", this.inputBuffers.length, ". Output buffers: ", this.outputBuffers.length));
                    return true;
                } catch (IllegalStateException e) {
                    Logging.e(TAG, "initDecode failed", e);
                    return false;
                }
            } else {
                str2 = "Cannot find HW decoder for ";
                StringBuilder sb4 = new StringBuilder(str2);
                sb4.append(videoCodecType);
                str = sb4.toString();
            }
        } else {
            str = "Forgot to release()?";
        }
        throw new RuntimeException(str);
    }

    public static boolean isH264HwSupported() {
        if (hwDecoderDisabledTypes.contains("video/avc") || findDecoder("video/avc", supportedH264HwCodecPrefixes) == null) {
            return false;
        }
        return true;
    }

    public static boolean isVp8HwSupported() {
        if (hwDecoderDisabledTypes.contains("video/x-vnd.on2.vp8") || findDecoder("video/x-vnd.on2.vp8", supportedVp8HwCodecPrefixes) == null) {
            return false;
        }
        return true;
    }

    public static boolean isVp9HwSupported() {
        if (hwDecoderDisabledTypes.contains("video/x-vnd.on2.vp9") || findDecoder("video/x-vnd.on2.vp9", supportedVp9HwCodecPrefixes) == null) {
            return false;
        }
        return true;
    }

    public static void printStackTrace() {
        Thread thread;
        StackTraceElement[] stackTrace;
        int length;
        MediaCodecVideoDecoder mediaCodecVideoDecoder = runningInstance;
        if (!(mediaCodecVideoDecoder == null || (thread = mediaCodecVideoDecoder.mediaCodecThread) == null || (length = (stackTrace = thread.getStackTrace()).length) <= 0)) {
            Logging.d(TAG, "MediaCodecVideoDecoder stacks trace:");
            for (int i = 0; i < length; i++) {
                Logging.d(TAG, stackTrace[i].toString());
            }
        }
    }

    private void release() {
        Logging.d(TAG, AnonymousClass006.A01("Java releaseDecoder. Total number of dropped frames: ", this.droppedFrames));
        checkOnMediaCodecThread();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            /* class org.webrtc.MediaCodecVideoDecoder.AnonymousClass1 */

            public void run() {
                try {
                    Logging.d(MediaCodecVideoDecoder.TAG, "Java releaseDecoder on release thread");
                    MediaCodecVideoDecoder.this.mediaCodec.stop();
                    MediaCodecVideoDecoder.this.mediaCodec.release();
                    Logging.d(MediaCodecVideoDecoder.TAG, "Java releaseDecoder on release thread done");
                } catch (Exception e) {
                    Logging.e(MediaCodecVideoDecoder.TAG, "Media decoder release failed", e);
                }
                countDownLatch.countDown();
            }
        }).start();
        if (!ThreadUtils.awaitUninterruptibly(countDownLatch, 5000)) {
            Logging.e(TAG, "Media decoder release timeout");
            int i = codecErrors + 1;
            codecErrors = i;
            if (errorCallback != null) {
                Logging.e(TAG, AnonymousClass006.A01("Invoke codec error callback. Errors: ", i));
                errorCallback.onMediaCodecVideoDecoderCriticalError(codecErrors);
            }
        }
        this.mediaCodec = null;
        this.mediaCodecThread = null;
        runningInstance = null;
        if (this.useSurface) {
            this.surface.release();
            this.surface = null;
            this.textureListener.release();
        }
        Logging.d(TAG, "Java releaseDecoder done");
    }

    public static void setErrorCallback(MediaCodecVideoDecoderErrorCallback mediaCodecVideoDecoderErrorCallback) {
        Logging.d(TAG, "Set error callback");
        errorCallback = mediaCodecVideoDecoderErrorCallback;
    }

    private int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(500000);
        } catch (IllegalStateException e) {
            Logging.e(TAG, "dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    private DecodedTextureBuffer dequeueTextureBuffer(int i) {
        StringBuilder sb;
        checkOnMediaCodecThread();
        if (this.useSurface) {
            DecodedOutputBuffer dequeueOutputBuffer = dequeueOutputBuffer(i);
            if (dequeueOutputBuffer != null) {
                this.dequeuedSurfaceOutputBuffers.add(dequeueOutputBuffer);
            }
            MaybeRenderDecodedTextureBuffer();
            DecodedTextureBuffer dequeueTextureBuffer = this.textureListener.dequeueTextureBuffer(i);
            if (dequeueTextureBuffer != null) {
                MaybeRenderDecodedTextureBuffer();
                return dequeueTextureBuffer;
            } else if (this.dequeuedSurfaceOutputBuffers.size() < Math.min(3, this.outputBuffers.length) && (i <= 0 || this.dequeuedSurfaceOutputBuffers.isEmpty())) {
                return null;
            } else {
                this.droppedFrames++;
                DecodedOutputBuffer remove = this.dequeuedSurfaceOutputBuffers.remove();
                if (i > 0) {
                    sb = new StringBuilder("Draining decoder. Dropping frame with TS: ");
                } else {
                    sb = new StringBuilder("Too many output buffers ");
                    sb.append(this.dequeuedSurfaceOutputBuffers.size());
                    sb.append(". Dropping frame with TS: ");
                }
                sb.append(remove.presentationTimeStampMs);
                sb.append(". Total number of dropped frames: ");
                sb.append(this.droppedFrames);
                Logging.w(TAG, sb.toString());
                this.mediaCodec.releaseOutputBuffer(remove.index, false);
                return new DecodedTextureBuffer(0, null, remove.presentationTimeStampMs, remove.timeStampMs, remove.ntpTimeStampMs, remove.decodeTimeMs, SystemClock.elapsedRealtime() - remove.endDecodeTimeMs);
            }
        } else {
            throw new IllegalStateException("dequeueTexture() called for byte buffer decoding.");
        }
    }

    private boolean queueInputBuffer(int i, int i2, long j, long j2, long j3) {
        checkOnMediaCodecThread();
        try {
            this.inputBuffers[i].position(0);
            this.inputBuffers[i].limit(i2);
            this.decodeStartTimeMs.add(new TimeStamps(SystemClock.elapsedRealtime(), j2, j3));
            this.mediaCodec.queueInputBuffer(i, 0, i2, j, 0);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "decode failed", e);
            return false;
        }
    }

    private void returnDecodedOutputBuffer(int i) throws IllegalStateException, MediaCodec.CodecException {
        checkOnMediaCodecThread();
        if (!this.useSurface) {
            this.mediaCodec.releaseOutputBuffer(i, false);
            return;
        }
        throw new IllegalStateException("returnDecodedOutputBuffer() called for surface decoding.");
    }
}
