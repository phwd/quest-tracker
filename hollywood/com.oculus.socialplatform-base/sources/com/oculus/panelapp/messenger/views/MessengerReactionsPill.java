package com.oculus.panelapp.messenger.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.messengervr.interfaces.MessengerReaction;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.databinding.MessengerReactionsPillBinding;
import java.util.List;

public class MessengerReactionsPill extends ConstraintLayout {
    public static final String TAG = "MessengerReactionsPill";
    public MessengerReactionsPillBinding mBinding;
    public final MessengerReactionsPillViewModel mViewModel = new MessengerReactionsPillViewModel();

    public void destroy() {
        this.mViewModel.destroy();
    }

    public MessengerReactionsPill(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        MessengerReactionsPillBinding inflate = MessengerReactionsPillBinding.inflate(LayoutInflater.from(context), this, true);
        this.mBinding = inflate;
        inflate.setViewModel(this.mViewModel);
    }

    public boolean setReactions(MessengerPanelApp messengerPanelApp, List<MessengerReaction> list) {
        if (list.isEmpty()) {
            return false;
        }
        this.mViewModel.mEmojiHandler = messengerPanelApp.getEmojiHandler();
        this.mViewModel.setReactions(list);
        return true;
    }
}
