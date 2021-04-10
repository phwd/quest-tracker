package com.oculus.panelapp.androiddialog.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.integrity.block.FacebookBlockDialog;
import com.oculus.panelapp.androiddialog.dialogs.integrity.block.FacebookBlockDialogViewModel;

public class FacebookBlockDialogBindingImpl extends FacebookBlockDialogBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(29);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(0, new String[]{"dialog_title", "block_dialog_bullet", "block_dialog_bullet", "block_dialog_bullet", "block_dialog_bullet", "block_dialog_bullet", "dialog_action_space_secondary", "block_dialog_bullet", "block_dialog_bullet", "dialog_action_space_secondary"}, new int[]{9, 10, 11, 12, 13, 14, 15, 16, 17, 18}, new int[]{R.layout.dialog_title, R.layout.block_dialog_bullet, R.layout.block_dialog_bullet, R.layout.block_dialog_bullet, R.layout.block_dialog_bullet, R.layout.block_dialog_bullet, R.layout.dialog_action_space_secondary, R.layout.block_dialog_bullet, R.layout.block_dialog_bullet, R.layout.dialog_action_space_secondary});
        sViewsWithIds.put(R.id.left_guideline, 19);
        sViewsWithIds.put(R.id.right_guideline, 20);
        sViewsWithIds.put(R.id.back_button, 21);
        sViewsWithIds.put(R.id.block_title_icon, 22);
        sViewsWithIds.put(R.id.fb_block_see_posts_explanation, 23);
        sViewsWithIds.put(R.id.fb_block_tag_you_explanation, 24);
        sViewsWithIds.put(R.id.fb_block_invite_you_explanation, 25);
        sViewsWithIds.put(R.id.fb_block_start_conversation_explanation, 26);
        sViewsWithIds.put(R.id.fb_block_add_you_explanation, 27);
        sViewsWithIds.put(R.id.fb_unblock_title_icon, 28);
    }

    public FacebookBlockDialogBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 29, sIncludes, sViewsWithIds));
    }

    private FacebookBlockDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 11, (OCButton) objArr[21], (ImageView) objArr[22], (Group) objArr[1], (Group) objArr[2], (OCTextView) objArr[27], (BlockDialogBulletBinding) objArr[14], (DialogActionSpaceSecondaryBinding) objArr[15], (OCTextView) objArr[25], (BlockDialogBulletBinding) objArr[12], (OCTextView) objArr[23], (BlockDialogBulletBinding) objArr[10], (OCTextView) objArr[26], (BlockDialogBulletBinding) objArr[13], (OCTextView) objArr[24], (BlockDialogBulletBinding) objArr[11], (OCTextView) objArr[3], (OCTextView) objArr[5], (OCTextView) objArr[4], (DialogActionSpaceSecondaryBinding) objArr[18], (OCTextView) objArr[8], (BlockDialogBulletBinding) objArr[17], (OCTextView) objArr[7], (BlockDialogBulletBinding) objArr[16], (OCTextView) objArr[6], (ImageView) objArr[28], (Guideline) objArr[19], (Guideline) objArr[20], (DialogTitleBinding) objArr[9], (FacebookBlockDialog) objArr[0]);
        this.mDirtyFlags = -1;
        this.facebookBlockSection.setTag(null);
        this.facebookUnblockSection.setTag(null);
        this.fbBlockTitle.setTag(null);
        this.fbBlockWillNotOculusBlockExplanation.setTag(null);
        this.fbBlockWillUnfriendExplanation.setTag(null);
        this.fbUnblockMessengerStillBlockedExplanation.setTag(null);
        this.fbUnblockRefriendExplanation.setTag(null);
        this.fbUnblockTitle.setTag(null);
        this.view.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 262144;
        }
        this.title.invalidateAll();
        this.fbBlockSeePostsExplanationBullet.invalidateAll();
        this.fbBlockTagYouExplanationBullet.invalidateAll();
        this.fbBlockInviteYouExplanationBullet.invalidateAll();
        this.fbBlockStartConversationExplanationBullet.invalidateAll();
        this.fbBlockAddYouExplanationBullet.invalidateAll();
        this.fbBlockButtonActionSpace.invalidateAll();
        this.fbUnblockRefriendExplanationBullet.invalidateAll();
        this.fbUnblockMessengerStillBlockedExplanationBullet.invalidateAll();
        this.fbUnblockButtonActionSpace.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.fbBlockSeePostsExplanationBullet.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r4.fbBlockTagYouExplanationBullet.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (r4.fbBlockInviteYouExplanationBullet.hasPendingBindings() == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        if (r4.fbBlockStartConversationExplanationBullet.hasPendingBindings() == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0040, code lost:
        if (r4.fbBlockAddYouExplanationBullet.hasPendingBindings() == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0042, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r4.fbBlockButtonActionSpace.hasPendingBindings() == false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0052, code lost:
        if (r4.fbUnblockRefriendExplanationBullet.hasPendingBindings() == false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0054, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005b, code lost:
        if (r4.fbUnblockMessengerStillBlockedExplanationBullet.hasPendingBindings() == false) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005d, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0064, code lost:
        if (r4.fbUnblockButtonActionSpace.hasPendingBindings() == false) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0066, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0067, code lost:
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
        // Method dump skipped, instructions count: 108
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.databinding.FacebookBlockDialogBindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((FacebookBlockDialogViewModel) obj);
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.FacebookBlockDialogBinding
    public void setViewModel(@Nullable FacebookBlockDialogViewModel facebookBlockDialogViewModel) {
        updateRegistration(9, facebookBlockDialogViewModel);
        this.mViewModel = facebookBlockDialogViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.title.setLifecycleOwner(lifecycleOwner);
        this.fbBlockSeePostsExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.fbBlockTagYouExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.fbBlockInviteYouExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.fbBlockStartConversationExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.fbBlockAddYouExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.fbBlockButtonActionSpace.setLifecycleOwner(lifecycleOwner);
        this.fbUnblockRefriendExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.fbUnblockMessengerStillBlockedExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.fbUnblockButtonActionSpace.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeFbBlockInviteYouExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 1:
                return onChangeFbUnblockMessengerStillBlockedExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 2:
                return onChangeFbBlockTagYouExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 3:
                return onChangeFbBlockSeePostsExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 4:
                return onChangeFbUnblockButtonActionSpace((DialogActionSpaceSecondaryBinding) obj, i2);
            case 5:
                return onChangeFbBlockStartConversationExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 6:
                return onChangeFbUnblockRefriendExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 7:
                return onChangeFbBlockAddYouExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 8:
                return onChangeTitle((DialogTitleBinding) obj, i2);
            case 9:
                return onChangeViewModel((FacebookBlockDialogViewModel) obj, i2);
            case 10:
                return onChangeFbBlockButtonActionSpace((DialogActionSpaceSecondaryBinding) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeFbBlockInviteYouExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeFbUnblockMessengerStillBlockedExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeFbBlockTagYouExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeFbBlockSeePostsExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeFbUnblockButtonActionSpace(DialogActionSpaceSecondaryBinding dialogActionSpaceSecondaryBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeFbBlockStartConversationExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeFbUnblockRefriendExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeFbBlockAddYouExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeTitle(DialogTitleBinding dialogTitleBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeViewModel(FacebookBlockDialogViewModel facebookBlockDialogViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i == BR.blockSectionVisibility) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        } else if (i == BR.unblockSectionVisibility) {
            synchronized (this) {
                this.mDirtyFlags |= 4096;
            }
            return true;
        } else if (i == BR.targetName) {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        } else if (i == BR.blockButtonText) {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        } else if (i == BR.blockButtonEnabled) {
            synchronized (this) {
                this.mDirtyFlags |= 32768;
            }
            return true;
        } else if (i == BR.unblockButtonText) {
            synchronized (this) {
                this.mDirtyFlags |= 65536;
            }
            return true;
        } else if (i != BR.unblockButtonEnabled) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 131072;
            }
            return true;
        }
    }

    private boolean onChangeFbBlockButtonActionSpace(DialogActionSpaceSecondaryBinding dialogActionSpaceSecondaryBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1024;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        long j2;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        boolean z;
        String str8;
        int i;
        boolean z2;
        String str9;
        int i2;
        boolean z3;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        FacebookBlockDialogViewModel facebookBlockDialogViewModel = this.mViewModel;
        boolean z4 = false;
        if ((522752 & j) != 0) {
            i = ((j & 266752) == 0 || facebookBlockDialogViewModel == null) ? 0 : facebookBlockDialogViewModel.getUnblockSectionVisibility();
            boolean blockButtonEnabled = ((j & 295424) == 0 || facebookBlockDialogViewModel == null) ? false : facebookBlockDialogViewModel.getBlockButtonEnabled();
            int blockSectionVisibility = ((j & 264704) == 0 || facebookBlockDialogViewModel == null) ? 0 : facebookBlockDialogViewModel.getBlockSectionVisibility();
            String blockButtonText = ((j & 279040) == 0 || facebookBlockDialogViewModel == null) ? null : facebookBlockDialogViewModel.getBlockButtonText();
            if ((j & 270848) != 0) {
                String targetName = facebookBlockDialogViewModel != null ? facebookBlockDialogViewModel.getTargetName() : null;
                str14 = String.format(this.fbBlockWillNotOculusBlockExplanation.getResources().getString(R.string.facebook_block_dialog_block_section_will_not_oculus_block_explanation), targetName);
                str13 = String.format(this.fbUnblockMessengerStillBlockedExplanation.getResources().getString(R.string.facebook_block_dialog_block_section_messages_calls_blocked_explanation), targetName);
                str12 = String.format(this.fbUnblockRefriendExplanation.getResources().getString(R.string.facebook_block_dialog_unblock_section_readd_friend_explanation), targetName);
                str11 = String.format(this.fbUnblockTitle.getResources().getString(R.string.facebook_block_dialog_unblock_section_title), targetName);
                str10 = String.format(getRoot().getResources().getString(R.string.facebook_block_dialog_title), targetName);
                str16 = String.format(this.fbBlockWillUnfriendExplanation.getResources().getString(R.string.facebook_block_dialog_block_section_will_unfriend_explanation), targetName);
                str15 = String.format(this.fbBlockTitle.getResources().getString(R.string.facebook_block_dialog_block_section_title), targetName);
            } else {
                str16 = null;
                str15 = null;
                str14 = null;
                str13 = null;
                str12 = null;
                str11 = null;
                str10 = null;
            }
            j2 = 0;
            if (!((j & 393728) == 0 || facebookBlockDialogViewModel == null)) {
                z4 = facebookBlockDialogViewModel.getUnblockButtonEnabled();
            }
            if ((j & 328192) == 0 || facebookBlockDialogViewModel == null) {
                str = str11;
                str7 = str10;
                z = z4;
                i2 = blockSectionVisibility;
                str8 = null;
            } else {
                str8 = facebookBlockDialogViewModel.getUnblockButtonText();
                str = str11;
                str7 = str10;
                z = z4;
                i2 = blockSectionVisibility;
            }
            str3 = str13;
            str2 = str12;
            str4 = str16;
            str5 = str14;
            str9 = blockButtonText;
            str6 = str15;
            z2 = blockButtonEnabled;
        } else {
            j2 = 0;
            i2 = 0;
            z2 = false;
            i = 0;
            z = false;
            str9 = null;
            str8 = null;
            str7 = null;
            str6 = null;
            str5 = null;
            str4 = null;
            str3 = null;
            str2 = null;
            str = null;
        }
        if ((j & 264704) != j2) {
            z3 = z;
            this.facebookBlockSection.setVisibility(i2);
        } else {
            z3 = z;
        }
        if ((j & 266752) != j2) {
            this.facebookUnblockSection.setVisibility(i);
        }
        if ((j & 279040) != j2) {
            this.fbBlockButtonActionSpace.setButtonText(str9);
        }
        if ((j & 295424) != j2) {
            this.fbBlockButtonActionSpace.setIsButtonEnabled(z2);
        }
        if ((270848 & j) != j2) {
            TextViewBindingAdapter.setText(this.fbBlockTitle, str6);
            TextViewBindingAdapter.setText(this.fbBlockWillNotOculusBlockExplanation, str5);
            TextViewBindingAdapter.setText(this.fbBlockWillUnfriendExplanation, str4);
            TextViewBindingAdapter.setText(this.fbUnblockMessengerStillBlockedExplanation, str3);
            TextViewBindingAdapter.setText(this.fbUnblockRefriendExplanation, str2);
            TextViewBindingAdapter.setText(this.fbUnblockTitle, str);
            this.title.setDialogTitleText(str7);
        }
        if ((328192 & j) != 0) {
            this.fbUnblockButtonActionSpace.setButtonText(str8);
        }
        if ((393728 & j) != 0) {
            this.fbUnblockButtonActionSpace.setIsButtonEnabled(z3);
        }
        if ((j & 262144) != 0) {
            this.fbUnblockMessengerStillBlockedExplanationBullet.setDisplayForBlocked(true);
            this.fbUnblockRefriendExplanationBullet.setDisplayForBlocked(true);
        }
        executeBindingsOn(this.title);
        executeBindingsOn(this.fbBlockSeePostsExplanationBullet);
        executeBindingsOn(this.fbBlockTagYouExplanationBullet);
        executeBindingsOn(this.fbBlockInviteYouExplanationBullet);
        executeBindingsOn(this.fbBlockStartConversationExplanationBullet);
        executeBindingsOn(this.fbBlockAddYouExplanationBullet);
        executeBindingsOn(this.fbBlockButtonActionSpace);
        executeBindingsOn(this.fbUnblockRefriendExplanationBullet);
        executeBindingsOn(this.fbUnblockMessengerStillBlockedExplanationBullet);
        executeBindingsOn(this.fbUnblockButtonActionSpace);
    }
}
