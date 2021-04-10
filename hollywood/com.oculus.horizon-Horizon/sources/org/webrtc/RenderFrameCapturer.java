package org.webrtc;

import android.content.Context;
import android.view.Surface;
import java.util.List;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.EglBase14;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoCapturer;

public class RenderFrameCapturer implements SurfaceTextureHelper.OnTextureFrameAvailableListener, VideoCapturer {
    public static EglBase14 rootEglContext;
    public String TAG = "OVRPlatform-RenderFrameCapturer";
    public int frameLoggingTimer = 60;
    public long mNativeRenderFrameCapturerPtr;
    public Surface mSurface;
    public SurfaceTextureHelper mSurfaceTextureHelper;

    public static native void nativeFrameCaptured(long j, int i, float[] fArr, long j2);

    @Override // org.webrtc.VideoCapturer
    public void dispose() {
    }

    @Override // org.webrtc.VideoCapturer
    public List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats() {
        return null;
    }

    @Override // org.webrtc.VideoCapturer
    public void stopCapture() throws InterruptedException {
        this.mSurface = null;
    }

    public static EglBase14.Context CreateRootEGLContext() {
        EglBase14 eglBase14 = new EglBase14(null, EglBase.CONFIG_RECORDABLE);
        rootEglContext = eglBase14;
        return eglBase14.getEglBaseContext();
    }

    @Override // org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener
    public void onTextureFrameAvailable(int i, float[] fArr, long j) {
        int i2 = this.frameLoggingTimer - 1;
        this.frameLoggingTimer = i2;
        if (i2 < 0) {
            this.frameLoggingTimer = 60;
        }
        nativeFrameCaptured(this.mNativeRenderFrameCapturerPtr, i, fArr, j);
    }

    @Override // org.webrtc.VideoCapturer
    public void startCapture(int i, int i2, int i3, Context context, VideoCapturer.CapturerObserver capturerObserver) {
        this.mSurfaceTextureHelper.surfaceTexture.setDefaultBufferSize(i, i2);
        this.mSurface = new Surface(this.mSurfaceTextureHelper.surfaceTexture);
    }

    public RenderFrameCapturer(long j) {
        this.mNativeRenderFrameCapturerPtr = j;
        SurfaceTextureHelper create = SurfaceTextureHelper.create(rootEglContext.getEglBaseContext(), null);
        this.mSurfaceTextureHelper = create;
        create.setListener(this);
    }

    public Surface getCaptureSurface() {
        return this.mSurface;
    }

    @Override // org.webrtc.VideoCapturer
    public SurfaceTextureHelper getSurfaceTextureHelper() {
        return this.mSurfaceTextureHelper;
    }
}
