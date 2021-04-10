package com.oculus.messengervr.oc;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Objects;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class OcMessengerManagerUpdateResult {
    public final long mThreadKey;
    public final UpdateResultType mUpdateResultType;

    public static final class Builder {
        public long mThreadKey;
        @Nullable
        public UpdateResultType mUpdateResultType;

        public OcMessengerManagerUpdateResult build() {
            return new OcMessengerManagerUpdateResult(this.mThreadKey, (UpdateResultType) Objects.requireNonNull(this.mUpdateResultType, "Must setUpdateResultType."));
        }

        public Builder setThreadKey(long j) {
            this.mThreadKey = j;
            return this;
        }

        public Builder setUpdateResultType(UpdateResultType updateResultType) {
            this.mUpdateResultType = updateResultType;
            return this;
        }
    }

    public enum UpdateResultType {
        NEW_MESSAGE,
        THREAD_DELETION,
        THREAD_UPDATE,
        THREAD_UNREAD_COUNT,
        NONE
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public long getThreadKey() {
        return this.mThreadKey;
    }

    public UpdateResultType getUpdateResultType() {
        return this.mUpdateResultType;
    }

    public OcMessengerManagerUpdateResult(long j, UpdateResultType updateResultType) {
        this.mThreadKey = j;
        this.mUpdateResultType = updateResultType;
    }
}
