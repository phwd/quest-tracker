package com.oculus.panelapp.socialsettings.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCLink;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.ocui.OCToggle;
import com.oculus.panelapp.socialsettings.views.SocialSettingsActiveStatus;
import com.oculus.panelapp.socialsettings.views.SocialSettingsActiveStatusViewModel;

public class SocialSettingsActiveStatusBindingImpl extends SocialSettingsActiveStatusBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds = null;
    public long mDirtyFlags;
    @NonNull
    public final ConstraintLayout mboundView0;

    private boolean onChangeViewModel(SocialSettingsActiveStatusViewModel socialSettingsActiveStatusViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i != 88) {
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
        boolean z;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SocialSettingsActiveStatusViewModel socialSettingsActiveStatusViewModel = this.mViewModel;
        long j4 = j & 7;
        int i2 = 0;
        if (j4 != 0) {
            if (socialSettingsActiveStatusViewModel != null) {
                z = socialSettingsActiveStatusViewModel.mIsLoaded;
            } else {
                z = false;
            }
            if (j4 != 0) {
                if (z) {
                    j2 = j | 16;
                    j3 = 64;
                } else {
                    j2 = j | 8;
                    j3 = 32;
                }
                j = j2 | j3;
            }
            i = 8;
            if (!z) {
                i = 0;
                i2 = 8;
            }
        } else {
            i = 0;
        }
        if ((7 & j) != 0) {
            this.activeStatusBody.setVisibility(i2);
            this.activeStatusDescription.setVisibility(i2);
            this.activeStatusHeader.setVisibility(i2);
            this.activeStatusLoadingSpinner.setVisibility(i);
            this.activeStatusToggle.setVisibility(i2);
            this.activeStatusToggleView.setVisibility(i2);
        }
        if ((j & 4) != 0) {
            OCLink.setUri(this.activeStatusBody, SocialSettingsActiveStatus.ACTIVE_STATUS_LEARN_MORE);
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

    @Override // com.oculus.panelapp.socialsettings.databinding.SocialSettingsActiveStatusBinding
    public void setViewModel(@Nullable SocialSettingsActiveStatusViewModel socialSettingsActiveStatusViewModel) {
        updateRegistration(0, socialSettingsActiveStatusViewModel);
        this.mViewModel = socialSettingsActiveStatusViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((SocialSettingsActiveStatusViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((SocialSettingsActiveStatusViewModel) obj);
        return true;
    }

    public SocialSettingsActiveStatusBindingImpl(@Nullable AbstractC003408r r3, @NonNull View view) {
        this(r3, view, AnonymousClass1uW.mapBindings(r3, view, 7, (AnonymousClass1ui) null, (SparseIntArray) null));
    }

    public SocialSettingsActiveStatusBindingImpl(AbstractC003408r r13, View view, Object[] objArr) {
        super(r13, view, 1, (OCLink) objArr[3], (OCTextView) objArr[2], (OCTextView) objArr[1], (OCSpinner) objArr[4], (OCToggle) objArr[6], (RelativeLayout) objArr[5]);
        this.mDirtyFlags = -1;
        this.activeStatusBody.setTag(null);
        this.activeStatusDescription.setTag(null);
        this.activeStatusHeader.setTag(null);
        this.activeStatusLoadingSpinner.setTag(null);
        this.activeStatusToggle.setTag(null);
        this.activeStatusToggleView.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
