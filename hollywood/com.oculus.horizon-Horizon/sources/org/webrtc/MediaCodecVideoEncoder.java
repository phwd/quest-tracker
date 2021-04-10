package org.webrtc;

import X.AnonymousClass006;
import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import com.squareup.okhttp.internal.framed.Http2;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@TargetApi(19)
public class MediaCodecVideoEncoder {
    public static final int COLOR_QCOM_FORMATYUV420PackedSemiPlanar32m = 2141391876;
    public static final int DEQUEUE_TIMEOUT = 0;
    public static final String[] H264_HW_EXCEPTION_MODELS = {"SAMSUNG-SGH-I337", "Nexus 7", "Nexus 4"};
    public static final String H264_MIME_TYPE = "video/avc";
    public static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    public static final String TAG = "MediaCodecVideoEncoder";
    public static final int VIDEO_ControlRateConstant = 2;
    public static final String VP8_MIME_TYPE = "video/x-vnd.on2.vp8";
    public static final String VP9_MIME_TYPE = "video/x-vnd.on2.vp9";
    public static int codecErrors;
    public static MediaCodecVideoEncoderErrorCallback errorCallback;
    public static Set<String> hwEncoderDisabledTypes = new HashSet();
    public static MediaCodecVideoEncoder runningInstance;
    public static final int[] supportedColorList = {19, 21, 2141391872, 2141391876};
    public static final String[] supportedH264HwCodecPrefixes = {"OMX.qcom.", "OMX.Exynos."};
    public static final int[] supportedSurfaceColorList = {2130708361};
    public static final String[] supportedVp8HwCodecPrefixes = {"OMX.qcom.", "OMX.Intel.", "OMX.Exynos."};
    public static final String[] supportedVp9HwCodecPrefixes = {"OMX.qcom.", "OMX.Exynos."};
    public int colorFormat;
    public ByteBuffer configData = null;
    public GlRectDrawer drawer;
    public EglBase14 eglBase;
    public int height;
    public Surface inputSurface;
    public MediaCodec mediaCodec;
    public Thread mediaCodecThread;
    public ByteBuffer[] outputBuffers;
    public VideoCodecType type;
    public int width;

    public interface MediaCodecVideoEncoderErrorCallback {
        void onMediaCodecVideoEncoderCriticalError(int i);
    }

    public enum VideoCodecType {
        VIDEO_CODEC_VP8,
        VIDEO_CODEC_VP9,
        VIDEO_CODEC_H264
    }

