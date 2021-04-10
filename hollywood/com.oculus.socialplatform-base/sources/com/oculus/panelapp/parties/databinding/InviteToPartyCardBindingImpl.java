package com.oculus.panelapp.parties.databinding;

import X.AbstractC003408r;
import X.AnonymousClass0AS;
import X.AnonymousClass1uW;
import X.AnonymousClass1ui;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.parties.views.InviteToPartyCardViewModel;
import com.oculus.socialplatform.R;

public class InviteToPartyCardBindingImpl extends InviteToPartyCardBinding {
    @Nullable
    public static final AnonymousClass1ui sIncludes;
    @Nullable
    public static final SparseIntArray sViewsWithIds;
    public long mDirtyFlags;

    private boolean onChangeInviteToPartyCardViewModel(InviteToPartyCardViewModel inviteToPartyCardViewModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        } else if (i == 10) {
            synchronized (this) {
                this.mDirtyFlags |= 40;
            }
            return true;
        } else if (i == 35) {
            synchronized (this) {
                this.mDirtyFlags |= 16;
            }
            return true;
        } else if (i != 7) {
            return false;
        } else {
            synchronized (this) {
                this.mDirtyFlags |= 32;
            }
            return true;
        }
    }

    private boolean onChangeOverlayFbActionCtaButton(InviteToPartyCtaButtonBinding inviteToPartyCtaButtonBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeOverlayOcActionCtaButton(InviteToPartyCtaButtonBinding inviteToPartyCtaButtonBinding, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    @Override // X.AnonymousClass1uW
    public boolean hasPendingBindings() {
        int i;
        synchronized (this) {
            i = (this.mDirtyFlags > 0 ? 1 : (this.mDirtyFlags == 0 ? 0 : -1));
        }
        if (i != 0 || this.overlayFbActionCtaButton.hasPendingBindings() || this.overlayOcActionCtaButton.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1uW
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64;
        }
        this.overlayFbActionCtaButton.invalidateAll();
        this.overlayOcActionCtaButton.invalidateAll();
        requestRebind();
    }

    @Override // com.oculus.panelapp.parties.databinding.InviteToPartyCardBinding
    public void setInviteToPartyCardViewModel(@Nullable InviteToPartyCardViewModel inviteToPartyCardViewModel) {
        updateRegistration(0, inviteToPartyCardViewModel);
        this.mInviteToPartyCardViewModel = inviteToPartyCardViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(36);
        super.requestRebind();
    }

    static {
        AnonymousClass1ui r3 = new AnonymousClass1ui(10);
        sIncludes = r3;
        r3.A00(new String[]{"invite_to_party_cta_button", "invite_to_party_cta_button"}, new int[]{4, 5}, new int[]{R.layout.invite_to_party_cta_button, R.layout.invite_to_party_cta_button});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.invite_to_party_icon, 6);
        sparseIntArray.put(R.id.action_description, 7);
        sparseIntArray.put(R.id.overlay_action_description, 8);
        sparseIntArray.put(R.id.card_hover_overlay, 9);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0034, code lost:
        if (r12 != false) goto L_0x0036;
     */
    @Override // X.AnonymousClass1uW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
        // Method dump skipped, instructions count: 305
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.parties.databinding.InviteToPartyCardBindingImpl.executeBindings():void");
    }

    @Override // X.AnonymousClass1uW
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeInviteToPartyCardViewModel((InviteToPartyCardViewModel) obj, i2);
        }
        if (i == 1) {
            return onChangeOverlayFbActionCtaButton((InviteToPartyCtaButtonBinding) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangeOverlayOcActionCtaButton((InviteToPartyCtaButtonBinding) obj, i2);
    }

    @Override // X.AnonymousClass1uW
    public boolean setVariable(int i, @Nullable Object obj) {
        if (36 != i) {
            return false;
        }
        setInviteToPartyCardViewModel((InviteToPartyCardViewModel) obj);
        return true;
    }

    @Override // X.AnonymousClass1uW
    public void setLifecycleOwner(@Nullable AnonymousClass0AS r2) {
        super.setLifecycleOwner(r2);
        this.overlayFbActionCtaButton.setLifecycleOwner(r2);
        this.overlayOcActionCtaButton.setLifecycleOwner(r2);
    }

    public InviteToPartyCardBindingImpl(@Nullable AbstractC003408r r4, @NonNull View view) {
        this(r4, view, AnonymousClass1uW.mapBindings(r4, view, 10, sIncludes, sViewsWithIds));
    }

    public InviteToPartyCardBindingImpl(AbstractC003408r r17, View view, Object[] objArr) {
        super(r17, view, 3, (OCTextView) objArr[7], (View) objArr[9], (ConstraintLayout) objArr[0], (Group) objArr[2], (View) objArr[6], (Group) objArr[1], (OCTextView) objArr[8], (InviteToPartyCtaButtonBinding) objArr[4], (InviteToPartyCtaButtonBinding) objArr[5], (OCTextView) objArr[3]);
        this.mDirtyFlags = -1;
        this.inviteToPartyActionCard.setTag(null);
        this.inviteToPartyHoverGroup.setTag(null);
        this.inviteToPartyUnhoverGroup.setTag(null);
        this.subtitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
