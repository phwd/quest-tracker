package org.webrtc;

import X.AnonymousClass006;
import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.view.WindowManager;
import com.facebook.BuildConfig;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.webrtc.CameraEnumerationAndroid;
import org.webrtc.EglBase;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.ThreadUtils;
import org.webrtc.VideoCapturer;

public class VideoCapturerAndroid implements SurfaceTextureHelper.OnTextureFrameAvailableListener, Camera.PreviewCallback, VideoCapturer {
    public static final int CAMERA_FREEZE_REPORT_TIMOUT_MS = 6000;
    public static final int CAMERA_OBSERVER_PERIOD_MS = 2000;
    public static final int MAX_OPEN_CAMERA_ATTEMPTS = 3;
    public static final int NUMBER_OF_CAPTURE_BUFFERS = 3;
    public static final int OPEN_CAMERA_DELAY_MS = 500;
    public static final String TAG = "VideoCapturerAndroid";
    public Context applicationContext;
    public Camera camera;
    public final Camera.ErrorCallback cameraErrorCallback;
    public final Object cameraIdLock = new Object();
    public final Runnable cameraObserver;
    public final CameraStatistics cameraStatistics;
    public HandlerThread cameraThread;
    public final Handler cameraThreadHandler;
    public CameraEnumerationAndroid.CaptureFormat captureFormat;
    public boolean dropNextFrame;
    public final CameraEventsHandler eventsHandler;
    public boolean firstFrameReported;
    public VideoCapturer.CapturerObserver frameObserver = null;
    public int id;
    public Camera.CameraInfo info;
    public final boolean isCapturingToTexture;
    public int openCameraAttempts;
    public Runnable openCameraOnCodecThreadRunner;
    public volatile boolean pendingCameraSwitch;
    public final Object pendingCameraSwitchLock = new Object();
    public final Set<byte[]> queuedBuffers = new HashSet();
    public int requestedFramerate;
    public int requestedHeight;
    public int requestedWidth;
    public final SurfaceTextureHelper surfaceHelper;

    public interface CameraEventsHandler {
        void onCameraClosed();

        void onCameraError(String str);

        void onCameraFreezed(String str);

        void onCameraOpening(int i);

        void onFirstFrameAvailable();
    }

    public static class CameraStatistics {
        public int frameCount = 0;
        public final ThreadUtils.ThreadChecker threadChecker;

        public void addFrame() {
            this.threadChecker.checkIsOnValidThread();
            this.frameCount++;
        }

        public int getAndResetFrameCount() {
            this.threadChecker.checkIsOnValidThread();
            int i = this.frameCount;
            this.frameCount = 0;
            return i;
        }

        public CameraStatistics() {
            ThreadUtils.ThreadChecker threadChecker2 = new ThreadUtils.ThreadChecker();
            this.threadChecker = threadChecker2;
            threadChecker2.thread = null;
        }
    }

    public interface CameraSwitchHandler {
        void onCameraSwitchDone(boolean z);

        void onCameraSwitchError(String str);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void switchCameraOnCameraThread() {
        checkIsOnCameraThread();
        Logging.d(TAG, "switchCameraOnCameraThread");
        stopCaptureOnCameraThread();
        synchronized (this.cameraIdLock) {
            this.id = (this.id + 1) % Camera.getNumberOfCameras();
        }
        this.dropNextFrame = true;
        startCaptureOnCameraThread(this.requestedWidth, this.requestedHeight, this.requestedFramerate, this.frameObserver, this.applicationContext);
        Logging.d(TAG, "switchCameraOnCameraThread done");
    }

