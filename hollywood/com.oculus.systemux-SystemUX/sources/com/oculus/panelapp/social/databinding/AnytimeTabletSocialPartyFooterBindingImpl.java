package com.oculus.panelapp.social.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.social.BR;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.tablet.R;
import com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding;

public class AnytimeTabletSocialPartyFooterBindingImpl extends AnytimeTabletSocialPartyFooterBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(10);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(0, new String[]{"common_tablet_rectangular_button", "common_tablet_rectangular_button"}, new int[]{2, 3}, new int[]{R.layout.common_tablet_rectangular_button, R.layout.common_tablet_rectangular_button});
        sViewsWithIds.put(com.oculus.panelapp.social.R.id.icon, 4);
        sViewsWithIds.put(com.oculus.panelapp.social.R.id.application_name, 5);
        sViewsWithIds.put(com.oculus.panelapp.social.R.id.destination_info_separator, 6);
        sViewsWithIds.put(com.oculus.panelapp.social.R.id.destination_name, 7);
        sViewsWithIds.put(com.oculus.panelapp.social.R.id.change_destination_button, 8);
        sViewsWithIds.put(com.oculus.panelapp.social.R.id.primary_action_button_spinner, 9);
    }

    public AnytimeTabletSocialPartyFooterBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletSocialPartyFooterBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (OCTextView) objArr[5], (OCButton) objArr[8], (OCTextView) objArr[6], (OCTextView) objArr[7], (ImageView) objArr[4], (ConstraintLayout) objArr[0], (CommonTabletRectangularButtonBinding) objArr[2], (OCSpinner) objArr[9], (CommonTabletRectangularButtonBinding) objArr[3], (OCTextView) objArr[1]);
        this.mDirtyFlags = -1;
        this.partyFooter.setTag(null);
        this.statusText.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256;
        }
        this.primaryActionButton.invalidateAll();
        this.sharePartyButton.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.sharePartyButton.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.primaryActionButton.hasPendingBindings() == false) goto L_0x0016;
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
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x0021 }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding r0 = r4.primaryActionButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.oculus.tablet.databinding.CommonTabletRectangularButtonBinding r0 = r4.sharePartyButton
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            r0 = 0
            return r0
        L_0x0021:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyFooterBindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.secondaryActionButton == i) {
            setSecondaryActionButton((String) obj);
        } else if (BR.party == i) {
            setParty((SocialParty) obj);
        } else if (BR.statusText == i) {
            setStatusText((String) obj);
        } else if (BR.socialViewModel == i) {
            setSocialViewModel((SocialViewModel) obj);
        } else if (BR.primaryActionButton != i) {
            return false;
        } else {
            setPrimaryActionButton((String) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyFooterBinding
    public void setSecondaryActionButton(@Nullable String str) {
        this.mSecondaryActionButton = str;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyFooterBinding
    public void setParty(@Nullable SocialParty socialParty) {
        this.mParty = socialParty;
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyFooterBinding
    public void setStatusText(@Nullable String str) {
        this.mStatusText = str;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.statusText);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyFooterBinding
    public void setSocialViewModel(@Nullable SocialViewModel socialViewModel) {
        updateRegistration(1, socialViewModel);
        this.mSocialViewModel = socialViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.socialViewModel);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.social.databinding.AnytimeTabletSocialPartyFooterBinding
    public void setPrimaryActionButton(@Nullable String str) {
        this.mPrimaryActionButton = str;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.primaryActionButton);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.primaryActionButton.setLifecycleOwner(lifecycleOwner);
        this.sharePartyButton.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeSharePartyButton((CommonTabletRectangularButtonBinding) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangeSocialViewModel((SocialViewModel) obj, i2);
    }

    private boolean onChangeSharePartyButton(CommonTabletRectangularButtonBinding commonTabletRectangularButtonBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeSocialViewModel(SocialViewModel socialViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == BR.shouldShowSharePartyButton) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i != BR.sharePartyButtonText) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        boolean z;
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
            int i2 = ((j & 322) > 0 ? 1 : ((j & 322) == 0 ? 0 : -1));
            if (i2 != 0) {
                if (socialViewModel != null) {
                    z = socialViewModel.getShouldShowSharePartyButton();
                } else {
                    z = false;
                }
                if (i2 != 0) {
                    j |= z ? 1024 : 512;
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
            this.sharePartyButton.getRoot().setVisibility(i);
        }
        if ((j & 386) != 0) {
            this.sharePartyButton.setText(str2);
        }
        if ((j & 272) != 0) {
            TextViewBindingAdapter.setText(this.statusText, str);
        }
        executeBindingsOn(this.primaryActionButton);
        executeBindingsOn(this.sharePartyButton);
    }
}
