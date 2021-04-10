package com.oculus.panelapp.messenger.views;

import android.content.Context;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.socialtablet.util.ImageCornerRadius;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemContainerV2Binding;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemImageV2Binding;
import com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder;
import com.oculus.socialplatform.R;
import java.util.Optional;

public class ImageBubbleViewHolder extends BaseMessageBubbleViewHolder {
    public static final String TAG = LoggingUtil.tag(ImageBubbleViewHolder.class);
    public AnytimeTabletMessengerMessageItemImageV2Binding mBinding;
    public Context mContext;
    public MessengerPanelApp mPanelApp;

    /* renamed from: com.oculus.panelapp.messenger.views.ImageBubbleViewHolder$1  reason: invalid class name */
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
                com.oculus.panelapp.messenger.views.ImageBubbleViewHolder.AnonymousClass1.$SwitchMap$com$oculus$panelapp$messenger$views$BaseMessageBubbleViewHolder$MessageStyleType = r2
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
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.messenger.views.ImageBubbleViewHolder.AnonymousClass1.<clinit>():void");
        }
    }

    public ImageBubbleViewHolder(MessengerPanelApp messengerPanelApp, AnytimeTabletMessengerMessageItemContainerV2Binding anytimeTabletMessengerMessageItemContainerV2Binding, AnytimeTabletMessengerMessageItemImageV2Binding anytimeTabletMessengerMessageItemImageV2Binding, BaseMessageBubbleViewHolder.MessageBubbleCallbacks messageBubbleCallbacks) {
        super(messengerPanelApp, anytimeTabletMessengerMessageItemContainerV2Binding, anytimeTabletMessengerMessageItemImageV2Binding.imageAttachment, messageBubbleCallbacks);
        this.mPanelApp = messengerPanelApp;
        this.mBinding = anytimeTabletMessengerMessageItemImageV2Binding;
        this.mContext = anytimeTabletMessengerMessageItemContainerV2Binding.mRoot.getContext();
    }

    private ImageCornerRadius getImageBubbleRoundedCornersRadius(BaseMessageBubbleViewHolder.MessageStyleType messageStyleType) {
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.abc_dialog_padding_top_material);
        int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_elevation_material);
        switch (messageStyleType.ordinal()) {
            case 0:
                return new ImageCornerRadius(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize);
            case 1:
                return new ImageCornerRadius(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize);
            case 2:
                return new ImageCornerRadius(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize);
            case 3:
                return new ImageCornerRadius(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
            default:
                return null;
        }
    }

    public void bindMessage(MessengerMessage messengerMessage, MessengerMessage messengerMessage2, MessengerMessage messengerMessage3) {
        configureMessageItemLayout(messengerMessage, messengerMessage2, messengerMessage3, this.mBinding.imageAttachment);
        bindReactionsProperties(messengerMessage);
        if (messengerMessage.getAttachmentImageUrls().length == 0) {
            this.mBinding.imageAttachment.setImageDrawable(this.mContext.getDrawable(R.drawable.ic_social_default_profile));
        } else if (MessengerMessage.AttachmentType.STICKER.equals(messengerMessage.getAttachmentType())) {
            int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_tablet_sticker_max_size);
            this.mPanelApp.getImageHandler().loadToView(messengerMessage.getAttachmentImageUrls()[0], Math.min(messengerMessage.getStickerPreviewWidth(), dimensionPixelSize), Math.min(messengerMessage.getStickerPreviewHeight(), dimensionPixelSize), this.mBinding.imageAttachment);
        } else {
            this.mPanelApp.getImageHandler().loadRoundedCornersToView(messengerMessage.getAttachmentImageUrls()[0], this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_tablet_image_bubble_max_height), this.mContext.getResources().getDimensionPixelSize(R.dimen.messenger_tablet_image_bubble_max_height), Optional.ofNullable(getImageBubbleRoundedCornersRadius(getMessageStyleType(messengerMessage, messengerMessage2, messengerMessage3))), this.mBinding.imageAttachment);
        }
    }

    @Override // com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder
    public void onViewRecycled() {
        super.onViewRecycled();
        this.mPanelApp.getImageHandler().unloadView(this.mBinding.imageAttachment);
    }
}
