package org.webrtc;

import X.AnonymousClass006;
import java.nio.ByteBuffer;

public class VideoRenderer {
    public long nativeVideoRenderer;

    public interface Callbacks {
        void renderFrame(I420Frame i420Frame);
    }

    public static class I420Frame {
        public final int height;
        public long nativeFramePointer;
        public int rotationDegree;
        public final float[] samplingMatrix;
        public int textureId;
        public final int width;
        public final boolean yuvFrame;
        public ByteBuffer[] yuvPlanes;
        public final int[] yuvStrides;

        public int rotatedHeight() {
            if (this.rotationDegree % 180 == 0) {
                return this.height;
            }
            return this.width;
        }

        public int rotatedWidth() {
            if (this.rotationDegree % 180 == 0) {
                return this.width;
            }
            return this.height;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.width);
            sb.append("x");
            sb.append(this.height);
            sb.append(":");
            int[] iArr = this.yuvStrides;
            sb.append(iArr[0]);
            sb.append(":");
            sb.append(iArr[1]);
            sb.append(":");
            sb.append(iArr[2]);
            return sb.toString();
        }

        public I420Frame(int i, int i2, int i3, int i4, float[] fArr, long j) {
            this.width = i;
            this.height = i2;
            this.yuvStrides = null;
            this.yuvPlanes = null;
            this.samplingMatrix = fArr;
            this.textureId = i4;
            this.yuvFrame = false;
            this.rotationDegree = i3;
            this.nativeFramePointer = j;
            if (i3 % 90 != 0) {
                throw new IllegalArgumentException(AnonymousClass006.A01("Rotation degree not multiple of 90: ", i3));
            }
        }

        public I420Frame(int i, int i2, int i3, int[] iArr, ByteBuffer[] byteBufferArr, long j) {
            this.width = i;
            this.height = i2;
            this.yuvStrides = iArr;
            this.yuvPlanes = byteBufferArr;
            this.yuvFrame = true;
            this.rotationDegree = i3;
            this.nativeFramePointer = j;
            if (i3 % 90 == 0) {
                this.samplingMatrix = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f};
                return;
            }
            throw new IllegalArgumentException(AnonymousClass006.A01("Rotation degree not multiple of 90: ", i3));
        }
    }

    public static native void freeWrappedVideoRenderer(long j);

    public static native void nativeCopyPlane(ByteBuffer byteBuffer, int i, int i2, int i3, ByteBuffer byteBuffer2, int i4);

    public static native long nativeWrapVideoRenderer(Callbacks callbacks);

    public static native void releaseNativeFrame(long j);

    public static void renderFrameDone(I420Frame i420Frame) {
        i420Frame.yuvPlanes = null;
        i420Frame.textureId = 0;
        long j = i420Frame.nativeFramePointer;
        if (j != 0) {
            releaseNativeFrame(j);
            i420Frame.nativeFramePointer = 0;
        }
    }

    public void dispose() {
        long j = this.nativeVideoRenderer;
        if (j != 0) {
            freeWrappedVideoRenderer(j);
            this.nativeVideoRenderer = 0;
        }
    }

    public VideoRenderer(long j) {
        this.nativeVideoRenderer = j;
    }

    public VideoRenderer(Callbacks callbacks) {
        this.nativeVideoRenderer = nativeWrapVideoRenderer(callbacks);
    }
}
