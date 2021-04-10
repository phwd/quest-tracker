package com.oculus.messengervr.oc.models;

import android.annotation.TargetApi;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messenger.models.Message;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerReaction;
import com.oculus.messengervr.interfaces.XmaAttachment;
import java.util.Objects;
import java.util.Optional;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class OcMessengerMessage implements MessengerMessage {
    public static final String NORMAL_MESSAGE_TYPE = "NORMAL";
    public final Message mOcMessage;
    @Nullable
    public String mPatchedSenderProfilePictureUrl;

    public static final class Builder {
        @Nullable
        public Message mOcMessage;
        @Nullable
        public String mPatchedSenderProfilePictureUrl;

        private Builder mergeFrom(OcMessengerMessage ocMessengerMessage) {
            this.mOcMessage = ocMessengerMessage.mOcMessage;
            return this;
        }

        public OcMessengerMessage build() {
            return new OcMessengerMessage((Message) Objects.requireNonNull(this.mOcMessage, "Must set ocMessage."), this.mPatchedSenderProfilePictureUrl);
        }

        public Builder setOcMessage(Message message) {
            this.mOcMessage = message;
            return this;
        }

        public Builder setPatchedSenderProfilePictureUrl(@Nullable String str) {
            this.mPatchedSenderProfilePictureUrl = str;
            return this;
        }

        public Builder() {
        }

        public /* synthetic */ Builder(AnonymousClass1 r1) {
        }
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public String[] getAttachmentImageUrls() {
        return new String[0];
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public String getSenderName() {
        return "";
    }

    private Message getOcMessage() {
        return this.mOcMessage;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof OcMessengerMessage)) {
            return false;
        }
        return Objects.equals(this.mOcMessage, ((OcMessengerMessage) obj).mOcMessage);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public MessengerMessage.AttachmentType getAttachmentType() {
        return MessengerMessage.AttachmentType.NONE;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public MessengerMessage.DisplayedContentType getDisplayedContentTypes() {
        return MessengerMessage.DisplayedContentType.TEXT;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public String getMessageId() {
        return this.mOcMessage.mMessageId;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public long getSenderId() {
        return this.mOcMessage.mActorId;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    @Nullable
    public String getSenderProfilePictureUrl() {
        return this.mPatchedSenderProfilePictureUrl;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public int getStickerPreviewHeight() {
        throw new UnsupportedOperationException("OcMessenger Message does not support method: getStickerPreviewHeight()");
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public int getStickerPreviewWidth() {
        throw new UnsupportedOperationException("OcMessenger Message does not support method: getStickerPreviewWidth()");
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public String getText() {
        String str = this.mOcMessage.mBody;
        if (str == null) {
            return "";
        }
        return str;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public long getTimestampMs() {
        return this.mOcMessage.mTimestamp;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public int hashCode() {
        return Objects.hash(this.mOcMessage);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public boolean isAdminMessage() {
        return !NORMAL_MESSAGE_TYPE.equals(this.mOcMessage.mType);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public Optional<MessengerMessage.HotEmojiSize> getHotEmojiSize() {
        return Optional.empty();
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public Optional<MessengerReaction[]> getReactions() {
        return Optional.empty();
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public Optional<XmaAttachment> getXmaAttachment() {
        return Optional.empty();
    }

    public OcMessengerMessage(Message message, @Nullable String str) {
        this.mOcMessage = message;
        this.mPatchedSenderProfilePictureUrl = str;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(OcMessengerMessage ocMessengerMessage) {
        Builder builder = new Builder();
        builder.mOcMessage = ocMessengerMessage.mOcMessage;
        return builder;
    }
}
