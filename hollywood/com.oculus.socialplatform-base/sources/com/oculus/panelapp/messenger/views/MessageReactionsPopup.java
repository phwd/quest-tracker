package com.oculus.panelapp.messenger.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.messengervr.interfaces.MessengerReaction;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageReactionsPopupBinding;
import com.oculus.socialplatform.R;
import java.util.ArrayList;

public class MessageReactionsPopup extends PopupWindow {
    public static final String TAG = LoggingUtil.tag(MessageReactionsPopup.class);
    public MessageReactionsListAdapter mAdapter;
    public AnytimeTabletMessengerMessageReactionsPopupBinding mBinding;
    public MessengerPanelApp mPanelApp;

    public void destroy() {
        OCButton oCButton = this.mBinding.closeButton;
        oCButton.mEventHandler = null;
        oCButton.setOnClickListener(null);
        setOnDismissListener(null);
    }

    public /* synthetic */ void lambda$setOnDismissListener$1$MessageReactionsPopup(PopupWindow.OnDismissListener onDismissListener) {
        this.mPanelApp.getLogger().logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_REACTIONS_DETAILS_CLOSE, SurfaceType.FB_MESSENGER_REACTIONS_DETAILS);
        onDismissListener.onDismiss();
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        super.setOnDismissListener(new PopupWindow.OnDismissListener(onDismissListener) {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$MessageReactionsPopup$61JuJBZ21gxZ8R_YNFfdCXSNIoM2 */
            public final /* synthetic */ PopupWindow.OnDismissListener f$1;

            {
                this.f$1 = r2;
            }

            public final void onDismiss() {
                MessageReactionsPopup.this.lambda$setOnDismissListener$1$MessageReactionsPopup(this.f$1);
            }
        });
    }

    public void show(View view, int i, int i2, MessengerReaction[] messengerReactionArr) {
        this.mPanelApp.getLogger().logImpression(SurfaceType.FB_MESSENGER_REACTIONS_DETAILS, null);
        long parseLong = Long.parseLong(this.mPanelApp.getAPIManager().mCurrentAPI.getUserID());
        int length = messengerReactionArr.length;
        ArrayList arrayList = new ArrayList(length);
        for (MessengerReaction messengerReaction : messengerReactionArr) {
            if (messengerReaction.mActorId == parseLong) {
                arrayList.add(0, new MessageReactionAdapterItem(messengerReaction));
            } else {
                arrayList.add(new MessageReactionAdapterItem(messengerReaction));
            }
        }
        this.mAdapter.submitList(arrayList);
        showAsDropDown(view, i, i2);
    }

    public MessageReactionsPopup(Context context, MessengerPanelApp messengerPanelApp) {
        this.mPanelApp = messengerPanelApp;
        AnytimeTabletMessengerMessageReactionsPopupBinding inflate = AnytimeTabletMessengerMessageReactionsPopupBinding.inflate(LayoutInflater.from(context), null, false);
        this.mBinding = inflate;
        setContentView(inflate.mRoot);
        setAnimationStyle(0);
        setHeight(-2);
        setWidth(getWidth(context));
        MessageReactionsListAdapter messageReactionsListAdapter = new MessageReactionsListAdapter(context);
        this.mAdapter = messageReactionsListAdapter;
        messageReactionsListAdapter.setHasStableIds(true);
        this.mBinding.reactionsList.setAdapter(messageReactionsListAdapter);
        OCButton oCButton = this.mBinding.closeButton;
        oCButton.mEventHandler = messengerPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$MessageReactionsPopup$QTtmMahlJ0oXnU9B3HS_vsQagA2 */

            public final void onClick(View view) {
                MessageReactionsPopup.this.dismiss();
            }
        });
    }

    private int getWidth(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.messenger_tablet_thread_width) - (context.getResources().getDimensionPixelSize(R.dimen.messenger_reactions_pill_radius) << 1);
    }

    public /* synthetic */ void lambda$new$0$MessageReactionsPopup(View view) {
        dismiss();
    }
}
