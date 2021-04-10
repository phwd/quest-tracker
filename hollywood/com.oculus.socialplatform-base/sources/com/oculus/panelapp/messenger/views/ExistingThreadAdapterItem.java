package com.oculus.panelapp.messenger.views;

import com.oculus.messengervr.interfaces.MessengerThread;

public class ExistingThreadAdapterItem implements ThreadListAdapterItem {
    public MessengerThread mThread;

    @Override // com.oculus.panelapp.messenger.views.ThreadListAdapterItem
    public boolean equals(ThreadListAdapterItem threadListAdapterItem) {
        if (threadListAdapterItem == null || threadListAdapterItem.getItemViewType() == getItemViewType()) {
            return ((ExistingThreadAdapterItem) threadListAdapterItem).mThread.equals(this.mThread);
        }
        return false;
    }

    @Override // com.oculus.panelapp.messenger.views.ThreadListAdapterItem
    public long getID() {
        return this.mThread.getThreadKey();
    }

    @Override // com.oculus.panelapp.messenger.views.ThreadListAdapterItem
    public ThreadListAdapterItemType getItemViewType() {
        return ThreadListAdapterItemType.EXISTING;
    }

    public MessengerThread getThread() {
        return this.mThread;
    }

    public ExistingThreadAdapterItem(MessengerThread messengerThread) {
        this.mThread = messengerThread;
    }
}
