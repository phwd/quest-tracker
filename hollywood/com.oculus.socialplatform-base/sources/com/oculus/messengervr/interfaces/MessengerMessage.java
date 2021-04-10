package com.oculus.messengervr.interfaces;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Optional;

@Nullsafe(Nullsafe.Mode.LOCAL)
public interface MessengerMessage {

    public enum AttachmentType {
        UNKNOWN,
        NONE,
        STICKER,
        IMAGE,
        ANIMATED_IMAGE,
        VIDEO,
        AUDIO,
        FILE,
        XMA,
        EPHEMERAL_IMAGE,
        EPHEMERAL_VIDEO
    }

    public enum DisplayedContentType {
        TEXT,
        MEDIA_PREVIEW,
        AUDIO_CLIP,
        XMA_FALLBACK,
        LWA_WAVE,
        ADMIN,
        FILE_ATTACHMENT,
        GLYPH,
        TOMBSTONE,
        STORY_REPLY,
        SINGLE_XMA,
        HSCROLL_XMA,
        STICKER,
        FORWARD_INDICATOR,
        ANIMATED_IMAGE,
        NULL_STATE
    }

    public enum HotEmojiSize {
        SMALL,
        MEDIUM,
        LARGE
    }

    boolean equals(@Nullable Object obj);

    String[] getAttachmentImageUrls();

    AttachmentType getAttachmentType();

    DisplayedContentType getDisplayedContentTypes();

    Optional<HotEmojiSize> getHotEmojiSize();

    String getMessageId();

    Optional<MessengerReaction[]> getReactions();

    long getSenderId();

    String getSenderName();

    @Nullable
    String getSenderProfilePictureUrl();

    int getStickerPreviewHeight();

    int getStickerPreviewWidth();

    String getText();

    long getTimestampMs();

    Optional<XmaAttachment> getXmaAttachment();

    int hashCode();

    boolean isAdminMessage();
}
