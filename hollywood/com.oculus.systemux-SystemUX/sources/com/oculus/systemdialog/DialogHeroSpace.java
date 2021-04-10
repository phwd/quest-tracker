package com.oculus.systemdialog;

import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogHeroSpace {
    public static final String DIALOG_HERO_ASPECT_RATIO_KEY = "aspectRatio";
    public static final String DIALOG_HERO_BACKGROUND_COLOR_KEY = "backgroundColor";
    public static final String DIALOG_HERO_IMAGE_SOURCE_URL_KEY = "imageSourceUrl";
    public static final String DIALOG_HERO_VIDEO_AUTO_LOOPS_KEY = "videoAutoLoops";
    public static final String DIALOG_HERO_VIDEO_SOURCE_URL_KEY = "videoSourceUrl";
    private static final String TAG = LoggingUtil.tag(DialogHeroSpace.class);
    private final float mAspectRatio;
    private final String mBackgroundColor;
    private final String mImageSourceUrl;
    private boolean mVideoAutoLoops = true;
    private final String mVideoSourceUrl;

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

    public JSONObject getDialogHeroSpaceConfigurationIPCParcel() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(DIALOG_HERO_IMAGE_SOURCE_URL_KEY, this.mImageSourceUrl);
            jSONObject.put(DIALOG_HERO_VIDEO_AUTO_LOOPS_KEY, this.mVideoAutoLoops);
            jSONObject.put(DIALOG_HERO_VIDEO_SOURCE_URL_KEY, this.mVideoSourceUrl);
            if (this.mAspectRatio <= 0.0f) {
                Log.e(TAG, String.format("The given dialog video aspect ratio %f is not postive; defaulting to 2.0f.", Float.valueOf(this.mAspectRatio)));
            }
            jSONObject.put(DIALOG_HERO_ASPECT_RATIO_KEY, this.mAspectRatio > 0.0f ? (double) this.mAspectRatio : 2.0d);
            if (this.mBackgroundColor != null) {
                jSONObject.put(DIALOG_HERO_BACKGROUND_COLOR_KEY, this.mBackgroundColor);
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

    @Nullable
    public String getVideoSourceUrl() {
        return this.mVideoSourceUrl;
    }

    public float getAspectRatio() {
        return this.mAspectRatio;
    }

    @Nullable
    public String getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public boolean getVideoAutoLoops() {
        return this.mVideoAutoLoops;
    }
}
