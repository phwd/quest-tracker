package com.oculus.panelapp.anytimeui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCLink;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveTextItem;

public class AnytimeTabletAndroidSettingsDescriptiveTextBindingImpl extends AnytimeTabletAndroidSettingsDescriptiveTextBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    @NonNull
    private final SettingsDescriptiveTextItem mboundView0;

    public AnytimeTabletAndroidSettingsDescriptiveTextBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletAndroidSettingsDescriptiveTextBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (OCLink) objArr[2], (OCButton) objArr[3], (OCTextView) objArr[1]);
        this.mDirtyFlags = -1;
        this.body.setTag(null);
        this.button.setTag(null);
        this.header.setTag(null);
        this.mboundView0 = (SettingsDescriptiveTextItem) objArr[0];
        this.mboundView0.setTag(null);
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
        if (BR.description != i) {
            return false;
        }
        setDescription((SettingsDescriptiveText) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsDescriptiveTextBinding
    public void setDescription(@Nullable SettingsDescriptiveText settingsDescriptiveText) {
        updateRegistration(0, settingsDescriptiveText);
        this.mDescription = settingsDescriptiveText;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.description);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeDescription((SettingsDescriptiveText) obj, i2);
    }

    private boolean onChangeDescription(SettingsDescriptiveText settingsDescriptiveText, int i) {
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
        boolean z;
        int i;
        String str;
        String str2;
        int i2;
        String str3;
        boolean z2;
        boolean z3;
        String str4;
        boolean z4;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SettingsDescriptiveText settingsDescriptiveText = this.mDescription;
        int i3 = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        String str5 = null;
        int i4 = 0;
        if (i3 != 0) {
            if (settingsDescriptiveText != null) {
                z4 = settingsDescriptiveText.isButtonVisible();
                str2 = settingsDescriptiveText.getButtonText();
                str4 = settingsDescriptiveText.getBodyUri();
                str = settingsDescriptiveText.getHeader();
                z3 = settingsDescriptiveText.isHeaderVisible();
                z = settingsDescriptiveText.isButtonEnabled();
                z2 = settingsDescriptiveText.isBodyVisible();
                str3 = settingsDescriptiveText.getBody();
            } else {
                str3 = null;
                str2 = null;
                str4 = null;
                str = null;
                z4 = false;
                z3 = false;
                z = false;
                z2 = false;
            }
            if (i3 != 0) {
                j |= z4 ? 32 : 16;
            }
            if ((j & 3) != 0) {
                j |= z3 ? 128 : 64;
            }
            if ((j & 3) != 0) {
                j |= z2 ? 8 : 4;
            }
            int i5 = z4 ? 0 : 8;
            i = z3 ? 0 : 8;
            if (!z2) {
                i4 = 8;
            }
            i2 = i5;
            str5 = str4;
        } else {
            str3 = null;
            str2 = null;
            str = null;
            i2 = 0;
            i = 0;
            z = false;
        }
        if ((j & 3) != 0) {
            TextViewBindingAdapter.setText(this.body, str3);
            OCLink.setUri(this.body, str5);
            this.body.setVisibility(i4);
            this.button.setEnabled(z);
            TextViewBindingAdapter.setText(this.button, str2);
            this.button.setVisibility(i2);
            TextViewBindingAdapter.setText(this.header, str);
            this.header.setVisibility(i);
        }
    }
}
