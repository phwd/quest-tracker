package com.oculus.panelapp.social;

public class SocialGuidedActionAdapterItem implements SocialAdapterItem {
    private SocialGuidedAction mAction;

    public SocialGuidedActionAdapterItem(SocialGuidedAction socialGuidedAction) {
        this.mAction = socialGuidedAction;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public SocialAdapterItemType getItemViewType() {
        return SocialAdapterItemType.GUIDED_ACTION;
    }

    public SocialGuidedAction getAction() {
        return this.mAction;
    }

    @Override // com.oculus.panelapp.social.SocialAdapterItem
    public String getID() {
        return this.mAction.toString();
    }

    public String toString() {
        return "SocialGuidedActionAdapterItem{mAction=" + this.mAction.toString() + '}';
    }

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

    public int hashCode() {
        return this.mAction.hashCode();
    }
}
