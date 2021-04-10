package com.oculus.panelapp.messenger.views;

import X.AnonymousClass1Ah;
import X.AnonymousClass2a4;
import android.content.Context;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.LoggingConstants;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerReaction;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemContainerV2Binding;
import com.oculus.panelapp.messenger.util.EmojiReplacer;
import com.oculus.socialplatform.R;
import com.oculus.socialplatform.tablet.emoji.MessengerVrEmojiUtil;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public abstract class BaseMessageBubbleViewHolder extends AnonymousClass1Ah {
    public static final long MESSAGE_GROUPING_OFFSET_TIME = TimeUnit.MINUTES.toMillis(5);
    public static final String TAG = LoggingUtil.tag(BaseMessageBubbleViewHolder.class);
    public static SparseArray<AnonymousClass2a4> sBubbleConstraintsThemSenderMap = new SparseArray<>();
    public static SparseArray<AnonymousClass2a4> sBubbleConstraintsViewerSenderMap = new SparseArray<>();
    public AnytimeTabletMessengerMessageItemContainerV2Binding mBinding;
    public Context mContext;
    public MessageBubbleCallbacks mMessageBubbleCallbacks;
    public MessengerPanelApp mPanelApp;
    public MessageReactionsSummaryPopup mReactionsSummaryPopup;

    public interface MessageBubbleCallbacks {
        void onReactionsPillClick(MessengerMessage messengerMessage);
    }

    public enum MessageStyleType {
        TOP,
        CENTER,
        BOTTOM,
        SOLO
    }

    public void configureMessageItemLayout(MessengerMessage messengerMessage, MessengerMessage messengerMessage2, MessengerMessage messengerMessage3, View view) {
        configureMessageItemLayoutInternal(messengerMessage, messengerMessage2, messengerMessage3, view, false);
    }

    public void configureMessageItemLayoutForXMA(MessengerMessage messengerMessage, MessengerMessage messengerMessage2, MessengerMessage messengerMessage3, View view) {
        configureMessageItemLayoutInternal(messengerMessage, messengerMessage2, messengerMessage3, view, true);
    }

    /* renamed from: com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$messenger$views$BaseMessageBubbleViewHolder$MessageStyleType;

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
                com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder$MessageStyleType[] r0 = com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.MessageStyleType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.AnonymousClass1.$SwitchMap$com$oculus$panelapp$messenger$views$BaseMessageBubbleViewHolder$MessageStyleType = r2
                com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder$MessageStyleType r0 = com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.MessageStyleType.CENTER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder$MessageStyleType r0 = com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.MessageStyleType.TOP     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder$MessageStyleType r0 = com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.MessageStyleType.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder$MessageStyleType r0 = com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.MessageStyleType.SOLO     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BaseMessageBubbleViewHolder(com.oculus.panelapp.messenger.MessengerPanelApp r3, com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemContainerV2Binding r4, android.view.View r5, com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.MessageBubbleCallbacks r6) {
        /*
            r2 = this;
            android.view.View r0 = r4.mRoot
            r2.<init>(r0)
            r2.mPanelApp = r3
            r2.mBinding = r4
            android.content.Context r0 = r0.getContext()
            r2.mContext = r0
            r2.mMessageBubbleCallbacks = r6
            r2.cacheMessageBubbleViewConstraints(r5)
            android.content.Context r1 = r2.mContext
            com.oculus.panelapp.messenger.views.MessageReactionsSummaryPopup r0 = new com.oculus.panelapp.messenger.views.MessageReactionsSummaryPopup
            r0.<init>(r1, r3)
            r2.mReactionsSummaryPopup = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.<init>(com.oculus.panelapp.messenger.MessengerPanelApp, com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemContainerV2Binding, android.view.View, com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder$MessageBubbleCallbacks):void");
    }

    private void cacheMessageBubbleViewConstraints(View view) {
        if (sBubbleConstraintsViewerSenderMap.get(view.getId()) == null) {
            AnonymousClass2a4 r5 = new AnonymousClass2a4();
            r5.A0A(this.mBinding.messageItem);
            r5.A06(view.getId(), 7, R.id.message_item, 7);
            r5.A06(R.id.profile_pic_them_button, 4, view.getId(), 4);
            r5.A06(R.id.reactions_pill_wrapper, 3, view.getId(), 4);
            r5.A07(R.id.reactions_pill_wrapper, 7, view.getId(), 7, 2);
            sBubbleConstraintsViewerSenderMap.put(view.getId(), r5);
        }
        if (sBubbleConstraintsThemSenderMap.get(view.getId()) == null) {
            AnonymousClass2a4 r6 = new AnonymousClass2a4();
            r6.A0A(this.mBinding.messageItem);
            r6.A06(view.getId(), 6, R.id.profile_pic_them_button, 7);
            r6.A06(R.id.profile_pic_them_button, 4, view.getId(), 4);
            r6.A06(R.id.reactions_pill_wrapper, 3, view.getId(), 4);
            int id = view.getId();
            int id2 = view.getId();
            r6.A07(R.id.reactions_pill_wrapper, 6, id, 6, 2);
            r6.A07(R.id.reactions_pill_wrapper, 7, id2, 7, 2);
            r6.A00.get(Integer.valueOf((int) R.id.reactions_pill_wrapper)).A02.A03 = 0.5f;
            sBubbleConstraintsThemSenderMap.put(view.getId(), r6);
        }
    }

    private boolean isMessageExclusivelyEmojis(String str) {
        MessengerVrEmojiUtil messengerVrEmojiUtil = EmojiReplacer.sEmojiUtil;
        if (messengerVrEmojiUtil == null) {
            return false;
        }
        return messengerVrEmojiUtil.isTextOnlyEmojiAndWhitespace(str, Integer.MAX_VALUE);
    }

    private void setReactionPillProperties(MessengerMessage messengerMessage) {
        if (!this.mPanelApp.getDeviceConfig(DeviceConfigSocialPlatformMC.MESSENGER_VR_REACTIONS_READ)) {
            this.mBinding.reactionsPill.setVisibility(8);
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mBinding.messageItem.getLayoutParams();
        marginLayoutParams.setMargins(0, 0, 0, 0);
        Optional<MessengerReaction[]> reactions = messengerMessage.getReactions();
        if (!reactions.isPresent() || !this.mBinding.reactionsPill.setReactions(this.mPanelApp, Arrays.asList(reactions.get()))) {
            this.mBinding.reactionsPill.setVisibility(8);
            this.mBinding.messageItem.setLayoutParams(marginLayoutParams);
            this.mBinding.messageItem.setPadding(0, 0, 0, this.mContext.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_elevation_material));
            return;
        }
        this.mBinding.reactionsPill.setVisibility(0);
        marginLayoutParams.setMargins(0, 0, 0, this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_tablet_message_bubble_margin_bottom_with_reactions));
        this.mBinding.messageItem.setLayoutParams(marginLayoutParams);
        this.mBinding.messageItem.setPadding(0, 0, 0, this.mContext.getResources().getDimensionPixelSize(R.dimen.abc_control_corner_material));
        this.mPanelApp.getLogger().logImpression(SurfaceType.FB_MESSENGER_REACTIONS_PILL, ImmutableMap.A05(LoggingConstants.MESSAGE_ID, messengerMessage.getMessageId()));
    }

    public void bindReactionsProperties(MessengerMessage messengerMessage) {
        this.mBinding.reactionsPill.setOnClickListener(new View.OnClickListener(messengerMessage) {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$BaseMessageBubbleViewHolder$cXJo7aZhntWL0SaqEcCQIFnmXiM2 */
            public final /* synthetic */ MessengerMessage f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                BaseMessageBubbleViewHolder.this.lambda$bindReactionsProperties$0$BaseMessageBubbleViewHolder(this.f$1, view);
            }
        });
        this.mBinding.reactionsPill.setOnHoverListener(new View.OnHoverListener(messengerMessage) {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$BaseMessageBubbleViewHolder$ge25CGGfGfb0FGdm5CWIODp87_Y2 */
            public final /* synthetic */ MessengerMessage f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return BaseMessageBubbleViewHolder.this.lambda$bindReactionsProperties$1$BaseMessageBubbleViewHolder(this.f$1, view, motionEvent);
            }
        });
    }

    @Nullable
    @VisibleForTesting
    public MessengerVrEmojiUtil getEmojiUtil() {
        return EmojiReplacer.sEmojiUtil;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r3 != null) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.MessageStyleType getMessageStyleType(com.oculus.messengervr.interfaces.MessengerMessage r2, com.oculus.messengervr.interfaces.MessengerMessage r3, com.oculus.messengervr.interfaces.MessengerMessage r4) {
        /*
            r1 = this;
            if (r3 == 0) goto L_0x0008
            boolean r0 = r1.messagesAreInDiffBlocks(r2, r3)
            if (r0 == 0) goto L_0x0015
        L_0x0008:
            if (r4 == 0) goto L_0x0013
            boolean r0 = r1.messagesAreInDiffBlocks(r2, r4)
            if (r0 != 0) goto L_0x0013
            com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder$MessageStyleType r0 = com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.MessageStyleType.TOP
            return r0
        L_0x0013:
            if (r3 == 0) goto L_0x0037
        L_0x0015:
            boolean r0 = r1.messagesAreInDiffBlocks(r2, r3)
            if (r0 != 0) goto L_0x0026
            if (r4 == 0) goto L_0x0023
            boolean r0 = r1.messagesAreInDiffBlocks(r2, r4)
            if (r0 == 0) goto L_0x0026
        L_0x0023:
            com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder$MessageStyleType r0 = com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.MessageStyleType.BOTTOM
            return r0
        L_0x0026:
            boolean r0 = r1.messagesAreInDiffBlocks(r2, r3)
            if (r0 != 0) goto L_0x0037
            if (r4 == 0) goto L_0x0037
            boolean r0 = r1.messagesAreInDiffBlocks(r2, r4)
            if (r0 != 0) goto L_0x0037
            com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder$MessageStyleType r0 = com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.MessageStyleType.CENTER
            return r0
        L_0x0037:
            com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder$MessageStyleType r0 = com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.MessageStyleType.SOLO
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder.getMessageStyleType(com.oculus.messengervr.interfaces.MessengerMessage, com.oculus.messengervr.interfaces.MessengerMessage, com.oculus.messengervr.interfaces.MessengerMessage):com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder$MessageStyleType");
    }

    public boolean isMessageSenderLocalViewer(long j) {
        if (this.mPanelApp.getAPIManager().mCurrentAPI.getUserID() == null || j != Long.parseLong(this.mPanelApp.getAPIManager().mCurrentAPI.getUserID())) {
            return false;
        }
        return true;
    }

    public /* synthetic */ void lambda$bindReactionsProperties$0$BaseMessageBubbleViewHolder(MessengerMessage messengerMessage, View view) {
        this.mPanelApp.getLogger().logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_THREAD_REACTIONS_PILL, SurfaceType.THREAD_VIEW);
        this.mMessageBubbleCallbacks.onReactionsPillClick(messengerMessage);
    }

    public void onViewRecycled() {
        this.mPanelApp.getImageHandler().unloadView(this.mBinding.profilePicThem);
        MessengerReactionsPill messengerReactionsPill = this.mBinding.reactionsPill;
        messengerReactionsPill.destroy();
        messengerReactionsPill.setOnHoverListener(null);
        this.mBinding.reactionsPill.setOnClickListener(null);
    }

    private void configureMessageItemLayoutInternal(MessengerMessage messengerMessage, MessengerMessage messengerMessage2, MessengerMessage messengerMessage3, View view, boolean z) {
        MessageStyleType messageStyleType = getMessageStyleType(messengerMessage, messengerMessage2, messengerMessage3);
        boolean shouldShowSenderInfo = shouldShowSenderInfo(messengerMessage, messageStyleType);
        this.mBinding.setShouldShowSenderInfo(shouldShowSenderInfo);
        if (shouldShowSenderInfo) {
            this.mPanelApp.getImageHandler().loadCircleCroppedToView(messengerMessage.getSenderProfilePictureUrl(), this.mBinding.profilePicThem);
        }
        OCButton oCButton = this.mBinding.profilePicThemButton;
        oCButton.mEventHandler = this.mPanelApp;
        oCButton.setOnClickListener(new View.OnClickListener(messengerMessage) {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$BaseMessageBubbleViewHolder$DyJysH8lpd42r5XoSN4CxoThYe82 */
            public final /* synthetic */ MessengerMessage f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                BaseMessageBubbleViewHolder.this.lambda$configureMessageItemLayoutInternal$2$BaseMessageBubbleViewHolder(this.f$1, view);
            }
        });
        setMessageBubbleProperties(messengerMessage, messageStyleType, view, z);
        setReactionPillProperties(messengerMessage);
    }

    private boolean messagesAreInDiffBlocks(MessengerMessage messengerMessage, MessengerMessage messengerMessage2) {
        if (messengerMessage2.isAdminMessage() || messengerMessage.getSenderId() != messengerMessage2.getSenderId() || Math.abs(messengerMessage.getTimestampMs() - messengerMessage2.getTimestampMs()) > MESSAGE_GROUPING_OFFSET_TIME) {
            return true;
        }
        return false;
    }

    private void setMessageBubbleProperties(MessengerMessage messengerMessage, MessageStyleType messageStyleType, View view, boolean z) {
        Context context;
        int i;
        if (isMessageSenderLocalViewer(messengerMessage.getSenderId())) {
            sBubbleConstraintsViewerSenderMap.get(view.getId()).A09(this.mBinding.messageItem);
            if (messengerMessage.getAttachmentType() != MessengerMessage.AttachmentType.STICKER && !isMessageExclusivelyEmojis(messengerMessage.getText())) {
                if (!z) {
                    switch (messageStyleType.ordinal()) {
                        case 0:
                            context = this.mContext;
                            i = R.drawable.anytime_tablet_messenger_thread_message_bubble_viewer_top;
                            break;
                        case 1:
                            context = this.mContext;
                            i = R.drawable.anytime_tablet_messenger_thread_message_bubble_viewer_center;
                            break;
                        case 2:
                            context = this.mContext;
                            i = R.drawable.anytime_tablet_messenger_thread_message_bubble_viewer_bottom;
                            break;
                        case 3:
                            break;
                        default:
                            return;
                    }
                }
                context = this.mContext;
                i = R.drawable.anytime_tablet_messenger_thread_message_bubble_viewer_solo;
            }
            view.setBackgroundColor(0);
            return;
        }
        sBubbleConstraintsThemSenderMap.get(view.getId()).A09(this.mBinding.messageItem);
        if (messengerMessage.getAttachmentType() != MessengerMessage.AttachmentType.STICKER && !isMessageExclusivelyEmojis(messengerMessage.getText())) {
            if (!z) {
                switch (messageStyleType.ordinal()) {
                    case 0:
                        context = this.mContext;
                        i = R.drawable.anytime_tablet_messenger_thread_message_bubble_them_top;
                        break;
                    case 1:
                        context = this.mContext;
                        i = R.drawable.anytime_tablet_messenger_thread_message_bubble_them_center;
                        break;
                    case 2:
                        context = this.mContext;
                        i = R.drawable.anytime_tablet_messenger_thread_message_bubble_them_bottom;
                        break;
                    case 3:
                        break;
                    default:
                        return;
                }
            }
            context = this.mContext;
            i = R.drawable.anytime_tablet_messenger_thread_message_bubble_them_solo;
        }
        view.setBackgroundColor(0);
        return;
        view.setBackground(context.getDrawable(i));
    }

    private boolean shouldShowSenderInfo(MessengerMessage messengerMessage, MessageStyleType messageStyleType) {
        if (isMessageSenderLocalViewer(messengerMessage.getSenderId())) {
            return false;
        }
        if (messageStyleType == MessageStyleType.BOTTOM || messageStyleType == MessageStyleType.SOLO) {
            return true;
        }
        return false;
    }

    public /* synthetic */ boolean lambda$bindReactionsProperties$1$BaseMessageBubbleViewHolder(MessengerMessage messengerMessage, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.mPanelApp.onButtonEnter();
            MessageReactionsSummaryPopup messageReactionsSummaryPopup = this.mReactionsSummaryPopup;
            AnytimeTabletMessengerMessageItemContainerV2Binding anytimeTabletMessengerMessageItemContainerV2Binding = this.mBinding;
            messageReactionsSummaryPopup.show(anytimeTabletMessengerMessageItemContainerV2Binding.reactionsPill, anytimeTabletMessengerMessageItemContainerV2Binding.messageItem, messengerMessage);
            return false;
        } else if (actionMasked != 10 || !this.mReactionsSummaryPopup.isShowing()) {
            return false;
        } else {
            this.mReactionsSummaryPopup.dismiss();
            return false;
        }
    }

    public /* synthetic */ void lambda$configureMessageItemLayoutInternal$2$BaseMessageBubbleViewHolder(MessengerMessage messengerMessage, View view) {
        messengerMessage.getSenderId();
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_THREAD_PROFILE_PICTURE_THEM, SurfaceType.THREAD_VIEW);
        this.mPanelApp.actionNavigateToProfile(messengerMessage.getSenderId());
    }
}
