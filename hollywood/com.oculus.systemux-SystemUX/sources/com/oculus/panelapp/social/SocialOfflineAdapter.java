package com.oculus.panelapp.social;

import java.util.Objects;

public class SocialOfflineAdapter implements SocialAdapterItem {
    private final String mSubtitle;
    private final String mTitle;

    public SocialOfflineAdapter(String str, String str2) {
        this.mTitle = str;
        this.mSubtitle = str2;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public SocialAdapterItemType getItemViewType() {
        return SocialAdapterItemType.OFFLINE;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public String getID() {
        return this.mTitle;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public boolean equals(SocialAdapterItem socialAdapterItem) {
        if (this == socialAdapterItem) {
            return true;
        }
        if (socialAdapterItem == null || getClass() != socialAdapterItem.getClass()) {
            return false;
        }
        return this.mTitle.equals(((SocialOfflineAdapter) socialAdapterItem).getTitle());
    }

    public int hashCode() {
        return Objects.hash(this.mTitle);
    }
}
