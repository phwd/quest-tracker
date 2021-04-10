package com.oculus.video;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.Log;
import android.view.Surface;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;

public class OculusVideoPlayer {
    private static final String TAG = "OculusVideoPlayer";
    private EventListener currentEventListener;
    private volatile int currentPlayerState;
    private final ArrayList<OESTextureCopier> discardedTextureCopiers = new ArrayList<>();
    private long instancePtr;
    private long jniVideoSurfaceToReload;
    private boolean needReloadCopyVideoSurface;
    private OesSurfaceManager videoSurfaceManager;

    public interface EventListener {
        void onAnalyticsEvent(String str);

        void onFrameSizeChanged(int i, int i2, float f, int i3, int i4, int i5, int i6);

        void onPlayerSelected();

        void onStateChanged(int i, int i2);

        void onVideoReset(String str);

        void onVideoUpdated(String str, int i);
    }

    private static native long nativeCreateInstance(Context context, OculusVideoPlayer oculusVideoPlayer);

    private static native void nativeDestroyInstance(long j);

    private native long nativeGetBufferedPosition(long j);

    private native float nativeGetComputedFrameRate(long j);

    private native boolean nativeGetDisableAnalyticsVideoSourceRedaction(long j);

    private native long nativeGetEpochTimePosition(long j);

    private native long nativeGetPosition(long j);

    private native int nativeGetState(long j);

    private native float nativeGetVolume(long j);

    private native boolean nativeIsAudioVirtualizationEnabled(long j);

    private native boolean nativeIsPlaybackBuffering(long j);

    private native void nativeOnRender(long j, double d);

    private native boolean nativePauseVideo(long j);

    private native boolean nativeResumeVideo(long j);

    private native void nativeSetAudioVirtualizationEnabled(long j, boolean z);

    private native void nativeSetDisableAnalyticsVideoSourceRedaction(long j, boolean z);

    private native boolean nativeSetEventListener(long j, EventListener eventListener);

    private native boolean nativeSetJniVideoSurface(long j, long j2);

    private native boolean nativeSetPosition(long j, int i);

    private native boolean nativeSetVolume(long j, float f);

    private native boolean nativeStartVideo(long j, String str);

    private native boolean nativeStopVideo(long j);

    private native void nativeUpdateFrame(long j, double d, float f, float f2, float f3, float f4, float f5, float f6, float f7);

    public OculusVideoPlayer(Context context) {
        this.instancePtr = nativeCreateInstance(context, this);
    }

    public long getInstancePtr() {
        return this.instancePtr;
    }

    public void release() {
        clearVideoSurface();
        nativeDestroyInstance(this.instancePtr);
        this.instancePtr = 0;
    }

    public void finalize() {
        release();
    }

    public void setEventListener(EventListener eventListener) {
        this.currentEventListener = eventListener;
    }

    /* access modifiers changed from: package-private */
    public void onStateChanged(int i, int i2) {
        this.currentPlayerState = i;
        if (i == 0 && i2 != 0) {
            reloadVideoSurfaceOnPlayerClose();
        }
        EventListener eventListener = this.currentEventListener;
        if (eventListener != null) {
            eventListener.onStateChanged(i, i2);
        }
    }

