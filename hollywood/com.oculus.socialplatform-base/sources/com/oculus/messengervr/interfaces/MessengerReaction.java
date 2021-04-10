package com.oculus.messengervr.interfaces;

import android.annotation.TargetApi;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Objects;
import java.util.Optional;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public class MessengerReaction {
    public final long mActorId;
    @Nullable
    public final String mActorName;
    @Nullable
    public final String mActorProfilePictureUrl;
    public final String mMessageId;
    public final String mReaction;
    public final long mTimestampMs;

    public static final class Builder {
        @Nullable
        public Long mActorId;
        @Nullable
        public String mActorName;
        @Nullable
        public String mActorProfilePictureUrl;
        @Nullable
        public String mMessageId;
        @Nullable
        public String mReaction;
        @Nullable
        public Long mTimestampMs;

        public MessengerReaction build() {
            return new MessengerReaction(((Number) Objects.requireNonNull(this.mActorId, "Must setActorId.")).longValue(), this.mActorProfilePictureUrl, this.mActorName, (String) Objects.requireNonNull(this.mMessageId, "Must setMessageId."), (String) Objects.requireNonNull(this.mReaction, "Must setReaction."), ((Number) Objects.requireNonNull(this.mTimestampMs, "Must setTimestampMs.")).longValue());
        }

        public Builder setActorId(long j) {
            this.mActorId = Long.valueOf(j);
            return this;
        }

        public Builder setTimestampMs(long j) {
            this.mTimestampMs = Long.valueOf(j);
            return this;
        }

        public Builder setActorName(@Nullable String str) {
            this.mActorName = str;
            return this;
        }

        public Builder setActorProfilePictureUrl(@Nullable String str) {
            this.mActorProfilePictureUrl = str;
            return this;
        }

        public Builder setMessageId(String str) {
            this.mMessageId = str;
            return this;
        }

        public Builder setReaction(String str) {
            this.mReaction = str;
            return this;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof MessengerReaction)) {
            return false;
        }
        MessengerReaction messengerReaction = (MessengerReaction) obj;
        if (this.mActorId != messengerReaction.mActorId || !Optional.ofNullable(this.mActorProfilePictureUrl).equals(Optional.ofNullable(messengerReaction.mActorProfilePictureUrl)) || !Optional.ofNullable(this.mActorName).equals(Optional.ofNullable(messengerReaction.mActorName)) || !this.mMessageId.equals(messengerReaction.mMessageId) || !this.mReaction.equals(messengerReaction.mReaction) || this.mTimestampMs != messengerReaction.mTimestampMs) {
            return false;
        }
        return true;
    }

    public long getActorId() {
        return this.mActorId;
    }

    public Optional<String> getActorName() {
        return Optional.ofNullable(this.mActorName);
    }

    public Optional<String> getActorProfilePictureUrl() {
        return Optional.ofNullable(this.mActorProfilePictureUrl);
    }

    public String getMessageId() {
        return this.mMessageId;
    }

    public String getReaction() {
        return this.mReaction;
    }

    public long getTimestampMs() {
        return this.mTimestampMs;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mActorId), this.mActorProfilePictureUrl, this.mActorName, this.mMessageId, this.mReaction, Long.valueOf(this.mTimestampMs));
    }

    public MessengerReaction(long j, @Nullable String str, @Nullable String str2, String str3, String str4, long j2) {
        this.mActorId = j;
        this.mActorProfilePictureUrl = str;
        this.mActorName = str2;
        this.mMessageId = str3;
        this.mReaction = str4;
        this.mTimestampMs = j2;
    }
}
