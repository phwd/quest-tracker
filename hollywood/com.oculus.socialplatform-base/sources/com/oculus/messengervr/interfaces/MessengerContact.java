package com.oculus.messengervr.interfaces;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public interface MessengerContact {
    boolean equals(@Nullable Object obj);

    BlockedByViewerStatus getBlockedByViewerStatus();

    long getContactId();

    String getName();

    String getProfilePictureUrl();

    int hashCode();
}
