package com.oculus.panelapp.socialsettings.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.socialsettings.views.SocialSettingsMessengerAccountViewModel;
import com.oculus.socialplatform.R;

public class SocialSettingsMessengerAccountBindingImpl extends SocialSettingsMessengerAccountBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;
    @NonNull
    public final ConstraintLayout mboundView0;

    private boolean onChangeViewModel(SocialSettingsMessengerAccountViewModel socialSettingsMessengerAccountViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 86) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i != 87) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean z = false;
        String str = null;
        SocialSettingsMessengerAccountViewModel socialSettingsMessengerAccountViewModel = this.mViewModel;
        if ((15 & j) != 0) {
            if (!((j & 11) == 0 || socialSettingsMessengerAccountViewModel == null)) {
                z = !socialSettingsMessengerAccountViewModel.mIsSigningOut;
            }
            if (!((j & 13) == 0 || socialSettingsMessengerAccountViewModel == null)) {
                str = socialSettingsMessengerAccountViewModel.mUserName;
            }
        }
        if ((j & 13) != 0) {
            C11051qV.A02(this.messengerAccountProfileName, str);
        }
        if ((j & 11) != 0) {
            this.messengerAccountSignOutButton.setEnabled(z);
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
            this.mDirtyFlags = 8;
        }
        requestRebind();
    }

    @Override // com.oculus.panelapp.socialsettings.databinding.SocialSettingsMessengerAccountBinding
    public void setViewModel(@Nullable SocialSettingsMessengerAccountViewModel socialSettingsMessengerAccountViewModel) {
        updateRegistration(0, socialSettingsMessengerAccountViewModel);
        this.mViewModel = socialSettingsMessengerAccountViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.messenger_account_header, 3);
        sparseIntArray.put(R.id.messenger_account_body, 4);
        sparseIntArray.put(R.id.messenger_account_profile_picture, 5);
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((SocialSettingsMessengerAccountViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (62 != i) {
            return false;
        }
        setViewModel((SocialSettingsMessengerAccountViewModel) obj);
        return true;
    }

    public SocialSettingsMessengerAccountBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 6, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public SocialSettingsMessengerAccountBindingImpl(AbstractC003408r r12, View view, Object[] objArr) {
        super(r12, view, 1, (OCTextView) objArr[4], (OCTextView) objArr[3], (OCTextView) objArr[2], (ImageView) objArr[5], (OCButton) objArr[1]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.messengerAccountProfileName.setTag(null);
        this.messengerAccountSignOutButton.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
