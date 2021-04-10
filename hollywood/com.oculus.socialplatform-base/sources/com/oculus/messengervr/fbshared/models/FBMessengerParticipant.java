package com.oculus.messengervr.fbshared.models;

import android.annotation.TargetApi;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Initializer;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messengervr.fbshared.utils.BlockedByViewerStatusUtils;
import com.oculus.messengervr.interfaces.BlockedByViewerStatus;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import java.util.Objects;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class FBMessengerParticipant implements MessengerParticipant {
    @Nullable
    public final BlockedByViewerStatus mBlockedByViewerStatus;
    @Nullable
    public final String mName;
    @Nullable
    public final String mNickname;
    public final long mParticipantId;
    @Nullable
    public final String mProfilePictureUrl;

    public static final class Builder {
        @Nullable
        public BlockedByViewerStatus mBlockedByViewerStatus;
        @Nullable
        public String mName;
        @Nullable
        public String mNickname;
        public long mParticipantId;
        @Nullable
        public String mProfilePictureUrl;

        public FBMessengerParticipant build() {
            return new FBMessengerParticipant(this.mParticipantId, this.mName, this.mNickname, this.mProfilePictureUrl, this.mBlockedByViewerStatus);
        }

        public Builder setBlockedByViewerStatus(@Nullable Integer num) {
            BlockedByViewerStatus blockedByViewerStatus;
            if (num != null) {
                blockedByViewerStatus = BlockedByViewerStatusUtils.transformBlockedByViewerStatus(num);
            } else {
                blockedByViewerStatus = null;
            }
            this.mBlockedByViewerStatus = blockedByViewerStatus;
            return this;
        }

        public Builder setName(@Nullable String str) {
            this.mName = str;
            return this;
        }

        public Builder setNickname(@Nullable String str) {
            this.mNickname = str;
            return this;
        }

        @Initializer
        public Builder setParticipantId(long j) {
            this.mParticipantId = j;
            return this;
        }

        public Builder setProfilePictureUrl(@Nullable String str) {
            this.mProfilePictureUrl = str;
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

    @Override // com.oculus.messengervr.interfaces.MessengerParticipant
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof FBMessengerParticipant)) {
            return false;
        }
        FBMessengerParticipant fBMessengerParticipant = (FBMessengerParticipant) obj;
        if (getParticipantId() != fBMessengerParticipant.getParticipantId() || !Objects.equals(getName(), fBMessengerParticipant.getName()) || !Objects.equals(getNickname(), fBMessengerParticipant.getNickname()) || !Objects.equals(getProfilePictureUrl(), fBMessengerParticipant.getProfilePictureUrl()) || !Objects.equals(getBlockedByViewerStatus(), fBMessengerParticipant.getBlockedByViewerStatus())) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerParticipant
    @Nullable
    public BlockedByViewerStatus getBlockedByViewerStatus() {
        return this.mBlockedByViewerStatus;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerParticipant
    @Nullable
    public String getName() {
        return this.mName;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerParticipant
    @Nullable
    public String getNickname() {
        return this.mNickname;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerParticipant
    public long getParticipantId() {
        return this.mParticipantId;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerParticipant
    @Nullable
    public String getProfilePictureUrl() {
        return this.mProfilePictureUrl;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerParticipant
    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mParticipantId), this.mName, this.mNickname, this.mProfilePictureUrl, this.mBlockedByViewerStatus);
    }

    public FBMessengerParticipant(long j, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable BlockedByViewerStatus blockedByViewerStatus) {
        this.mParticipantId = j;
        this.mName = str;
        this.mNickname = str2;
        this.mProfilePictureUrl = str3;
        this.mBlockedByViewerStatus = blockedByViewerStatus;
    }
}
