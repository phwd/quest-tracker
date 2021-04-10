package com.oculus.panelapp.messenger.views;

import com.oculus.messengervr.interfaces.MessengerReaction;

public class MessageReactionAdapterItem implements MessageReactionsListAdapterItem {
    public MessengerReaction mReaction;

    @Override // com.oculus.panelapp.messenger.views.MessageReactionsListAdapterItem
    public boolean equals(MessageReactionsListAdapterItem messageReactionsListAdapterItem) {
        if (messageReactionsListAdapterItem == null || messageReactionsListAdapterItem.getItemViewType() == getItemViewType()) {
            return ((MessageReactionAdapterItem) messageReactionsListAdapterItem).mReaction.equals(this.mReaction);
        }
        return false;
    }

    @Override // com.oculus.panelapp.messenger.views.MessageReactionsListAdapterItem
    public long getID() {
        return (long) this.mReaction.hashCode();
    }

    @Override // com.oculus.panelapp.messenger.views.MessageReactionsListAdapterItem
    public MessageReactionsListAdapterItemType getItemViewType() {
        return MessageReactionsListAdapterItemType.USER;
    }

    public MessengerReaction getReaction() {
        return this.mReaction;
    }

    public MessageReactionAdapterItem(MessengerReaction messengerReaction) {
        this.mReaction = messengerReaction;
    }
}
