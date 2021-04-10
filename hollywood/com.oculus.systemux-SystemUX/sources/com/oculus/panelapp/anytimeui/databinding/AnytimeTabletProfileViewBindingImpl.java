package com.oculus.panelapp.anytimeui.databinding;

import android.content.res.Resources;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.facebook.systrace.Systrace;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCEmptyLayout;
import com.oculus.ocui.OCSpinner;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.BR;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileEditText;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileViewModel;

public class AnytimeTabletProfileViewBindingImpl extends AnytimeTabletProfileViewBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private long mDirtyFlags_1;
    @NonNull
    private final ProfileView mboundView0;

    public AnytimeTabletProfileViewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 28, sIncludes, sViewsWithIds));
    }

    private AnytimeTabletProfileViewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ImageView) objArr[11], (OCTextView) objArr[21], (OCButton) objArr[17], (OCSpinner) objArr[19], (Group) objArr[2], (OCButton) objArr[20], (OCTextView) objArr[23], (ProfileEditText) objArr[22], (OCTextView) objArr[10], (ImageView) objArr[25], (OCTextView) objArr[27], (OCTextView) objArr[26], (ImageView) objArr[8], (OCEmptyLayout) objArr[24], (OCSpinner) objArr[16], (OCTextView) objArr[9], (OCSpinner) objArr[13], (OCButton) objArr[4], (OCButton) objArr[18], (OCTextView) objArr[3], (OCSpinner) objArr[1], (OCButton) objArr[15], (ImageView) objArr[5], (ImageView) objArr[6], (OCButton) objArr[12], (OCTextView) objArr[7], (OCButton) objArr[14]);
        this.mDirtyFlags = -1;
        this.mDirtyFlags_1 = -1;
        this.avatar.setTag(null);
        this.biography.setTag(null);
        this.buttonSwitchAccount.setTag(null);
        this.confirmEditLoadingSpinner.setTag(null);
        this.contentGroup.setTag(null);
        this.editAvatar.setTag(null);
        this.editBioCharacterCount.setTag(null);
        this.editBiography.setTag(null);
        this.friendCount.setTag(null);
        this.loadingErrorImage.setTag(null);
        this.loadingErrorSubtitle.setTag(null);
        this.loadingErrorTitle.setTag(null);
        this.mboundView0 = (ProfileView) objArr[0];
        this.mboundView0.setTag(null);
        this.messengerPresenceIcon.setTag(null);
        this.offlineState.setTag(null);
        this.overflowButtonLoadingSpinner.setTag(null);
        this.presenceString.setTag(null);
        this.primaryButtonLoadingSpinner.setTag(null);
        this.profileBackButton.setTag(null);
        this.profileConfirmEdit.setTag(null);
        this.profileHeader.setTag(null);
        this.profileLoadingSpinner.setTag(null);
        this.profileOverflow.setTag(null);
        this.profilePicture.setTag(null);
        this.profilePictureEditOverlay.setTag(null);
        this.profilePrimaryButton.setTag(null);
        this.profilePrimaryName.setTag(null);
        this.profileSecondaryButton.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = Systrace.TRACE_TAG_APP_CORE_INFRA;
            this.mDirtyFlags_1 = 0;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags == 0) {
                if (this.mDirtyFlags_1 == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.resources == i) {
            setResources((Resources) obj);
        } else if (BR.viewModel != i) {
            return false;
        } else {
            setViewModel((ProfileViewModel) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletProfileViewBinding
    public void setResources(@Nullable Resources resources) {
        this.mResources = resources;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.resources);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.anytimeui.databinding.AnytimeTabletProfileViewBinding
    public void setViewModel(@Nullable ProfileViewModel profileViewModel) {
        updateRegistration(0, profileViewModel);
        this.mViewModel = profileViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModel((ProfileViewModel) obj, i2);
    }

    private boolean onChangeViewModel(ProfileViewModel profileViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.loadingProfile) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i == BR.showProfileContent) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == BR.showHeader) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i == BR.showBackButton) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == BR.editMode) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i == BR.presenceIcon) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == BR.presenceStringColor) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == BR.showPresenceIcon) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i == BR.presenceString) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        } else if (i == BR.showPresenceString) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        } else if (i == BR.showFriendCount) {
            synchronized (this) {
                this.mDirtyFlags |= 4096;
            }
            return true;
        } else if (i == BR.userHasAvatar) {
            synchronized (this) {
                this.mDirtyFlags |= 8192;
            }
            return true;
        } else if (i == BR.primaryButtonDrawable) {
            synchronized (this) {
                this.mDirtyFlags |= 16384;
            }
            return true;
        } else if (i == BR.primaryButtonType) {
            synchronized (this) {
                this.mDirtyFlags |= 32768;
            }
            return true;
        } else if (i == BR.isPrimaryButtonLoading) {
            synchronized (this) {
                this.mDirtyFlags |= 65536;
            }
            return true;
        } else if (i == BR.showPrimaryButton) {
            synchronized (this) {
                this.mDirtyFlags |= 131072;
            }
            return true;
        } else if (i == BR.secondaryButtonEnabled) {
            synchronized (this) {
                this.mDirtyFlags |= 262144;
            }
            return true;
        } else if (i == BR.showSecondaryButton) {
            synchronized (this) {
                this.mDirtyFlags |= 524288;
            }
            return true;
        } else if (i == BR.secondaryButtonType) {
            synchronized (this) {
                this.mDirtyFlags |= 1048576;
            }
            return true;
        } else if (i == BR.secondaryButtonDrawable) {
            synchronized (this) {
                this.mDirtyFlags |= 2097152;
            }
            return true;
        } else if (i == BR.isOverflowButtonLoading) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_COMPONENTS;
            }
            return true;
        } else if (i == BR.showContextMenu) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_VIEW_PERF_HARNESS;
            }
            return true;
        } else if (i == BR.showEditControls) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_REACT_FRESCO;
            }
            return true;
        } else if (i == BR.isSavingChanges) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_REACT_VIEW;
            }
            return true;
        } else if (i == BR.showSavingChangesSpinner) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_JS_VM;
            }
            return true;
        } else if (i == BR.showBiography) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_REACT_JS_VM_CALLS;
            }
            return true;
        } else if (i == BR.bio) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_PROFILO;
            }
            return true;
        } else if (i == BR.hasInternetConnection) {
            synchronized (this) {
                this.mDirtyFlags |= Systrace.TRACE_TAG_COMPONENT_SCRIPT;
            }
            return true;
        } else if (i != BR.showLoadingError) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 1073741824;
            }
            return true;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:320:0x04b4 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v3, types: [int] */
    /* JADX WARN: Type inference failed for: r0v61, types: [com.oculus.ocui.OCTextView] */
    /* JADX WARN: Type inference failed for: r0v62, types: [com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileEditText] */
    /* JADX WARN: Type inference failed for: r0v63, types: [com.oculus.ocui.OCButton] */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r37v3 */
    /* JADX WARN: Type inference failed for: r37v4 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r37v5 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x04b4, code lost:
        if (r64 != false) goto L_0x04b6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0184  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x01e1 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0227  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x024d A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0261  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x027c A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0290  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x02b7  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x02d3  */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x02ef  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x031c A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x0330  */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x0361  */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x0372 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x0386  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x03b5  */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x03e5  */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x0403  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:279:0x042c  */
    /* JADX WARNING: Removed duplicated region for block: B:286:0x0448  */
    /* JADX WARNING: Removed duplicated region for block: B:299:0x0477  */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x04a0  */
    /* JADX WARNING: Removed duplicated region for block: B:324:0x04c0  */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x051a  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0162  */
    /* JADX WARNING: Unknown variable types count: 5 */
    @Override // androidx.databinding.ViewDataBinding
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 2167
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.databinding.AnytimeTabletProfileViewBindingImpl.executeBindings():void");
    }
}
