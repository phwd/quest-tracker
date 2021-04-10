package com.oculus.panelapp.messenger.views;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.sociallogger.ActionId;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SocialLogger;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.common.socialtablet.util.ImageCornerRadius;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.api.MessengerAPIType;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerMessageItemContainerV2Binding;
import com.oculus.panelapp.messenger.databinding.AnytimeTabletMessengerXmaItemV2Binding;
import com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.telemetry.SourceConstants;
import java.util.Optional;
import java.util.UUID;

public class XMABubbleViewHolder extends BaseMessageBubbleViewHolder {
    public static final String OCULUS_ICON = "vrmsys://icon?name=oculus";
    public static final String OCULUS_JOIN_PARTY = "oculus_join_party";
    public static final String TAG = LoggingUtil.tag(XMABubbleViewHolder.class);
    public AnytimeTabletMessengerXmaItemV2Binding mBinding;
    public Context mContext;
    public MessengerPanelApp mPanelApp;
    public XMABubbleViewModel mViewModel;

    public XMABubbleViewHolder(MessengerPanelApp messengerPanelApp, AnytimeTabletMessengerMessageItemContainerV2Binding anytimeTabletMessengerMessageItemContainerV2Binding, AnytimeTabletMessengerXmaItemV2Binding anytimeTabletMessengerXmaItemV2Binding, BaseMessageBubbleViewHolder.MessageBubbleCallbacks messageBubbleCallbacks) {
        super(messengerPanelApp, anytimeTabletMessengerMessageItemContainerV2Binding, anytimeTabletMessengerXmaItemV2Binding.messageWithXma, messageBubbleCallbacks);
        this.mBinding = anytimeTabletMessengerXmaItemV2Binding;
        this.mContext = anytimeTabletMessengerMessageItemContainerV2Binding.mRoot.getContext();
        this.mPanelApp = messengerPanelApp;
        XMABubbleViewModel xMABubbleViewModel = new XMABubbleViewModel();
        this.mViewModel = xMABubbleViewModel;
        anytimeTabletMessengerXmaItemV2Binding.setXmaBubbleViewModel(xMABubbleViewModel);
        this.mBinding.xmaItem.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$XMABubbleViewHolder$ijJ0Yd0b7b3zTsZXjZdRKD2ADAc2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return XMABubbleViewHolder.this.lambda$new$0$XMABubbleViewHolder(view, motionEvent);
            }
        });
    }

    private void bindFavicon(XMABubbleConfig xMABubbleConfig) {
        String str = (String) Optional.ofNullable(xMABubbleConfig.mFaviconUrl).orElse(null);
        XMABubbleViewModel xMABubbleViewModel = this.mViewModel;
        boolean z = false;
        if (str != null) {
            z = true;
        }
        xMABubbleViewModel.setShouldShowFavicon(z);
        if (str == null) {
            return;
        }
        if (OCULUS_ICON.equals(str)) {
            this.mBinding.favicon.setImageDrawable(this.mContext.getDrawable(R.drawable.anytime_tablet_messenger_thread_xma_favicon_oculus));
            return;
        }
        Resources resources = this.mContext.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.anytime_tablet_common_rectangular_button_height_v2);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.abc_action_bar_elevation_material);
        this.mPanelApp.getImageHandler().loadRoundedCornersCroppedToView(str, dimensionPixelSize, dimensionPixelSize, Optional.of(new ImageCornerRadius(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2)), this.mBinding.favicon);
    }

    private void bindPartyInviteXma(XMABubbleConfig xMABubbleConfig) {
        this.mBinding.xmaItem.setOnClickListener(new View.OnClickListener((Long) Optional.ofNullable(xMABubbleConfig.mDefaultCtaTargetId).orElse(null), (String) Optional.ofNullable(xMABubbleConfig.mDefaultCtaActionContentBlob).orElse(null)) {
            /* class com.oculus.panelapp.messenger.views.$$Lambda$XMABubbleViewHolder$Y9jppndRMly4fsEKvJxH5jfFZVA2 */
            public final /* synthetic */ Long f$1;
            public final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                XMABubbleViewHolder.this.lambda$bindPartyInviteXma$1$XMABubbleViewHolder(this.f$1, this.f$2, view);
            }
        });
    }

    private String getSource() {
        Optional map = Optional.ofNullable(this.mPanelApp.getAPIManager().mCurrentAPI).map($$Lambda$VjxMNw2D9Jz4NH0_m_0sM6armbU2.INSTANCE);
        if (map.isPresent()) {
            if (MessengerAPIType.FB_MSYS.equals(map.get())) {
                return SourceConstants.MESSENGER_FB_CHATS_XMA;
            }
            if (MessengerAPIType.OC_CHATS.equals(map.get())) {
                return SourceConstants.MESSENGER_OC_CHATS_XMA;
            }
        }
        return SourceConstants.MESSENGER_CHATS_XMA;
    }

    private String getXmaSubtitle(XMABubbleConfig xMABubbleConfig) {
        return (String) Optional.ofNullable(xMABubbleConfig.mSubtitleText).orElse("");
    }

    private String getXmaTitle(XMABubbleConfig xMABubbleConfig) {
        return (String) Optional.ofNullable(xMABubbleConfig.mTitleText).orElse("");
    }

    public void bindMessage(XMABubbleConfig xMABubbleConfig, MessengerMessage messengerMessage, MessengerMessage messengerMessage2, MessengerMessage messengerMessage3) {
        configureMessageItemLayoutForXMA(messengerMessage, messengerMessage2, messengerMessage3, this.mBinding.messageWithXma);
        if (OCULUS_JOIN_PARTY.equals(Optional.ofNullable(xMABubbleConfig.mDefaultCtaType).orElse(null))) {
            bindPartyInviteXma(xMABubbleConfig);
        } else {
            this.mBinding.xmaItem.setOnClickListener(null);
        }
        this.mViewModel.setAttachmentTitle(getXmaTitle(xMABubbleConfig));
        this.mViewModel.setAttachmentSubtitle(getXmaSubtitle(xMABubbleConfig));
        this.mViewModel.setMessageDisplayText(messengerMessage.getText());
        bindFavicon(xMABubbleConfig);
        bindReactionsProperties(messengerMessage);
    }

    public /* synthetic */ void lambda$bindPartyInviteXma$1$XMABubbleViewHolder(Long l, String str, View view) {
        this.mPanelApp.onButtonClick();
        onClick(String.valueOf(l), str);
    }

    public void onClick(String str, String str2) {
        String obj;
        MessengerPanelApp messengerPanelApp;
        SystemUXRoute systemUXRoute;
        SocialLogger logger = this.mPanelApp.getLogger();
        if (logger != null) {
            logger.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_THREAD_XMA_PARTY_INVITE, SurfaceType.THREAD_VIEW);
            logger.logActionSuccess(ActionId.NAVIGATE_TO_DIALOG, ClickEventButtonId.AUIV2_MESSENGER_THREAD_XMA_PARTY_INVITE, SurfaceType.THREAD_VIEW, "party_id", String.valueOf(str));
        }
        if (this.mPanelApp.getDeviceConfig(DeviceConfigSocialPlatformMC.JOIN_PARTY_FAR_FIELD_VIEW)) {
            Uri build = Uri.parse("").buildUpon().appendPath("join_party").appendQueryParameter("deeplink_target_id", str).appendQueryParameter("nonce", str2).appendQueryParameter("correlation_id", UUID.randomUUID().toString()).appendQueryParameter("source", getSource()).build();
            messengerPanelApp = this.mPanelApp;
            systemUXRoute = SystemUXRoute.SOCIAL_OVERLAY_PANEL;
            obj = build.toString();
        } else {
            obj = new Uri.Builder().encodedPath("").appendQueryParameter("deeplink_target_id", str).appendQueryParameter("nonce", str2).appendQueryParameter("correlation_id", UUID.randomUUID().toString()).appendQueryParameter("source", getSource()).toString();
            messengerPanelApp = this.mPanelApp;
            systemUXRoute = SystemUXRoute.SOCIAL_CONFIRM_JOIN_PARTY;
        }
        messengerPanelApp.actionNavigate(systemUXRoute, obj);
    }

    public /* synthetic */ boolean lambda$new$0$XMABubbleViewHolder(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 9) {
            return false;
        }
        this.mPanelApp.onButtonEnter();
        return false;
    }

    @Override // com.oculus.panelapp.messenger.views.BaseMessageBubbleViewHolder
    public void onViewRecycled() {
        super.onViewRecycled();
        this.mPanelApp.getImageHandler().unloadView(this.mBinding.favicon);
    }
}
