package com.oculus.panelapp.anytimeui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oculus.ocui.OCBackButton;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.AndroidSettingsView;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.AndroidSettingsViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection;

public class AnytimeTabletAndroidSettingsViewBindingImpl extends AnytimeTabletAndroidSettingsViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final AndroidSettingsView mboundView0;

    static {
        sViewsWithIds.put(R.id.title, 4);
        sViewsWithIds.put(R.id.toolbar_guideline, 5);
        sViewsWithIds.put(R.id.settings_content, 6);
    }

    public AnytimeTabletAndroidSettingsViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletAndroidSettingsViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (OCBackButton) objArr[1], (View) objArr[3], (OCRecyclerView) objArr[6], (OCTextView) objArr[4], (OCButton) objArr[2], (Guideline) objArr[5]);
        this.mDirtyFlags = -1;
        this.backButton.setTag(null);
        this.internalDisclaimer.setTag(null);
        this.mboundView0 = (AndroidSettingsView) objArr[0];
        this.mboundView0.setTag(null);
        this.toolbarButton.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
        if (BR.section == i) {
            setSection((SettingsSection) obj);
        } else if (BR.viewModel != i) {
            return false;
        } else {
            setViewModel((AndroidSettingsViewModel) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsViewBinding
    public void setSection(@Nullable SettingsSection settingsSection) {
        updateRegistration(0, settingsSection);
        this.mSection = settingsSection;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.section);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsViewBinding
    public void setViewModel(@Nullable AndroidSettingsViewModel androidSettingsViewModel) {
        this.mViewModel = androidSettingsViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
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
        return onChangeSection((SettingsSection) obj, i2);
    }

    private boolean onChangeSection(SettingsSection settingsSection, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.toolbarButtonTitle) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i != BR.hasToolbarButton) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0047  */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsViewBindingImpl.executeBindings():void");
    }
}
