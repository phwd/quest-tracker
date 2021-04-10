package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AbstractC003408r;
import X.AnonymousClass0AS;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import com.facebook.acra.ErrorReporter;
import com.facebook.breakpad.BreakpadManager;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteDialog;
import com.oculus.panelapp.socialandroidbackpanel.views.create_party.CreateVrInviteViewModel;
import com.oculus.panelapp.socialandroidbackpanel.views.list_item.ListItem;
import com.oculus.panelapp.socialandroidbackpanel.views.social.SocialUserViewModel;
import com.oculus.socialplatform.R;
import okio.SegmentPool;

public class SocialCreateVrInviteDialogBindingImpl extends SocialCreateVrInviteDialogBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeChooseLaterListItem(SocialCreateVrInviteListItemBinding socialCreateVrInviteListItemBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeSocialCreatePartyReminder(SocialMenuItemToggleBinding socialMenuItemToggleBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeSocialUserViewModel(SocialUserViewModel socialUserViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 219) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i != 202) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        }
    }

    private boolean onChangeTitle(DialogTitleBinding dialogTitleBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModel(CreateVrInviteViewModel createVrInviteViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == 225) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == 192) {
            synchronized (this) {
                this.mDirtyFlags |= 116352;
            }
            return true;
        } else if (i == 194) {
            synchronized (this) {
                this.mDirtyFlags |= 14592;
            }
            return true;
        } else if (i == 70) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i == 223) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        } else if (i == 185) {
            synchronized (this) {
                this.mDirtyFlags |= BreakpadManager.MD_FB_WITH_UNWINDSTACK_STREAM;
            }
            return true;
        } else if (i == 226) {
            synchronized (this) {
                this.mDirtyFlags |= BreakpadManager.MD_FB_INSTALL_ALT_STACK;
            }
            return true;
        } else if (i == 224) {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        } else if (i == 209) {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        } else if (i == 227) {
            synchronized (this) {
                this.mDirtyFlags |= 229376;
            }
            return true;
        } else if (i == 198) {
            synchronized (this) {
                this.mDirtyFlags |= 32768;
            }
            return true;
        } else if (i != 197) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= SegmentPool.MAX_SIZE;
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
        if (i != 0 || this.title.hasPendingBindings() || this.socialCreatePartyReminder.hasPendingBindings() || this.chooseLaterListItem.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = ErrorReporter.SIGQUIT_MAX_REPORT_SIZE;
        }
        this.title.invalidateAll();
        this.socialCreatePartyReminder.invalidateAll();
        this.chooseLaterListItem.invalidateAll();
        requestRebind();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.databinding.SocialCreateVrInviteDialogBinding
    public void setSocialUserViewModel(@Nullable SocialUserViewModel socialUserViewModel) {
        updateRegistration(0, socialUserViewModel);
        this.mSocialUserViewModel = socialUserViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(228);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.databinding.SocialCreateVrInviteDialogBinding
    public void setViewModel(@Nullable CreateVrInviteViewModel createVrInviteViewModel) {
        updateRegistration(4, createVrInviteViewModel);
        this.mViewModel = createVrInviteViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(62);
        super.requestRebind();
    }

    static {
        AnonymousClass1ui r5 = new AnonymousClass1ui(21);
        sIncludes = r5;
        r5.A00(new String[]{"dialog_title", "social_menu_item_toggle", "social_create_vr_invite_list_item"}, new int[]{13, 14, 15}, new int[]{R.layout.dialog_title, R.layout.social_menu_item_toggle, R.layout.social_create_vr_invite_list_item});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.social_create_party_link_sharing, 16);
        sparseIntArray.put(R.id.selected_app_header, 17);
        sparseIntArray.put(R.id.selected_app_background, 18);
        sparseIntArray.put(R.id.selected_app_image, 19);
        sparseIntArray.put(R.id.create_vr_invite_secondary_button, 20);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
        if (r10 == null) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007d, code lost:
        if (r7 != false) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00b1, code lost:
        if (r9.mSelectedApp == null) goto L_0x00b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00f3, code lost:
        if (r16 != false) goto L_0x00f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0134, code lost:
        if (r18 != false) goto L_0x0136;
     */
    @Override // X.AnonymousClass1uW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 783
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.socialandroidbackpanel.databinding.SocialCreateVrInviteDialogBindingImpl.executeBindings():void");
    }

    @Override // X.AnonymousClass1uW
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

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (228 == i) {
            setSocialUserViewModel((SocialUserViewModel) obj);
            return true;
        } else if (62 != i) {
            return false;
        } else {
            setViewModel((CreateVrInviteViewModel) obj);
            return true;
        }
    }

    @Override // X.AnonymousClass1uW
    public void setLifecycleOwner(@Nullable AnonymousClass0AS r2) {
        super.setLifecycleOwner(r2);
        this.title.setLifecycleOwner(r2);
        this.socialCreatePartyReminder.setLifecycleOwner(r2);
        this.chooseLaterListItem.setLifecycleOwner(r2);
    }

    public SocialCreateVrInviteDialogBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 21, sIncludes, sViewsWithIds));
    }

    public SocialCreateVrInviteDialogBindingImpl(AbstractC003408r r48, View view, Object[] objArr) {
        super(r48, view, 5, (SocialCreateVrInviteListItemBinding) objArr[15], (OCButton) objArr[3], (OCSpinner) objArr[12], (OCButton) objArr[11], (OCButton) objArr[20], (OCTextView) objArr[8], (OCSpinner) objArr[9], (OCRecyclerView) objArr[10], (Group) objArr[1], (View) objArr[18], (OCTextView) objArr[7], (OCTextView) objArr[5], (Group) objArr[2], (OCTextView) objArr[6], (OCTextView) objArr[17], (ImageView) objArr[19], (ListItem) objArr[4], (CreateVrInviteDialog) objArr[0], (ListItem) objArr[16], (SocialMenuItemToggleBinding) objArr[14], (DialogTitleBinding) objArr[13]);
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
}
