package com.oculus.panelapp.anytimeui.v2.tablet.destinationui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.library.model.Image;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.tablet.view.ViewModelLifecycle;
import com.oculus.vrshell.util.HorizonUtil;
import com.oculus.vrshell.util.PackageUtil;
import com.oculus.vrshell.util.SystemUXScreenshotUtil;

public class DestinationUIViewModel extends BaseObservable implements ViewModelLifecycle, SystemUXScreenshotUtil.AppScreenshotObserver {
    private static final float CENTER_BIAS = 0.5f;
    private static final float LEFT_BIAS = 0.0f;
    private static final String TAG = LoggingUtil.tag(DestinationUIViewModel.class);
    private App mApp;
    private String mAppName;
    private Context mContext;
    private String mHeroImageUri;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private String mParentAppName;
    private String mPausedAppPackageName;
    private String mSquareImageUri;

    public DestinationUIViewModel(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        Log.d(TAG, "Constructing ViewModel");
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        SystemUXScreenshotUtil.addAppScreenshotObserver(this);
    }

    @Override // com.oculus.tablet.view.ViewModelLifecycle
    public void destroy() {
        Log.d(TAG, "Destroying ViewModel");
        SystemUXScreenshotUtil.removeAppScreenshotObserver(this);
        Fresco.getImagePipeline().clearMemoryCaches();
    }

    public void setPausedApp(String str) {
        String str2;
        if (!this.mPanelApp.isInOverlayMode() || !isPackageEmptyOrShell(str)) {
            this.mPausedAppPackageName = str;
            notifyPropertyChanged(BR.isPaused);
            this.mApp = TextUtils.isEmpty(str) ? null : HorizonUtil.getApplication(this.mContext, str);
            if (TextUtils.isEmpty(str)) {
                str2 = null;
            } else {
                str2 = HorizonUtil.getSafeApplicationName(this.mContext, str);
            }
            this.mAppName = str2;
            if ("Unknown".equals(this.mAppName)) {
                this.mAppName = str;
            }
            if ("com.oculus.browser".equals(str)) {
                this.mParentAppName = this.mAppName;
                this.mAppName = this.mContext.getResources().getString(R.string.anytime_tablet_destination_ui_webxr_app_name);
            } else {
                this.mParentAppName = null;
            }
            notifyPropertyChanged(BR.appName);
            notifyPropertyChanged(BR.quitButtonText);
            if (this.mApp != null) {
                fetchImageAndNotify(Image.ImageName.HERO);
                fetchImageAndNotify(Image.ImageName.SOURCE_SQUARE);
                return;
            }
            notifyPropertyChanged(BR.gameScreenshot);
        }
    }

    private void fetchImageAndNotify(Image.ImageName imageName) {
        if (!"com.oculus.browser".equals(this.mPausedAppPackageName)) {
            Image image = this.mApp.images.get(imageName);
            if (imageName == Image.ImageName.HERO && !image.uri.equals(this.mHeroImageUri)) {
                this.mHeroImageUri = image.uri;
                notifyPropertyChanged(BR.heroImage);
            } else if (imageName == Image.ImageName.SOURCE_SQUARE && !image.uri.equals(this.mSquareImageUri)) {
                this.mSquareImageUri = image.uri;
                notifyPropertyChanged(BR.squareImage);
            }
        } else if (imageName == Image.ImageName.HERO) {
            String browserResourceToUriString = browserResourceToUriString(R.drawable.browser_pause_state);
            if (!browserResourceToUriString.equals(this.mHeroImageUri)) {
                this.mHeroImageUri = browserResourceToUriString;
                notifyPropertyChanged(BR.heroImage);
            }
        } else {
            String browserResourceToUriString2 = browserResourceToUriString(this.mPanelApp.getStoreOptionalityExperiment().isSystemAppsAsTilesEnabled() ? R.drawable.colorful_browser_dock_tile : R.drawable.browser_dock_tile);
            if (!browserResourceToUriString2.equals(this.mSquareImageUri)) {
                this.mSquareImageUri = browserResourceToUriString2;
                notifyPropertyChanged(BR.squareImage);
            }
        }
    }

    public String getPackageName() {
        return this.mPausedAppPackageName;
    }

    @Bindable
    public String getParentAppName() {
        return this.mParentAppName;
    }

    @Bindable
    public String getAppName() {
        return this.mAppName;
    }

    @Bindable
    public String getHeroImage() {
        return this.mHeroImageUri;
    }

    @Bindable
    public String getSquareImage() {
        return this.mSquareImageUri;
    }

    @Bindable
    public Drawable getGameScreenshot() {
        Bitmap appScreenshot = SystemUXScreenshotUtil.getAppScreenshot();
        if (appScreenshot != null) {
            return new BitmapDrawable(this.mContext.getResources(), appScreenshot);
        }
        return null;
    }

    @Bindable
    public boolean getIsScreenshotVisible() {
        return TextUtils.isEmpty(this.mHeroImageUri) && getGameScreenshot() != null;
    }

    public App getApp() {
        return this.mApp;
    }

    @Override // com.oculus.vrshell.util.SystemUXScreenshotUtil.AppScreenshotObserver
    public void onAppScreenshotChanged() {
        notifyPropertyChanged(BR.gameScreenshot);
    }

    @Bindable
    public String getQuitButtonText() {
        Resources resources = this.mContext.getResources();
        if ("com.oculus.browser".equals(this.mPausedAppPackageName)) {
            return resources.getString(R.string.anytime_tablet_destination_ui_exit_button);
        }
        return resources.getString(R.string.anytime_tablet_destination_ui_quit_button);
    }

    @Bindable
    public boolean getIsPaused() {
        return this.mPanelApp.isInOverlayMode() || !isPackageEmptyOrShell(this.mPausedAppPackageName);
    }

    private boolean isPackageEmptyOrShell(String str) {
        return TextUtils.isEmpty(str) || PackageUtil.isShellApp(str);
    }

    public boolean isRestartButtonVisible() {
        return this.mPanelApp.getSystemUXConfig().isEnterpriseMode && this.mPanelApp.getSystemUXConfig().isDefaultApplicationSet && this.mPanelApp.getSystemUXConfig().canDefaultApplicationBeRestarted;
    }

    public boolean isQuitButtonVisible() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode || !this.mPanelApp.getSystemUXConfig().isDefaultApplicationSet;
    }

    public boolean isLivestreamButtonVisible() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode;
    }

    public boolean isCastingButtonVisible() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode || this.mPanelApp.getSystemUXConfig().isEnterpriseAdminModeEnabled;
    }

    public boolean isReportButtonVisible() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode;
    }

    public boolean isReportButtonEnabled() {
        return !this.mPanelApp.getScreenCaptureUtil().isCapturingAbuseReportVideo();
    }

    public int getButtonAlignment() {
        return this.mPanelApp.getSystemUXConfig().isEnterpriseMode ? 2 : 0;
    }

    public float getButtonHorizontalBias() {
        return this.mPanelApp.getSystemUXConfig().isEnterpriseMode ? 0.0f : 0.5f;
    }

    public String browserResourceToUriString(int i) {
        return new Uri.Builder().scheme(UriUtil.QUALIFIED_RESOURCE_SCHEME).authority(this.mContext.getResources().getResourcePackageName(i)).appendPath(this.mContext.getResources().getResourceTypeName(i)).appendPath(this.mContext.getResources().getResourceEntryName(i)).build().toString();
    }
}
