package com.oculus.panelapp.anytimeui.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileView;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileViewModel;

public class AnytimeTabletEnterpriseProfileViewV2BindingImpl extends AnytimeTabletEnterpriseProfileViewV2Binding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(18);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final EnterpriseProfileView mboundView0;
    @NonNull
    private final ConstraintLayout mboundView10;
    @NonNull
    private final OCSpinner mboundView7;

    static {
        sIncludes.setIncludes(0, new String[]{"anytime_tablet_enterprise_profile_admin_keypad"}, new int[]{12}, new int[]{R.layout.anytime_tablet_enterprise_profile_admin_keypad});
        sViewsWithIds.put(R.id.enterprise_profile_tablet_title, 13);
        sViewsWithIds.put(R.id.enterprise_profile_company_logo, 14);
        sViewsWithIds.put(R.id.enterprise_profile_license_title, 15);
        sViewsWithIds.put(R.id.enterprise_profile_configuration_title, 16);
        sViewsWithIds.put(R.id.enterprise_profile_enterprise_profile_admin_mode_info, 17);
    }

    public AnytimeTabletEnterpriseProfileViewV2BindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 18, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletEnterpriseProfileViewV2BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (AnytimeTabletEnterpriseProfileAdminKeypadBinding) objArr[12], (OCButton) objArr[5], (OCButton) objArr[6], (ImageView) objArr[14], (OCTextView) objArr[1], (OCTextView) objArr[16], (OCButton) objArr[8], (OCTextView) objArr[17], (ImageView) objArr[11], (OCButton) objArr[9], (OCTextView) objArr[4], (OCTextView) objArr[3], (OCTextView) objArr[2], (OCTextView) objArr[15], (OCTextView) objArr[13]);
        this.mDirtyFlags = -1;
        this.enterpriseProfileCheckForUpdatesButton.setTag(null);
        this.enterpriseProfileCheckingForUpdatesButton.setTag(null);
        this.enterpriseProfileCompanyNameTitle.setTag(null);
        this.enterpriseProfileEnterAdminModeButton.setTag(null);
        this.enterpriseProfileEnterpriseProfileAdminModeInfoIcon.setTag(null);
        this.enterpriseProfileExitAdminModeButton.setTag(null);
        this.enterpriseProfileLastSyncTimeSubtitle.setTag(null);
        this.enterpriseProfileLastUpdateTimeSubtitle.setTag(null);
        this.enterpriseProfileLicenseSubtitle.setTag(null);
        this.mboundView0 = (EnterpriseProfileView) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView10 = (ConstraintLayout) objArr[10];
        this.mboundView10.setTag(null);
        this.mboundView7 = (OCSpinner) objArr[7];
        this.mboundView7.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4096;
        }
        this.enterpriseProfileAdminKeypad.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.enterpriseProfileAdminKeypad.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0018 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0018 }
            com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseProfileAdminKeypadBinding r0 = r4.enterpriseProfileAdminKeypad
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            r0 = 0
            return r0
        L_0x0018:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseProfileViewV2BindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((EnterpriseProfileViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseProfileViewV2Binding
    public void setViewModel(@Nullable EnterpriseProfileViewModel enterpriseProfileViewModel) {
        updateRegistration(1, enterpriseProfileViewModel);
        this.mViewModel = enterpriseProfileViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.enterpriseProfileAdminKeypad.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeEnterpriseProfileAdminKeypad((AnytimeTabletEnterpriseProfileAdminKeypadBinding) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangeViewModel((EnterpriseProfileViewModel) obj, i2);
    }

    private boolean onChangeEnterpriseProfileAdminKeypad(AnytimeTabletEnterpriseProfileAdminKeypadBinding anytimeTabletEnterpriseProfileAdminKeypadBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModel(EnterpriseProfileViewModel enterpriseProfileViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == BR.companyName) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == BR.licenseText) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == BR.lastUpdateTimeText) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == BR.lastSyncSubtitleText) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == BR.lastSyncTimeText) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == BR.updateCheckState) {
            synchronized (this) {
                this.mDirtyFlags |= 224;
            }
            return true;
        } else if (i == BR.isAdminModeEnabled) {
            synchronized (this) {
                this.mDirtyFlags |= 704;
            }
            return true;
        } else if (i == BR.isCheckForUpdatesVisible) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i == BR.isCheckingForUpdatesVisible) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i != BR.isAdminPinRequired) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0169, code lost:
        if (r38 == false) goto L_0x016e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x012f  */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 609
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseProfileViewV2BindingImpl.executeBindings():void");
    }
}
