package com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters;

import android.graphics.drawable.Animatable;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import javax.annotation.Nullable;

public class FrescoControllerListener extends BaseControllerListener<ImageInfo> {
    private FrescoImageReadyCallback mCallback;

    public interface FrescoImageReadyCallback {
        void onImageReady();
    }

    public FrescoControllerListener(FrescoImageReadyCallback frescoImageReadyCallback) {
        this.mCallback = frescoImageReadyCallback;
    }

    public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
        FrescoImageReadyCallback frescoImageReadyCallback = this.mCallback;
        if (frescoImageReadyCallback != null) {
            frescoImageReadyCallback.onImageReady();
        }
    }
}
