package com.oculus.panelapp.messenger.views;

import android.content.Context;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.XmaAttachment;
import com.oculus.panelapp.messenger.MessengerPanelApp;
import com.oculus.panelapp.messenger.api.MessengerAPIType;
import com.oculus.panelapp.messenger.util.PartyInviteLink;
import com.oculus.socialplatform.R;
import java.util.Optional;

public class XMABubbleConfig {
    public static final String TAG = LoggingUtil.tag(XMABubbleConfig.class);
    @Nullable
    public String mDefaultCtaActionContentBlob;
    @Nullable
    public Long mDefaultCtaTargetId;
    @Nullable
    public String mDefaultCtaType;
    @Nullable
    public String mFaviconUrl;
    @Nullable
    public String mSubtitleText;
    @Nullable
    public String mTitleText;

    public Optional<String> getDefaultCtaActionContentBlob() {
        return Optional.ofNullable(this.mDefaultCtaActionContentBlob);
    }

    public Optional<Long> getDefaultCtaTargetId() {
        return Optional.ofNullable(this.mDefaultCtaTargetId);
    }

    public Optional<String> getDefaultCtaType() {
        return Optional.ofNullable(this.mDefaultCtaType);
    }

    public Optional<String> getFaviconUrl() {
        return Optional.ofNullable(this.mFaviconUrl);
    }

    public Optional<String> getSubtitleText() {
        return Optional.ofNullable(this.mSubtitleText);
    }

    public Optional<String> getTitleText() {
        return Optional.ofNullable(this.mTitleText);
    }

    public XMABubbleConfig(MessengerMessage messengerMessage, MessengerPanelApp messengerPanelApp, Context context) {
        Optional flatMap;
        if (messengerPanelApp.getAPIManager().mCurrentAPI.getType() == MessengerAPIType.OC_CHATS) {
            PartyInviteLink fromString = PartyInviteLink.fromString(messengerMessage.getText());
            this.mTitleText = context.getResources().getString(R.string.anytime_tablet_messenger_static_party_invite_link_title);
            this.mSubtitleText = context.getResources().getString(R.string.anytime_tablet_messenger_static_party_invite_link_subtitle);
            this.mDefaultCtaTargetId = Long.valueOf(Long.parseLong(fromString.mPartyId));
            this.mDefaultCtaType = XMABubbleViewHolder.OCULUS_JOIN_PARTY;
            this.mFaviconUrl = XMABubbleViewHolder.OCULUS_ICON;
            flatMap = fromString.mNonce;
        } else {
            Optional<XmaAttachment> xmaAttachment = messengerMessage.getXmaAttachment();
            this.mTitleText = xmaAttachment.flatMap($$Lambda$ooowXWnTqA44aMv3pTyFuqmy5V42.INSTANCE).orElse(null);
            this.mSubtitleText = xmaAttachment.flatMap($$Lambda$ZT59zCXvh8U2TgZFAn_mRlrbqaY2.INSTANCE).orElse(null);
            this.mFaviconUrl = xmaAttachment.flatMap($$Lambda$UpIzK7XsYCiK74_pIdtHmN4fBsE2.INSTANCE).orElse(null);
            this.mDefaultCtaTargetId = xmaAttachment.flatMap($$Lambda$D3bRZS6qLTFwIoK9JBmg_nLSzYI2.INSTANCE).orElse(null);
            this.mDefaultCtaType = xmaAttachment.flatMap($$Lambda$evKp5JzbqwuUpeOuYgzSqOgOJM2.INSTANCE).orElse(null);
            flatMap = xmaAttachment.flatMap($$Lambda$290gPNWAQsLAZNutkNKvspX2t3E2.INSTANCE);
        }
        this.mDefaultCtaActionContentBlob = (String) flatMap.orElse(null);
    }
}
