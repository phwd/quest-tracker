package com.oculus.panelapp.anytimeui.databinding;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.library.model.App;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryAppTileFooter;

public abstract class AnytimeTabletLibraryAppTileV2Binding extends ViewDataBinding {
    @NonNull
    public final View attentionBadge;
    @NonNull
    public final View blockingBackground;
    @NonNull
    public final OCButton button;
    @NonNull
    public final CardView cardView;
    @NonNull
    public final OCButton contextMenuButton;
    @NonNull
    public final ImageView ctaIcon;
    @NonNull
    public final OCTextView ctaText;
    @NonNull
    public final LibraryAppTileFooter footerBackground;
    @NonNull
    public final View hoverOverlayBackground;
    @NonNull
    public final ImageView icon;
    @NonNull
    public final ImageView imageBadge;
    @NonNull
    public final SimpleDraweeView imageView;
    @Bindable
    protected App mApp;
    @Bindable
    protected boolean mHasContextMenu;
    @Bindable
    protected Drawable mIconDrawable;
    @Bindable
    protected boolean mImageAvailable;
    @Bindable
    protected boolean mIsContextMenuButtonHovered;
    @Bindable
    protected boolean mIsHovered;
    @Bindable
    protected boolean mIsInternetConnected;
    @Bindable
    protected boolean mIsPrototype;
    @Bindable
    protected boolean mIsTrackingEnabled;
    @Bindable
    protected AnytimeUIPanelAppBase mPanelApp;
    @Bindable
    protected int mPosition;
    @NonNull
    public final View outline;
    @NonNull
    public final ProgressBar progressBar;
    @NonNull
    public final OCTextView subtitle;
    @NonNull
    public final OCTextView textBadge;
    @NonNull
    public final OCTextView title;

    public abstract void setApp(@Nullable App app);

    public abstract void setHasContextMenu(boolean z);

    public abstract void setIconDrawable(@Nullable Drawable drawable);

    public abstract void setImageAvailable(boolean z);

    public abstract void setIsContextMenuButtonHovered(boolean z);

    public abstract void setIsHovered(boolean z);

    public abstract void setIsInternetConnected(boolean z);

    public abstract void setIsPrototype(boolean z);

    public abstract void setIsTrackingEnabled(boolean z);

    public abstract void setPanelApp(@Nullable AnytimeUIPanelAppBase anytimeUIPanelAppBase);

    public abstract void setPosition(int i);

    protected AnytimeTabletLibraryAppTileV2Binding(Object obj, View view, int i, View view2, View view3, OCButton oCButton, CardView cardView2, OCButton oCButton2, ImageView imageView2, OCTextView oCTextView, LibraryAppTileFooter libraryAppTileFooter, View view4, ImageView imageView3, ImageView imageView4, SimpleDraweeView simpleDraweeView, View view5, ProgressBar progressBar2, OCTextView oCTextView2, OCTextView oCTextView3, OCTextView oCTextView4) {
        super(obj, view, i);
        this.attentionBadge = view2;
        this.blockingBackground = view3;
        this.button = oCButton;
        this.cardView = cardView2;
        this.contextMenuButton = oCButton2;
        this.ctaIcon = imageView2;
        this.ctaText = oCTextView;
        this.footerBackground = libraryAppTileFooter;
        this.hoverOverlayBackground = view4;
        this.icon = imageView3;
        this.imageBadge = imageView4;
        this.imageView = simpleDraweeView;
        this.outline = view5;
        this.progressBar = progressBar2;
        this.subtitle = oCTextView2;
        this.textBadge = oCTextView3;
        this.title = oCTextView4;
    }

    public int getPosition() {
        return this.mPosition;
    }

    @Nullable
    public App getApp() {
        return this.mApp;
    }

    @Nullable
    public Drawable getIconDrawable() {
        return this.mIconDrawable;
    }

    public boolean getIsHovered() {
        return this.mIsHovered;
    }

    public boolean getIsContextMenuButtonHovered() {
        return this.mIsContextMenuButtonHovered;
    }

    public boolean getImageAvailable() {
        return this.mImageAvailable;
    }

    public boolean getIsInternetConnected() {
        return this.mIsInternetConnected;
    }

    public boolean getIsTrackingEnabled() {
        return this.mIsTrackingEnabled;
    }

    public boolean getHasContextMenu() {
        return this.mHasContextMenu;
    }

    public boolean getIsPrototype() {
        return this.mIsPrototype;
    }

    @Nullable
    public AnytimeUIPanelAppBase getPanelApp() {
        return this.mPanelApp;
    }

    @NonNull
    public static AnytimeTabletLibraryAppTileV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibraryAppTileV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AnytimeTabletLibraryAppTileV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_app_tile_v2, viewGroup, z, obj);
    }

    @NonNull
    public static AnytimeTabletLibraryAppTileV2Binding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletLibraryAppTileV2Binding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AnytimeTabletLibraryAppTileV2Binding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.anytime_tablet_library_app_tile_v2, null, false, obj);
    }

    public static AnytimeTabletLibraryAppTileV2Binding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static AnytimeTabletLibraryAppTileV2Binding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletLibraryAppTileV2Binding) bind(obj, view, R.layout.anytime_tablet_library_app_tile_v2);
    }
}
