package com.oculus.systemdialog;

import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogHeroSpace {
    public static final String DIALOG_HERO_ASPECT_RATIO_KEY = "aspectRatio";
    public static final String DIALOG_HERO_BACKGROUND_COLOR_KEY = "backgroundColor";
    public static final String DIALOG_HERO_IMAGE_SOURCE_URL_KEY = "imageSourceUrl";
    public static final String DIALOG_HERO_VIDEO_AUTO_LOOPS_KEY = "videoAutoLoops";
    public static final String DIALOG_HERO_VIDEO_SOURCE_URL_KEY = "videoSourceUrl";
    public static final String TAG = LoggingUtil.tag(DialogHeroSpace.class);
    public final float mAspectRatio;
    public final String mBackgroundColor;
    public final String mImageSourceUrl;
    public boolean mVideoAutoLoops = true;
    public final String mVideoSourceUrl;

    public float getAspectRatio() {
        return this.mAspectRatio;
    }

    @Nullable
    public String getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public JSONObject getDialogHeroSpaceConfigurationIPCParcel() throws JSONException {
        double d;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(DIALOG_HERO_IMAGE_SOURCE_URL_KEY, this.mImageSourceUrl);
            jSONObject.put(DIALOG_HERO_VIDEO_AUTO_LOOPS_KEY, this.mVideoAutoLoops);
            jSONObject.put(DIALOG_HERO_VIDEO_SOURCE_URL_KEY, this.mVideoSourceUrl);
            float f = this.mAspectRatio;
            if (f <= AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                Log.e(TAG, String.format("The given dialog video aspect ratio %f is not postive; defaulting to 2.0f.", Float.valueOf(f)));
            }
            float f2 = this.mAspectRatio;
            if (f2 > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                d = (double) f2;
            } else {
                d = 2.0d;
            }
            jSONObject.put(DIALOG_HERO_ASPECT_RATIO_KEY, d);
            String str = this.mBackgroundColor;
            if (str != null) {
                jSONObject.put(DIALOG_HERO_BACKGROUND_COLOR_KEY, str);
            }
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog video configuration IPC parcel.", e);
            throw e;
        }
    }

    @Nullable
    public String getImageSourceUrl() {
        return this.mImageSourceUrl;
    }

    public boolean getVideoAutoLoops() {
        return this.mVideoAutoLoops;
    }

    @Nullable
    public String getVideoSourceUrl() {
        return this.mVideoSourceUrl;
    }

    public DialogHeroSpace(@Nullable String str, @Nullable String str2, float f, @Nullable String str3) {
        this.mVideoSourceUrl = str;
        this.mImageSourceUrl = str2;
        this.mAspectRatio = f;
        this.mBackgroundColor = str3;
    }

    public DialogHeroSpace setVideoAutoLoops(boolean z) {
        this.mVideoAutoLoops = z;
        return this;
    }
}
