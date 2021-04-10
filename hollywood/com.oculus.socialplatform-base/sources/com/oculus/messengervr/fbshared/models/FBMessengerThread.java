package com.oculus.messengervr.fbshared.models;

import android.annotation.TargetApi;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messengervr.interfaces.MessengerThread;
import java.util.Objects;
import java.util.Optional;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class FBMessengerThread implements MessengerThread {
    @Nullable
    public final Boolean mIsMuted;
    public final boolean mIsUnread;
    public final long mLastActivityTimestampMs;
    @Nullable
    public final Long mLastReadWatermarkTimestampMs;
    @Nullable
    public final Pair<String, String> mParticipantProfilePictureUrlsForThreadPicture;
    @Nullable
    public final String mSnippet;
    public final long mThreadKey;
    @Nullable
    public final String mThreadName;
    @Nullable
    public final String mThreadPictureUrl;

    public static final class Builder {
        @Nullable
        public Boolean mIsMuted;
        @Nullable
        public Boolean mIsUnread;
        @Nullable
        public Long mLastActivityTimestampMs;
        @Nullable
        public Long mLastReadWatermarkTimestampMs;
        @Nullable
        public Pair<String, String> mParticipantProfilePictureUrlsForThreadPicture;
        @Nullable
        public String mSnippet;
        @Nullable
        public Long mThreadKey;
        @Nullable
        public String mThreadName;
        @Nullable
        public String mThreadPictureUrl;

        public FBMessengerThread build() {
            return new FBMessengerThread(((Number) Objects.requireNonNull(this.mThreadKey, "Must set threadKey.")).longValue(), this.mThreadName, ((Number) Objects.requireNonNull(this.mLastActivityTimestampMs, "Must set lastActivityTimestampMs.")).longValue(), this.mSnippet, this.mThreadPictureUrl, this.mParticipantProfilePictureUrlsForThreadPicture, ((Boolean) Objects.requireNonNull(this.mIsUnread, "Must set isUnread.")).booleanValue(), this.mIsMuted, this.mLastReadWatermarkTimestampMs);
        }

        public Builder setIsUnread(boolean z) {
            this.mIsUnread = Boolean.valueOf(z);
            return this;
        }

        public Builder setLastActivityTimestampMs(long j) {
            this.mLastActivityTimestampMs = Long.valueOf(j);
            return this;
        }

        public Builder setThreadKey(long j) {
            this.mThreadKey = Long.valueOf(j);
            return this;
        }

        public Builder setIsMuted(@Nullable Boolean bool) {
            this.mIsMuted = bool;
            return this;
        }

        public Builder setLastReadWatermarkTimestampMs(@Nullable Long l) {
            this.mLastReadWatermarkTimestampMs = l;
            return this;
        }

        public Builder setParticipantProfilePictureUrlsForThreadPicture(@Nullable Pair<String, String> pair) {
            this.mParticipantProfilePictureUrlsForThreadPicture = pair;
            return this;
        }

        public Builder setSnippet(@Nullable String str) {
            this.mSnippet = str;
            return this;
        }

        public Builder setThreadName(@Nullable String str) {
            this.mThreadName = str;
            return this;
        }

        public Builder setThreadPictureUrl(@Nullable String str) {
            this.mThreadPictureUrl = str;
            return this;
        }

        public Builder() {
        }

        public /* synthetic */ Builder(AnonymousClass1 r1) {
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof FBMessengerThread)) {
            return false;
        }
        FBMessengerThread fBMessengerThread = (FBMessengerThread) obj;
        if (getThreadKey() != fBMessengerThread.getThreadKey() || !getThreadName().equals(fBMessengerThread.getThreadName()) || getLastActivityTimestampMs() != fBMessengerThread.getLastActivityTimestampMs() || !getSnippet().equals(fBMessengerThread.getSnippet()) || !Objects.equals(getThreadPictureUrl(), fBMessengerThread.getThreadPictureUrl()) || !Objects.equals(getParticipantProfilePictureUrlsForThreadPicture(), fBMessengerThread.getParticipantProfilePictureUrlsForThreadPicture()) || isUnread() != fBMessengerThread.isUnread() || !Objects.equals(isMuted(), fBMessengerThread.isMuted()) || !getLastReadWatermarkTimestampMs().equals(fBMessengerThread.getLastReadWatermarkTimestampMs())) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public long getLastActivityTimestampMs() {
        return this.mLastActivityTimestampMs;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public Optional<Long> getLastReadWatermarkTimestampMs() {
        return Optional.ofNullable(this.mLastReadWatermarkTimestampMs);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    @Nullable
    public Pair<String, String> getParticipantProfilePictureUrlsForThreadPicture() {
        return this.mParticipantProfilePictureUrlsForThreadPicture;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public String getSnippet() {
        String str = this.mSnippet;
        if (str == null) {
            return "";
        }
        return str;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public long getThreadKey() {
        return this.mThreadKey;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public String getThreadName() {
        String str = this.mThreadName;
        if (str == null) {
            return "";
        }
        return str;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    @Nullable
    public String getThreadPictureUrl() {
        return this.mThreadPictureUrl;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mThreadKey), this.mThreadName, Long.valueOf(this.mLastActivityTimestampMs), this.mSnippet, this.mThreadPictureUrl, this.mParticipantProfilePictureUrlsForThreadPicture, Boolean.valueOf(this.mIsUnread), this.mIsMuted, this.mLastReadWatermarkTimestampMs);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    @Nullable
    public Boolean isMuted() {
        return this.mIsMuted;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public boolean isUnread() {
        return this.mIsUnread;
    }

    public FBMessengerThread(long j, @Nullable String str, long j2, @Nullable String str2, @Nullable String str3, @Nullable Pair<String, String> pair, boolean z, @Nullable Boolean bool, @Nullable Long l) {
        this.mThreadKey = j;
        this.mThreadName = str;
        this.mLastActivityTimestampMs = j2;
        this.mSnippet = str2;
        this.mThreadPictureUrl = str3;
        this.mParticipantProfilePictureUrlsForThreadPicture = pair;
        this.mIsUnread = z;
        this.mIsMuted = bool;
        this.mLastReadWatermarkTimestampMs = l;
    }
}
