package com.oculus.panelapp.social;

import java.util.Objects;

public class SocialAdapterHeader implements SocialAdapterItem {
    public boolean mIsFriendHeader;
    public String mName;

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public boolean equals(SocialAdapterItem socialAdapterItem) {
        boolean z;
        return this == socialAdapterItem || (socialAdapterItem != null && getClass() == socialAdapterItem.getClass() && this.mName.equals(((SocialAdapterHeader) socialAdapterItem).mName) && (z = this.mIsFriendHeader) == z);
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public String getID() {
        return this.mName;
    }

    public boolean getIsFriendHeader() {
        return this.mIsFriendHeader;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public SocialAdapterItemType getItemViewType() {
        return SocialAdapterItemType.HEADER;
    }

    public String getName() {
        return this.mName;
    }

    public int hashCode() {
        return Objects.hash(this.mName);
    }

    public SocialAdapterHeader(String str) {
        this.mName = str;
        this.mIsFriendHeader = false;
    }

    public SocialAdapterHeader(String str, boolean z) {
        this.mName = str;
        this.mIsFriendHeader = z;
    }
}
