package com.oculus.panelapp.anytimeui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsNullState;

public class AnytimeTabletAndroidSettingsNullStateBindingImpl extends AnytimeTabletAndroidSettingsNullStateBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    private final OCTextView mboundView1;
    @NonNull
    private final OCTextView mboundView2;

    public AnytimeTabletAndroidSettingsNullStateBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletAndroidSettingsNullStateBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1);
        this.mDirtyFlags = -1;
        this.mboundView0 = (LinearLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (OCTextView) objArr[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (OCTextView) objArr[2];
        this.mboundView2.setTag(null);
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
        if (BR.nullState != i) {
            return false;
        }
        setNullState((SettingsNullState) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsNullStateBinding
    public void setNullState(@Nullable SettingsNullState settingsNullState) {
        updateRegistration(0, settingsNullState);
        this.mNullState = settingsNullState;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.nullState);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeNullState((SettingsNullState) obj, i2);
    }

    private boolean onChangeNullState(SettingsNullState settingsNullState, int i) {
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
        String str;
        int i;
        String str2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SettingsNullState settingsNullState = this.mNullState;
        int i2 = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        String str3 = null;
        int i3 = 0;
        if (i2 != 0) {
            if (settingsNullState != null) {
                str3 = settingsNullState.getTitle();
                str2 = settingsNullState.getSubtitle();
            } else {
                str2 = null;
            }
            boolean z = true;
            boolean z2 = str3 == null;
            if (str2 != null) {
                z = false;
            }
            if (i2 != 0) {
                j |= z2 ? 8 : 4;
            }
            if ((j & 3) != 0) {
                j |= z ? 32 : 16;
            }
            int i4 = z2 ? 8 : 0;
            if (z) {
                i3 = 8;
            }
            str = str2;
            i = i3;
            i3 = i4;
        } else {
            str = null;
            i = 0;
        }
        if ((j & 3) != 0) {
            this.mboundView1.setVisibility(i3);
            TextViewBindingAdapter.setText(this.mboundView1, str3);
            this.mboundView2.setVisibility(i);
            TextViewBindingAdapter.setText(this.mboundView2, str);
        }
    }
}