    /* access modifiers changed from: package-private */
    public void onVideoUpdated(String str, int i) {
        EventListener eventListener = this.currentEventListener;
        if (eventListener != null) {
            eventListener.onVideoUpdated(str, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void onVideoReset(String str) {
        EventListener eventListener = this.currentEventListener;
        if (eventListener != null) {
            eventListener.onVideoReset(str);
        }
    }

    /* access modifiers changed from: package-private */
    public void onPlayerSelected() {
        EventListener eventListener = this.currentEventListener;
        if (eventListener != null) {
            eventListener.onPlayerSelected();
        }
    }

    /* access modifiers changed from: package-private */
    public void onAnalyticsEvent(String str) {
        EventListener eventListener = this.currentEventListener;
        if (eventListener != null) {
            eventListener.onAnalyticsEvent(str);
        }
    }

    /* access modifiers changed from: package-private */
    public void onFrameSizeChanged(int i, int i2, float f, int i3, int i4, int i5, int i6) {
        EventListener eventListener = this.currentEventListener;
        if (eventListener != null) {
            eventListener.onFrameSizeChanged(i, i2, f, i3, i4, i5, i6);
        }
    }

    public boolean loadJniVideoSurface(long j) {
        this.jniVideoSurfaceToReload = 0;
        if (this.currentPlayerState == 0) {
            return nativeSetJniVideoSurface(this.instancePtr, j);
        }
        this.jniVideoSurfaceToReload = j;
        this.needReloadCopyVideoSurface = false;
        return true;
    }

    public void updateFrame(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        nativeUpdateFrame(this.instancePtr, ((double) System.nanoTime()) * 1.0E-9d, f, f2, f3, f4, f5, f6, f7);
    }

    public void updateJniVideoSurface() {
        nativeOnRender(this.instancePtr, ((double) System.nanoTime()) * 1.0E-9d);
    }

    public boolean startVideo(Video video) {
        return video != null && nativeStartVideo(this.instancePtr, video.toString());
    }

    public boolean stopVideo() {
        return nativeStopVideo(this.instancePtr);
    }

    public boolean pauseVideo() {
        return nativePauseVideo(this.instancePtr);
    }

    public boolean resumeVideo() {
        return nativeResumeVideo(this.instancePtr);
    }

    public long getPosition() {
        return nativeGetPosition(this.instancePtr);
    }

    public boolean setPosition(int i) {
        return nativeSetPosition(this.instancePtr, i);
    }

    public boolean isPlaybackBuffering() {
        return nativeIsPlaybackBuffering(this.instancePtr);
    }

    public float getComputedFrameRate() {
        return nativeGetComputedFrameRate(this.instancePtr);
    }

    public float getVolume() {
        return nativeGetVolume(this.instancePtr);
    }

    public boolean setVolume(float f) {
        return nativeSetVolume(this.instancePtr, f);
    }

    public long getEpochTimePosition() {
        return nativeGetEpochTimePosition(this.instancePtr);
    }

    public long getBufferedPosition() {
        return nativeGetBufferedPosition(this.instancePtr);
    }

    public int getState() {
        return nativeGetState(this.instancePtr);
    }

    public boolean isAudioVirtualizationEnabled() {
        return nativeIsAudioVirtualizationEnabled(this.instancePtr);
    }

    public void setAudioVirtualizationEnabled(boolean z) {
        nativeSetAudioVirtualizationEnabled(this.instancePtr, z);
    }

    public boolean getDisableAnalyticsVideoSourceRedaction() {
        return nativeGetDisableAnalyticsVideoSourceRedaction(this.instancePtr);
    }

    public void setDisableAnalyticsVideoSourceRedaction(boolean z) {
        nativeSetDisableAnalyticsVideoSourceRedaction(this.instancePtr, z);
    }

    public boolean startVideoJson(String str) {
        Video video;
        try {
            video = new Video(str);
        } catch (JSONException e) {
            Log.d(TAG, "No video created");
            Log.d(TAG, Log.getStackTraceString(e));
            video = null;
        }
        return startVideo(video);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
        return false;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean loadVideoSurface() {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            int r1 = r5.currentPlayerState     // Catch:{ Exception -> 0x001f, all -> 0x001c }
            r2 = 1
            if (r1 != 0) goto L_0x0014
            r5.releaseVideoSurfaceManager()     // Catch:{ Exception -> 0x001f, all -> 0x001c }
            com.oculus.video.OculusVideoPlayer$OesSurfaceManager r1 = new com.oculus.video.OculusVideoPlayer$OesSurfaceManager     // Catch:{ Exception -> 0x001f, all -> 0x001c }
            r1.<init>()     // Catch:{ Exception -> 0x001f, all -> 0x001c }
            r5.videoSurfaceManager = r1     // Catch:{ Exception -> 0x001f, all -> 0x001c }
            r5.needReloadCopyVideoSurface = r0     // Catch:{ Exception -> 0x001f, all -> 0x001c }
            goto L_0x001a
        L_0x0014:
            r3 = 0
            r5.jniVideoSurfaceToReload = r3     // Catch:{ Exception -> 0x001f, all -> 0x001c }
            r5.needReloadCopyVideoSurface = r2     // Catch:{ Exception -> 0x001f, all -> 0x001c }
        L_0x001a:
            monitor-exit(r5)
            return r2
        L_0x001c:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        L_0x001f:
            monitor-exit(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.OculusVideoPlayer.loadVideoSurface():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0010, code lost:
        return false;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean clearVideoSurface() {
        /*
            r1 = this;
            monitor-enter(r1)
            int r0 = r1.currentPlayerState     // Catch:{ Exception -> 0x000e, all -> 0x000b }
            if (r0 != 0) goto L_0x0008
            r1.releaseVideoSurfaceManager()     // Catch:{ Exception -> 0x000e, all -> 0x000b }
        L_0x0008:
            r0 = 1
            monitor-exit(r1)
            return r0
        L_0x000b:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        L_0x000e:
            r0 = 0
            monitor-exit(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.OculusVideoPlayer.clearVideoSurface():boolean");
    }

    public synchronized void updateVideoTexture(int i, int i2, int i3, boolean z) {
        if (this.videoSurfaceManager != null) {
            this.videoSurfaceManager.loadCopyTargetTexture(i, i2, i3, z);
            nativeOnRender(this.instancePtr, ((double) System.nanoTime()) * 1.0E-9d);
        }
    }

    public synchronized void executeTextureCopy() {
        if (this.videoSurfaceManager != null) {
            this.videoSurfaceManager.copyToTexture();
        }
    }

    private synchronized void releaseVideoSurfaceManager() {
        if (this.videoSurfaceManager != null) {
            this.videoSurfaceManager.release();
            this.videoSurfaceManager = null;
        }
    }

    private synchronized void reloadVideoSurfaceOnPlayerClose() {
        releaseVideoSurfaceManager();
        if (this.jniVideoSurfaceToReload != 0) {
            loadJniVideoSurface(this.jniVideoSurfaceToReload);
        } else if (this.needReloadCopyVideoSurface) {
            loadVideoSurface();
        }
    }

    /* access modifiers changed from: private */
    public class OesSurfaceManager {
        private boolean mCopyTargetCreateMipmap;
        private int mCopyTargetHeight;
        private int mCopyTargetTexture;
        private int mCopyTargetWidth;
        private Surface mOesSurface;
        private SurfaceTexture mOesSurfaceTexture = new SurfaceTexture(0);
        private OESTextureCopier mOesTextureCopier;

        private native boolean nativeSetVideoSurface(long j, Surface surface);

        public OesSurfaceManager() {
            this.mOesSurfaceTexture.detachFromGLContext();
            this.mOesSurface = new Surface(this.mOesSurfaceTexture);
            if (!nativeSetVideoSurface(OculusVideoPlayer.this.instancePtr, this.mOesSurface)) {
                throw new RuntimeException("OesSurfaceManager creation failed");
            }
        }

        public void release() {
            nativeSetVideoSurface(OculusVideoPlayer.this.instancePtr, null);
            this.mOesSurface.release();
            this.mOesSurface = null;
            this.mOesSurfaceTexture.release();
            this.mOesSurfaceTexture = null;
            if (this.mOesTextureCopier != null) {
                OculusVideoPlayer.this.discardedTextureCopiers.add(this.mOesTextureCopier);
            }
            this.mOesTextureCopier = null;
        }

        public void loadCopyTargetTexture(int i, int i2, int i3, boolean z) {
            this.mCopyTargetTexture = i;
            this.mCopyTargetWidth = i2;
            this.mCopyTargetHeight = i3;
            this.mCopyTargetCreateMipmap = z;
        }

        public long copyToTexture() {
            if (this.mOesSurfaceTexture == null || this.mCopyTargetTexture == 0 || this.mCopyTargetWidth == 0 || this.mCopyTargetHeight == 0) {
                return -1;
            }
            Iterator it = OculusVideoPlayer.this.discardedTextureCopiers.iterator();
            while (it.hasNext()) {
                ((OESTextureCopier) it.next()).release();
            }
            OculusVideoPlayer.this.discardedTextureCopiers.clear();
            if (this.mOesTextureCopier == null) {
                this.mOesTextureCopier = new OESTextureCopier();
                String str = OculusVideoPlayer.TAG;
                Log.d(str, "oesTextureID=" + this.mOesTextureCopier.getOesTexture());
                this.mOesSurfaceTexture.attachToGLContext(this.mOesTextureCopier.getOesTexture());
            }
            this.mOesSurfaceTexture.updateTexImage();
            long timestamp = this.mOesSurfaceTexture.getTimestamp();
            this.mOesTextureCopier.copyTexture(this.mCopyTargetTexture, timestamp, this.mCopyTargetWidth, this.mCopyTargetHeight, this.mCopyTargetCreateMipmap);
            return timestamp;
        }
    }
}
