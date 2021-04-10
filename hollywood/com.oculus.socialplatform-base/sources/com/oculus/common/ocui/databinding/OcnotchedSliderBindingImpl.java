package com.oculus.common.ocui.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RatingBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCNotchedSlider;

public class OcnotchedSliderBindingImpl extends OcnotchedSliderBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;
    @NonNull
    public final View mboundView1;

    private boolean onChangeViewModel(OCNotchedSlider.OCNotchedSliderViewModel oCNotchedSliderViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i != 173) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        long j2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable drawable = null;
        boolean z = false;
        Drawable drawable2 = this.mInactiveDrawable;
        Drawable drawable3 = this.mActiveDrawable;
        OCNotchedSlider.OCNotchedSliderViewModel oCNotchedSliderViewModel = this.mViewModel;
        long j3 = j & 31;
        if (j3 != 0) {
            if (oCNotchedSliderViewModel != null) {
                z = oCNotchedSliderViewModel.mIsActive;
            }
            if (j3 != 0) {
                if (z) {
                    j2 = 64;
                } else {
                    j2 = 32;
                }
                j |= j2;
            }
        }
        long j4 = j & 31;
        if (j4 != 0) {
            drawable = drawable2;
            if (z) {
                drawable = drawable3;
            }
        }
        if (j4 != 0) {
            this.mboundView1.setBackground(drawable);
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
        }
        requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OcnotchedSliderBinding
    public void setViewModel(@Nullable OCNotchedSlider.OCNotchedSliderViewModel oCNotchedSliderViewModel) {
        updateRegistration(0, oCNotchedSliderViewModel);
        this.mViewModel = oCNotchedSliderViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((OCNotchedSlider.OCNotchedSliderViewModel) obj, i2);
    }

    @Override // com.oculus.common.ocui.databinding.OcnotchedSliderBinding
    public void setActiveDrawable(@Nullable Drawable drawable) {
        this.mActiveDrawable = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(176);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OcnotchedSliderBinding
    public void setInactiveDrawable(@Nullable Drawable drawable) {
        this.mInactiveDrawable = drawable;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(170);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (170 == i) {
            setInactiveDrawable((Drawable) obj);
            return true;
        } else if (176 == i) {
            setActiveDrawable((Drawable) obj);
            return true;
        } else if (62 != i) {
            return false;
        } else {
            setViewModel((OCNotchedSlider.OCNotchedSliderViewModel) obj);
            return true;
        }
    }

    public OcnotchedSliderBindingImpl(@Nullable AbstractC003408r r3, @NonNull View[] viewArr) {
        this(r3, viewArr, AnonymousClass1uW.mapBindings(r3, viewArr, 3, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public OcnotchedSliderBindingImpl(AbstractC003408r r9, View[] viewArr, Object[] objArr) {
        super(r9, viewArr[0], 1, (RatingBar) objArr[2], (OCButton) objArr[0]);
        this.mDirtyFlags = -1;
        View view = (View) objArr[1];
        this.mboundView1 = view;
        view.setTag(null);
        this.slider.setTag(null);
        this.toggle.setTag(null);
        setRootTag(viewArr);
        invalidateAll();
    }
}
