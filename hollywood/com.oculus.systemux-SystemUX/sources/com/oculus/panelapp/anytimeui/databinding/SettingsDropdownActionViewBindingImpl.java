package com.oculus.panelapp.anytimeui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCSelect;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType;

public class SettingsDropdownActionViewBindingImpl extends SettingsDropdownActionViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public SettingsDropdownActionViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 1, sIncludes, sViewsWithIds));
    }

    private SettingsDropdownActionViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (OCSelect) objArr[0]);
        this.mDirtyFlags = -1;
        this.dropdown.setTag(null);
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
        if (BR.dropdownAction != i) {
            return false;
        }
        setDropdownAction((SettingsDropdownActionType) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.SettingsDropdownActionViewBinding
    public void setDropdownAction(@Nullable SettingsDropdownActionType settingsDropdownActionType) {
        updateRegistration(0, settingsDropdownActionType);
        this.mDropdownAction = settingsDropdownActionType;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.dropdownAction);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeDropdownAction((SettingsDropdownActionType) obj, i2);
    }

    private boolean onChangeDropdownAction(SettingsDropdownActionType settingsDropdownActionType, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i != BR.currentItem) {
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
        Object obj = null;
        SettingsDropdownActionType settingsDropdownActionType = this.mDropdownAction;
        int i = ((j & 7) > 0 ? 1 : ((j & 7) == 0 ? 0 : -1));
        if (!(i == 0 || settingsDropdownActionType == null)) {
            obj = settingsDropdownActionType.getCurrentItem();
        }
        if (i != 0) {
            this.dropdown.setSelectedItem(obj);
        }
    }
}
