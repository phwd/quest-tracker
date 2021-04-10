package com.facebook.drawee.backends.pipeline.info.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.time.MonotonicClock;
import com.facebook.drawee.backends.pipeline.info.ImagePerfNotifier;
import com.facebook.drawee.backends.pipeline.info.ImagePerfState;
import com.facebook.fresco.ui.common.BaseControllerListener2;
import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.fresco.ui.common.DimensionsInfo;
import com.facebook.fresco.ui.common.OnDrawControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import javax.annotation.Nullable;

public class ImagePerfControllerListener2 extends BaseControllerListener2<ImageInfo> implements OnDrawControllerListener<ImageInfo> {
    private static final String TAG = "ImagePerfControllerListener2";
    private static final int WHAT_STATUS = 1;
    private static final int WHAT_VISIBILITY = 2;
    private final Supplier<Boolean> mAsyncLogging;
    private final MonotonicClock mClock;
    @Nullable
    private Handler mHandler;
    private final ImagePerfNotifier mImagePerfNotifier;
    private final ImagePerfState mImagePerfState;

    /* access modifiers changed from: package-private */
    public static class LogHandler extends Handler {
        private final ImagePerfNotifier mNotifier;

        public LogHandler(@NonNull Looper looper, @NonNull ImagePerfNotifier imagePerfNotifier) {
            super(looper);
            this.mNotifier = imagePerfNotifier;
        }

        public void handleMessage(@NonNull Message message) {
            int i = message.what;
            if (i == 1) {
                this.mNotifier.notifyStatusUpdated((ImagePerfState) message.obj, message.arg1);
            } else if (i == 2) {
                this.mNotifier.notifyListenersOfVisibilityStateUpdate((ImagePerfState) message.obj, message.arg1);
            }
        }
    }

    public ImagePerfControllerListener2(MonotonicClock monotonicClock, ImagePerfState imagePerfState, ImagePerfNotifier imagePerfNotifier, Supplier<Boolean> supplier) {
        this.mClock = monotonicClock;
        this.mImagePerfState = imagePerfState;
        this.mImagePerfNotifier = imagePerfNotifier;
        this.mAsyncLogging = supplier;
    }

    @Override // com.facebook.fresco.ui.common.ControllerListener2, com.facebook.fresco.ui.common.BaseControllerListener2
    public void onSubmit(String str, @Nullable Object obj, @Nullable ControllerListener2.Extras extras) {
        long now = this.mClock.now();
        this.mImagePerfState.resetPointsTimestamps();
        this.mImagePerfState.setControllerSubmitTimeMs(now);
        this.mImagePerfState.setControllerId(str);
        this.mImagePerfState.setCallerContext(obj);
        this.mImagePerfState.setExtraData(extras);
        updateStatus(0);
        reportViewVisible(now);
    }

    public void onIntermediateImageSet(String str, @Nullable ImageInfo imageInfo) {
        this.mImagePerfState.setControllerIntermediateImageSetTimeMs(this.mClock.now());
        this.mImagePerfState.setControllerId(str);
        this.mImagePerfState.setImageInfo(imageInfo);
        updateStatus(2);
    }

    public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable ControllerListener2.Extras extras) {
        long now = this.mClock.now();
        extras.view.size();
        this.mImagePerfState.setExtraData(extras);
        this.mImagePerfState.setControllerFinalImageSetTimeMs(now);
        this.mImagePerfState.setImageRequestEndTimeMs(now);
        this.mImagePerfState.setControllerId(str);
        this.mImagePerfState.setImageInfo(imageInfo);
        updateStatus(3);
    }

    @Override // com.facebook.fresco.ui.common.ControllerListener2, com.facebook.fresco.ui.common.BaseControllerListener2
    public void onFailure(String str, Throwable th, @Nullable ControllerListener2.Extras extras) {
        long now = this.mClock.now();
        this.mImagePerfState.setExtraData(extras);
        this.mImagePerfState.setControllerFailureTimeMs(now);
        this.mImagePerfState.setControllerId(str);
        this.mImagePerfState.setErrorThrowable(th);
        updateStatus(5);
        reportViewInvisible(now);
    }

    @Override // com.facebook.fresco.ui.common.ControllerListener2, com.facebook.fresco.ui.common.BaseControllerListener2
    public void onRelease(String str, ControllerListener2.Extras extras) {
        long now = this.mClock.now();
        this.mImagePerfState.setExtraData(extras);
        int imageLoadStatus = this.mImagePerfState.getImageLoadStatus();
        if (!(imageLoadStatus == 3 || imageLoadStatus == 5 || imageLoadStatus == 6)) {
            this.mImagePerfState.setControllerCancelTimeMs(now);
            this.mImagePerfState.setControllerId(str);
            updateStatus(4);
        }
        reportViewInvisible(now);
    }

    public void onImageDrawn(String str, ImageInfo imageInfo, DimensionsInfo dimensionsInfo) {
        this.mImagePerfState.setImageDrawTimeMs(this.mClock.now());
        this.mImagePerfState.setDimensionsInfo(dimensionsInfo);
        updateStatus(6);
    }

    @VisibleForTesting
    public void reportViewVisible(long j) {
        this.mImagePerfState.setVisible(true);
        this.mImagePerfState.setVisibilityEventTimeMs(j);
        updateVisibility(1);
    }

    @VisibleForTesting
    private void reportViewInvisible(long j) {
        this.mImagePerfState.setVisible(false);
        this.mImagePerfState.setInvisibilityEventTimeMs(j);
        updateVisibility(2);
    }

    private void updateStatus(int i) {
        if (shouldDispatchAsync()) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.arg1 = i;
            obtainMessage.obj = this.mImagePerfState;
            this.mHandler.sendMessage(obtainMessage);
            return;
        }
        this.mImagePerfNotifier.notifyStatusUpdated(this.mImagePerfState, i);
    }

    private void updateVisibility(int i) {
        if (shouldDispatchAsync()) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 2;
            obtainMessage.arg1 = i;
            obtainMessage.obj = this.mImagePerfState;
            this.mHandler.sendMessage(obtainMessage);
            return;
        }
        this.mImagePerfNotifier.notifyListenersOfVisibilityStateUpdate(this.mImagePerfState, i);
    }

    private synchronized void initHandler() {
        if (this.mHandler == null) {
            HandlerThread handlerThread = new HandlerThread("ImagePerfControllerListener2Thread");
            handlerThread.start();
            this.mHandler = new LogHandler(handlerThread.getLooper(), this.mImagePerfNotifier);
        }
    }

    private boolean shouldDispatchAsync() {
        boolean booleanValue = this.mAsyncLogging.get().booleanValue();
        if (booleanValue && this.mHandler == null) {
            initHandler();
        }
        return booleanValue;
    }
}
