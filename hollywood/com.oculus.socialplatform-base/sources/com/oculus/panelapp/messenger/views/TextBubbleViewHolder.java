package com.oculus.panelapp.messenger.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.TypedValue;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.api.MessengerAPIType;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemContainerV2Binding;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemTextV2Binding;
import com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder;
import com.oculus.socialplatform.R;

public class TextBubbleViewHolder extends BaseMessageBubbleViewHolder {
    public Context mContext;
    public MessengerPanelApp mPanelApp;
    public AnytimeTabletMessengerMessageItemTextV2Binding mTextBinding;

    /* renamed from: com.oculus.panelapp.messenger.views.TextBubbleViewHolder$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$messengervr$interfaces$MessengerMessage$AttachmentType;

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.oculus.messengervr.interfaces.MessengerMessage$AttachmentType[] r0 = com.oculus.messengervr.interfaces.MessengerMessage.AttachmentType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.messenger.views.TextBubbleViewHolder.AnonymousClass1.$SwitchMap$com$oculus$messengervr$interfaces$MessengerMessage$AttachmentType = r2
                com.oculus.messengervr.interfaces.MessengerMessage$AttachmentType r0 = com.oculus.messengervr.interfaces.MessengerMessage.AttachmentType.STICKER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.messengervr.interfaces.MessengerMessage$AttachmentType r0 = com.oculus.messengervr.interfaces.MessengerMessage.AttachmentType.IMAGE     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.messengervr.interfaces.MessengerMessage$AttachmentType r0 = com.oculus.messengervr.interfaces.MessengerMessage.AttachmentType.VIDEO     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.messengervr.interfaces.MessengerMessage$AttachmentType r0 = com.oculus.messengervr.interfaces.MessengerMessage.AttachmentType.AUDIO     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.messengervr.interfaces.MessengerMessage$AttachmentType r0 = com.oculus.messengervr.interfaces.MessengerMessage.AttachmentType.FILE     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                com.oculus.messengervr.interfaces.MessengerMessage$AttachmentType r0 = com.oculus.messengervr.interfaces.MessengerMessage.AttachmentType.ANIMATED_IMAGE     // Catch:{ NoSuchFieldError -> 0x003f }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x003f }
                r0 = 6
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x003f }
            L_0x003f:
                com.oculus.messengervr.interfaces.MessengerMessage$AttachmentType r0 = com.oculus.messengervr.interfaces.MessengerMessage.AttachmentType.XMA     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r0 = 7
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                com.oculus.messengervr.interfaces.MessengerMessage$AttachmentType r0 = com.oculus.messengervr.interfaces.MessengerMessage.AttachmentType.NONE     // Catch:{ NoSuchFieldError -> 0x0052 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0052 }
                r0 = 8
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0052 }
            L_0x0052:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.views.TextBubbleViewHolder.AnonymousClass1.<clinit>():void");
        }
    }

    public TextBubbleViewHolder(MessengerPanelApp messengerPanelApp, AnytimeTabletMessengerMessageItemContainerV2Binding anytimeTabletMessengerMessageItemContainerV2Binding, AnytimeTabletMessengerMessageItemTextV2Binding anytimeTabletMessengerMessageItemTextV2Binding, BaseMessageBubbleViewHolder.MessageBubbleCallbacks messageBubbleCallbacks) {
        super(messengerPanelApp, anytimeTabletMessengerMessageItemContainerV2Binding, anytimeTabletMessengerMessageItemTextV2Binding.messageText, messageBubbleCallbacks);
        this.mTextBinding = anytimeTabletMessengerMessageItemTextV2Binding;
        this.mContext = anytimeTabletMessengerMessageItemContainerV2Binding.mRoot.getContext();
        this.mPanelApp = messengerPanelApp;
    }

    private boolean hasXmaAttachment(MessengerMessage messengerMessage) {
        if (this.mPanelApp.getAPIManager().mCurrentAPI.getType() == MessengerAPIType.OC_CHATS || !messengerMessage.getXmaAttachment().isPresent()) {
            return false;
        }
        return true;
    }

    public void bindMessage(MessengerMessage messengerMessage, MessengerMessage messengerMessage2, MessengerMessage messengerMessage3) {
        configureMessageItemLayout(messengerMessage, messengerMessage2, messengerMessage3, this.mTextBinding.messageText);
        this.mTextBinding.setDisplayText(getMessageDisplayText(messengerMessage));
        this.mTextBinding.setDisplayTextTypeface(getMessageTextTypeFace(messengerMessage));
        this.mTextBinding.setDisplayTextColor(getMessageTextColor(messengerMessage));
        bindReactionsProperties(messengerMessage);
    }

    public int getMessageTextColor(MessengerMessage messengerMessage) {
        Resources.Theme theme;
        int i;
        TypedValue typedValue = new TypedValue();
        if (isMessageTypeSupported(messengerMessage)) {
            theme = this.mContext.getTheme();
            i = R.attr.ocPrimaryText;
        } else {
            theme = this.mContext.getTheme();
            i = R.attr.ocSecondaryText;
        }
        theme.resolveAttribute(i, typedValue, true);
        return typedValue.data;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0037, code lost:
        if (r1 <= 1) goto L_0x0039;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getMessageDisplayText(com.oculus.messengervr.interfaces.MessengerMessage r4) {
        /*
            r3 = this;
            boolean r0 = r3.isMessageTypeSupported(r4)
            if (r0 == 0) goto L_0x000b
            java.lang.String r0 = r4.getText()
            return r0
        L_0x000b:
            com.oculus.messengervr.interfaces.MessengerMessage$AttachmentType r0 = r4.getAttachmentType()
            int r0 = r0.ordinal()
            switch(r0) {
                case 2: goto L_0x0024;
                case 3: goto L_0x0028;
                case 4: goto L_0x0049;
                case 5: goto L_0x003d;
                case 6: goto L_0x0041;
                case 7: goto L_0x0045;
                case 8: goto L_0x004d;
                default: goto L_0x0016;
            }
        L_0x0016:
            r2 = 2131689543(0x7f0f0047, float:1.9008104E38)
        L_0x0019:
            android.content.Context r0 = r3.mContext
            android.content.res.Resources r0 = r0.getResources()
            java.lang.String r0 = r0.getString(r2)
            return r0
        L_0x0024:
            r2 = 2131689546(0x7f0f004a, float:1.900811E38)
            goto L_0x0019
        L_0x0028:
            java.lang.String[] r0 = r4.getAttachmentImageUrls()
            if (r0 == 0) goto L_0x0039
            java.lang.String[] r0 = r4.getAttachmentImageUrls()
            int r1 = r0.length
            r0 = 1
            r2 = 2131689545(0x7f0f0049, float:1.9008108E38)
            if (r1 > r0) goto L_0x0019
        L_0x0039:
            r2 = 2131689544(0x7f0f0048, float:1.9008106E38)
            goto L_0x0019
        L_0x003d:
            r2 = 2131689547(0x7f0f004b, float:1.9008112E38)
            goto L_0x0019
        L_0x0041:
            r2 = 2131689541(0x7f0f0045, float:1.90081E38)
            goto L_0x0019
        L_0x0045:
            r2 = 2131689542(0x7f0f0046, float:1.9008102E38)
            goto L_0x0019
        L_0x0049:
            r2 = 2131689540(0x7f0f0044, float:1.9008098E38)
            goto L_0x0019
        L_0x004d:
            r2 = 2131689493(0x7f0f0015, float:1.9008003E38)
            goto L_0x0019
            switch-data {2->0x0024, 3->0x0028, 4->0x0049, 5->0x003d, 6->0x0041, 7->0x0045, 8->0x004d, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.views.TextBubbleViewHolder.getMessageDisplayText(com.oculus.messengervr.interfaces.MessengerMessage):java.lang.String");
    }

    private boolean isMessageTypeSupported(MessengerMessage messengerMessage) {
        if (messengerMessage.getAttachmentType() != MessengerMessage.AttachmentType.NONE || hasXmaAttachment(messengerMessage)) {
            return false;
        }
        return true;
    }

    public Typeface getMessageTextTypeFace(MessengerMessage messengerMessage) {
        if (isMessageTypeSupported(messengerMessage)) {
            return Typeface.DEFAULT;
        }
        return Typeface.create(Typeface.DEFAULT, 2);
    }
}
