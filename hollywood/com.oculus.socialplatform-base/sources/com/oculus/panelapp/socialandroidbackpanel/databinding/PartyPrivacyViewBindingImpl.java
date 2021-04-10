package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AbstractC003408r;
import X.AnonymousClass0AS;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import X.C11051qV;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.breakpad.BreakpadManager;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyView;
import com.oculus.panelapp.socialandroidbackpanel.views.party_privacy.PartyPrivacyViewModel;
import com.oculus.socialplatform.R;

public class PartyPrivacyViewBindingImpl extends PartyPrivacyViewBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeJoinPolicyToggleMenuItem(PartyPrivacyMenuItemToggleBinding partyPrivacyMenuItemToggleBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeLinkPolicyToggleMenuItem(PartyPrivacyMenuItemToggleBinding partyPrivacyMenuItemToggleBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangePartyPrivacyViewModel(PartyPrivacyViewModel partyPrivacyViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 215) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 216) {
            synchronized (this) {
                this.mDirtyFlags |= 8;
            }
            return true;
        } else if (i == 196) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i != 214) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 32;
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
        if (i != 0 || this.joinPolicyToggleMenuItem.hasPendingBindings() || this.linkPolicyToggleMenuItem.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128;
        }
        this.joinPolicyToggleMenuItem.invalidateAll();
        this.linkPolicyToggleMenuItem.invalidateAll();
        requestRebind();
    }

    @Override // com.oculus.panelapp.socialandroidbackpanel.databinding.PartyPrivacyViewBinding
    public void setPartyPrivacyViewModel(@Nullable PartyPrivacyViewModel partyPrivacyViewModel) {
        updateRegistration(0, partyPrivacyViewModel);
        this.mPartyPrivacyViewModel = partyPrivacyViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(201);
        super.requestRebind();
    }

    static {
        AnonymousClass1ui r3 = new AnonymousClass1ui(9);
        sIncludes = r3;
        r3.A00(new String[]{"party_privacy_menu_item_toggle", "party_privacy_menu_item_toggle"}, new int[]{4, 5}, new int[]{R.layout.party_privacy_menu_item_toggle, R.layout.party_privacy_menu_item_toggle});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.party_privacy_title, 6);
        sparseIntArray.put(R.id.party_privacy_link_and_share, 7);
        sparseIntArray.put(R.id.party_privacy_close_button, 8);
    }

    @Override // X.AnonymousClass1uW
    public void executeBindings() {
        long j;
        boolean z;
        int i;
        int i2;
        String str;
        OCTextView oCTextView;
        int i3;
        long j2;
        long j3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        PartyPrivacyViewModel partyPrivacyViewModel = this.mPartyPrivacyViewModel;
        boolean z2 = false;
        if ((249 & j) != 0) {
            long j4 = j & 145;
            if (j4 != 0) {
                if (partyPrivacyViewModel != null) {
                    z = partyPrivacyViewModel.mHasLinkInvite;
                } else {
                    z = false;
                }
                if (j4 != 0) {
                    if (z) {
                        j2 = j | 512;
                        j3 = BreakpadManager.MD_FB_WITH_UNWINDSTACK_STREAM;
                    } else {
                        j2 = j | 256;
                        j3 = 1024;
                    }
                    j = j2 | j3;
                }
                OCTextView oCTextView2 = this.partyPrivacyLink;
                int i4 = R.color.octypography_placeholder_color;
                if (z) {
                    i4 = R.color.octypography_primary_color;
                }
                i2 = AnonymousClass1uW.getColorFromResource(oCTextView2, i4);
                if (z) {
                    oCTextView = this.partyPrivacyLinkTitle;
                    i3 = R.color.icon_clickable_on_exit;
                } else {
                    oCTextView = this.partyPrivacyLinkTitle;
                    i3 = R.color.octypography_placeholder_color;
                }
                i = AnonymousClass1uW.getColorFromResource(oCTextView, i3);
            } else {
                i = 0;
                i2 = 0;
                z = false;
            }
            if ((j & 161) == 0 || partyPrivacyViewModel == null) {
                str = null;
            } else {
                str = partyPrivacyViewModel.mPartyUrl;
            }
            if ((j & 137) != 0 && (partyPrivacyViewModel == null || !partyPrivacyViewModel.getIsInviteOnly())) {
                z2 = true;
            }
        } else {
            z = false;
            i = 0;
            i2 = 0;
            str = null;
        }
        if ((j & 128) != 0) {
            this.joinPolicyToggleMenuItem.setTopText(this.mRoot.getResources().getString(R.string.party_privacy_view_join_policy_header));
            this.joinPolicyToggleMenuItem.setBottomText(this.mRoot.getResources().getString(R.string.party_privacy_view_join_policy_body));
            this.joinPolicyToggleMenuItem.setIcon(AnonymousClass1uW.getDrawableFromResource(this.mRoot, R.drawable.oc_icon_friends_filled_24_d2d2d2));
            this.linkPolicyToggleMenuItem.setTopText(this.mRoot.getResources().getString(R.string.party_privacy_view_link_permission_header));
            this.linkPolicyToggleMenuItem.setBottomText(this.mRoot.getResources().getString(R.string.party_privacy_view_link_permission_body));
            this.linkPolicyToggleMenuItem.setIcon(AnonymousClass1uW.getDrawableFromResource(this.mRoot, R.drawable.oc_icon_link_filled_24_d2d2d2));
        }
        if ((j & 137) != 0) {
            this.joinPolicyToggleMenuItem.setChecked(z2);
        }
        if ((145 & j) != 0) {
            this.linkPolicyToggleMenuItem.setChecked(z);
            this.partyPrivacyLink.setTextColor(i2);
            this.partyPrivacyLinkTitle.setTextColor(i);
            this.partyPrivacyShareButton.setEnabled(z);
        }
        if ((j & 161) != 0) {
            C11051qV.A02(this.partyPrivacyLink, str);
        }
        AnonymousClass1uW.executeBindingsOn(this.joinPolicyToggleMenuItem);
        AnonymousClass1uW.executeBindingsOn(this.linkPolicyToggleMenuItem);
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangePartyPrivacyViewModel((PartyPrivacyViewModel) obj, i2);
        }
        if (i == 1) {
            return onChangeLinkPolicyToggleMenuItem((PartyPrivacyMenuItemToggleBinding) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangeJoinPolicyToggleMenuItem((PartyPrivacyMenuItemToggleBinding) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (201 != i) {
            return false;
        }
        setPartyPrivacyViewModel((PartyPrivacyViewModel) obj);
        return true;
    }

    @Override // X.AnonymousClass1uW
    public void setLifecycleOwner(@Nullable AnonymousClass0AS r2) {
        super.setLifecycleOwner(r2);
        this.joinPolicyToggleMenuItem.setLifecycleOwner(r2);
        this.linkPolicyToggleMenuItem.setLifecycleOwner(r2);
    }

    public PartyPrivacyViewBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 9, sIncludes, sViewsWithIds));
    }

    public PartyPrivacyViewBindingImpl(AbstractC003408r r16, View view, Object[] objArr) {
        super(r16, view, 3, (PartyPrivacyMenuItemToggleBinding) objArr[4], (PartyPrivacyMenuItemToggleBinding) objArr[5], (OCButton) objArr[8], (OCTextView) objArr[2], (View) objArr[7], (OCTextView) objArr[1], (OCButton) objArr[3], (OCTextView) objArr[6], (PartyPrivacyView) objArr[0]);
        this.mDirtyFlags = -1;
        this.partyPrivacyLink.setTag(null);
        this.partyPrivacyLinkTitle.setTag(null);
        this.partyPrivacyShareButton.setTag(null);
        this.partyPrivacyView.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
