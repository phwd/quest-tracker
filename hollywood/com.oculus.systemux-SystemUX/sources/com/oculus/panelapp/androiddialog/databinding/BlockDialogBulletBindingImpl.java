package com.oculus.panelapp.androiddialog.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;

public class BlockDialogBulletBindingImpl extends BlockDialogBulletBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final OCTextView mboundView0;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public BlockDialogBulletBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 1, sIncludes, sViewsWithIds));
    }

    private BlockDialogBulletBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0);
        this.mDirtyFlags = -1;
        this.mboundView0 = (OCTextView) objArr[0];
        this.mboundView0.setTag(null);
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
        if (BR.displayForBlocked == i) {
            setDisplayForBlocked(((Boolean) obj).booleanValue());
        } else if (BR.alpha != i) {
            return false;
        } else {
            setAlpha(((Float) obj).floatValue());
        }
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.BlockDialogBulletBinding
    public void setDisplayForBlocked(boolean z) {
        this.mDisplayForBlocked = z;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.displayForBlocked);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.BlockDialogBulletBinding
    public void setAlpha(float f) {
        this.mAlpha = f;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.alpha);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean z2 = this.mDisplayForBlocked;
        float f = this.mAlpha;
        int i = ((j & 5) > 0 ? 1 : ((j & 5) == 0 ? 0 : -1));
        if (!(i == 0 || i == 0)) {
            j = z2 ? j | 64 : j | 32;
        }
        int i2 = ((j & 6) > 0 ? 1 : ((j & 6) == 0 ? 0 : -1));
        float f2 = 0.0f;
        int i3 = 0;
        if (i2 != 0) {
            z = f == 0.0f;
            if (i2 != 0) {
                j |= z ? 16 : 8;
            }
        } else {
            z = false;
        }
        int color = (32 & j) != 0 ? getRoot().getContext().getResources().getColor(R.color.octypography_primary_color) : 0;
        int color2 = (64 & j) != 0 ? getRoot().getContext().getResources().getColor(R.color.octypography_secondary_color) : 0;
        int i4 = ((j & 6) > 0 ? 1 : ((j & 6) == 0 ? 0 : -1));
        if (i4 != 0) {
            if (z) {
                f = 1.0f;
            }
            f2 = f;
        }
        int i5 = ((j & 5) > 0 ? 1 : ((j & 5) == 0 ? 0 : -1));
        if (i5 != 0) {
            i3 = z2 ? color2 : color;
        }
        if (i4 != 0 && getBuildSdkInt() >= 11) {
            this.mboundView0.setAlpha(f2);
        }
        if (i5 != 0) {
            this.mboundView0.setTextColor(i3);
        }
    }
}
