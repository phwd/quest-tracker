package com.oculus.panelapp.anytimeui.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsNavigationActionType;

public class SettingsNavigationActionViewBindingImpl extends SettingsNavigationActionViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public SettingsNavigationActionViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 1, sIncludes, sViewsWithIds));
    }

    private SettingsNavigationActionViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (View) objArr[0]);
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
        if (BR.navigationActionType != i) {
            return false;
        }
        setNavigationActionType((SettingsNavigationActionType) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.SettingsNavigationActionViewBinding
    public void setNavigationActionType(@Nullable SettingsNavigationActionType settingsNavigationActionType) {
        updateRegistration(0, settingsNavigationActionType);
        this.mNavigationActionType = settingsNavigationActionType;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.navigationActionType);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeNavigationActionType((SettingsNavigationActionType) obj, i2);
    }

    private boolean onChangeNavigationActionType(SettingsNavigationActionType settingsNavigationActionType, int i) {
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
        int i;
        View view;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable drawable = null;
        boolean z = false;
        SettingsNavigationActionType settingsNavigationActionType = this.mNavigationActionType;
        int i2 = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (i2 != 0) {
            if (settingsNavigationActionType != null) {
                z = settingsNavigationActionType.isInternal();
            }
            if (i2 != 0) {
                j |= z ? 8 : 4;
            }
            if (z) {
                view = this.button;
                i = R.drawable.ocicon_button_chevron_right;
            } else {
                view = this.button;
                i = R.drawable.ocicon_button_open_tab;
            }
            drawable = getDrawableFromResource(view, i);
        }
        if ((j & 3) != 0) {
            ViewBindingAdapter.setBackground(this.button, drawable);
        }
    }
}
