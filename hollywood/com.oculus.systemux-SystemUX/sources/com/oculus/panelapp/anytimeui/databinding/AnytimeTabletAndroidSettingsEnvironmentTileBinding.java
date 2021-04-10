package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.library.model.App;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryAppTileFooter;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsEnvironmentTile;

public abstract class AnytimeTabletAndroidSettingsEnvironmentTileBinding extends ViewDataBinding {
    @NonNull
    public final View attentionBadge;
    @NonNull
    public final OCButton button;
    @NonNull
    public final SettingsEnvironmentTile cardView;
    @NonNull
    public final ImageView ctaIcon;
    @NonNull
    public final OCTextView ctaText;
    @NonNull
    public final LibraryAppTileFooter footerBackground;
    @NonNull
    public final View hoverOverlayBackground;
    @NonNull
    public final SimpleDraweeView imageView;
    @Bindable
    protected App mEnvironment;
    @Bindable
    protected boolean mImageAvailable;
    @Bindable
    protected boolean mIsHovered;
    @NonNull
    public final View outline;
    @NonNull
    public final ProgressBar progressBar;
    @NonNull
    public final OCTextView subtitle;
    @NonNull
    public final OCTextView title;

    public abstract void setEnvironment(@Nullable App app);

    public abstract void setImageAvailable(boolean z);

    public abstract void setIsHovered(boolean z);

    protected AnytimeTabletAndroidSettingsEnvironmentTileBinding(Object obj, View view, int i, View view2, OCButton oCButton, SettingsEnvironmentTile settingsEnvironmentTile, ImageView imageView2, OCTextView oCTextView, LibraryAppTileFooter libraryAppTileFooter, View view3, SimpleDraweeView simpleDraweeView, View view4, ProgressBar progressBar2, OCTextView oCTextView2, OCTextView oCTextView3) {
        super(obj, view, i);
        this.attentionBadge = view2;
        this.button = oCButton;
        this.cardView = settingsEnvironmentTile;
        this.ctaIcon = imageView2;
        this.ctaText = oCTextView;
        this.footerBackground = libraryAppTileFooter;
        this.hoverOverlayBackground = view3;
        this.imageView = simpleDraweeView;
        this.outline = view4;
        this.progressBar = progressBar2;
        this.subtitle = oCTextView2;
        this.title = oCTextView3;
    }

    @Nullable
    public App getEnvironment() {
        return this.mEnvironment;
    }

    public boolean getIsHovered() {
        return this.mIsHovered;
    }

    public boolean getImageAvailable() {
        return this.mImageAvailable;
    }

    @NonNull
    public static AnytimeTabletAndroidSettingsEnvironmentTileBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletAndroidSettingsEnvironmentTileBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsEnvironmentTileBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_android_settings_environment_tile, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletAndroidSettingsEnvironmentTileBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletAndroidSettingsEnvironmentTileBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsEnvironmentTileBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_android_settings_environment_tile, null, false, obj);
    }

    public static AnytimeTabletAndroidSettingsEnvironmentTileBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletAndroidSettingsEnvironmentTileBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletAndroidSettingsEnvironmentTileBinding) bind(obj, view, R.layout.anytime_tablet_android_settings_environment_tile);
    }
}