    @Override // org.webrtc.SurfaceTextureHelper.OnTextureFrameAvailableListener
    public void onTextureFrameAvailable(int i, float[] fArr, long j) {
        float[] fArr2 = fArr;
        checkIsOnCameraThread();
        if (this.camera == null) {
            this.surfaceHelper.returnTextureFrame();
        } else if (this.dropNextFrame) {
            this.surfaceHelper.returnTextureFrame();
            this.dropNextFrame = false;
        } else {
            CameraEventsHandler cameraEventsHandler = this.eventsHandler;
            if (cameraEventsHandler != null && !this.firstFrameReported) {
                cameraEventsHandler.onFirstFrameAvailable();
                this.firstFrameReported = true;
            }
            int frameOrientation = getFrameOrientation();
            if (this.info.facing == 1) {
                fArr2 = RendererCommon.multiplyMatrices(fArr, RendererCommon.horizontalFlipMatrix());
            }
            this.cameraStatistics.addFrame();
            VideoCapturer.CapturerObserver capturerObserver = this.frameObserver;
            CameraEnumerationAndroid.CaptureFormat captureFormat2 = this.captureFormat;
            capturerObserver.onTextureFrameCaptured(captureFormat2.width, captureFormat2.height, i, fArr2, frameOrientation, j);
        }
    }

    private int getDeviceOrientation() {
        int rotation = ((WindowManager) this.applicationContext.getSystemService("window")).getDefaultDisplay().getRotation();
        if (rotation == 1) {
            return 90;
        }
        if (rotation == 2) {
            return 180;
        }
        if (rotation != 3) {
            return 0;
        }
        return 270;
    }

    public static int lookupDeviceName(String str) {
        Logging.d(TAG, AnonymousClass006.A05("lookupDeviceName: ", str));
        if (!(str == null || Camera.getNumberOfCameras() == 0)) {
            int i = 0;
            if (!str.isEmpty()) {
                while (i < Camera.getNumberOfCameras()) {
                    if (!str.equals(CameraEnumerationAndroid.getDeviceName(i))) {
                        i++;
                    }
                }
            }
            return i;
        }
        return -1;
    }

    public void changeCaptureFormat(final int i, final int i2, final int i3) {
        this.cameraThreadHandler.post(new Runnable() {
            /* class org.webrtc.VideoCapturerAndroid.AnonymousClass5 */

            public void run() {
                VideoCapturerAndroid.this.startPreviewOnCameraThread(i, i2, i3);
            }
        });
    }

    @Override // org.webrtc.VideoCapturer
    public void dispose() {
        Logging.d(TAG, BuildConfig.BUILD_TYPE);
        if (!isDisposed()) {
            ThreadUtils.invokeUninterruptibly(this.cameraThreadHandler, new Runnable() {
                /* class org.webrtc.VideoCapturerAndroid.AnonymousClass6 */

                public void run() {
                    if (VideoCapturerAndroid.this.camera != null) {
                        throw new IllegalStateException("Release called while camera is running");
                    }
                }
            });
            this.surfaceHelper.disconnect(this.cameraThreadHandler);
            this.cameraThread = null;
            return;
        }
        throw new IllegalStateException("Already released");
    }

    public int getCurrentCameraId() {
        int i;
        synchronized (this.cameraIdLock) {
            i = this.id;
        }
        return i;
    }

    public boolean isDisposed() {
        if (this.cameraThread == null) {
            return true;
        }
        return false;
    }

    public void onOutputFormatRequest(final int i, final int i2, final int i3) {
        this.cameraThreadHandler.post(new Runnable() {
            /* class org.webrtc.VideoCapturerAndroid.AnonymousClass4 */

            public void run() {
                VideoCapturerAndroid.this.onOutputFormatRequestOnCameraThread(i, i2, i3);
            }
        });
    }

    public void printStackTrace() {
        StackTraceElement[] stackTrace;
        int length;
        HandlerThread handlerThread = this.cameraThread;
        if (handlerThread != null && (length = (stackTrace = handlerThread.getStackTrace()).length) > 0) {
            Logging.d(TAG, "VideoCapturerAndroid stacks trace:");
            for (int i = 0; i < length; i++) {
                Logging.d(TAG, stackTrace[i].toString());
            }
        }
    }

