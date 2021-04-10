package com.oculus.panelapp.androiddialog.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.ocui.OCToggle;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.localstream.LocalStreamPrivacyCheckDialog;

public class LocalStreamPrivacyCheckBindingImpl extends LocalStreamPrivacyCheckBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final LocalStreamPrivacyCheckDialog mboundView0;

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    static {
        sViewsWithIds.put(R.id.title, 1);
        sViewsWithIds.put(R.id.body, 2);
        sViewsWithIds.put(R.id.disable_privacy_check_toggle, 3);
        sViewsWithIds.put(R.id.toggle_text, 4);
        sViewsWithIds.put(R.id.deny_button, 5);
        sViewsWithIds.put(R.id.allow_button, 6);
    }

    public LocalStreamPrivacyCheckBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private LocalStreamPrivacyCheckBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (OCButton) objArr[6], (OCTextView) objArr[2], (OCButton) objArr[5], (OCToggle) objArr[3], (OCTextView) objArr[1], (OCTextView) objArr[4]);
        this.mDirtyFlags = -1;
        this.mboundView0 = (LocalStreamPrivacyCheckDialog) objArr[0];
        this.mboundView0.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
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

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            long j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }
}
