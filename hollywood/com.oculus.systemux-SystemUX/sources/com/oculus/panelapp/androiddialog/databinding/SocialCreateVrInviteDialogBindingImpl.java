package com.oculus.panelapp.androiddialog.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel;
import com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteDialog;
import com.oculus.panelapp.androiddialog.dialogs.social.create_vr_invite.CreateVrInviteViewModel;
import com.oculus.panelapp.androiddialog.dialogs.social.list_item.SocialListItem;

public class SocialCreateVrInviteDialogBindingImpl extends SocialCreateVrInviteDialogBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(21);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(0, new String[]{"dialog_title", "social_menu_item_toggle", "social_create_vr_invite_list_item"}, new int[]{13, 14, 15}, new int[]{R.layout.dialog_title, R.layout.social_menu_item_toggle, R.layout.social_create_vr_invite_list_item});
        sViewsWithIds.put(R.id.social_create_party_link_sharing, 16);
        sViewsWithIds.put(R.id.selected_app_header, 17);
        sViewsWithIds.put(R.id.selected_app_background, 18);
        sViewsWithIds.put(R.id.selected_app_image, 19);
        sViewsWithIds.put(R.id.create_vr_invite_secondary_button, 20);
    }

    public SocialCreateVrInviteDialogBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 21, sIncludes, sViewsWithIds));
    }

    private SocialCreateVrInviteDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 5, (SocialCreateVrInviteListItemBinding) objArr[15], (OCButton) objArr[3], (OCSpinner) objArr[12], (OCButton) objArr[11], (OCButton) objArr[20], (OCTextView) objArr[8], (OCSpinner) objArr[9], (OCRecyclerView) objArr[10], (Group) objArr[1], (View) objArr[18], (OCTextView) objArr[7], (OCTextView) objArr[5], (Group) objArr[2], (OCTextView) objArr[6], (OCTextView) objArr[17], (ImageView) objArr[19], (SocialListItem) objArr[4], (CreateVrInviteDialog) objArr[0], (SocialListItem) objArr[16], (SocialMenuItemToggleBinding) objArr[14], (DialogTitleBinding) objArr[13]);
        this.mDirtyFlags = -1;
        this.createVrInviteBackButton.setTag(null);
        this.createVrInviteLoadingSpinner.setTag(null);
        this.createVrInvitePrimaryButton.setTag(null);
        this.destinationsHeader.setTag(null);
        this.destinationsHeaderSpinner.setTag(null);
        this.destinationsView.setTag(null);
        this.profileGroup.setTag(null);
        this.selectedAppDescription.setTag(null);
        this.selectedAppDisplayName.setTag(null);
        this.selectedAppGroup.setTag(null);
        this.selectedAppGroupJoinDetails.setTag(null);
        this.socialCreatePartyAsRow.setTag(null);
        this.socialCreatePartyDialog.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 524288;
        }
        this.title.invalidateAll();
        this.socialCreatePartyReminder.invalidateAll();
        this.chooseLaterListItem.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.socialCreatePartyReminder.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r4.chooseLaterListItem.hasPendingBindings() == false) goto L_0x0028;
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
            com.oculus.panelapp.androiddialog.databinding.SocialMenuItemToggleBinding r0 = r4.socialCreatePartyReminder
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r1
        L_0x001f:
            com.oculus.panelapp.androiddialog.databinding.SocialCreateVrInviteListItemBinding r0 = r4.chooseLaterListItem
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
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.databinding.SocialCreateVrInviteDialogBindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.socialUserViewModel == i) {
            setSocialUserViewModel((SocialUserViewModel) obj);
        } else if (BR.viewModel != i) {
            return false;
        } else {
            setViewModel((CreateVrInviteViewModel) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.SocialCreateVrInviteDialogBinding
    public void setSocialUserViewModel(@Nullable SocialUserViewModel socialUserViewModel) {
        updateRegistration(0, socialUserViewModel);
        this.mSocialUserViewModel = socialUserViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.socialUserViewModel);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.SocialCreateVrInviteDialogBinding
    public void setViewModel(@Nullable CreateVrInviteViewModel createVrInviteViewModel) {
        updateRegistration(4, createVrInviteViewModel);
        this.mViewModel = createVrInviteViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.title.setLifecycleOwner(lifecycleOwner);
        this.socialCreatePartyReminder.setLifecycleOwner(lifecycleOwner);
        this.chooseLaterListItem.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeSocialUserViewModel((SocialUserViewModel) obj, i2);
        }
        if (i == 1) {
            return onChangeChooseLaterListItem((SocialCreateVrInviteListItemBinding) obj, i2);
        }
        if (i == 2) {
            return onChangeSocialCreatePartyReminder((SocialMenuItemToggleBinding) obj, i2);
        }
        if (i == 3) {
            return onChangeTitle((DialogTitleBinding) obj, i2);
        }
        if (i != 4) {
            return false;
        }
        return onChangeViewModel((CreateVrInviteViewModel) obj, i2);
    }

    private boolean onChangeSocialUserViewModel(SocialUserViewModel socialUserViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.alias) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i != BR.profilePhotoUrl) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
    }

    private boolean onChangeChooseLaterListItem(SocialCreateVrInviteListItemBinding socialCreateVrInviteListItemBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeSocialCreatePartyReminder(SocialMenuItemToggleBinding socialMenuItemToggleBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
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

    private boolean onChangeViewModel(CreateVrInviteViewModel createVrInviteViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == BR.inviteStep) {
            synchronized (this) {
                this.mDirtyFlags |= 116352;
            }
            return true;
        } else if (i == BR.profileVisible) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == BR.selectedApp) {
            synchronized (this) {
                this.mDirtyFlags |= 14592;
            }
            return true;
        } else if (i == BR.titleText) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i == BR.chooseLaterVisible) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        } else if (i == BR.selectedAppDisplayName) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        } else if (i == BR.selectedAppGroupJoinDetails) {
            synchronized (this) {
                this.mDirtyFlags |= 4096;
            }
            return true;
        } else if (i == BR.selectedAppDisplayShortDescription) {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        } else if (i == BR.isDestinationsSpinnerVisible) {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        } else if (i == BR.primaryActionButtonEnabled) {
            synchronized (this) {
                this.mDirtyFlags |= 32768;
            }
            return true;
        } else if (i == BR.isCreatingOrUpdating) {
            synchronized (this) {
                this.mDirtyFlags |= 229376;
            }
            return true;
        } else if (i != BR.primaryActionButtonText) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 65536;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x01a4, code lost:
        if (r43 != false) goto L_0x01a6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x017f A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x018e  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x01bf A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01d1 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x01e3 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x015e A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x016d A[ADDED_TO_REGION] */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 857
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.databinding.SocialCreateVrInviteDialogBindingImpl.executeBindings():void");
    }
}
