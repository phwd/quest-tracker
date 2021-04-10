package com.oculus.common.ocui.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RatingBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.oculus.common.ocui.BR;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCNotchedSlider;

public class OcnotchedSliderBindingImpl extends OcnotchedSliderBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final View mboundView1;

    public OcnotchedSliderBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View[] viewArr) {
        this(dataBindingComponent, viewArr, mapBindings(dataBindingComponent, viewArr, 3, sIncludes, sViewsWithIds));
    }

    private OcnotchedSliderBindingImpl(DataBindingComponent dataBindingComponent, View[] viewArr, Object[] objArr) {
        super(dataBindingComponent, viewArr[0], 1, (RatingBar) objArr[2], (OCButton) objArr[0]);
        this.mDirtyFlags = -1;
        this.mboundView1 = (View) objArr[1];
        this.mboundView1.setTag(null);
        this.slider.setTag(null);
        this.toggle.setTag(null);
        setRootTag(viewArr);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
        if (BR.inactiveDrawable == i) {
            setInactiveDrawable((Drawable) obj);
        } else if (BR.activeDrawable == i) {
            setActiveDrawable((Drawable) obj);
        } else if (BR.viewModel != i) {
            return false;
        } else {
            setViewModel((OCNotchedSlider.OCNotchedSliderViewModel) obj);
        }
        return true;
    }

    @Override // com.oculus.common.ocui.databinding.OcnotchedSliderBinding
    public void setInactiveDrawable(@Nullable Drawable drawable) {
        this.mInactiveDrawable = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.inactiveDrawable);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OcnotchedSliderBinding
    public void setActiveDrawable(@Nullable Drawable drawable) {
        this.mActiveDrawable = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.activeDrawable);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OcnotchedSliderBinding
    public void setViewModel(@Nullable OCNotchedSlider.OCNotchedSliderViewModel oCNotchedSliderViewModel) {
        updateRegistration(0, oCNotchedSliderViewModel);
        this.mViewModel = oCNotchedSliderViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((OCNotchedSlider.OCNotchedSliderViewModel) obj, i2);
    }

    private boolean onChangeViewModel(OCNotchedSlider.OCNotchedSliderViewModel oCNotchedSliderViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i != BR.isActive) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable drawable = null;
        boolean z = false;
        Drawable drawable2 = this.mInactiveDrawable;
        Drawable drawable3 = this.mActiveDrawable;
        OCNotchedSlider.OCNotchedSliderViewModel oCNotchedSliderViewModel = this.mViewModel;
        int i = ((j & 31) > 0 ? 1 : ((j & 31) == 0 ? 0 : -1));
        if (i != 0) {
            if (oCNotchedSliderViewModel != null) {
                z = oCNotchedSliderViewModel.getIsActive();
            }
            if (i != 0) {
                j |= z ? 64 : 32;
            }
        }
        int i2 = ((j & 31) > 0 ? 1 : ((j & 31) == 0 ? 0 : -1));
        if (i2 != 0) {
            drawable = z ? drawable3 : drawable2;
        }
        if (i2 != 0) {
            ViewBindingAdapter.setBackground(this.mboundView1, drawable);
        }
    }
}