    public static EncoderProperties findHwEncoder(String str, String[] strArr, int[] iArr) {
        if (str.equals("video/avc")) {
            List asList = Arrays.asList(H264_HW_EXCEPTION_MODELS);
            String str2 = Build.MODEL;
            if (asList.contains(str2)) {
                Logging.w(TAG, AnonymousClass006.A07("Model: ", str2, " has black listed H.264 encoder."));
                return null;
            }
        }
        for (int i = 0; i < MediaCodecList.getCodecCount(); i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
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
                            Logging.v(TAG, AnonymousClass006.A05("Found candidate encoder ", name));
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
                                    for (int i5 : iArr) {
                                        int[] iArr2 = capabilitiesForType.colorFormats;
                                        for (int i6 : iArr2) {
                                            if (i6 == i5) {
                                                Logging.d(TAG, AnonymousClass006.A0A("Found target encoder for mime ", str, " : ", name, ". Color: 0x", Integer.toHexString(i6)));
                                                return new EncoderProperties(name, i6);
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
        return null;
    }

    public static class EncoderProperties {
        public final String codecName;
        public final int colorFormat;

        public EncoderProperties(String str, int i) {
            this.codecName = str;
            this.colorFormat = i;
        }
    }

    public static class OutputBufferInfo {
        public final ByteBuffer buffer;
        public final int index;
        public final boolean isKeyFrame;
        public final long presentationTimestampUs;

        public OutputBufferInfo(int i, ByteBuffer byteBuffer, boolean z, long j) {
            this.index = i;
            this.buffer = byteBuffer;
            this.isKeyFrame = z;
            this.presentationTimestampUs = j;
        }
    }

    private void checkOnMediaCodecThread() {
        long id = this.mediaCodecThread.getId();
        Thread currentThread = Thread.currentThread();
        if (id != currentThread.getId()) {
            StringBuilder sb = new StringBuilder("MediaCodecVideoEncoder previously operated on ");
            sb.append(this.mediaCodecThread);
            sb.append(" but is now called on ");
            sb.append(currentThread);
            throw new RuntimeException(sb.toString());
        }
    }

    public static void disableH264HwCodec() {
        Logging.w(TAG, "H.264 encoding is disabled by application.");
        hwEncoderDisabledTypes.add("video/avc");
    }

    public static void disableVp8HwCodec() {
        Logging.w(TAG, "VP8 encoding is disabled by application.");
        hwEncoderDisabledTypes.add("video/x-vnd.on2.vp8");
    }

    public static void disableVp9HwCodec() {
        Logging.w(TAG, "VP9 encoding is disabled by application.");
        hwEncoderDisabledTypes.add("video/x-vnd.on2.vp9");
    }

    public static boolean isH264HwSupported() {
        if (hwEncoderDisabledTypes.contains("video/avc") || findHwEncoder("video/avc", supportedH264HwCodecPrefixes, supportedColorList) == null) {
            return false;
        }
        return true;
    }

    public static boolean isH264HwSupportedUsingTextures() {
        if (hwEncoderDisabledTypes.contains("video/avc") || findHwEncoder("video/avc", supportedH264HwCodecPrefixes, supportedSurfaceColorList) == null) {
            return false;
        }
        return true;
    }

    public static boolean isVp8HwSupported() {
        if (hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp8") || findHwEncoder("video/x-vnd.on2.vp8", supportedVp8HwCodecPrefixes, supportedColorList) == null) {
            return false;
        }
        return true;
    }

    public static boolean isVp8HwSupportedUsingTextures() {
        if (hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp8") || findHwEncoder("video/x-vnd.on2.vp8", supportedVp8HwCodecPrefixes, supportedSurfaceColorList) == null) {
            return false;
        }
        return true;
    }

    public static boolean isVp9HwSupported() {
        if (hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp9") || findHwEncoder("video/x-vnd.on2.vp9", supportedVp9HwCodecPrefixes, supportedColorList) == null) {
            return false;
        }
        return true;
    }

    public static boolean isVp9HwSupportedUsingTextures() {
        if (hwEncoderDisabledTypes.contains("video/x-vnd.on2.vp9") || findHwEncoder("video/x-vnd.on2.vp9", supportedVp9HwCodecPrefixes, supportedSurfaceColorList) == null) {
            return false;
        }
        return true;
    }

    public static void printStackTrace() {
        Thread thread;
        StackTraceElement[] stackTrace;
        int length;
        MediaCodecVideoEncoder mediaCodecVideoEncoder = runningInstance;
        if (!(mediaCodecVideoEncoder == null || (thread = mediaCodecVideoEncoder.mediaCodecThread) == null || (length = (stackTrace = thread.getStackTrace()).length) <= 0)) {
            Logging.d(TAG, "MediaCodecVideoEncoder stacks trace:");
            for (int i = 0; i < length; i++) {
                Logging.d(TAG, stackTrace[i].toString());
            }
        }
    }

    public static void setErrorCallback(MediaCodecVideoEncoderErrorCallback mediaCodecVideoEncoderErrorCallback) {
        Logging.d(TAG, "Set error callback");
        errorCallback = mediaCodecVideoEncoderErrorCallback;
    }

    public OutputBufferInfo dequeueOutputBuffer() {
        checkOnMediaCodecThread();
        try {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, 0);
            boolean z = true;
            if (dequeueOutputBuffer >= 0) {
                if ((bufferInfo.flags & 2) != 0) {
                    Logging.d(TAG, AnonymousClass006.A03("Config frame generated. Offset: ", bufferInfo.offset, ". Size: ", bufferInfo.size));
                    this.configData = ByteBuffer.allocateDirect(bufferInfo.size);
                    this.outputBuffers[dequeueOutputBuffer].position(bufferInfo.offset);
                    this.outputBuffers[dequeueOutputBuffer].limit(bufferInfo.offset + bufferInfo.size);
                    this.configData.put(this.outputBuffers[dequeueOutputBuffer]);
                    this.mediaCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
                    dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, 0);
                }
                if (dequeueOutputBuffer >= 0) {
                    ByteBuffer duplicate = this.outputBuffers[dequeueOutputBuffer].duplicate();
                    duplicate.position(bufferInfo.offset);
                    duplicate.limit(bufferInfo.offset + bufferInfo.size);
                    if ((bufferInfo.flags & 1) != 0) {
                        Logging.d(TAG, "Sync frame generated");
                        if (this.type == VideoCodecType.VIDEO_CODEC_H264) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Appending config frame of size ");
                            sb.append(this.configData.capacity());
                            sb.append(" to output buffer with offset ");
                            sb.append(bufferInfo.offset);
                            sb.append(", size ");
                            sb.append(bufferInfo.size);
                            Logging.d(TAG, sb.toString());
                            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.configData.capacity() + bufferInfo.size);
                            this.configData.rewind();
                            allocateDirect.put(this.configData);
                            allocateDirect.put(duplicate);
                            allocateDirect.position(0);
                            return new OutputBufferInfo(dequeueOutputBuffer, allocateDirect, true, bufferInfo.presentationTimeUs);
                        }
                    } else {
                        z = false;
                    }
                    return new OutputBufferInfo(dequeueOutputBuffer, duplicate.slice(), z, bufferInfo.presentationTimeUs);
                }
            }
            if (dequeueOutputBuffer == -3) {
                this.outputBuffers = this.mediaCodec.getOutputBuffers();
                return dequeueOutputBuffer();
            } else if (dequeueOutputBuffer == -2) {
                return dequeueOutputBuffer();
            } else {
                if (dequeueOutputBuffer == -1) {
                    return null;
                }
                throw new RuntimeException(AnonymousClass006.A01("dequeueOutputBuffer: ", dequeueOutputBuffer));
            }
        } catch (IllegalStateException e) {
            Logging.e(TAG, "dequeueOutputBuffer failed", e);
            return new OutputBufferInfo(-1, null, false, -1);
        }
    }

