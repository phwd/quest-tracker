package com.oculus.panelapp.androiddialog.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.androiddialog.BR;

public class DialogActionSpaceSecondaryBindingImpl extends DialogActionSpaceSecondaryBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public DialogActionSpaceSecondaryBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 2, sIncludes, sViewsWithIds));
    }

    private DialogActionSpaceSecondaryBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ConstraintLayout) objArr[0], (OCButton) objArr[1]);
        this.mDirtyFlags = -1;
        this.actionSpaceSecondary.setTag(null);
        this.actionSpaceSecondaryButton.setTag(null);
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
        if (BR.isButtonEnabled == i) {
            setIsButtonEnabled(((Boolean) obj).booleanValue());
        } else if (BR.buttonText != i) {
            return false;
        } else {
            setButtonText((String) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.DialogActionSpaceSecondaryBinding
    public void setIsButtonEnabled(boolean z) {
        this.mIsButtonEnabled = z;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.isButtonEnabled);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.DialogActionSpaceSecondaryBinding
    public void setButtonText(@Nullable String str) {
        this.mButtonText = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.buttonText);
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
        boolean z = this.mIsButtonEnabled;
        String str = this.mButtonText;
        int i = ((5 & j) > 0 ? 1 : ((5 & j) == 0 ? 0 : -1));
        int i2 = ((j & 6) > 0 ? 1 : ((j & 6) == 0 ? 0 : -1));
        if (i != 0) {
            this.actionSpaceSecondaryButton.setEnabled(z);
        }
        if (i2 != 0) {
            TextViewBindingAdapter.setText(this.actionSpaceSecondaryButton, str);
        }
    }
}
