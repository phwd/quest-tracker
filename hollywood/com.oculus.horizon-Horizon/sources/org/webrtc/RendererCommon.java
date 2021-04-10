package org.webrtc;

import android.graphics.Point;
import android.opengl.GLES20;
import android.opengl.Matrix;
import java.nio.ByteBuffer;

public class RendererCommon {
    public static final float BALANCED_VISIBLE_FRACTION = 0.5625f;

    public interface GlDrawer {
        void drawOes(int i, float[] fArr, int i2, int i3, int i4, int i5);

        void drawRgb(int i, float[] fArr, int i2, int i3, int i4, int i5);

        void drawYuv(int[] iArr, float[] fArr, int i, int i2, int i3, int i4);

        void release();
    }

    public interface RendererEvents {
        void onFirstFrameRendered();

        void onFrameResolutionChanged(int i, int i2, int i3);
    }

    public enum ScalingType {
        SCALE_ASPECT_FIT,
        SCALE_ASPECT_FILL,
        SCALE_ASPECT_BALANCED
    }

    public static class YuvUploader {
        public ByteBuffer copyBuffer;

        public void uploadYuvData(int[] iArr, int i, int i2, int[] iArr2, ByteBuffer[] byteBufferArr) {
            ByteBuffer byteBuffer;
            ByteBuffer byteBuffer2;
            int i3 = 0;
            int i4 = i >> 1;
            int[] iArr3 = {i, i4, i4};
            int i5 = i2 >> 1;
            int[] iArr4 = {i2, i5, i5};
            int i6 = 0;
            int i7 = 0;
            do {
                if (iArr2[i6] > iArr3[i6]) {
                    i7 = Math.max(i7, iArr3[i6] * iArr4[i6]);
                }
                i6++;
            } while (i6 < 3);
            if (i7 > 0 && ((byteBuffer2 = this.copyBuffer) == null || byteBuffer2.capacity() < i7)) {
                this.copyBuffer = ByteBuffer.allocateDirect(i7);
            }
            do {
                GLES20.glActiveTexture(33984 + i3);
                GLES20.glBindTexture(3553, iArr[i3]);
                if (iArr2[i3] == iArr3[i3]) {
                    byteBuffer = byteBufferArr[i3];
                } else {
                    ByteBuffer byteBuffer3 = byteBufferArr[i3];
                    int i8 = iArr3[i3];
                    VideoRenderer.nativeCopyPlane(byteBuffer3, i8, iArr4[i3], iArr2[i3], this.copyBuffer, i8);
                    byteBuffer = this.copyBuffer;
                }
                GLES20.glTexImage2D(3553, 0, 6409, iArr3[i3], iArr4[i3], 0, 6409, 5121, byteBuffer);
                i3++;
            } while (i3 < 3);
        }
    }

    /* renamed from: org.webrtc.RendererCommon$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$org$webrtc$RendererCommon$ScalingType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                org.webrtc.RendererCommon$ScalingType[] r0 = org.webrtc.RendererCommon.ScalingType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                org.webrtc.RendererCommon.AnonymousClass1.$SwitchMap$org$webrtc$RendererCommon$ScalingType = r2
                org.webrtc.RendererCommon$ScalingType r0 = org.webrtc.RendererCommon.ScalingType.SCALE_ASPECT_FIT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                org.webrtc.RendererCommon$ScalingType r0 = org.webrtc.RendererCommon.ScalingType.SCALE_ASPECT_FILL     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                org.webrtc.RendererCommon$ScalingType r0 = org.webrtc.RendererCommon.ScalingType.SCALE_ASPECT_BALANCED     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.webrtc.RendererCommon.AnonymousClass1.<clinit>():void");
        }
    }

    public static void adjustOrigin(float[] fArr) {
        fArr[12] = fArr[12] - ((fArr[0] + fArr[4]) * 0.5f);
        fArr[13] = fArr[13] - ((fArr[1] + fArr[5]) * 0.5f);
        fArr[12] = fArr[12] + 0.5f;
        fArr[13] = fArr[13] + 0.5f;
    }

    public static float[] getLayoutMatrix(boolean z, float f, float f2) {
        float f3;
        float f4;
        if (f2 > f) {
            f4 = f / f2;
            f3 = 1.0f;
        } else {
            f3 = f2 / f;
            f4 = 1.0f;
        }
        if (z) {
            f3 *= -1.0f;
        }
        float[] fArr = new float[16];
        Matrix.setIdentityM(fArr, 0);
        Matrix.scaleM(fArr, 0, f3, f4, 1.0f);
        adjustOrigin(fArr);
        return fArr;
    }

    public static final float[] horizontalFlipMatrix() {
        return new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f};
    }

    public static final float[] identityMatrix() {
        return new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    }

    public static float[] multiplyMatrices(float[] fArr, float[] fArr2) {
        float[] fArr3 = new float[16];
        Matrix.multiplyMM(fArr3, 0, fArr, 0, fArr2, 0);
        return fArr3;
    }

    public static float[] rotateTextureMatrix(float[] fArr, float f) {
        float[] fArr2 = new float[16];
        Matrix.setRotateM(fArr2, 0, f, 0.0f, 0.0f, 1.0f);
        adjustOrigin(fArr2);
        return multiplyMatrices(fArr, fArr2);
    }

    public static final float[] verticalFlipMatrix() {
        return new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f};
    }

    public static float convertScalingTypeToVisibleFraction(ScalingType scalingType) {
        switch (scalingType.ordinal()) {
            case 0:
                return 1.0f;
            case 1:
                return 0.0f;
            case 2:
                return 0.5625f;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static Point getDisplaySize(float f, float f2, int i, int i2) {
        if (f == 0.0f || f2 == 0.0f) {
            return new Point(i, i2);
        }
        return new Point(Math.min(i, Math.round((((float) i2) / f) * f2)), Math.min(i2, Math.round((((float) i) / f) / f2)));
    }

    public static Point getDisplaySize(ScalingType scalingType, float f, int i, int i2) {
        return getDisplaySize(convertScalingTypeToVisibleFraction(scalingType), f, i, i2);
    }
}
