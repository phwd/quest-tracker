package com.oculus.panelapp.messenger.views;

import X.AnonymousClass006;
import X.AnonymousClass1Ah;
import X.AnonymousClass2MI;
import X.AnonymousClass2NM;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.api.MessengerAPIType;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerAdminMessageItemV2Binding;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemContainerV2Binding;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemImageV2Binding;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemTextV2Binding;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerXmaItemV2Binding;
import com.oculus.panelapp.messenger.util.PartyInviteLink;
import com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder;
import com.oculus.socialplatform.R;

public class MessageListAdapter extends AnonymousClass2MI<MessengerMessage, AnonymousClass1Ah> {
    public static final AnonymousClass2NM<MessengerMessage> DIFF_CALLBACK = new AnonymousClass2NM<MessengerMessage>() {
        /* class com.oculus.panelapp.messenger.views.MessageListAdapter.AnonymousClass1 */

        public boolean areContentsTheSame(@NonNull MessengerMessage messengerMessage, @NonNull MessengerMessage messengerMessage2) {
            return messengerMessage.equals(messengerMessage2);
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
        @Override // X.AnonymousClass2NM
        public /* bridge */ /* synthetic */ boolean areContentsTheSame(@NonNull MessengerMessage messengerMessage, @NonNull MessengerMessage messengerMessage2) {
            return messengerMessage.equals(messengerMessage2);
        }

        public boolean areItemsTheSame(@NonNull MessengerMessage messengerMessage, @NonNull MessengerMessage messengerMessage2) {
            return messengerMessage.getMessageId().equals(messengerMessage2.getMessageId());
        }
    };
    public static final String TAG = LoggingUtil.tag(MessageListAdapter.class);
    public BaseMessageBubbleViewHolder.MessageBubbleCallbacks mMessageBubbleCallbacks;
    public MessengerPanelApp mPanelApp;

    /* renamed from: com.oculus.panelapp.messenger.views.MessageListAdapter$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$messengervr$interfaces$MessengerMessage$AttachmentType;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        static {
            /*
                com.oculus.messengervr.interfaces.MessengerMessage$AttachmentType[] r0 = com.oculus.messengervr.interfaces.MessengerMessage.AttachmentType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.messenger.views.MessageListAdapter.AnonymousClass2.$SwitchMap$com$oculus$messengervr$interfaces$MessengerMessage$AttachmentType = r2
                com.oculus.messengervr.interfaces.MessengerMessage$AttachmentType r0 = com.oculus.messengervr.interfaces.MessengerMessage.AttachmentType.IMAGE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.messengervr.interfaces.MessengerMessage$AttachmentType r0 = com.oculus.messengervr.interfaces.MessengerMessage.AttachmentType.STICKER     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.messengervr.interfaces.MessengerMessage$AttachmentType r0 = com.oculus.messengervr.interfaces.MessengerMessage.AttachmentType.XMA     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.messengervr.interfaces.MessengerMessage$AttachmentType r0 = com.oculus.messengervr.interfaces.MessengerMessage.AttachmentType.NONE     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.views.MessageListAdapter.AnonymousClass2.<clinit>():void");
        }
    }

    public MessageListAdapter(MessengerPanelApp messengerPanelApp, BaseMessageBubbleViewHolder.MessageBubbleCallbacks messageBubbleCallbacks) {
        super(DIFF_CALLBACK);
        this.mPanelApp = messengerPanelApp;
        this.mMessageBubbleCallbacks = messageBubbleCallbacks;
    }

    @Override // X.AnonymousClass1Aj
    public long getItemId(int i) {
        return (long) ((MessengerMessage) getItem(i)).getMessageId().hashCode();
    }

    @Override // X.AnonymousClass1Aj
    public int getItemViewType(int i) {
        boolean isPresent;
        if (((MessengerMessage) getItem(i)).isAdminMessage()) {
            return R.layout.anytime_tablet_messenger_admin_message_item_v2;
        }
        switch (((MessengerMessage) getItem(i)).getAttachmentType().ordinal()) {
            case 2:
                return R.layout.anytime_tablet_messenger_message_item_image_v2;
            case 3:
                if (((MessengerMessage) getItem(i)).getAttachmentImageUrls() == null || ((MessengerMessage) getItem(i)).getAttachmentImageUrls().length <= 1) {
                    return R.layout.anytime_tablet_messenger_message_item_image_v2;
                }
                return R.layout.anytime_tablet_messenger_message_item_text_v2;
            case 4:
            case 5:
            case 6:
            case 7:
            default:
                if (this.mPanelApp.getAPIManager().mCurrentAPI.getType() == MessengerAPIType.OC_CHATS) {
                    isPresent = PartyInviteLink.validate(((MessengerMessage) getItem(i)).getText());
                    break;
                } else {
                    return R.layout.anytime_tablet_messenger_message_item_text_v2;
                }
            case 8:
                isPresent = ((MessengerMessage) getItem(i)).getXmaAttachment().isPresent();
                break;
        }
        if (!isPresent) {
            return R.layout.anytime_tablet_messenger_message_item_text_v2;
        }
        return R.layout.anytime_tablet_messenger_xma_item_v2;
    }

    @Override // X.AnonymousClass1Aj
    public void onBindViewHolder(AnonymousClass1Ah r7, int i) {
        MessengerMessage messengerMessage = (MessengerMessage) getItem(i);
        MessengerMessage messengerMessage2 = null;
        MessengerMessage messengerMessage3 = null;
        if (i > 0) {
            messengerMessage3 = (MessengerMessage) getItem(i - 1);
        }
        if (i < getItemCount() - 1) {
            messengerMessage2 = (MessengerMessage) getItem(i + 1);
        }
        if (r7 instanceof TextBubbleViewHolder) {
            ((TextBubbleViewHolder) r7).bindMessage(messengerMessage, messengerMessage3, messengerMessage2);
        } else if (r7 instanceof ImageBubbleViewHolder) {
            ((ImageBubbleViewHolder) r7).bindMessage(messengerMessage, messengerMessage3, messengerMessage2);
        } else if (r7 instanceof XMABubbleViewHolder) {
            ((XMABubbleViewHolder) r7).bindMessage(new XMABubbleConfig(messengerMessage, this.mPanelApp, r7.itemView.getContext()), messengerMessage, messengerMessage3, messengerMessage2);
        } else if (r7 instanceof AdminMessageViewHolder) {
            ((AdminMessageViewHolder) r7).bindMessage(messengerMessage);
        } else {
            Log.e(TAG, "Unhandled ViewHolder provided");
        }
        if (this.mPanelApp.getAPIManager().mCurrentAPI.getType() == MessengerAPIType.TEST) {
            r7.itemView.setTag(((MessengerMessage) getItem(i)).getMessageId());
        }
    }

    @Override // X.AnonymousClass1Aj
    public AnonymousClass1Ah onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == R.layout.anytime_tablet_messenger_admin_message_item_v2) {
            return new AdminMessageViewHolder(AnytimeTabletMessengerAdminMessageItemV2Binding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
        }
        Context context = viewGroup.getContext();
        AnytimeTabletMessengerMessageItemContainerV2Binding inflate = AnytimeTabletMessengerMessageItemContainerV2Binding.inflate(LayoutInflater.from(context), viewGroup, false);
        if (i == R.layout.anytime_tablet_messenger_message_item_text_v2) {
            return new TextBubbleViewHolder(this.mPanelApp, inflate, AnytimeTabletMessengerMessageItemTextV2Binding.inflate(LayoutInflater.from(context), inflate.messageItem, true), this.mMessageBubbleCallbacks);
        } else if (i == R.layout.anytime_tablet_messenger_message_item_image_v2) {
            return new ImageBubbleViewHolder(this.mPanelApp, inflate, AnytimeTabletMessengerMessageItemImageV2Binding.inflate(LayoutInflater.from(context), inflate.messageItem, true), this.mMessageBubbleCallbacks);
        } else if (i == R.layout.anytime_tablet_messenger_xma_item_v2) {
            return new XMABubbleViewHolder(this.mPanelApp, inflate, AnytimeTabletMessengerXmaItemV2Binding.inflate(LayoutInflater.from(context), inflate.messageItem, true), this.mMessageBubbleCallbacks);
        } else {
            Log.e(TAG, AnonymousClass006.A03("Unhandled view type provided: ", i));
            return null;
        }
    }

    @Override // X.AnonymousClass1Aj
    public void onViewRecycled(AnonymousClass1Ah r2) {
        super.onViewRecycled(r2);
        if (r2 instanceof BaseMessageBubbleViewHolder) {
            ((BaseMessageBubbleViewHolder) r2).onViewRecycled();
        }
    }
}
