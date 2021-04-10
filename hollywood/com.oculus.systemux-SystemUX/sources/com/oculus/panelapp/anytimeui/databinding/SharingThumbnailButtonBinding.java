package com.oculus.panelapp.anytimeui.databinding;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.oculus.panelapp.anytimeui.R;

public abstract class SharingThumbnailButtonBinding extends ViewDataBinding {
    @Bindable
    protected boolean mIsVideo;
    @Bindable
    protected Drawable mThumbnail;
    @NonNull
    public final ImageView thumbnailImage;

    public abstract void setIsVideo(boolean z);

    public abstract void setThumbnail(@Nullable Drawable drawable);

    protected SharingThumbnailButtonBinding(Object obj, View view, int i, ImageView imageView) {
        super(obj, view, i);
        this.thumbnailImage = imageView;
    }

    @Nullable
    public Drawable getThumbnail() {
        return this.mThumbnail;
    }

    public boolean getIsVideo() {
        return this.mIsVideo;
    }

    @NonNull
    public static SharingThumbnailButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SharingThumbnailButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (SharingThumbnailButtonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sharing_thumbnail_button, viewGroup, z, obj);
    }

    @NonNull
    public static SharingThumbnailButtonBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static SharingThumbnailButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (SharingThumbnailButtonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.sharing_thumbnail_button, null, false, obj);
    }

    public static SharingThumbnailButtonBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SharingThumbnailButtonBinding bind(@NonNull View view, @Nullable Object obj) {
        return (SharingThumbnailButtonBinding) bind(obj, view, R.layout.sharing_thumbnail_button);
    }
}