    @Override // org.webrtc.VideoCapturer
    public void startCapture(final int i, final int i2, final int i3, final Context context, final VideoCapturer.CapturerObserver capturerObserver) {
        String str;
        StringBuilder sb = new StringBuilder("startCapture requested: ");
        sb.append(i);
        sb.append("x");
        sb.append(i2);
        sb.append("@");
        sb.append(i3);
        Logging.d(TAG, sb.toString());
        if (context == null) {
            str = "applicationContext not set.";
        } else if (capturerObserver != null) {
            this.cameraThreadHandler.post(new Runnable() {
                /* class org.webrtc.VideoCapturerAndroid.AnonymousClass7 */

                public void run() {
                    VideoCapturerAndroid.this.startCaptureOnCameraThread(i, i2, i3, capturerObserver, context);
                }
            });
            return;
        } else {
            str = "frameObserver not set.";
        }
        throw new RuntimeException(str);
    }

    @Override // org.webrtc.VideoCapturer
    public void stopCapture() throws InterruptedException {
        Logging.d(TAG, "stopCapture");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.cameraThreadHandler.post(new Runnable() {
            /* class org.webrtc.VideoCapturerAndroid.AnonymousClass9 */

            public void run() {
                VideoCapturerAndroid.this.stopCaptureOnCameraThread();
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        Logging.d(TAG, "stopCapture done");
    }

    public VideoCapturerAndroid(int i, CameraEventsHandler cameraEventsHandler, EglBase.Context context) {
        boolean z = false;
        this.dropNextFrame = false;
        this.cameraErrorCallback = new Camera.ErrorCallback() {
            /* class org.webrtc.VideoCapturerAndroid.AnonymousClass1 */

            public void onError(int i, Camera camera) {
                String A01;
                if (i == 100) {
                    A01 = "Camera server died!";
                } else {
                    A01 = AnonymousClass006.A01("Camera error: ", i);
                }
                Logging.e(VideoCapturerAndroid.TAG, A01);
                CameraEventsHandler cameraEventsHandler = VideoCapturerAndroid.this.eventsHandler;
                if (cameraEventsHandler != null) {
                    cameraEventsHandler.onCameraError(A01);
                }
            }
        };
        this.cameraObserver = new Runnable() {
            /* class org.webrtc.VideoCapturerAndroid.AnonymousClass2 */
            public int freezePeriodCount;

            public void run() {
                String str;
                int andResetFrameCount = VideoCapturerAndroid.this.cameraStatistics.getAndResetFrameCount();
                Logging.d(VideoCapturerAndroid.TAG, AnonymousClass006.A02("Camera fps: ", ((andResetFrameCount * 1000) + 1000) / 2000, "."));
                if (andResetFrameCount == 0) {
                    int i = this.freezePeriodCount + 1;
                    this.freezePeriodCount = i;
                    if (i * 2000 > 6000 && VideoCapturerAndroid.this.eventsHandler != null) {
                        Logging.e(VideoCapturerAndroid.TAG, "Camera freezed.");
                        boolean z = VideoCapturerAndroid.this.surfaceHelper.isTextureInUse;
                        CameraEventsHandler cameraEventsHandler = VideoCapturerAndroid.this.eventsHandler;
                        if (z) {
                            str = "Camera failure. Client must return video buffers.";
                        } else {
                            str = "Camera failure.";
                        }
                        cameraEventsHandler.onCameraFreezed(str);
                        return;
                    }
                } else {
                    this.freezePeriodCount = 0;
                }
                VideoCapturerAndroid.this.cameraThreadHandler.postDelayed(this, 2000);
            }
        };
        this.id = i;
        this.eventsHandler = cameraEventsHandler;
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.cameraThread = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(this.cameraThread.getLooper());
        this.cameraThreadHandler = handler;
        this.isCapturingToTexture = context != null ? true : z;
        this.cameraStatistics = new CameraStatistics();
        SurfaceTextureHelper create = SurfaceTextureHelper.create(context, handler);
        this.surfaceHelper = create;
        if (this.isCapturingToTexture) {
            create.setListener(this);
        }
        Logging.d(TAG, AnonymousClass006.A0C("VideoCapturerAndroid isCapturingToTexture : ", this.isCapturingToTexture));
    }

    private void checkIsOnCameraThread() {
        if (Thread.currentThread() != this.cameraThread) {
            throw new IllegalStateException("Wrong thread");
        }
    }

    private int getFrameOrientation() {
        int deviceOrientation = getDeviceOrientation();
        Camera.CameraInfo cameraInfo = this.info;
        if (cameraInfo.facing == 0) {
            deviceOrientation = 360 - deviceOrientation;
        }
        return (cameraInfo.orientation + deviceOrientation) % 360;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onOutputFormatRequestOnCameraThread(int i, int i2, int i3) {
        checkIsOnCameraThread();
        if (this.camera == null) {
            Logging.e(TAG, "Calling onOutputFormatRequest() on stopped camera.");
            return;
        }
        StringBuilder sb = new StringBuilder("onOutputFormatRequestOnCameraThread: ");
        sb.append(i);
        sb.append("x");
        sb.append(i2);
        sb.append("@");
        sb.append(i3);
        Logging.d(TAG, sb.toString());
        this.frameObserver.onOutputFormatRequest(i, i2, i3);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void startCaptureOnCameraThread(final int i, final int i2, final int i3, final VideoCapturer.CapturerObserver capturerObserver, final Context context) {
        checkIsOnCameraThread();
        if (this.camera == null) {
            this.applicationContext = context;
            this.frameObserver = capturerObserver;
            this.firstFrameReported = false;
            try {
                synchronized (this.cameraIdLock) {
                    Logging.d(TAG, AnonymousClass006.A01("Opening camera ", this.id));
                    CameraEventsHandler cameraEventsHandler = this.eventsHandler;
                    if (cameraEventsHandler != null) {
                        cameraEventsHandler.onCameraOpening(this.id);
                    }
                    this.camera = Camera.open(this.id);
                    Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                    this.info = cameraInfo;
                    Camera.getCameraInfo(this.id, cameraInfo);
                }
                try {
                    this.camera.setPreviewTexture(this.surfaceHelper.surfaceTexture);
                    try {
                        Logging.d(TAG, AnonymousClass006.A03("Camera orientation: ", this.info.orientation, " .Device orientation: ", getDeviceOrientation()));
                        this.camera.setErrorCallback(this.cameraErrorCallback);
                        startPreviewOnCameraThread(i, i2, i3);
                        capturerObserver.onCapturerStarted(true);
                        this.cameraThreadHandler.postDelayed(this.cameraObserver, 2000);
                    } catch (RuntimeException e) {
                        Logging.e(TAG, "startCapture failed", e);
                        stopCaptureOnCameraThread();
                        capturerObserver.onCapturerStarted(false);
                        CameraEventsHandler cameraEventsHandler2 = this.eventsHandler;
                        if (cameraEventsHandler2 != null) {
                            cameraEventsHandler2.onCameraError("Camera can not be started.");
                        }
                    }
                } catch (IOException e2) {
                    Logging.e(TAG, "setPreviewTexture failed", null);
                    throw new RuntimeException(e2);
                }
            } catch (RuntimeException e3) {
                int i4 = this.openCameraAttempts + 1;
                this.openCameraAttempts = i4;
                if (i4 < 3) {
                    Logging.e(TAG, "Camera.open failed, retrying", e3);
                    AnonymousClass8 r6 = new Runnable() {
                        /* class org.webrtc.VideoCapturerAndroid.AnonymousClass8 */

                        public void run() {
                            VideoCapturerAndroid.this.startCaptureOnCameraThread(i, i2, i3, capturerObserver, context);
                        }
                    };
                    this.openCameraOnCodecThreadRunner = r6;
                    this.cameraThreadHandler.postDelayed(r6, 500);
                    return;
                }
                this.openCameraAttempts = 0;
                throw e3;
            }
        } else {
            throw new RuntimeException("Camera has already been started.");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void startPreviewOnCameraThread(int i, int i2, int i3) {
        checkIsOnCameraThread();
        StringBuilder sb = new StringBuilder("startPreviewOnCameraThread requested: ");
        sb.append(i);
        sb.append("x");
        sb.append(i2);
        sb.append("@");
        sb.append(i3);
        Logging.d(TAG, sb.toString());
        Camera camera2 = this.camera;
        if (camera2 == null) {
            Logging.e(TAG, "Calling startPreviewOnCameraThread on stopped camera.");
            return;
        }
        this.requestedWidth = i;
        this.requestedHeight = i2;
        this.requestedFramerate = i3;
        Camera.Parameters parameters = camera2.getParameters();
        int[] framerateRange = CameraEnumerationAndroid.getFramerateRange(parameters, i3 * 1000);
        Camera.Size closestSupportedSize = CameraEnumerationAndroid.getClosestSupportedSize(parameters.getSupportedPreviewSizes(), i, i2);
        int i4 = 0;
        CameraEnumerationAndroid.CaptureFormat captureFormat2 = new CameraEnumerationAndroid.CaptureFormat(closestSupportedSize.width, closestSupportedSize.height, framerateRange[0], framerateRange[1]);
        if (!captureFormat2.isSameFormat(this.captureFormat)) {
            Logging.d(TAG, AnonymousClass006.A0C("isVideoStabilizationSupported: ", parameters.isVideoStabilizationSupported()));
            if (parameters.isVideoStabilizationSupported()) {
                parameters.setVideoStabilization(true);
            }
            int i5 = captureFormat2.maxFramerate;
            if (i5 > 0) {
                parameters.setPreviewFpsRange(captureFormat2.minFramerate, i5);
            }
            parameters.setPreviewSize(captureFormat2.width, captureFormat2.height);
            if (!this.isCapturingToTexture) {
                parameters.setPreviewFormat(17);
            }
            Camera.Size closestSupportedSize2 = CameraEnumerationAndroid.getClosestSupportedSize(parameters.getSupportedPictureSizes(), i, i2);
            parameters.setPictureSize(closestSupportedSize2.width, closestSupportedSize2.height);
            if (this.captureFormat != null) {
                this.camera.stopPreview();
                this.dropNextFrame = true;
                this.camera.setPreviewCallbackWithBuffer(null);
            }
            StringBuilder sb2 = new StringBuilder("Start capturing: ");
            sb2.append(captureFormat2);
            Logging.d(TAG, sb2.toString());
            this.captureFormat = captureFormat2;
            if (parameters.getSupportedFocusModes().contains("continuous-video")) {
                parameters.setFocusMode("continuous-video");
            }
            this.camera.setParameters(parameters);
            if (!this.isCapturingToTexture) {
                this.queuedBuffers.clear();
                int frameSize = captureFormat2.frameSize();
                do {
                    ByteBuffer allocateDirect = ByteBuffer.allocateDirect(frameSize);
                    this.queuedBuffers.add(allocateDirect.array());
                    this.camera.addCallbackBuffer(allocateDirect.array());
                    i4++;
                } while (i4 < 3);
                this.camera.setPreviewCallbackWithBuffer(this);
            }
            this.camera.startPreview();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void stopCaptureOnCameraThread() {
        checkIsOnCameraThread();
        Logging.d(TAG, "stopCaptureOnCameraThread");
        Runnable runnable = this.openCameraOnCodecThreadRunner;
        if (runnable != null) {
            this.cameraThreadHandler.removeCallbacks(runnable);
        }
        this.openCameraAttempts = 0;
        if (this.camera == null) {
            Logging.e(TAG, "Calling stopCapture() for already stopped camera.");
            return;
        }
        this.cameraThreadHandler.removeCallbacks(this.cameraObserver);
        this.cameraStatistics.getAndResetFrameCount();
        Logging.d(TAG, "Stop preview.");
        this.camera.stopPreview();
        this.camera.setPreviewCallbackWithBuffer(null);
        this.queuedBuffers.clear();
        this.captureFormat = null;
        Logging.d(TAG, "Release camera.");
        this.camera.release();
        this.camera = null;
        CameraEventsHandler cameraEventsHandler = this.eventsHandler;
        if (cameraEventsHandler != null) {
            cameraEventsHandler.onCameraClosed();
        }
    }

    public Handler getCameraThreadHandler() {
        return this.cameraThreadHandler;
    }

    @Override // org.webrtc.VideoCapturer
    public List<CameraEnumerationAndroid.CaptureFormat> getSupportedFormats() {
        return CameraEnumerationAndroid.getSupportedFormats(getCurrentCameraId());
    }

    @Override // org.webrtc.VideoCapturer
    public SurfaceTextureHelper getSurfaceTextureHelper() {
        return this.surfaceHelper;
    }

    public boolean isCapturingToTexture() {
        return this.isCapturingToTexture;
    }

    public void onPreviewFrame(byte[] bArr, Camera camera2) {
        checkIsOnCameraThread();
        if (this.camera != null && this.queuedBuffers.contains(bArr)) {
            if (this.camera == camera2) {
                long nanos = TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
                CameraEventsHandler cameraEventsHandler = this.eventsHandler;
                if (cameraEventsHandler != null && !this.firstFrameReported) {
                    cameraEventsHandler.onFirstFrameAvailable();
                    this.firstFrameReported = true;
                }
                this.cameraStatistics.addFrame();
                VideoCapturer.CapturerObserver capturerObserver = this.frameObserver;
                CameraEnumerationAndroid.CaptureFormat captureFormat2 = this.captureFormat;
                capturerObserver.onByteBufferFrameCaptured(bArr, captureFormat2.width, captureFormat2.height, getFrameOrientation(), nanos);
                this.camera.addCallbackBuffer(bArr);
                return;
            }
            throw new RuntimeException("Unexpected camera in callback!");
        }
    }

    public void switchCamera(final CameraSwitchHandler cameraSwitchHandler) {
        if (Camera.getNumberOfCameras() >= 2) {
            synchronized (this.pendingCameraSwitchLock) {
                if (this.pendingCameraSwitch) {
                    Logging.w(TAG, "Ignoring camera switch request.");
                    if (cameraSwitchHandler != null) {
                        cameraSwitchHandler.onCameraSwitchError("Pending camera switch already in progress.");
                    }
                    return;
                }
                this.pendingCameraSwitch = true;
                this.cameraThreadHandler.post(new Runnable() {
                    /* class org.webrtc.VideoCapturerAndroid.AnonymousClass3 */

                    public void run() {
                        boolean z;
                        VideoCapturerAndroid videoCapturerAndroid = VideoCapturerAndroid.this;
                        if (videoCapturerAndroid.camera == null) {
                            CameraSwitchHandler cameraSwitchHandler = cameraSwitchHandler;
                            if (cameraSwitchHandler != null) {
                                cameraSwitchHandler.onCameraSwitchError("Camera is stopped.");
                                return;
                            }
                            return;
                        }
                        videoCapturerAndroid.switchCameraOnCameraThread();
                        synchronized (VideoCapturerAndroid.this.pendingCameraSwitchLock) {
                            z = false;
                            VideoCapturerAndroid.this.pendingCameraSwitch = false;
                        }
                        CameraSwitchHandler cameraSwitchHandler2 = cameraSwitchHandler;
                        if (cameraSwitchHandler2 != null) {
                            if (VideoCapturerAndroid.this.info.facing == 1) {
                                z = true;
                            }
                            cameraSwitchHandler2.onCameraSwitchDone(z);
                        }
                    }
                });
            }
        } else if (cameraSwitchHandler != null) {
            cameraSwitchHandler.onCameraSwitchError("No camera to switch to.");
        }
    }

    public static VideoCapturerAndroid create(String str, CameraEventsHandler cameraEventsHandler) {
        return create(str, cameraEventsHandler, null);
    }

    public static VideoCapturerAndroid create(String str, CameraEventsHandler cameraEventsHandler, EglBase.Context context) {
        int lookupDeviceName = lookupDeviceName(str);
        if (lookupDeviceName == -1) {
            return null;
        }
        return new VideoCapturerAndroid(lookupDeviceName, cameraEventsHandler, context);
    }
}
