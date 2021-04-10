package com.oculus.panelapp.anytimeui.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;

public class SharingThumbnailButtonBindingImpl extends SharingThumbnailButtonBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final CardView mboundView0;
    @NonNull
    private final View mboundView1;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public SharingThumbnailButtonBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private SharingThumbnailButtonBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[2]);
        this.mDirtyFlags = -1;
        this.mboundView0 = (CardView) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (View) objArr[1];
        this.mboundView1.setTag(null);
        this.thumbnailImage.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.isVideo == i) {
            setIsVideo(((Boolean) obj).booleanValue());
        } else if (BR.thumbnail != i) {
            return false;
        } else {
            setThumbnail((Drawable) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.SharingThumbnailButtonBinding
    public void setIsVideo(boolean z) {
        this.mIsVideo = z;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.isVideo);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.SharingThumbnailButtonBinding
    public void setThumbnail(@Nullable Drawable drawable) {
        this.mThumbnail = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.thumbnail);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        int i;
        View view;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable drawable = null;
        boolean z = this.mIsVideo;
        Drawable drawable2 = this.mThumbnail;
        int i2 = ((j & 5) > 0 ? 1 : ((j & 5) == 0 ? 0 : -1));
        if (i2 != 0) {
            if (i2 != 0) {
                j |= z ? 16 : 8;
            }
            if (z) {
                view = this.mboundView1;
                i = R.drawable.sharing_video_thumbnail_button_background;
            } else {
                view = this.mboundView1;
                i = R.drawable.sharing_thumbnail_button_background;
            }
            drawable = getDrawableFromResource(view, i);
        }
        int i3 = ((6 & j) > 0 ? 1 : ((6 & j) == 0 ? 0 : -1));
        if ((j & 5) != 0) {
            ViewBindingAdapter.setBackground(this.mboundView1, drawable);
        }
        if (i3 != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.thumbnailImage, drawable2);
        }
    }
}
