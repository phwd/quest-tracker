package com.oculus.panelapp.parties.views;

import X.AnonymousClass1uc;
import androidx.databinding.Bindable;

public class InviteToPartyCardViewModel extends AnonymousClass1uc {
    public InviteToPartyCardAdapterItem mInviteToPartyCardAdapterItem;
    public boolean mIsFbActionEnabled = false;
    public boolean mIsHoverOverlayShowing;

    @Bindable
    public boolean getHasLinkSharing() {
        InviteToPartyCardAdapterItem inviteToPartyCardAdapterItem = this.mInviteToPartyCardAdapterItem;
        if (inviteToPartyCardAdapterItem == null) {
            return false;
        }
        return inviteToPartyCardAdapterItem.mParty.mHasLinkSharing;
    }

    @Bindable
    public boolean getIsHoverOverlayShowing() {
        return this.mIsHoverOverlayShowing;
    }

    @Bindable({"isHoverOverlayShowing"})
    public boolean isFbActionEnabled() {
        if (!this.mIsHoverOverlayShowing || !this.mIsFbActionEnabled) {
            return false;
        }
        return true;
    }

    public void setInviteToPartyCardAdapterItem(InviteToPartyCardAdapterItem inviteToPartyCardAdapterItem) {
        this.mInviteToPartyCardAdapterItem = inviteToPartyCardAdapterItem;
        notifyPropertyChanged(35);
    }

    public void setIsHoverOverlayShowing(boolean z) {
        this.mIsHoverOverlayShowing = z;
        notifyPropertyChanged(10);
    }

    public void setIsFbActionEnabled(boolean z) {
        this.mIsFbActionEnabled = z;
    }
}
