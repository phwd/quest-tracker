package com.oculus.panelapp.messenger.views;

import com.oculus.panelapp.messenger.api.models.DraftThread;

public class DraftThreadAdapterItem implements ThreadListAdapterItem {
    public DraftThread mDraftThread;

    @Override // com.oculus.panelapp.messenger.views.ThreadListAdapterItem
    public boolean equals(ThreadListAdapterItem threadListAdapterItem) {
        if (threadListAdapterItem == null || threadListAdapterItem.getID() != getID()) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.panelapp.messenger.views.ThreadListAdapterItem
    public ThreadListAdapterItemType getItemViewType() {
        return ThreadListAdapterItemType.DRAFT;
    }

    public DraftThreadAdapterItem(DraftThread draftThread) {
        this.mDraftThread = draftThread;
    }

    @Override // com.oculus.panelapp.messenger.views.ThreadListAdapterItem
    public long getID() {
        return (long) 95844769;
    }
}
