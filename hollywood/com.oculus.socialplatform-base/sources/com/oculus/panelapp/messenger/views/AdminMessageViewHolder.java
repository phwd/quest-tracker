package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1Ah;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerAdminMessageItemV2Binding;

public class AdminMessageViewHolder extends AnonymousClass1Ah {
    public AnytimeTabletMessengerAdminMessageItemV2Binding mBinding;

    public AdminMessageViewHolder(AnytimeTabletMessengerAdminMessageItemV2Binding anytimeTabletMessengerAdminMessageItemV2Binding) {
        super(anytimeTabletMessengerAdminMessageItemV2Binding.mRoot);
        this.mBinding = anytimeTabletMessengerAdminMessageItemV2Binding;
    }

    public void bindMessage(MessengerMessage messengerMessage) {
        this.mBinding.setMessage(messengerMessage);
    }
}
