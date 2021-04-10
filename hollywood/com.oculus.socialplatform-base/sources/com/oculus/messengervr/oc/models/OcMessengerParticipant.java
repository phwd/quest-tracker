package com.oculus.messengervr.oc.models;

import android.annotation.TargetApi;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messenger.models.Participant;
import com.oculus.messengervr.interfaces.BlockedByViewerStatus;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import java.util.Objects;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class OcMessengerParticipant implements MessengerParticipant {
    public final Participant mOcParticipant;
    public final boolean mPatchedIsUserBlocked;

    public static final class Builder {
        @Nullable
        public Participant mOcParticipant;
        @Nullable
        public Boolean mPatchedIsUserBlocked;

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Builder mergeFrom(OcMessengerParticipant ocMessengerParticipant) {
            this.mOcParticipant = ocMessengerParticipant.mOcParticipant;
            this.mPatchedIsUserBlocked = Boolean.valueOf(ocMessengerParticipant.mPatchedIsUserBlocked);
            return this;
        }

        public OcMessengerParticipant build() {
            boolean booleanValue;
            Participant participant = (Participant) Objects.requireNonNull(this.mOcParticipant, "Must set ocParticipant.");
            Boolean bool = this.mPatchedIsUserBlocked;
            if (bool == null) {
                booleanValue = false;
            } else {
                booleanValue = bool.booleanValue();
            }
            return new OcMessengerParticipant(participant, booleanValue);
        }

        public Builder setIsUserBlocked(Boolean bool) {
            this.mPatchedIsUserBlocked = bool;
            return this;
        }

        public Builder setOcParticipant(Participant participant) {
            this.mOcParticipant = participant;
            return this;
        }

        public Builder() {
        }

        public /* synthetic */ Builder(AnonymousClass1 r1) {
        }
    }

    @Override // com.oculus.messengervr.interfaces.MessengerParticipant
    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof OcMessengerParticipant)) {
            return false;
        }
        OcMessengerParticipant ocMessengerParticipant = (OcMessengerParticipant) obj;
        return Objects.equals(this.mOcParticipant, ocMessengerParticipant.mOcParticipant) && getBlockedByViewerStatus() == ocMessengerParticipant.getBlockedByViewerStatus();
    }

    @Override // com.oculus.messengervr.interfaces.MessengerParticipant
    @Nullable
    public String getProfilePictureUrl() {
        return "";
    }

    private Participant getOcParticipant() {
        return this.mOcParticipant;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerParticipant
    @Nullable
    public BlockedByViewerStatus getBlockedByViewerStatus() {
        if (this.mPatchedIsUserBlocked) {
            return BlockedByViewerStatus.FULLY_BLOCKED;
        }
        return BlockedByViewerStatus.UNBLOCKED;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerParticipant
    @Nullable
    public String getName() {
        return this.mOcParticipant.mAlias;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerParticipant
    @Nullable
    public String getNickname() {
        return this.mOcParticipant.mAlias;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerParticipant
    public long getParticipantId() {
        return this.mOcParticipant.mParticipantId;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerParticipant
    public int hashCode() {
        return Objects.hash(this.mOcParticipant, Boolean.valueOf(this.mPatchedIsUserBlocked));
    }

    public OcMessengerParticipant(Participant participant, boolean z) {
        this.mOcParticipant = participant;
        this.mPatchedIsUserBlocked = z;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(OcMessengerParticipant ocMessengerParticipant) {
        Builder builder = new Builder();
        builder.mergeFrom(ocMessengerParticipant);
        return builder;
    }
}
