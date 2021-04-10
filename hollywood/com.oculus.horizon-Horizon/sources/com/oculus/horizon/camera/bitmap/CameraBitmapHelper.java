package com.oculus.horizon.camera.bitmap;

import android.content.Context;
import android.content.res.Resources;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.Dependencies;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class CameraBitmapHelper {
    public static final int BLURRED_BITMAP_BLUR_RADIUS = 18;
    public static final int BLURRED_BITMAP_ITERATIONS = 2;
    public static final int BLURRED_BITMAP_SCALE_FACTOR = 6;
    public final Resources mResources;

    @Inject
    public CameraBitmapHelper(@ForAppContext Context context) {
        this.mResources = context.getResources();
    }
}
