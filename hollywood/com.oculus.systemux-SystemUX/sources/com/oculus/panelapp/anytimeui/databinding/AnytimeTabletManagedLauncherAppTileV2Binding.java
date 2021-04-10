package com.oculus.panelapp.anytimeui.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem;
import com.oculus.vrshell.panels.views.ShellButton;

public abstract class AnytimeTabletManagedLauncherAppTileV2Binding extends ViewDataBinding {
    @NonNull
    public final ShellButton appTileButton;
    @NonNull
    public final ImageView ctaIcon;
    @NonNull
    public final OCTextView ctaText;
    @NonNull
    public final View footerBackground;
    @NonNull
    public final View hoverOverlayBackground;
    @NonNull
    public final ImageView icon;
    @NonNull
    public final SimpleDraweeView imageView;
    @Bindable
    protected String mDisplayName;
    @Bindable
    protected boolean mImageAvailable;
    @Bindable
    protected boolean mIsHovered;
    @Bindable
    protected ManagedLauncherItem mItem;
    @NonNull
    public final View outline;
    @NonNull
    public final OCTextView title;

    public abstract void setDisplayName(@Nullable String str);

    public abstract void setImageAvailable(boolean z);

    public abstract void setIsHovered(boolean z);

    public abstract void setItem(@Nullable ManagedLauncherItem managedLauncherItem);

    protected AnytimeTabletManagedLauncherAppTileV2Binding(Object obj, View view, int i, ShellButton shellButton, ImageView imageView2, OCTextView oCTextView, View view2, View view3, ImageView imageView3, SimpleDraweeView simpleDraweeView, View view4, OCTextView oCTextView2) {
        super(obj, view, i);
        this.appTileButton = shellButton;
        this.ctaIcon = imageView2;
        this.ctaText = oCTextView;
        this.footerBackground = view2;
        this.hoverOverlayBackground = view3;
        this.icon = imageView3;
        this.imageView = simpleDraweeView;
        this.outline = view4;
        this.title = oCTextView2;
    }

    @Nullable
    public ManagedLauncherItem getItem() {
        return this.mItem;
    }

    @Nullable
    public String getDisplayName() {
        return this.mDisplayName;
    }

    public boolean getIsHovered() {
        return this.mIsHovered;
    }

    public boolean getImageAvailable() {
        return this.mImageAvailable;
    }

    @NonNull
    public static AnytimeTabletManagedLauncherAppTileV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletManagedLauncherAppTileV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletManagedLauncherAppTileV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_managed_launcher_app_tile_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletManagedLauncherAppTileV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletManagedLauncherAppTileV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletManagedLauncherAppTileV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_managed_launcher_app_tile_v2, null, false, obj);
    }

    public static AnytimeTabletManagedLauncherAppTileV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletManagedLauncherAppTileV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletManagedLauncherAppTileV2Binding) bind(obj, view, R.layout.anytime_tablet_managed_launcher_app_tile_v2);
    }
}
