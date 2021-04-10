package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1Ah;
import com.oculus.messengervr.interfaces.MessengerReaction;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageReactionsItemBinding;

public class MessageReactionsListItemViewHolder extends AnonymousClass1Ah {
    public AnytimeTabletMessengerMessageReactionsItemBinding mBinding;

    public MessageReactionsListItemViewHolder(AnytimeTabletMessengerMessageReactionsItemBinding anytimeTabletMessengerMessageReactionsItemBinding) {
        super(anytimeTabletMessengerMessageReactionsItemBinding.mRoot);
        this.mBinding = anytimeTabletMessengerMessageReactionsItemBinding;
    }

    public void setReaction(MessengerReaction messengerReaction) {
        this.mBinding.setReaction(messengerReaction);
    }
}
