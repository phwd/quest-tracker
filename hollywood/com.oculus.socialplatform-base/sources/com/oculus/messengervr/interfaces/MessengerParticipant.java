package com.oculus.messengervr.interfaces;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public interface MessengerParticipant {
    boolean equals(@Nullable Object obj);

    @Nullable
    BlockedByViewerStatus getBlockedByViewerStatus();

    @Nullable
    String getName();

    @Nullable
    String getNickname();

    long getParticipantId();

    @Nullable
    String getProfilePictureUrl();

    int hashCode();
}
