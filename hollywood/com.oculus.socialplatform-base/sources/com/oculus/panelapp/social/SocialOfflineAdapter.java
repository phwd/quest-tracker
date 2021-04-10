package com.oculus.panelapp.social;

import java.util.Objects;

public class SocialOfflineAdapter implements SocialAdapterItem {
    public final String mSubtitle;
    public final String mTitle;

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public boolean equals(SocialAdapterItem socialAdapterItem) {
        if (this == socialAdapterItem) {
            return true;
        }
        if (socialAdapterItem == null || getClass() != socialAdapterItem.getClass()) {
            return false;
        }
        return this.mTitle.equals(((SocialOfflineAdapter) socialAdapterItem).mTitle);
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public String getID() {
        return this.mTitle;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public SocialAdapterItemType getItemViewType() {
        return SocialAdapterItemType.OFFLINE;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(this.mTitle);
    }

    public SocialOfflineAdapter(String str, String str2) {
        this.mTitle = str;
        this.mSubtitle = str2;
    }
}
