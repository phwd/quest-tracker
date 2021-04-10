package com.oculus.panelapp.social;

import com.oculus.horizoncontent.user.LinkedAccountsInfo;
import java.util.Objects;

public class SocialAdapterNullRow implements SocialAdapterItem {
    private LinkedAccountsInfo mLinkedAccountsInfo;

    public SocialAdapterNullRow(LinkedAccountsInfo linkedAccountsInfo) {
        this.mLinkedAccountsInfo = linkedAccountsInfo;
    }

    public LinkedAccountsInfo getLinkedAccountsInfo() {
        return this.mLinkedAccountsInfo;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public SocialAdapterItemType getItemViewType() {
        return SocialAdapterItemType.NULL;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public String getID() {
        return this.mLinkedAccountsInfo.toString();
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public boolean equals(SocialAdapterItem socialAdapterItem) {
        if (this == socialAdapterItem) {
            return true;
        }
        if (socialAdapterItem == null || getClass() != socialAdapterItem.getClass()) {
            return false;
        }
        return this.mLinkedAccountsInfo.equals(((SocialAdapterNullRow) socialAdapterItem).mLinkedAccountsInfo);
    }

    public int hashCode() {
        return Objects.hash(this.mLinkedAccountsInfo);
    }
}
