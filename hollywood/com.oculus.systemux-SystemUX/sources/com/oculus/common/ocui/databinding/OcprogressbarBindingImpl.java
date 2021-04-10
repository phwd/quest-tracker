package com.oculus.common.ocui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.common.ocui.BR;
import com.oculus.ocui.OCTextView;

public class OcprogressbarBindingImpl extends OcprogressbarBinding {
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

    public OcprogressbarBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private OcprogressbarBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (OCTextView) objArr[1], (ProgressBar) objArr[2]);
        this.mDirtyFlags = -1;
        this.mboundView0 = (ConstraintLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.percantageText.setTag(null);
        this.progressBar.setTag(null);
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
        if (BR.progress == i) {
            setProgress(((Integer) obj).intValue());
        } else if (BR.showProgressPercentage != i) {
            return false;
        } else {
            setShowProgressPercentage(((Boolean) obj).booleanValue());
        }
        return true;
    }

    @Override // com.oculus.common.ocui.databinding.OcprogressbarBinding
    public void setProgress(int i) {
        this.mProgress = i;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.progress);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OcprogressbarBinding
    public void setShowProgressPercentage(boolean z) {
        this.mShowProgressPercentage = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.showProgressPercentage);
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
        int i = this.mProgress;
        boolean z = this.mShowProgressPercentage;
        String str = null;
        if ((j & 5) != 0) {
            str = Integer.toString(i) + "%";
        }
        int i2 = ((j & 6) > 0 ? 1 : ((j & 6) == 0 ? 0 : -1));
        int i3 = 0;
        if (i2 != 0) {
            if (i2 != 0) {
                j |= z ? 16 : 8;
            }
            if (!z) {
                i3 = 8;
            }
        }
        if ((5 & j) != 0) {
            TextViewBindingAdapter.setText(this.percantageText, str);
            this.progressBar.setProgress(i);
        }
        if ((j & 6) != 0) {
            this.percantageText.setVisibility(i3);
        }
    }
}
