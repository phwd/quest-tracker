package com.oculus.messengervr.interfaces;

import android.util.Pair;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Optional;

@Nullsafe(Nullsafe.Mode.LOCAL)
public interface MessengerThread {
    boolean equals(@Nullable Object obj);

    long getLastActivityTimestampMs();

    Optional<Long> getLastReadWatermarkTimestampMs();

    @Nullable
    Pair<String, String> getParticipantProfilePictureUrlsForThreadPicture();

    String getSnippet();

    long getThreadKey();

    String getThreadName();

    @Nullable
    String getThreadPictureUrl();

    int hashCode();

    @Nullable
    Boolean isMuted();

    boolean isUnread();
}
