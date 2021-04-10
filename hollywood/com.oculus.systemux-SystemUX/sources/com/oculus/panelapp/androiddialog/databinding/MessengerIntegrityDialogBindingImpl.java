package com.oculus.panelapp.androiddialog.databinding;

import android.content.res.Resources;
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
import com.facebook.systrace.Systrace;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCRecyclerView;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.integrity.MessengerIntegrityDialog;
import com.oculus.panelapp.androiddialog.dialogs.integrity.MessengerIntegrityDialogViewModel;

public class MessengerIntegrityDialogBindingImpl extends MessengerIntegrityDialogBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = new ViewDataBinding.IncludedLayouts(43);
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sIncludes.setIncludes(0, new String[]{"dialog_title", "block_dialog_bullet", "block_dialog_bullet", "block_dialog_bullet", "block_dialog_bullet", "block_dialog_bullet", "block_dialog_bullet", "block_dialog_bullet", "block_dialog_bullet", "block_dialog_bullet", "block_dialog_bullet", "block_dialog_bullet"}, new int[]{24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35}, new int[]{R.layout.dialog_title, R.layout.block_dialog_bullet, R.layout.block_dialog_bullet, R.layout.block_dialog_bullet, R.layout.block_dialog_bullet, R.layout.block_dialog_bullet, R.layout.block_dialog_bullet, R.layout.block_dialog_bullet, R.layout.block_dialog_bullet, R.layout.block_dialog_bullet, R.layout.block_dialog_bullet, R.layout.block_dialog_bullet});
        sViewsWithIds.put(R.id.left_guideline, 36);
        sViewsWithIds.put(R.id.right_guideline, 37);
        sViewsWithIds.put(R.id.back_button, 38);
        sViewsWithIds.put(R.id.messenger_block_groups_rooms_explanation, 39);
        sViewsWithIds.put(R.id.messenger_block_no_oculus_block_explanation, 40);
        sViewsWithIds.put(R.id.messenger_unblock_blocked_sent_messages_explanation, 41);
        sViewsWithIds.put(R.id.fb_section_title_icon, 42);
    }

    public MessengerIntegrityDialogBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 43, sIncludes, sViewsWithIds));
    }

    private MessengerIntegrityDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 13, (OCSpinner) objArr[9], (OCButton) objArr[38], (Group) objArr[6], (Group) objArr[4], (Group) objArr[5], (Group) objArr[7], (OCTextView) objArr[20], (BlockDialogBulletBinding) objArr[33], (OCTextView) objArr[19], (BlockDialogBulletBinding) objArr[32], (OCTextView) objArr[15], (BlockDialogBulletBinding) objArr[31], (OCButton) objArr[23], (OCTextView) objArr[18], (ImageView) objArr[42], (OCTextView) objArr[22], (BlockDialogBulletBinding) objArr[35], (OCTextView) objArr[21], (BlockDialogBulletBinding) objArr[34], (Guideline) objArr[36], (OCTextView) objArr[39], (BlockDialogBulletBinding) objArr[27], (OCTextView) objArr[12], (BlockDialogBulletBinding) objArr[25], (OCTextView) objArr[13], (BlockDialogBulletBinding) objArr[26], (OCTextView) objArr[40], (BlockDialogBulletBinding) objArr[28], (Group) objArr[2], (Group) objArr[1], (OCButton) objArr[16], (OCTextView) objArr[11], (ImageView) objArr[10], (OCTextView) objArr[41], (BlockDialogBulletBinding) objArr[30], (OCTextView) objArr[14], (BlockDialogBulletBinding) objArr[29], (Group) objArr[3], (OCRecyclerView) objArr[8], (Guideline) objArr[37], (View) objArr[17], (DialogTitleBinding) objArr[24], (MessengerIntegrityDialog) objArr[0]);
        this.mDirtyFlags = -1;
        this.activeStatusLoadingSpinner.setTag(null);
        this.facebookBlockSection.setTag(null);
        this.facebookBlockedMessengerUnblockSection.setTag(null);
        this.facebookSection.setTag(null);
        this.facebookUnblockSection.setTag(null);
        this.fbBlockMessengerAlsoBlockedExplanation.setTag(null);
        this.fbBlockUnfriendExplanation.setTag(null);
        this.fbBlockedCannotUnblockMessagesCallsExplanation.setTag(null);
        this.fbSectionButton.setTag(null);
        this.fbSectionTitle.setTag(null);
        this.fbUnblockMessengerStillBlockedExplanation.setTag(null);
        this.fbUnblockRefriendExplanation.setTag(null);
        this.messengerBlockMessagesCallsExplanation.setTag(null);
        this.messengerBlockNoFacebookBlockExplanation.setTag(null);
        this.messengerBlockSection.setTag(null);
        this.messengerSection.setTag(null);
        this.messengerSectionButton.setTag(null);
        this.messengerSectionTitle.setTag(null);
        this.messengerSectionTitleIcon.setTag(null);
        this.messengerUnblockMessagesCallsExplanation.setTag(null);
        this.messengerUnblockSection.setTag(null);
        this.participantList.setTag(null);
        this.sectionDivider.setTag(null);
        this.view.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 17179869184L;
        }
        this.title.invalidateAll();
        this.messengerBlockMessagesCallsExplanationBullet.invalidateAll();
        this.messengerBlockNoFacebookBlockExplanationBullet.invalidateAll();
        this.messengerBlockGroupsRoomsExplanationBullet.invalidateAll();
        this.messengerBlockNoOculusBlockExplanationBullet.invalidateAll();
        this.messengerUnblockMessagesCallsExplanationBullet.invalidateAll();
        this.messengerUnblockBlockedSentMessagesExplanationBullet.invalidateAll();
        this.fbBlockedCannotUnblockMessagesCallsExplanationBullet.invalidateAll();
        this.fbBlockUnfriendExplanationBullet.invalidateAll();
        this.fbBlockMessengerAlsoBlockedExplanationBullet.invalidateAll();
        this.fbUnblockRefriendExplanationBullet.invalidateAll();
        this.fbUnblockMessengerStillBlockedExplanationBullet.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r4.messengerBlockMessagesCallsExplanationBullet.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r4.messengerBlockNoFacebookBlockExplanationBullet.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (r4.messengerBlockGroupsRoomsExplanationBullet.hasPendingBindings() == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        if (r4.messengerBlockNoOculusBlockExplanationBullet.hasPendingBindings() == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0040, code lost:
        if (r4.messengerUnblockMessagesCallsExplanationBullet.hasPendingBindings() == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0042, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r4.messengerUnblockBlockedSentMessagesExplanationBullet.hasPendingBindings() == false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0052, code lost:
        if (r4.fbBlockedCannotUnblockMessagesCallsExplanationBullet.hasPendingBindings() == false) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0054, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005b, code lost:
        if (r4.fbBlockUnfriendExplanationBullet.hasPendingBindings() == false) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005d, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0064, code lost:
        if (r4.fbBlockMessengerAlsoBlockedExplanationBullet.hasPendingBindings() == false) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0066, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006d, code lost:
        if (r4.fbUnblockRefriendExplanationBullet.hasPendingBindings() == false) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006f, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0076, code lost:
        if (r4.fbUnblockMessengerStillBlockedExplanationBullet.hasPendingBindings() == false) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0078, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0079, code lost:
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
        // Method dump skipped, instructions count: 126
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.databinding.MessengerIntegrityDialogBindingImpl.hasPendingBindings():boolean");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.resources == i) {
            setResources((Resources) obj);
        } else if (BR.viewModel != i) {
            return false;
        } else {
            setViewModel((MessengerIntegrityDialogViewModel) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.MessengerIntegrityDialogBinding
    public void setResources(@Nullable Resources resources) {
        this.mResources = resources;
        synchronized (this) {
            this.mDirtyFlags |= 8192;
        }
        notifyPropertyChanged(BR.resources);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.MessengerIntegrityDialogBinding
    public void setViewModel(@Nullable MessengerIntegrityDialogViewModel messengerIntegrityDialogViewModel) {
        updateRegistration(11, messengerIntegrityDialogViewModel);
        this.mViewModel = messengerIntegrityDialogViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.title.setLifecycleOwner(lifecycleOwner);
        this.messengerBlockMessagesCallsExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.messengerBlockNoFacebookBlockExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.messengerBlockGroupsRoomsExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.messengerBlockNoOculusBlockExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.messengerUnblockMessagesCallsExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.messengerUnblockBlockedSentMessagesExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.fbBlockedCannotUnblockMessagesCallsExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.fbBlockUnfriendExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.fbBlockMessengerAlsoBlockedExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.fbUnblockRefriendExplanationBullet.setLifecycleOwner(lifecycleOwner);
        this.fbUnblockMessengerStillBlockedExplanationBullet.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeMessengerBlockMessagesCallsExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 1:
                return onChangeMessengerBlockNoOculusBlockExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 2:
                return onChangeMessengerBlockNoFacebookBlockExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 3:
                return onChangeFbUnblockMessengerStillBlockedExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 4:
                return onChangeMessengerBlockGroupsRoomsExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 5:
                return onChangeFbBlockMessengerAlsoBlockedExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 6:
                return onChangeMessengerUnblockBlockedSentMessagesExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 7:
                return onChangeMessengerUnblockMessagesCallsExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 8:
                return onChangeFbBlockedCannotUnblockMessagesCallsExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 9:
                return onChangeFbUnblockRefriendExplanationBullet((BlockDialogBulletBinding) obj, i2);
            case 10:
                return onChangeTitle((DialogTitleBinding) obj, i2);
            case 11:
                return onChangeViewModel((MessengerIntegrityDialogViewModel) obj, i2);
            case 12:
                return onChangeFbBlockUnfriendExplanationBullet((BlockDialogBulletBinding) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeMessengerBlockMessagesCallsExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeMessengerBlockNoOculusBlockExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMessengerBlockNoFacebookBlockExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeFbUnblockMessengerStillBlockedExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeMessengerBlockGroupsRoomsExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeFbBlockMessengerAlsoBlockedExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeMessengerUnblockBlockedSentMessagesExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeMessengerUnblockMessagesCallsExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeFbBlockedCannotUnblockMessagesCallsExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeFbUnblockRefriendExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeTitle(DialogTitleBinding dialogTitleBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1024;
        }
        return true;
    }

    private boolean onChangeViewModel(MessengerIntegrityDialogViewModel messengerIntegrityDialogViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        } else if (i == BR.messengerSectionVisibility) {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        } else if (i == BR.messengerBlockSectionVisibility) {
            synchronized (this) {
                this.mDirtyFlags |= 32768;
            }
            return true;
        } else if (i == BR.messengerUnblockSectionVisibility) {
            synchronized (this) {
                this.mDirtyFlags |= 65536;
            }
            return true;
        } else if (i == BR.fbBlockedMessengerUnblockSectionVisibility) {
            synchronized (this) {
                this.mDirtyFlags |= 131072;
            }
            return true;
        } else if (i == BR.fbSectionVisibility) {
            synchronized (this) {
                this.mDirtyFlags |= 262144;
            }
            return true;
        } else if (i == BR.fbBlockSectionVisibility) {
            synchronized (this) {
                this.mDirtyFlags |= 524288;
            }
            return true;
        } else if (i == BR.fbUnblockSectionVisibility) {
            synchronized (this) {
                this.mDirtyFlags |= 1048576;
            }
            return true;
        } else if (i == BR.dialogTitle) {
            synchronized (this) {
                this.mDirtyFlags |= 2097152;
            }
            return true;
        } else if (i == BR.participantSelectorVisibility) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_COMPONENTS;
            }
            return true;
        } else if (i == BR.blockUnblockViewSpinnerVisibility) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_VIEW_PERF_HARNESS;
            }
            return true;
        } else if (i == BR.messengerSectionTitleAlpha) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_REACT_FRESCO;
            }
            return true;
        } else if (i == BR.messengerSectionTitle) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_REACT_VIEW;
            }
            return true;
        } else if (i == BR.targetName) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_JS_VM;
            }
            return true;
        } else if (i == BR.messengerSectionButtonEnabled) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_REACT_JS_VM_CALLS;
            }
            return true;
        } else if (i == BR.messengerSectionButtonText) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_PROFILO;
            }
            return true;
        } else if (i == BR.sectionDividerVisibility) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_COMPONENT_SCRIPT;
            }
            return true;
        } else if (i == BR.fbSectionTitle) {
            synchronized (this) {
                this.mDirtyFlags |= 1073741824;
            }
            return true;
        } else if (i == BR.fbUnblockMessengerExplanation) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_APP_CORE_INFRA;
            }
            return true;
        } else if (i == BR.fbSectionButtonEnabled) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_COMPACTDISK;
            }
            return true;
        } else if (i != BR.fbSectionButtonText) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 8589934592L;
            }
            return true;
        }
    }

    private boolean onChangeFbBlockUnfriendExplanationBullet(BlockDialogBulletBinding blockDialogBulletBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4096;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        String str;
        int i8;
        int i9;
        int i10;
        String str2;
        String str3;
        boolean z;
        int i11;
        int i12;
        String str4;
        String str5;
        String str6;
        boolean z2;
        float f;
        long j2;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        int i13;
        int i14;
        int i15;
        int i16;
        String str14;
        String str15;
        String str16;
        String str17;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Resources resources = this.mResources;
        MessengerIntegrityDialogViewModel messengerIntegrityDialogViewModel = this.mViewModel;
        int i17 = ((17246988288L & j) > 0 ? 1 : ((17246988288L & j) == 0 ? 0 : -1));
        int i18 = 0;
        if (i17 != 0) {
            int i19 = R.string.messenger_block_no_facebook_block_explanation;
            i5 = R.string.messenger_block_messages_calls_explanation;
            int i20 = R.string.facebook_unblock_refriend_explanation;
            i3 = R.string.messenger_block_messenger_also_blocked_explanation;
            i2 = R.string.messenger_block_unfriend_explanation;
            i = R.string.messenger_unblock_messages_calls_explanation;
            i7 = i20;
            i4 = i19;
            i6 = R.string.messenger_integrity_dialog_facebook_blocked_cannot_messenger_unblock_explanation;
        } else {
            i7 = 0;
            i6 = 0;
            i5 = 0;
            i4 = 0;
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        String str18 = null;
        if ((j & 34359732224L) != 0) {
            int messengerUnblockSectionVisibility = ((j & 17179936768L) == 0 || messengerIntegrityDialogViewModel == null) ? 0 : messengerIntegrityDialogViewModel.getMessengerUnblockSectionVisibility();
            int fbBlockedMessengerUnblockSectionVisibility = ((j & 17180002304L) == 0 || messengerIntegrityDialogViewModel == null) ? 0 : messengerIntegrityDialogViewModel.getFbBlockedMessengerUnblockSectionVisibility();
            int participantSelectorVisibility = ((j & 17184065536L) == 0 || messengerIntegrityDialogViewModel == null) ? 0 : messengerIntegrityDialogViewModel.getParticipantSelectorVisibility();
            String messengerSectionTitle = ((j & 17213425664L) == 0 || messengerIntegrityDialogViewModel == null) ? null : messengerIntegrityDialogViewModel.getMessengerSectionTitle();
            String fbUnblockMessengerExplanation = ((j & 19327354880L) == 0 || messengerIntegrityDialogViewModel == null) ? null : messengerIntegrityDialogViewModel.getFbUnblockMessengerExplanation();
            int fbSectionVisibility = ((j & 17180133376L) == 0 || messengerIntegrityDialogViewModel == null) ? 0 : messengerIntegrityDialogViewModel.getFbSectionVisibility();
            String fbSectionButtonText = ((j & 25769805824L) == 0 || messengerIntegrityDialogViewModel == null) ? null : messengerIntegrityDialogViewModel.getFbSectionButtonText();
            boolean messengerSectionButtonEnabled = ((j & 17314088960L) == 0 || messengerIntegrityDialogViewModel == null) ? false : messengerIntegrityDialogViewModel.getMessengerSectionButtonEnabled();
            boolean fbSectionButtonEnabled = ((j & 21474838528L) == 0 || messengerIntegrityDialogViewModel == null) ? false : messengerIntegrityDialogViewModel.getFbSectionButtonEnabled();
            int fbUnblockSectionVisibility = ((j & 17180919808L) == 0 || messengerIntegrityDialogViewModel == null) ? 0 : messengerIntegrityDialogViewModel.getFbUnblockSectionVisibility();
            int blockUnblockViewSpinnerVisibility = ((j & 17188259840L) == 0 || messengerIntegrityDialogViewModel == null) ? 0 : messengerIntegrityDialogViewModel.getBlockUnblockViewSpinnerVisibility();
            String dialogTitle = ((j & 17181968384L) == 0 || messengerIntegrityDialogViewModel == null) ? null : messengerIntegrityDialogViewModel.getDialogTitle();
            if (i17 != 0) {
                String targetName = messengerIntegrityDialogViewModel != null ? messengerIntegrityDialogViewModel.getTargetName() : null;
                if (resources != null) {
                    str16 = resources.getString(i5, targetName);
                    str12 = resources.getString(i6, targetName);
                    str15 = resources.getString(i, targetName);
                    str9 = resources.getString(i3, targetName);
                    str8 = resources.getString(i2, targetName);
                    str10 = resources.getString(i4, targetName);
                    str17 = resources.getString(i7, targetName);
                    j2 = 0;
                    int messengerSectionVisibility = ((j & 17179887616L) != 0 || messengerIntegrityDialogViewModel == null) ? 0 : messengerIntegrityDialogViewModel.getMessengerSectionVisibility();
                    String fbSectionTitle = ((j & 18253613056L) != 0 || messengerIntegrityDialogViewModel == null) ? null : messengerIntegrityDialogViewModel.getFbSectionTitle();
                    int messengerBlockSectionVisibility = ((j & 17179904000L) != 0 || messengerIntegrityDialogViewModel == null) ? 0 : messengerIntegrityDialogViewModel.getMessengerBlockSectionVisibility();
                    int sectionDividerVisibility = ((j & 17716742144L) != 0 || messengerIntegrityDialogViewModel == null) ? 0 : messengerIntegrityDialogViewModel.getSectionDividerVisibility();
                    if (!((j & 17448306688L) == 0 || messengerIntegrityDialogViewModel == null)) {
                        str18 = messengerIntegrityDialogViewModel.getMessengerSectionButtonText();
                    }
                    float messengerSectionTitleAlpha = ((j & 17196648448L) != 0 || messengerIntegrityDialogViewModel == null) ? 0.0f : messengerIntegrityDialogViewModel.getMessengerSectionTitleAlpha();
                    if (!((j & 17180395520L) == 0 || messengerIntegrityDialogViewModel == null)) {
                        i18 = messengerIntegrityDialogViewModel.getFbBlockSectionVisibility();
                    }
                    str13 = str17;
                    i11 = messengerSectionVisibility;
                    str5 = fbSectionTitle;
                    i16 = i18;
                    i12 = messengerBlockSectionVisibility;
                    f = messengerSectionTitleAlpha;
                    i10 = messengerUnblockSectionVisibility;
                    str3 = str18;
                    i15 = fbBlockedMessengerUnblockSectionVisibility;
                    i9 = participantSelectorVisibility;
                    str2 = messengerSectionTitle;
                    str4 = fbUnblockMessengerExplanation;
                    str6 = fbSectionButtonText;
                    z = messengerSectionButtonEnabled;
                    z2 = fbSectionButtonEnabled;
                    i13 = fbUnblockSectionVisibility;
                    i18 = blockUnblockViewSpinnerVisibility;
                    str = dialogTitle;
                    i8 = sectionDividerVisibility;
                    str7 = str15;
                    str11 = str16;
                    i14 = fbSectionVisibility;
                }
            }
            str17 = null;
            str16 = null;
            str12 = null;
            str15 = null;
            str10 = null;
            str9 = null;
            str8 = null;
            j2 = 0;
            if ((j & 17179887616L) != 0) {
            }
            if ((j & 18253613056L) != 0) {
            }
            if ((j & 17179904000L) != 0) {
            }
            if ((j & 17716742144L) != 0) {
            }
            str18 = messengerIntegrityDialogViewModel.getMessengerSectionButtonText();
            if ((j & 17196648448L) != 0) {
            }
            i18 = messengerIntegrityDialogViewModel.getFbBlockSectionVisibility();
            str13 = str17;
            i11 = messengerSectionVisibility;
            str5 = fbSectionTitle;
            i16 = i18;
            i12 = messengerBlockSectionVisibility;
            f = messengerSectionTitleAlpha;
            i10 = messengerUnblockSectionVisibility;
            str3 = str18;
            i15 = fbBlockedMessengerUnblockSectionVisibility;
            i9 = participantSelectorVisibility;
            str2 = messengerSectionTitle;
            str4 = fbUnblockMessengerExplanation;
            str6 = fbSectionButtonText;
            z = messengerSectionButtonEnabled;
            z2 = fbSectionButtonEnabled;
            i13 = fbUnblockSectionVisibility;
            i18 = blockUnblockViewSpinnerVisibility;
            str = dialogTitle;
            i8 = sectionDividerVisibility;
            str7 = str15;
            str11 = str16;
            i14 = fbSectionVisibility;
        } else {
            j2 = 0;
            i16 = 0;
            i15 = 0;
            i14 = 0;
            i13 = 0;
            z2 = false;
            i12 = 0;
            i11 = 0;
            z = false;
            i10 = 0;
            i9 = 0;
            i8 = 0;
            str13 = null;
            str12 = null;
            str11 = null;
            str10 = null;
            str9 = null;
            str8 = null;
            str7 = null;
            str6 = null;
            str5 = null;
            str4 = null;
            str3 = null;
            str2 = null;
            str = null;
            f = 0.0f;
        }
        if ((j & 17188259840L) != j2) {
            str14 = str7;
            this.activeStatusLoadingSpinner.setVisibility(i18);
        } else {
            str14 = str7;
        }
        if ((j & 17180395520L) != j2) {
            this.facebookBlockSection.setVisibility(i16);
        }
        if ((j & 17180002304L) != j2) {
            this.facebookBlockedMessengerUnblockSection.setVisibility(i15);
        }
        if ((j & 17180133376L) != j2) {
            this.facebookSection.setVisibility(i14);
        }
        if ((j & 17180919808L) != j2) {
            this.facebookUnblockSection.setVisibility(i13);
        }
        if (i17 != 0) {
            TextViewBindingAdapter.setText(this.fbBlockMessengerAlsoBlockedExplanation, str9);
            TextViewBindingAdapter.setText(this.fbBlockUnfriendExplanation, str8);
            TextViewBindingAdapter.setText(this.fbBlockedCannotUnblockMessagesCallsExplanation, str12);
            TextViewBindingAdapter.setText(this.fbUnblockRefriendExplanation, str13);
            TextViewBindingAdapter.setText(this.messengerBlockMessagesCallsExplanation, str11);
            TextViewBindingAdapter.setText(this.messengerBlockNoFacebookBlockExplanation, str10);
            TextViewBindingAdapter.setText(this.messengerUnblockMessagesCallsExplanation, str14);
        }
        if ((j & 17196648448L) != 0) {
            this.fbBlockedCannotUnblockMessagesCallsExplanationBullet.setAlpha(f);
            if (getBuildSdkInt() >= 11) {
                this.messengerSectionTitle.setAlpha(f);
                this.messengerSectionTitleIcon.setAlpha(f);
            }
        }
        if ((21474838528L & j) != 0) {
            this.fbSectionButton.setEnabled(z2);
        }
        if ((j & 25769805824L) != 0) {
            TextViewBindingAdapter.setText(this.fbSectionButton, str6);
        }
        if ((18253613056L & j) != 0) {
            TextViewBindingAdapter.setText(this.fbSectionTitle, str5);
        }
        if ((j & 19327354880L) != 0) {
            TextViewBindingAdapter.setText(this.fbUnblockMessengerStillBlockedExplanation, str4);
        }
        if ((17179904000L & j) != 0) {
            this.messengerBlockSection.setVisibility(i12);
        }
        if ((17179887616L & j) != 0) {
            this.messengerSection.setVisibility(i11);
        }
        if ((17314088960L & j) != 0) {
            this.messengerSectionButton.setEnabled(z);
        }
        if ((17448306688L & j) != 0) {
            TextViewBindingAdapter.setText(this.messengerSectionButton, str3);
        }
        if ((j & 17213425664L) != 0) {
            TextViewBindingAdapter.setText(this.messengerSectionTitle, str2);
        }
        if ((j & 17179936768L) != 0) {
            this.messengerUnblockSection.setVisibility(i10);
        }
        if ((j & 17184065536L) != 0) {
            this.participantList.setVisibility(i9);
        }
        if ((17716742144L & j) != 0) {
            this.sectionDivider.setVisibility(i8);
        }
        if ((j & 17181968384L) != 0) {
            this.title.setDialogTitleText(str);
        }
        executeBindingsOn(this.title);
        executeBindingsOn(this.messengerBlockMessagesCallsExplanationBullet);
        executeBindingsOn(this.messengerBlockNoFacebookBlockExplanationBullet);
        executeBindingsOn(this.messengerBlockGroupsRoomsExplanationBullet);
        executeBindingsOn(this.messengerBlockNoOculusBlockExplanationBullet);
        executeBindingsOn(this.messengerUnblockMessagesCallsExplanationBullet);
        executeBindingsOn(this.messengerUnblockBlockedSentMessagesExplanationBullet);
        executeBindingsOn(this.fbBlockedCannotUnblockMessagesCallsExplanationBullet);
        executeBindingsOn(this.fbBlockUnfriendExplanationBullet);
        executeBindingsOn(this.fbBlockMessengerAlsoBlockedExplanationBullet);
        executeBindingsOn(this.fbUnblockRefriendExplanationBullet);
        executeBindingsOn(this.fbUnblockMessengerStillBlockedExplanationBullet);
    }
}
