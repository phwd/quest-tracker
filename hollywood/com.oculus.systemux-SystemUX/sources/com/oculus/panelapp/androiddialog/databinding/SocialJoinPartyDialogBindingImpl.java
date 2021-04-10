package com.oculus.panelapp.androiddialog.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.facebook.systrace.Systrace;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.androiddialog.BR;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.dialogs.social.SocialUserViewModel;
import com.oculus.panelapp.androiddialog.dialogs.social.join_party.PartyInviteViewModel;
import com.oculus.panelapp.androiddialog.dialogs.social.join_party.SocialJoinPartyDialog;
import com.oculus.panelapp.androiddialog.dialogs.social.list_item.SocialListItem;

public class SocialJoinPartyDialogBindingImpl extends SocialJoinPartyDialogBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;

    static {
        sViewsWithIds.put(R.id.social_join_party_title, 4);
        sViewsWithIds.put(R.id.social_join_party_secondary_button, 5);
        sViewsWithIds.put(R.id.social_join_party_primary_button, 6);
    }

    public SocialJoinPartyDialogBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private SocialJoinPartyDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (SocialListItem) objArr[2], (SocialJoinPartyDialog) objArr[0], (SocialListItem) objArr[1], (SocialListItem) objArr[3], (OCButton) objArr[6], (OCButton) objArr[5], (OCTextView) objArr[4]);
        this.mDirtyFlags = -1;
        this.socialJoinPartyDestinationRow.setTag(null);
        this.socialJoinPartyDialog.setTag(null);
        this.socialJoinPartyJoiningAsRow.setTag(null);
        this.socialJoinPartyPartyDescriptionRow.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 262144;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.viewerViewModel == i) {
            setViewerViewModel((SocialUserViewModel) obj);
        } else if (BR.partyViewModel != i) {
            return false;
        } else {
            setPartyViewModel((PartyInviteViewModel) obj);
        }
        return true;
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.SocialJoinPartyDialogBinding
    public void setViewerViewModel(@Nullable SocialUserViewModel socialUserViewModel) {
        updateRegistration(0, socialUserViewModel);
        this.mViewerViewModel = socialUserViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.viewerViewModel);
        super.requestRebind();
    }

    @Override // com.oculus.panelapp.androiddialog.databinding.SocialJoinPartyDialogBinding
    public void setPartyViewModel(@Nullable PartyInviteViewModel partyInviteViewModel) {
        updateRegistration(1, partyInviteViewModel);
        this.mPartyViewModel = partyInviteViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.partyViewModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeViewerViewModel((SocialUserViewModel) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangePartyViewModel((PartyInviteViewModel) obj, i2);
    }

    private boolean onChangeViewerViewModel(SocialUserViewModel socialUserViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == BR.profilePhotoUrl) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        } else if (i != BR.alias) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        }
    }

    private boolean onChangePartyViewModel(PartyInviteViewModel partyInviteViewModel, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        } else if (i == BR.groupLaunchDestination) {
            synchronized (this) {
                this.mDirtyFlags |= 112;
            }
            return true;
        } else if (i == BR.groupLaunchApplication) {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        } else if (i == BR.groupLaunchImageUrl) {
            synchronized (this) {
                this.mDirtyFlags |= 64;
            }
            return true;
        } else if (i == BR.displayablePartyUsers) {
            synchronized (this) {
                this.mDirtyFlags |= 6272;
            }
            return true;
        } else if (i == BR.partyUsers) {
            synchronized (this) {
                this.mDirtyFlags |= 6272;
            }
            return true;
        } else if (i == BR.viewerAlias) {
            synchronized (this) {
                this.mDirtyFlags |= 6272;
            }
            return true;
        } else if (i == BR.partyTitle) {
            synchronized (this) {
                this.mDirtyFlags |= 128;
            }
            return true;
        } else if (i == BR.partyType) {
            synchronized (this) {
                this.mDirtyFlags |= 768;
            }
            return true;
        } else if (i == BR.partyTypeDescription) {
            synchronized (this) {
                this.mDirtyFlags |= 256;
            }
            return true;
        } else if (i == BR.partyTypeIconImage) {
            synchronized (this) {
                this.mDirtyFlags |= 512;
            }
            return true;
        } else if (i == BR.blockedUsersWarning) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        } else if (i == BR.blockedPartyUsers) {
            synchronized (this) {
                this.mDirtyFlags |= 1024;
            }
            return true;
        } else if (i == BR.firstPartyUserImageUrl) {
            synchronized (this) {
                this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            return true;
        } else if (i != BR.secondPartyUserImageUrl) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 4096;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        boolean z;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        int i;
        String str6;
        boolean z2;
        String str7;
        String str8;
        String str9;
        String str10;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SocialUserViewModel socialUserViewModel = this.mViewerViewModel;
        PartyInviteViewModel partyInviteViewModel = this.mPartyViewModel;
        String str11 = null;
        if ((262157 & j) != 0) {
            int i2 = ((j & 262153) > 0 ? 1 : ((j & 262153) == 0 ? 0 : -1));
            if (i2 != 0) {
                if (socialUserViewModel != null) {
                    str = socialUserViewModel.getAlias();
                } else {
                    str = null;
                }
                z = str != null;
                if (i2 != 0) {
                    j = z ? j | Systrace.TRACE_TAG_REACT_FRESCO : j | Systrace.TRACE_TAG_VIEW_PERF_HARNESS;
                }
            } else {
                z = false;
                str = null;
            }
            str2 = ((j & 262149) == 0 || socialUserViewModel == null) ? null : socialUserViewModel.getProfilePhotoUrl();
        } else {
            z = false;
            str2 = null;
            str = null;
        }
        if ((j & 524274) != 0) {
            String blockedUsersWarning = ((j & 263170) == 0 || partyInviteViewModel == null) ? null : partyInviteViewModel.getBlockedUsersWarning();
            String firstPartyUserImageUrl = ((j & 264194) == 0 || partyInviteViewModel == null) ? null : partyInviteViewModel.getFirstPartyUserImageUrl();
            String partyTypeDescription = ((j & 262402) == 0 || partyInviteViewModel == null) ? null : partyInviteViewModel.getPartyTypeDescription();
            String secondPartyUserImageUrl = ((j & 266242) == 0 || partyInviteViewModel == null) ? null : partyInviteViewModel.getSecondPartyUserImageUrl();
            if (!((j & 327682) == 0 || partyInviteViewModel == null)) {
                partyInviteViewModel.getPartyType();
            }
            if (!((j & 270338) == 0 || partyInviteViewModel == null)) {
                partyInviteViewModel.getDisplayablePartyUsers();
            }
            if (!((j & 294914) == 0 || partyInviteViewModel == null)) {
                partyInviteViewModel.getViewerAlias();
            }
            String partyTitle = ((j & 262274) == 0 || partyInviteViewModel == null) ? null : partyInviteViewModel.getPartyTitle();
            if (!((j & 278530) == 0 || partyInviteViewModel == null)) {
                partyInviteViewModel.getPartyUsers();
            }
            int partyTypeIconImage = ((j & 262658) == 0 || partyInviteViewModel == null) ? 0 : partyInviteViewModel.getPartyTypeIconImage();
            if (!((j & 393218) == 0 || partyInviteViewModel == null)) {
                partyInviteViewModel.getBlockedPartyUsers();
            }
            if ((j & 262258) != 0) {
                if (partyInviteViewModel != null) {
                    str7 = partyInviteViewModel.getGroupLaunchDestination();
                } else {
                    str7 = null;
                }
                z2 = str7 != null;
                if ((j & 262226) != 0) {
                    j = z2 ? j | 1048576 : j | 524288;
                }
                if ((j & 262194) != 0) {
                    j = z2 ? j | Systrace.TRACE_TAG_COMPONENTS : j | 2097152;
                }
                str5 = blockedUsersWarning;
            } else {
                str5 = blockedUsersWarning;
                z2 = false;
                str7 = null;
            }
            str4 = firstPartyUserImageUrl;
            str6 = partyTypeDescription;
            str3 = secondPartyUserImageUrl;
            str8 = partyTitle;
            i = partyTypeIconImage;
        } else {
            z2 = false;
            i = 0;
            str8 = null;
            str7 = null;
            str6 = null;
            str5 = null;
            str4 = null;
            str3 = null;
        }
        if ((j & Systrace.TRACE_TAG_COMPONENTS) != 0) {
            str9 = this.socialJoinPartyDestinationRow.getResources().getString(R.string.social_join_party_dialog_party_destination_title, str7, partyInviteViewModel != null ? partyInviteViewModel.getGroupLaunchApplication() : null);
        } else {
            str9 = null;
        }
        String string = (Systrace.TRACE_TAG_REACT_FRESCO & j) != 0 ? this.socialJoinPartyJoiningAsRow.getResources().getString(R.string.social_join_party_dialog_joining_as_title, str) : null;
        if ((j & 1048576) == 0 || partyInviteViewModel == null) {
            str10 = null;
        } else {
            str10 = partyInviteViewModel.getGroupLaunchImageUrl();
        }
        int i3 = ((j & 262226) > 0 ? 1 : ((j & 262226) == 0 ? 0 : -1));
        if (i3 == 0 || !z2) {
            str10 = null;
        }
        int i4 = ((j & 262194) > 0 ? 1 : ((j & 262194) == 0 ? 0 : -1));
        if (i4 == 0) {
            str9 = null;
        } else if (!z2) {
            str9 = this.socialJoinPartyDestinationRow.getResources().getString(R.string.social_join_party_dialog_party_destination_not_set_title);
        }
        int i5 = ((262153 & j) > 0 ? 1 : ((262153 & j) == 0 ? 0 : -1));
        if (i5 != 0) {
            if (!z) {
                string = this.socialJoinPartyJoiningAsRow.getResources().getString(R.string.social_join_party_dialog_title);
            }
            str11 = string;
        }
        if (i4 != 0) {
            SocialListItem.setTitle(this.socialJoinPartyDestinationRow, str9);
        }
        if (i3 != 0) {
            SocialListItem.setImageUrl(this.socialJoinPartyDestinationRow, str10);
        }
        if ((262149 & j) != 0) {
            SocialListItem.setImageUrl(this.socialJoinPartyJoiningAsRow, str2);
        }
        if (i5 != 0) {
            SocialListItem.setTitle(this.socialJoinPartyJoiningAsRow, str11);
        }
        if ((262274 & j) != 0) {
            SocialListItem.setTitle(this.socialJoinPartyPartyDescriptionRow, str8);
        }
        if ((j & 262402) != 0) {
            SocialListItem.setSubtitle(this.socialJoinPartyPartyDescriptionRow, str6);
        }
        if ((262658 & j) != 0) {
            SocialListItem.setSubtitleIcon(this.socialJoinPartyPartyDescriptionRow, i);
        }
        if ((j & 263170) != 0) {
            SocialListItem.setAltSubtitle(this.socialJoinPartyPartyDescriptionRow, str5);
        }
        if ((j & 264194) != 0) {
            SocialListItem.setImageUrl(this.socialJoinPartyPartyDescriptionRow, str4);
        }
        if ((j & 266242) != 0) {
            SocialListItem.setImageUrl2(this.socialJoinPartyPartyDescriptionRow, str3);
        }
    }
}
