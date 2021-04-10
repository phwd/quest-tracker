package com.oculus.messengervr.fbshared.models;

import android.annotation.TargetApi;
import androidx.annotation.Nullable;
import com.facebook.breakpad.BreakpadManager;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messengervr.interfaces.MessengerMessage;
import com.oculus.messengervr.interfaces.MessengerReaction;
import com.oculus.messengervr.interfaces.XmaAttachment;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class FBMessengerMessage implements MessengerMessage {
    public final String[] mAttachmentImageUrls;
    public final MessengerMessage.AttachmentType mAttachmentType;
    public final MessengerMessage.DisplayedContentType mDisplayedContentTypes;
    @Nullable
    public final MessengerMessage.HotEmojiSize mHotEmojiSize;
    public final boolean mIsAdminMessage;
    public final String mMessageId;
    @Nullable
    public final MessengerReaction[] mReactions;
    public final long mSenderId;
    @Nullable
    public final String mSenderName;
    @Nullable
    public final String mSenderProfilePictureUrl;
    public final int mStickerPreviewHeight;
    public final int mStickerPreviewWidth;
    @Nullable
    public final String mText;
    public final long mTimestampMs;
    @Nullable
    public final FBXmaAttachment mXmaAttachment;

    public static final class Builder {
        @Nullable
        public String[] mAttachmentImageUrls;
        public int mAttachmentType = 0;
        @Nullable
        public Long mDisplayedContentTypes;
        @Nullable
        public Integer mHotEmojiSize;
        @Nullable
        public Boolean mIsAdminMessage;
        @Nullable
        public String mMessageId;
        @Nullable
        public MessengerReaction[] mReactions;
        @Nullable
        public Long mSenderId;
        @Nullable
        public String mSenderName;
        @Nullable
        public String mSenderProfilePictureUrl;
        public int mStickerPreviewHeight = 0;
        public int mStickerPreviewWidth = 0;
        @Nullable
        public String mText;
        @Nullable
        public Long mTimestampMs;
        @Nullable
        public FBXmaAttachment mXmaAttachment;

        public FBMessengerMessage build() {
            long j;
            String str = (String) Objects.requireNonNull(this.mMessageId, "Must setMessageId.");
            String str2 = this.mText;
            long longValue = ((Number) Objects.requireNonNull(this.mSenderId, "Must setSenderId.")).longValue();
            String str3 = this.mSenderName;
            String str4 = this.mSenderProfilePictureUrl;
            long longValue2 = ((Number) Objects.requireNonNull(this.mTimestampMs, "Must setTimestampMs.")).longValue();
            boolean booleanValue = ((Boolean) Objects.requireNonNull(this.mIsAdminMessage, "Must setIsAdminMessage.")).booleanValue();
            int i = this.mAttachmentType;
            String[] strArr = (String[]) Objects.requireNonNull(this.mAttachmentImageUrls, "Must setAttachmentImageUrls.");
            int i2 = this.mStickerPreviewWidth;
            int i3 = this.mStickerPreviewHeight;
            FBXmaAttachment fBXmaAttachment = this.mXmaAttachment;
            MessengerReaction[] messengerReactionArr = this.mReactions;
            Long l = this.mDisplayedContentTypes;
            if (l != null) {
                j = l.longValue();
            } else {
                j = 1;
            }
            return new FBMessengerMessage(str, str2, longValue, str3, str4, longValue2, booleanValue, i, strArr, i2, i3, fBXmaAttachment, messengerReactionArr, j, this.mHotEmojiSize);
        }

        public Builder setAttachmentType(Integer num) {
            this.mAttachmentType = num.intValue();
            return this;
        }

        public Builder setStickerPreviewHeight(Integer num) {
            this.mStickerPreviewHeight = num.intValue();
            return this;
        }

        public Builder setStickerPreviewWidth(Integer num) {
            this.mStickerPreviewWidth = num.intValue();
            return this;
        }

        public Builder setAttachmentImageUrls(String[] strArr) {
            this.mAttachmentImageUrls = strArr;
            return this;
        }

        public Builder setDisplayedContentTypes(@Nullable Long l) {
            this.mDisplayedContentTypes = l;
            return this;
        }

        public Builder setHotEmojiSize(@Nullable Integer num) {
            this.mHotEmojiSize = num;
            return this;
        }

        public Builder setIsAdminMessage(Boolean bool) {
            this.mIsAdminMessage = bool;
            return this;
        }

        public Builder setMessageId(String str) {
            this.mMessageId = str;
            return this;
        }

        public Builder setReactions(@Nullable MessengerReaction[] messengerReactionArr) {
            this.mReactions = messengerReactionArr;
            return this;
        }

        public Builder setSenderId(Long l) {
            this.mSenderId = l;
            return this;
        }

        public Builder setSenderName(@Nullable String str) {
            this.mSenderName = str;
            return this;
        }

        public Builder setSenderProfilePictureUrl(@Nullable String str) {
            this.mSenderProfilePictureUrl = str;
            return this;
        }

        public Builder setText(@Nullable String str) {
            this.mText = str;
            return this;
        }

        public Builder setTimestampMs(Long l) {
            this.mTimestampMs = l;
            return this;
        }

        public Builder setXmaAttachment(@Nullable FBXmaAttachment fBXmaAttachment) {
            this.mXmaAttachment = fBXmaAttachment;
            return this;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static MessengerMessage.DisplayedContentType toDisplayedContentType(long j) {
        if (j != 1) {
            if (j == 2) {
                return MessengerMessage.DisplayedContentType.MEDIA_PREVIEW;
            }
            if (j == 4) {
                return MessengerMessage.DisplayedContentType.AUDIO_CLIP;
            }
            if (j == 8) {
                return MessengerMessage.DisplayedContentType.XMA_FALLBACK;
            }
            if (j == 16) {
                return MessengerMessage.DisplayedContentType.LWA_WAVE;
            }
            if (j == 32) {
                return MessengerMessage.DisplayedContentType.ADMIN;
            }
            if (j == 64) {
                return MessengerMessage.DisplayedContentType.FILE_ATTACHMENT;
            }
            if (j == 128) {
                return MessengerMessage.DisplayedContentType.GLYPH;
            }
            if (j == 256) {
                return MessengerMessage.DisplayedContentType.TOMBSTONE;
            }
            if (j == 512) {
                return MessengerMessage.DisplayedContentType.STORY_REPLY;
            }
            if (j == 1024) {
                return MessengerMessage.DisplayedContentType.SINGLE_XMA;
            }
            if (j == BreakpadManager.MD_FB_WITH_UNWINDSTACK_STREAM) {
                return MessengerMessage.DisplayedContentType.HSCROLL_XMA;
            }
            if (j == BreakpadManager.MD_FB_INSTALL_ALT_STACK) {
                return MessengerMessage.DisplayedContentType.STICKER;
            }
            if (j == 8192) {
                return MessengerMessage.DisplayedContentType.FORWARD_INDICATOR;
            }
            if (j == 16384) {
                return MessengerMessage.DisplayedContentType.ANIMATED_IMAGE;
            }
            if (j == 32768) {
                return MessengerMessage.DisplayedContentType.NULL_STATE;
            }
        }
        return MessengerMessage.DisplayedContentType.TEXT;
    }

    @Nullable
    public static MessengerMessage.HotEmojiSize toHotEmojiSize(@Nullable Integer num) {
        if (num == null) {
            return null;
        }
        int intValue = num.intValue();
        if (intValue == 1) {
            return MessengerMessage.HotEmojiSize.SMALL;
        }
        if (intValue == 2) {
            return MessengerMessage.HotEmojiSize.MEDIUM;
        }
        return MessengerMessage.HotEmojiSize.LARGE;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof FBMessengerMessage)) {
            return false;
        }
        FBMessengerMessage fBMessengerMessage = (FBMessengerMessage) obj;
        if (!getMessageId().equals(fBMessengerMessage.getMessageId()) || !getText().equals(fBMessengerMessage.getText()) || getSenderId() != fBMessengerMessage.getSenderId() || !getSenderName().equals(fBMessengerMessage.getSenderName()) || !Objects.equals(getSenderProfilePictureUrl(), fBMessengerMessage.getSenderProfilePictureUrl()) || getTimestampMs() != fBMessengerMessage.getTimestampMs() || isAdminMessage() != fBMessengerMessage.isAdminMessage() || getAttachmentType() != fBMessengerMessage.getAttachmentType() || !Arrays.equals(getAttachmentImageUrls(), fBMessengerMessage.getAttachmentImageUrls()) || !getXmaAttachment().equals(fBMessengerMessage.getXmaAttachment()) || !getReactions().equals(fBMessengerMessage.getReactions()) || getDisplayedContentTypes() != fBMessengerMessage.getDisplayedContentTypes() || !getHotEmojiSize().equals(fBMessengerMessage.getHotEmojiSize())) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public String[] getAttachmentImageUrls() {
        return this.mAttachmentImageUrls;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public MessengerMessage.AttachmentType getAttachmentType() {
        return this.mAttachmentType;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public MessengerMessage.DisplayedContentType getDisplayedContentTypes() {
        return this.mDisplayedContentTypes;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public Optional<MessengerMessage.HotEmojiSize> getHotEmojiSize() {
        return Optional.ofNullable(this.mHotEmojiSize);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public String getMessageId() {
        return this.mMessageId;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public Optional<MessengerReaction[]> getReactions() {
        return Optional.ofNullable(this.mReactions);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public long getSenderId() {
        return this.mSenderId;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public String getSenderName() {
        String str = this.mSenderName;
        if (str == null) {
            return "";
        }
        return str;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    @Nullable
    public String getSenderProfilePictureUrl() {
        return this.mSenderProfilePictureUrl;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public int getStickerPreviewHeight() {
        return this.mStickerPreviewHeight;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public int getStickerPreviewWidth() {
        return this.mStickerPreviewWidth;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public String getText() {
        String str = this.mText;
        if (str == null) {
            return "";
        }
        return str;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public long getTimestampMs() {
        return this.mTimestampMs;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public Optional<XmaAttachment> getXmaAttachment() {
        return Optional.ofNullable(this.mXmaAttachment);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public int hashCode() {
        return Objects.hash(this.mMessageId, this.mText, Long.valueOf(this.mSenderId), this.mSenderName, this.mSenderProfilePictureUrl, Long.valueOf(this.mTimestampMs), Boolean.valueOf(this.mIsAdminMessage), this.mAttachmentType, Integer.valueOf(Arrays.hashCode(this.mAttachmentImageUrls)), this.mXmaAttachment, this.mReactions, this.mDisplayedContentTypes, this.mHotEmojiSize);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerMessage
    public boolean isAdminMessage() {
        return this.mIsAdminMessage;
    }

    public FBMessengerMessage(String str, @Nullable String str2, long j, @Nullable String str3, @Nullable String str4, long j2, boolean z, int i, String[] strArr, int i2, int i3, @Nullable FBXmaAttachment fBXmaAttachment, @Nullable MessengerReaction[] messengerReactionArr, long j3, @Nullable Integer num) {
        this.mMessageId = str;
        this.mText = str2;
        this.mSenderId = j;
        this.mTimestampMs = j2;
        this.mIsAdminMessage = z;
        this.mAttachmentType = toAttachmentType(i);
        this.mAttachmentImageUrls = strArr;
        this.mSenderProfilePictureUrl = str4;
        this.mSenderName = str3;
        this.mStickerPreviewWidth = i2;
        this.mStickerPreviewHeight = i3;
        this.mXmaAttachment = fBXmaAttachment;
        this.mReactions = messengerReactionArr;
        this.mDisplayedContentTypes = toDisplayedContentType(j3);
        this.mHotEmojiSize = toHotEmojiSize(num);
    }

    public static MessengerMessage.AttachmentType toAttachmentType(int i) {
        switch (i) {
            case 0:
                return MessengerMessage.AttachmentType.NONE;
            case 1:
                return MessengerMessage.AttachmentType.STICKER;
            case 2:
                return MessengerMessage.AttachmentType.IMAGE;
            case 3:
                return MessengerMessage.AttachmentType.ANIMATED_IMAGE;
            case 4:
                return MessengerMessage.AttachmentType.VIDEO;
            case 5:
                return MessengerMessage.AttachmentType.AUDIO;
            case 6:
                return MessengerMessage.AttachmentType.FILE;
            case 7:
                return MessengerMessage.AttachmentType.XMA;
            case 8:
                return MessengerMessage.AttachmentType.EPHEMERAL_IMAGE;
            case 9:
                return MessengerMessage.AttachmentType.EPHEMERAL_VIDEO;
            default:
                return MessengerMessage.AttachmentType.UNKNOWN;
        }
    }
}
