package com.oculus.panelapp.anytimeui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType;

public class SettingsButtonActionViewBindingImpl extends SettingsButtonActionViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public SettingsButtonActionViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 1, sIncludes, sViewsWithIds));
    }

    private SettingsButtonActionViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (OCButton) objArr[0]);
        this.mDirtyFlags = -1;
        this.button.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        if (BR.buttonActionType != i) {
            return false;
        }
        setButtonActionType((SettingsButtonActionType) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.SettingsButtonActionViewBinding
    public void setButtonActionType(@Nullable SettingsButtonActionType settingsButtonActionType) {
        updateRegistration(0, settingsButtonActionType);
        this.mButtonActionType = settingsButtonActionType;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.buttonActionType);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeButtonActionType((SettingsButtonActionType) obj, i2);
    }

    private boolean onChangeButtonActionType(SettingsButtonActionType settingsButtonActionType, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SettingsButtonActionType settingsButtonActionType = this.mButtonActionType;
        boolean z = false;
        String str = null;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (!(i == 0 || settingsButtonActionType == null)) {
            z = settingsButtonActionType.getEnabled();
            str = settingsButtonActionType.getTitle();
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.button, str);
            this.button.setEnabled(z);
        }
    }
}