    public ByteBuffer[] getInputBuffers() {
        ByteBuffer[] inputBuffers = this.mediaCodec.getInputBuffers();
        Logging.d(TAG, AnonymousClass006.A01("Input buffers: ", inputBuffers.length));
        return inputBuffers;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0071  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean initEncode(org.webrtc.MediaCodecVideoEncoder.VideoCodecType r18, int r19, int r20, int r21, int r22, org.webrtc.EglBase14.Context r23) {
        /*
        // Method dump skipped, instructions count: 364
        */
        throw new UnsupportedOperationException("Method not decompiled: org.webrtc.MediaCodecVideoEncoder.initEncode(org.webrtc.MediaCodecVideoEncoder$VideoCodecType, int, int, int, int, org.webrtc.EglBase14$Context):boolean");
    }

    public void release() {
        Logging.d(TAG, "Java releaseEncoder");
        checkOnMediaCodecThread();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            /* class org.webrtc.MediaCodecVideoEncoder.AnonymousClass1 */

            public void run() {
                try {
                    Logging.d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread");
                    MediaCodecVideoEncoder.this.mediaCodec.stop();
                    MediaCodecVideoEncoder.this.mediaCodec.release();
                    Logging.d(MediaCodecVideoEncoder.TAG, "Java releaseEncoder on release thread done");
                } catch (Exception e) {
                    Logging.e(MediaCodecVideoEncoder.TAG, "Media encoder release failed", e);
                }
                countDownLatch.countDown();
            }
        }).start();
        if (!ThreadUtils.awaitUninterruptibly(countDownLatch, 5000)) {
            Logging.e(TAG, "Media encoder release timeout");
            int i = codecErrors + 1;
            codecErrors = i;
            if (errorCallback != null) {
                Logging.e(TAG, AnonymousClass006.A01("Invoke codec error callback. Errors: ", i));
                errorCallback.onMediaCodecVideoEncoderCriticalError(codecErrors);
            }
        }
        this.mediaCodec = null;
        this.mediaCodecThread = null;
        GlRectDrawer glRectDrawer = this.drawer;
        if (glRectDrawer != null) {
            glRectDrawer.release();
            this.drawer = null;
        }
        EglBase14 eglBase14 = this.eglBase;
        if (eglBase14 != null) {
            eglBase14.release();
            this.eglBase = null;
        }
        Surface surface = this.inputSurface;
        if (surface != null) {
            surface.release();
            this.inputSurface = null;
        }
        runningInstance = null;
        Logging.d(TAG, "Java releaseEncoder done");
    }

    public static MediaCodec createByCodecName(String str) {
        try {
            return MediaCodec.createByCodecName(str);
        } catch (Exception unused) {
            return null;
        }
    }

    private boolean setRates(int i, int i2) {
        checkOnMediaCodecThread();
        Logging.v(TAG, AnonymousClass006.A03("setRates: ", i, " kbps. Fps: ", i2));
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("video-bitrate", i * 1000);
            this.mediaCodec.setParameters(bundle);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "setRates failed", e);
            return false;
        }
    }

    public int dequeueInputBuffer() {
        checkOnMediaCodecThread();
        try {
            return this.mediaCodec.dequeueInputBuffer(0);
        } catch (IllegalStateException e) {
            Logging.e(TAG, "dequeueIntputBuffer failed", e);
            return -2;
        }
    }

    public boolean encodeBuffer(boolean z, int i, int i2, long j) {
        checkOnMediaCodecThread();
        if (z) {
            try {
                Logging.d(TAG, "Sync frame request");
                Bundle bundle = new Bundle();
                bundle.putInt("request-sync", 0);
                this.mediaCodec.setParameters(bundle);
            } catch (IllegalStateException e) {
                Logging.e(TAG, "encodeBuffer failed", e);
                return false;
            }
        }
        this.mediaCodec.queueInputBuffer(i, 0, i2, j, 0);
        return true;
    }

    public boolean encodeTexture(boolean z, int i, float[] fArr, long j) {
        checkOnMediaCodecThread();
        if (z) {
            try {
                Logging.d(TAG, "Sync frame request");
                Bundle bundle = new Bundle();
                bundle.putInt("request-sync", 0);
                this.mediaCodec.setParameters(bundle);
            } catch (RuntimeException e) {
                Logging.e(TAG, "encodeTexture failed", e);
                return false;
            }
        }
        this.eglBase.makeCurrent();
        GLES20.glClear(Http2.INITIAL_MAX_FRAME_SIZE);
        this.drawer.drawOes(i, fArr, 0, 0, this.width, this.height);
        this.eglBase.swapBuffers(TimeUnit.MICROSECONDS.toNanos(j));
        return true;
    }

    public boolean releaseOutputBuffer(int i) {
        checkOnMediaCodecThread();
        try {
            this.mediaCodec.releaseOutputBuffer(i, false);
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, "releaseOutputBuffer failed", e);
            return false;
        }
    }
}
