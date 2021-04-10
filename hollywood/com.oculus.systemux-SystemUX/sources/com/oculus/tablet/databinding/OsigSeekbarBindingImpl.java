package com.oculus.tablet.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.SeekBarBindingAdapter;
import com.oculus.tablet.BR;
import com.oculus.tablet.R;

public class OsigSeekbarBindingImpl extends OsigSeekbarBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public OsigSeekbarBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private OsigSeekbarBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[2], (SeekBar) objArr[1]);
        this.mDirtyFlags = -1;
        this.icon.setTag(null);
        this.mboundView0 = (ConstraintLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.seekbar.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (BR.progressDrawable == i) {
            setProgressDrawable((Drawable) obj);
        } else if (BR.icon == i) {
            setIcon((Drawable) obj);
        } else if (BR.progress != i) {
            return false;
        } else {
            setProgress(((Integer) obj).intValue());
        }
        return true;
    }

    @Override // com.oculus.tablet.databinding.OsigSeekbarBinding
    public void setProgressDrawable(@Nullable Drawable drawable) {
        this.mProgressDrawable = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.progressDrawable);
        super.requestRebind();
    }

    @Override // com.oculus.tablet.databinding.OsigSeekbarBinding
    public void setIcon(@Nullable Drawable drawable) {
        this.mIcon = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.icon);
        super.requestRebind();
    }

    @Override // com.oculus.tablet.databinding.OsigSeekbarBinding
    public void setProgress(int i) {
        this.mProgress = i;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.progress);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable drawable = this.mProgressDrawable;
        Drawable drawable2 = this.mIcon;
        Drawable drawable3 = null;
        int i = this.mProgress;
        int i2 = ((j & 9) > 0 ? 1 : ((j & 9) == 0 ? 0 : -1));
        boolean z = false;
        if (i2 != 0) {
            if (drawable != null) {
                z = true;
            }
            if (i2 != 0) {
                j |= z ? 32 : 16;
            }
        }
        int i3 = ((10 & j) > 0 ? 1 : ((10 & j) == 0 ? 0 : -1));
        int i4 = ((12 & j) > 0 ? 1 : ((12 & j) == 0 ? 0 : -1));
        int i5 = ((j & 9) > 0 ? 1 : ((j & 9) == 0 ? 0 : -1));
        if (i5 != 0) {
            if (!z) {
                drawable = getDrawableFromResource(this.seekbar, R.drawable.anytime_tablet_settings_seekbar_progress_v2);
            }
            drawable3 = drawable;
        }
        if (i3 != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.icon, drawable2);
        }
        if (i4 != 0) {
            SeekBarBindingAdapter.setProgress(this.seekbar, i);
        }
        if (i5 != 0) {
            this.seekbar.setProgressDrawable(drawable3);
        }
    }
}
