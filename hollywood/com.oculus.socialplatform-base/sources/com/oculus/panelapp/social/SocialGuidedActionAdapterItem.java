package com.oculus.panelapp.social;

public class SocialGuidedActionAdapterItem implements SocialAdapterItem {
    public SocialGuidedAction mAction;

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public boolean equals(SocialAdapterItem socialAdapterItem) {
        if (this == socialAdapterItem) {
            return true;
        }
        if (socialAdapterItem == null || getClass() != socialAdapterItem.getClass()) {
            return false;
        }
        return this.mAction.equals(((SocialGuidedActionAdapterItem) socialAdapterItem).mAction);
    }

    public SocialGuidedAction getAction() {
        return this.mAction;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public String getID() {
        return this.mAction.toString();
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public SocialAdapterItemType getItemViewType() {
        return SocialAdapterItemType.GUIDED_ACTION;
    }

    public int hashCode() {
        return this.mAction.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SocialGuidedActionAdapterItem{mAction=");
        sb.append(this.mAction.toString());
        sb.append('}');
        return sb.toString();
    }

    public SocialGuidedActionAdapterItem(SocialGuidedAction socialGuidedAction) {
        this.mAction = socialGuidedAction;
    }
}
