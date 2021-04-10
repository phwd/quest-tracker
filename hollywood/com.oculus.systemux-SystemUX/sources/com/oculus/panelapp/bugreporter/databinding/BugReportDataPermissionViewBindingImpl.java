package com.oculus.panelapp.bugreporter.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.CompoundButtonBindingAdapter;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCCheckbox;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.bugreporter.BR;
import com.oculus.panelapp.bugreporter.DataPermissionView;
import com.oculus.panelapp.bugreporter.DataPermissionViewModel;
import com.oculus.panelapp.bugreporter.R;

public class BugReportDataPermissionViewBindingImpl extends BugReportDataPermissionViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private InverseBindingListener checkboxandroidCheckedAttrChanged;
    private long mDirtyFlags;
    @NonNull
    private final DataPermissionView mboundView0;

    static {
        sViewsWithIds.put(R.id.back_button, 2);
        sViewsWithIds.put(R.id.title, 3);
        sViewsWithIds.put(R.id.system_label, 4);
        sViewsWithIds.put(R.id.system_information, 5);
        sViewsWithIds.put(R.id.checkbox_label, 6);
        sViewsWithIds.put(R.id.cancel_button, 7);
        sViewsWithIds.put(R.id.submit_button, 8);
    }

    public BugReportDataPermissionViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 9, sIncludes, sViewsWithIds));
    }

    private BugReportDataPermissionViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (OCButton) objArr[2], (OCButton) objArr[7], (OCCheckbox) objArr[1], (OCTextView) objArr[6], (OCButton) objArr[8], (OCTextView) objArr[5], (OCTextView) objArr[4], (OCTextView) objArr[3]);
        this.checkboxandroidCheckedAttrChanged = new InverseBindingListener() {
            /* class com.oculus.panelapp.bugreporter.databinding.BugReportDataPermissionViewBindingImpl.AnonymousClass1 */

            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                boolean isChecked = BugReportDataPermissionViewBindingImpl.this.checkbox.isChecked();
                DataPermissionViewModel dataPermissionViewModel = BugReportDataPermissionViewBindingImpl.this.mViewModel;
                if (dataPermissionViewModel != null) {
                    dataPermissionViewModel.setAttachLogs(isChecked);
                }
            }
        };
        this.mDirtyFlags = -1;
        this.checkbox.setTag(null);
        this.mboundView0 = (DataPermissionView) objArr[0];
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
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((DataPermissionViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.bugreporter.databinding.BugReportDataPermissionViewBinding
    public void setViewModel(@Nullable DataPermissionViewModel dataPermissionViewModel) {
        updateRegistration(0, dataPermissionViewModel);
        this.mViewModel = dataPermissionViewModel;
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
        return onChangeViewModel((DataPermissionViewModel) obj, i2);
    }

    private boolean onChangeViewModel(DataPermissionViewModel dataPermissionViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i != BR.attachLogs) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 2;
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
        boolean z = false;
        DataPermissionViewModel dataPermissionViewModel = this.mViewModel;
        int i = ((7 & j) > 0 ? 1 : ((7 & j) == 0 ? 0 : -1));
        if (!(i == 0 || dataPermissionViewModel == null)) {
            z = dataPermissionViewModel.getAttachLogs();
        }
        if (i != 0) {
            CompoundButtonBindingAdapter.setChecked(this.checkbox, z);
        }
        if ((j & 4) != 0) {
            CompoundButtonBindingAdapter.setListeners(this.checkbox, null, this.checkboxandroidCheckedAttrChanged);
        }
    }
}
