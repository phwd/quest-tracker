package com.oculus.panelapp.social;

import java.util.Objects;

public class SocialAdapterHeader implements SocialAdapterItem {
    private boolean mIsFriendHeader;
    private String mName;

    public SocialAdapterHeader(String str) {
        this.mName = str;
        this.mIsFriendHeader = false;
    }

    public SocialAdapterHeader(String str, boolean z) {
        this.mName = str;
        this.mIsFriendHeader = z;
    }

    public String getName() {
        return this.mName;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public SocialAdapterItemType getItemViewType() {
        return SocialAdapterItemType.HEADER;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public boolean equals(SocialAdapterItem socialAdapterItem) {
        boolean z;
        if (this == socialAdapterItem) {
            return true;
        }
        if (socialAdapterItem == null || getClass() != socialAdapterItem.getClass()) {
            return false;
        }
        return this.mName.equals(((SocialAdapterHeader) socialAdapterItem).mName) && (z = this.mIsFriendHeader) == z;
    }

    public int hashCode() {
        return Objects.hash(this.mName);
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public String getID() {
        return this.mName;
    }

    public boolean getIsFriendHeader() {
        return this.mIsFriendHeader;
    }
}
