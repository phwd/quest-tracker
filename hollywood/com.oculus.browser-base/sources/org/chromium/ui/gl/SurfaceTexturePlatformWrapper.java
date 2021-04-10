package org.chromium.ui.gl;

import android.graphics.SurfaceTexture;
import android.util.Log;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SurfaceTexturePlatformWrapper {
    public static void attachToGLContext(SurfaceTexture surfaceTexture, int i) {
        surfaceTexture.attachToGLContext(i);
    }

    public static SurfaceTexture create(int i) {
        return new SurfaceTexture(i);
    }

    public static void destroy(SurfaceTexture surfaceTexture) {
        surfaceTexture.setOnFrameAvailableListener(null);
        surfaceTexture.release();
    }

    public static void detachFromGLContext(SurfaceTexture surfaceTexture) {
        surfaceTexture.detachFromGLContext();
    }

    public static void getTransformMatrix(SurfaceTexture surfaceTexture, float[] fArr) {
        surfaceTexture.getTransformMatrix(fArr);
    }

    public static void release(SurfaceTexture surfaceTexture) {
        surfaceTexture.release();
    }

    public static void setDefaultBufferSize(SurfaceTexture surfaceTexture, int i, int i2) {
        surfaceTexture.setDefaultBufferSize(i, i2);
    }

    public static void setFrameAvailableCallback(SurfaceTexture surfaceTexture, long j) {
        surfaceTexture.setOnFrameAvailableListener(new C2122d41(j));
    }

    public static void updateTexImage(SurfaceTexture surfaceTexture) {
        try {
            surfaceTexture.updateTexImage();
        } catch (RuntimeException e) {
            Log.e("SurfaceTexturePlatformWrapper", "Error calling updateTexImage", e);
        }
    }
}
