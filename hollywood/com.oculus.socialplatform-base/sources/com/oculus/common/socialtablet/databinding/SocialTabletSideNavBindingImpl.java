package com.oculus.common.socialtablet.databinding;

import X.AbstractC003408r;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.socialtablet.navbar.SocialTabletSideNavViewModel;
import com.oculus.ocui.OCButton;
import com.oculus.socialplatform.R;

public class SocialTabletSideNavBindingImpl extends SocialTabletSideNavBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes = null;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;
    @NonNull
    public final ConstraintLayout mboundView0;

    private boolean onChangeSocialTabletSideNavViewModel(SocialTabletSideNavViewModel socialTabletSideNavViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 231) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 230) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i != 229) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
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
            this.mDirtyFlags = 16;
        }
        requestRebind();
    }

    @Override // com.oculus.common.socialtablet.databinding.SocialTabletSideNavBinding
    public void setSocialTabletSideNavViewModel(@Nullable SocialTabletSideNavViewModel socialTabletSideNavViewModel) {
        updateRegistration(0, socialTabletSideNavViewModel);
        this.mSocialTabletSideNavViewModel = socialTabletSideNavViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(232);
        super.requestRebind();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.side_nav_profile_switcher, 4);
        sparseIntArray.put(R.id.side_nav_profile_switcher_border, 5);
        sparseIntArray.put(R.id.side_nav_profile_switcher_badge, 6);
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        int i;
        int i2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SocialTabletSideNavViewModel socialTabletSideNavViewModel = this.mSocialTabletSideNavViewModel;
        int i3 = 0;
        if ((31 & j) != 0) {
            if ((j & 21) == 0 || socialTabletSideNavViewModel == null) {
                i = 0;
            } else {
                i = socialTabletSideNavViewModel.getChatVisibility();
            }
            if ((j & 25) == 0 || socialTabletSideNavViewModel == null) {
                i2 = 0;
            } else {
                i2 = socialTabletSideNavViewModel.getSettingsVisibility();
            }
            if (!((j & 19) == 0 || socialTabletSideNavViewModel == null)) {
                i3 = socialTabletSideNavViewModel.getPeopleVisibility();
            }
        } else {
            i = 0;
            i2 = 0;
        }
        if ((21 & j) != 0) {
            this.sideNavChatButton.setVisibility(i);
        }
        if ((19 & j) != 0) {
            this.sideNavPeopleButton.setVisibility(i3);
        }
        if ((j & 25) != 0) {
            this.sideNavSettingsButton.setVisibility(i2);
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeSocialTabletSideNavViewModel((SocialTabletSideNavViewModel) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (232 != i) {
            return false;
        }
        setSocialTabletSideNavViewModel((SocialTabletSideNavViewModel) obj);
        return true;
    }

    public SocialTabletSideNavBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 7, (AnonymousClass1ui) null, sViewsWithIds));
    }

    public SocialTabletSideNavBindingImpl(AbstractC003408r r13, View view, Object[] objArr) {
        super(r13, view, 1, (OCButton) objArr[2], (OCButton) objArr[1], (ImageView) objArr[4], (View) objArr[6], (View) objArr[5], (OCButton) objArr[3]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.sideNavChatButton.setTag(null);
        this.sideNavPeopleButton.setTag(null);
        this.sideNavSettingsButton.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
