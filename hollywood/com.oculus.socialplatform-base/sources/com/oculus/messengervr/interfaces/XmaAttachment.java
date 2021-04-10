package com.oculus.messengervr.interfaces;

import androidx.annotation.Nullable;
import com.oculus.messengervr.interfaces.MessengerMessage;
import java.util.Optional;

public interface XmaAttachment {
    boolean equals(@Nullable Object obj);

    Optional<String> getAttachmentFbid();

    MessengerMessage.AttachmentType getAttachmentType();

    Optional<String> getDefaultCtaActionContentBlob();

    Optional<String> getDefaultCtaActionUrl();

    Optional<Long> getDefaultCtaTargetId();

    Optional<String> getDefaultCtaType();

    Optional<String> getFaviconUrl();

    Optional<Integer> getPreviewHeight();

    Optional<String> getPreviewUrl();

    Optional<Integer> getPreviewWidth();

    Optional<String> getSubtitleText();

    Optional<String> getTitleText();

    int hashCode();
}
