package com.oculus.panelapp.socialsettings.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.common.socialtablet.navbar.SocialTabletSideNav;
import com.oculus.panelapp.socialsettings.views.SocialSettingsActiveStatus;
import com.oculus.panelapp.socialsettings.views.SocialSettingsMessengerAccount;
import com.oculus.panelapp.socialsettings.views.SocialSettingsNotifications;
import com.oculus.panelapp.socialsettings.views.SocialSettingsSideNav;
import com.oculus.panelapp.socialsettings.views.SocialSettingsView;
import com.oculus.panelapp.socialsettings.views.SocialSettingsViewModel;
import com.oculus.panelapp.socialsettings.views.SocialSettingsViewType;
import com.oculus.socialplatform.R;

public class SocialSettingsTabletMainBindingImpl extends SocialSettingsTabletMainBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeViewModel(SocialSettingsViewModel socialSettingsViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i != 84) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        int i;
        int i2;
        long j2;
        long j3;
        long j4;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SocialSettingsViewType socialSettingsViewType = null;
        SocialSettingsViewModel socialSettingsViewModel = this.mViewModel;
        long j5 = j & 7;
        int i3 = 0;
        if (j5 != 0) {
            if (socialSettingsViewModel != null) {
                socialSettingsViewType = socialSettingsViewModel.mSelectedSetting;
            }
            boolean z = true;
            boolean z2 = false;
            if (socialSettingsViewType == SocialSettingsViewType.MESSENGER_ACCOUNT) {
                z2 = true;
            }
            boolean z3 = false;
            if (socialSettingsViewType == SocialSettingsViewType.ACTIVE_STATUS) {
                z3 = true;
            }
            if (socialSettingsViewType != SocialSettingsViewType.NOTIFICATIONS) {
                z = false;
            }
            if (j5 != 0) {
                if (z2) {
                    j4 = 64;
                } else {
                    j4 = 32;
                }
                j |= j4;
            }
            if ((j & 7) != 0) {
                if (z3) {
                    j3 = 16;
                } else {
                    j3 = 8;
                }
                j |= j3;
            }
            if ((j & 7) != 0) {
                if (z) {
                    j2 = 256;
                } else {
                    j2 = 128;
                }
                j |= j2;
            }
            i = 8;
            if (z2) {
                i = 0;
            }
            i2 = 8;
            if (z3) {
                i2 = 0;
            }
            if (!z) {
                i3 = 8;
            }
        } else {
            i = 0;
            i2 = 0;
        }
        if ((j & 7) != 0) {
            this.socialSettingsActiveStatus.setVisibility(i2);
            this.socialSettingsMessengerAccount.setVisibility(i);
            this.socialSettingsNotifications.setVisibility(i3);
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.socialsettings.databinding.SocialSettingsTabletMainBinding
    public void setViewModel(@Nullable SocialSettingsViewModel socialSettingsViewModel) {
        updateRegistration(0, socialSettingsViewModel);
        this.mViewModel = socialSettingsViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.social_tablet_side_nav, 4);
        sparseIntArray.put(R.id.social_settings_side_nav, 5);
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((SocialSettingsViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((SocialSettingsViewModel) obj);
        return true;
    }

    public SocialSettingsTabletMainBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 6, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public SocialSettingsTabletMainBindingImpl(AbstractC003408r r13, View view, Object[] objArr) {
        super(r13, view, 1, (SocialSettingsActiveStatus) objArr[1], (SocialSettingsMessengerAccount) objArr[3], (SocialSettingsNotifications) objArr[2], (SocialSettingsSideNav) objArr[5], (SocialSettingsView) objArr[0], (SocialTabletSideNav) objArr[4]);
        this.mDirtyFlags = -1;
        this.socialSettingsActiveStatus.setTag(null);
        this.socialSettingsMessengerAccount.setTag(null);
        this.socialSettingsNotifications.setTag(null);
        this.socialSettingsView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
