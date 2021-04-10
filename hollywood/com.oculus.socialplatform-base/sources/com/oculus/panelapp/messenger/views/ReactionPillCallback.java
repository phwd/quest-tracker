package com.oculus.panelapp.messenger.views;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerReaction;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerViewV2Binding;
import com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder;
import com.oculus.socialplatform.R;
import java.util.Optional;

public class ReactionPillCallback implements BaseMessageBubbleViewHolder.MessageBubbleCallbacks {
    public static final String TAG = LoggingUtil.tag(ReactionPillCallback.class);
    public final AnytimeTabletMessengerViewV2Binding mBinding;
    public final Context mContext;
    public final MessengerPanelApp mPanelApp;
    public final MessageReactionsPopup mPopup;

    public static /* synthetic */ boolean lambda$new$1(View view, MotionEvent motionEvent) {
        return true;
    }

    public void destroy() {
        this.mPopup.destroy();
    }

    public /* synthetic */ void lambda$new$0$ReactionPillCallback() {
        this.mBinding.messageList.suppressLayout(false);
    }

    @Override // com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.MessageBubbleCallbacks
    public void onReactionsPillClick(MessengerMessage messengerMessage) {
        if (!this.mPopup.isShowing()) {
            this.mPanelApp.onButtonClick();
            this.mBinding.messageList.suppressLayout(true);
            Optional<MessengerReaction[]> reactions = messengerMessage.getReactions();
            if (reactions.isPresent()) {
                this.mPopup.show(this.mBinding.facepileThreadIcon, 0, this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_tablet_reactions_details_offset_vertical), reactions.get());
            }
        }
    }

    public ReactionPillCallback(AnytimeTabletMessengerViewV2Binding anytimeTabletMessengerViewV2Binding, MessengerPanelApp messengerPanelApp, Context context, MessageReactionsPopup messageReactionsPopup) {
        this.mBinding = anytimeTabletMessengerViewV2Binding;
        this.mPanelApp = messengerPanelApp;
        this.mContext = context;
        this.mPopup = messageReactionsPopup;
        messageReactionsPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$ReactionPillCallback$V00XOGAPbRCwSVpxfBOhe5ecUJ42 */

            public final void onDismiss() {
                ReactionPillCallback.this.lambda$new$0$ReactionPillCallback();
            }
        });
        this.mPopup.getContentView().setOnHoverListener($$Lambda$ReactionPillCallback$yGew743LsrmsKljRqoLUhtXm6Gc2.INSTANCE);
    }
}
