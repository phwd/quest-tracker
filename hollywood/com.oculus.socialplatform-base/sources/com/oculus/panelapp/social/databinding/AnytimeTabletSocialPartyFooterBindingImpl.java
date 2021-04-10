package com.oculus.panelapp.social.databinding;

import X.AbstractC003408r;
import X.AnonymousClass0AS;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.socialplatform.R;
import com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding;

public class AnytimeTabletSocialPartyFooterBindingImpl extends AnytimeTabletSocialPartyFooterBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeSharePartyButton(CommonTabletRectangularButtonBinding commonTabletRectangularButtonBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeSocialViewModel(SocialViewModel socialViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == 110) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i != 107) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public boolean hasPendingBindings() {
        int i;
        synchronized (this) {
            i = (this.mDirtyFlags > 0 ? 1 : (this.mDirtyFlags == 0 ? 0 : -1));
        }
        if (i != 0 || this.primaryActionButton.hasPendingBindings() || this.sharePartyButton.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256;
        }
        this.primaryActionButton.invalidateAll();
        this.sharePartyButton.invalidateAll();
        requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyFooterBinding
    public void setSocialViewModel(@Nullable SocialViewModel socialViewModel) {
        updateRegistration(1, socialViewModel);
        this.mSocialViewModel = socialViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(89);
        super.requestRebind();
    }

    static {
        AnonymousClass1ui r3 = new AnonymousClass1ui(10);
        sIncludes = r3;
        r3.A00(new String[]{"common_tablet_rectangular_button", "common_tablet_rectangular_button"}, new int[]{2, 3}, new int[]{R.layout.common_tablet_rectangular_button, R.layout.common_tablet_rectangular_button});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.icon, 4);
        sparseIntArray.put(R.id.application_name, 5);
        sparseIntArray.put(R.id.destination_info_separator, 6);
        sparseIntArray.put(R.id.destination_name, 7);
        sparseIntArray.put(R.id.change_destination_button, 8);
        sparseIntArray.put(R.id.primary_action_button_spinner, 9);
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        boolean z;
        long j2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        String str = this.mStatusText;
        String str2 = null;
        SocialViewModel socialViewModel = this.mSocialViewModel;
        String str3 = this.mPrimaryActionButton;
        int i = 0;
        if ((450 & j) != 0) {
            long j3 = j & 322;
            if (j3 != 0) {
                if (socialViewModel != null) {
                    z = socialViewModel.getShouldShowSharePartyButton();
                } else {
                    z = false;
                }
                if (j3 != 0) {
                    if (z) {
                        j2 = 1024;
                    } else {
                        j2 = 512;
                    }
                    j |= j2;
                }
                if (!z) {
                    i = 8;
                }
            }
            if (!((j & 386) == 0 || socialViewModel == null)) {
                str2 = socialViewModel.getSharePartyButtonText();
            }
        }
        if ((288 & j) != 0) {
            this.primaryActionButton.setText(str3);
        }
        if ((j & 322) != 0) {
            this.sharePartyButton.mRoot.setVisibility(i);
        }
        if ((j & 386) != 0) {
            this.sharePartyButton.setText(str2);
        }
        if ((j & 272) != 0) {
            C11051qV.A02(this.statusText, str);
        }
        AnonymousClass1uW.executeBindingsOn(this.primaryActionButton);
        AnonymousClass1uW.executeBindingsOn(this.sharePartyButton);
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeSharePartyButton((CommonTabletRectangularButtonBinding) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangeSocialViewModel((SocialViewModel) obj, i2);
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyFooterBinding
    public void setPrimaryActionButton(@Nullable String str) {
        this.mPrimaryActionButton = str;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(105);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyFooterBinding
    public void setStatusText(@Nullable String str) {
        this.mStatusText = str;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(106);
        super.requestRebind();
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (101 == i) {
            setSecondaryActionButton((String) obj);
            return true;
        } else if (29 == i) {
            setParty((SocialParty) obj);
            return true;
        } else if (106 == i) {
            setStatusText((String) obj);
            return true;
        } else if (89 == i) {
            setSocialViewModel((SocialViewModel) obj);
            return true;
        } else if (105 != i) {
            return false;
        } else {
            setPrimaryActionButton((String) obj);
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public void setLifecycleOwner(@Nullable AnonymousClass0AS r2) {
        super.setLifecycleOwner(r2);
        this.primaryActionButton.setLifecycleOwner(r2);
        this.sharePartyButton.setLifecycleOwner(r2);
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyFooterBinding
    public void setParty(@Nullable SocialParty socialParty) {
        this.mParty = socialParty;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyFooterBinding
    public void setSecondaryActionButton(@Nullable String str) {
        this.mSecondaryActionButton = str;
    }

    public AnytimeTabletSocialPartyFooterBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 10, sIncludes, sViewsWithIds));
    }

    public AnytimeTabletSocialPartyFooterBindingImpl(AbstractC003408r r17, View view, Object[] objArr) {
        super(r17, view, 2, (OCTextView) objArr[5], (OCButton) objArr[8], (OCTextView) objArr[6], (OCTextView) objArr[7], (ImageView) objArr[4], (ConstraintLayout) objArr[0], (CommonTabletRectangularButtonBinding) objArr[2], (OCSpinner) objArr[9], (CommonTabletRectangularButtonBinding) objArr[3], (OCTextView) objArr[1]);
        this.mDirtyFlags = -1;
        this.partyFooter.setTag(null);
        this.statusText.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
