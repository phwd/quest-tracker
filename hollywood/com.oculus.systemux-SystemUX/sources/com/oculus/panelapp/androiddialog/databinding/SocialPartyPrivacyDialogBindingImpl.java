package com.oculus.panelapp.androiddialog.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyDialog;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialPartyPrivacyViewModel;

public class SocialPartyPrivacyDialogBindingImpl extends SocialPartyPrivacyDialogBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(9);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(0, new String[]{"dialog_title", "social_menu_item_toggle", "social_menu_item_toggle"}, new int[]{4, 5, 6}, new int[]{R.layout.dialog_title, R.layout.social_menu_item_toggle, R.layout.social_menu_item_toggle});
        sViewsWithIds.put(R.id.social_party_privacy_link_and_share, 7);
        sViewsWithIds.put(R.id.close_button, 8);
    }

    public SocialPartyPrivacyDialogBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 9, sIncludes, sViewsWithIds));
    }

    private SocialPartyPrivacyDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 4, (OCButton) objArr[8], (SocialMenuItemToggleBinding) objArr[5], (OCTextView) objArr[2], (SocialMenuItemToggleBinding) objArr[6], (OCTextView) objArr[1], (OCButton) objArr[3], (SocialPartyPrivacyDialog) objArr[0], (View) objArr[7], (DialogTitleBinding) objArr[4]);
        this.mDirtyFlags = -1;
        this.link.setTag(null);
        this.linkTitle.setTag(null);
        this.shareButton.setTag(null);
        this.socialPartyPrivacyDialog.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256;
        }
        this.title.invalidateAll();
        this.joinPolicyToggleMenuItem.invalidateAll();
        this.linkPolicyToggleMenuItem.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.joinPolicyToggleMenuItem.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r4.linkPolicyToggleMenuItem.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0028, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r4.title.hasPendingBindings() == false) goto L_0x0016;
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
            long r0 = r4.mDirtyFlags     // Catch:{ all -> 0x002a }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r1 = 1
            if (r0 == 0) goto L_0x000c
            monitor-exit(r4)     // Catch:{ all -> 0x002a }
            return r1
        L_0x000c:
            monitor-exit(r4)     // Catch:{ all -> 0x002a }
            com.oculus.panelapp.androiddialog.databinding.DialogTitleBinding r0 = r4.title
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r1
        L_0x0016:
            com.oculus.panelapp.androiddialog.databinding.SocialMenuItemToggleBinding r0 = r4.joinPolicyToggleMenuItem
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            com.oculus.panelapp.androiddialog.databinding.SocialMenuItemToggleBinding r0 = r4.linkPolicyToggleMenuItem
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0028
            return r1
        L_0x0028:
            r0 = 0
            return r0
        L_0x002a:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.databinding.SocialPartyPrivacyDialogBindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.privacyViewModel != i) {
            return false;
        }
        setPrivacyViewModel((SocialPartyPrivacyViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.SocialPartyPrivacyDialogBinding
    public void setPrivacyViewModel(@Nullable SocialPartyPrivacyViewModel socialPartyPrivacyViewModel) {
        updateRegistration(2, socialPartyPrivacyViewModel);
        this.mPrivacyViewModel = socialPartyPrivacyViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.privacyViewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.title.setLifecycleOwner(lifecycleOwner);
        this.joinPolicyToggleMenuItem.setLifecycleOwner(lifecycleOwner);
        this.linkPolicyToggleMenuItem.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeLinkPolicyToggleMenuItem((SocialMenuItemToggleBinding) obj, i2);
        }
        if (i == 1) {
            return onChangeJoinPolicyToggleMenuItem((SocialMenuItemToggleBinding) obj, i2);
        }
        if (i == 2) {
            return onChangePrivacyViewModel((SocialPartyPrivacyViewModel) obj, i2);
        }
        if (i != 3) {
            return false;
        }
        return onChangeTitle((DialogTitleBinding) obj, i2);
    }

    private boolean onChangeLinkPolicyToggleMenuItem(SocialMenuItemToggleBinding socialMenuItemToggleBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeJoinPolicyToggleMenuItem(SocialMenuItemToggleBinding socialMenuItemToggleBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangePrivacyViewModel(SocialPartyPrivacyViewModel socialPartyPrivacyViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == BR.isInviteOnly) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == BR.partyType) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == BR.hasLinkInvite) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i != BR.partyUrl) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
    }

    private boolean onChangeTitle(DialogTitleBinding dialogTitleBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0079  */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 341
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.databinding.SocialPartyPrivacyDialogBindingImpl.executeBindings():void");
    }
}
