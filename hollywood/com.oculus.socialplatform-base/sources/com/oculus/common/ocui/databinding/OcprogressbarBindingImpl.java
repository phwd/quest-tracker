package com.oculus.common.ocui.databinding;

import X.AbstractC003408r;
import X.AnonymousClass006;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCTextView;

public class OcprogressbarBindingImpl extends OcprogressbarBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;
    @NonNull
    public final ConstraintLayout mboundView0;

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        long j2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        int i = this.mProgress;
        boolean z = this.mShowProgressPercentage;
        String str = null;
        if ((j & 5) != 0) {
            str = AnonymousClass006.A07(Integer.toString(i), "%");
        }
        long j3 = j & 6;
        int i2 = 0;
        if (j3 != 0) {
            if (j3 != 0) {
                if (z) {
                    j2 = 16;
                } else {
                    j2 = 8;
                }
                j |= j2;
            }
            if (!z) {
                i2 = 8;
            }
        }
        if ((5 & j) != 0) {
            C11051qV.A02(this.percantageText, str);
            this.progressBar.setProgress(i);
        }
        if ((j & 6) != 0) {
            this.percantageText.setVisibility(i2);
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
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.oculus.common.ocui.databinding.OcprogressbarBinding
    public void setProgress(int i) {
        this.mProgress = i;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(179);
        super.requestRebind();
    }

    @Override // com.oculus.common.ocui.databinding.OcprogressbarBinding
    public void setShowProgressPercentage(boolean z) {
        this.mShowProgressPercentage = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(175);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (179 == i) {
            setProgress(((Number) obj).intValue());
            return true;
        } else if (175 != i) {
            return false;
        } else {
            setShowProgressPercentage(((Boolean) obj).booleanValue());
            return true;
        }
    }

    public OcprogressbarBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 3, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public OcprogressbarBindingImpl(AbstractC003408r r9, View view, Object[] objArr) {
        super(r9, view, 0, (OCTextView) objArr[1], (ProgressBar) objArr[2]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.percantageText.setTag(null);
        this.progressBar.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
