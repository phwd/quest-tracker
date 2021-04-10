package com.oculus.panelapp.androiddialog.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorDialog;
import com.oculus.panelapp.androiddialog.dialogs.error.ErrorViewModel;

public class ErrorDialogBindingImpl extends ErrorDialogBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final ErrorDialog mboundView0;

    static {
        sViewsWithIds.put(R.id.error_dialog_secondary_button, 3);
        sViewsWithIds.put(R.id.error_dialog_primary_secondary_button, 4);
        sViewsWithIds.put(R.id.error_dialog_primary_button, 5);
    }

    public ErrorDialogBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private ErrorDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (OCTextView) objArr[2], (OCButton) objArr[5], (OCButton) objArr[4], (OCButton) objArr[3], (OCTextView) objArr[1]);
        this.mDirtyFlags = -1;
        this.errorDialogMessage.setTag(null);
        this.errorDialogTitle.setTag(null);
        this.mboundView0 = (ErrorDialog) objArr[0];
        this.mboundView0.setTag(null);
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
        if (BR.errorViewModel != i) {
            return false;
        }
        setErrorViewModel((ErrorViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.ErrorDialogBinding
    public void setErrorViewModel(@Nullable ErrorViewModel errorViewModel) {
        updateRegistration(0, errorViewModel);
        this.mErrorViewModel = errorViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.errorViewModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeErrorViewModel((ErrorViewModel) obj, i2);
    }

    private boolean onChangeErrorViewModel(ErrorViewModel errorViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.title) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i != BR.message) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ErrorViewModel errorViewModel = this.mErrorViewModel;
        String str2 = null;
        if ((15 & j) != 0) {
            str = ((j & 13) == 0 || errorViewModel == null) ? null : errorViewModel.getMessage();
            if (!((j & 11) == 0 || errorViewModel == null)) {
                str2 = errorViewModel.getTitle();
            }
        } else {
            str = null;
        }
        if ((13 & j) != 0) {
            TextViewBindingAdapter.setText(this.errorDialogMessage, str);
        }
        if ((j & 11) != 0) {
            TextViewBindingAdapter.setText(this.errorDialogTitle, str2);
        }
    }
}
