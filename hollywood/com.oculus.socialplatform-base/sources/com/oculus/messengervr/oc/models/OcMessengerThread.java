package com.oculus.messengervr.oc.models;

import android.annotation.TargetApi;
import android.util.Pair;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messenger.models.Message;
import com.oculus.messenger.models.Participant;
import com.oculus.messenger.models.Thread;
import com.oculus.messengervr.interfaces.MessengerThread;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class OcMessengerThread implements MessengerThread {
    @Nullable
    public final Thread mOcThread;
    public final List<String> mParticipantIds;
    public final String mPatchedThreadName;
    @Nullable
    public List<String> mPatchedThreadPictureUrls;
    public final long mThreadKey;

    public static final class Builder {
        @Nullable
        public Thread mOcThread;
        @Nullable
        public long mPatchedThreadKey;
        @Nullable
        public String mPatchedThreadName;
        @Nullable
        public List<String> mPatchedThreadPictureUrls;
        @Nullable
        public String mViewerId;

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Builder mergeFrom(OcMessengerThread ocMessengerThread) {
            this.mOcThread = ocMessengerThread.mOcThread;
            this.mPatchedThreadPictureUrls = ocMessengerThread.mPatchedThreadPictureUrls;
            this.mPatchedThreadKey = ocMessengerThread.getThreadKey();
            this.mPatchedThreadName = ocMessengerThread.getThreadName();
            return this;
        }

        public OcMessengerThread build() {
            return new OcMessengerThread(this.mOcThread, (String) Objects.requireNonNull(this.mViewerId, "Must set viewerId."), this.mPatchedThreadPictureUrls, this.mPatchedThreadKey, this.mPatchedThreadName);
        }

        public Builder setOcThread(@Nullable Thread thread) {
            this.mOcThread = thread;
            return this;
        }

        public Builder setPatchedThreadKey(long j) {
            this.mPatchedThreadKey = j;
            return this;
        }

        public Builder setPatchedThreadName(@Nullable String str) {
            this.mPatchedThreadName = str;
            return this;
        }

        public Builder setPatchedThreadPictureUrls(@Nullable List<String> list) {
            this.mPatchedThreadPictureUrls = list;
            return this;
        }

        public Builder setViewerId(String str) {
            this.mViewerId = str;
            return this;
        }

        public Builder() {
        }

        public /* synthetic */ Builder(AnonymousClass1 r1) {
        }
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof OcMessengerThread)) {
            return false;
        }
        OcMessengerThread ocMessengerThread = (OcMessengerThread) obj;
        return Objects.equals(this.mOcThread, ocMessengerThread.mOcThread) && Objects.equals(getThreadName(), ocMessengerThread.getThreadName()) && Objects.equals(this.mParticipantIds, ocMessengerThread.mParticipantIds) && Objects.equals(getThreadPictureUrl(), ocMessengerThread.getThreadPictureUrl()) && Objects.equals(getParticipantProfilePictureUrlsForThreadPicture(), ocMessengerThread.getParticipantProfilePictureUrlsForThreadPicture());
    }

    @Nullable
    private Thread getOcThread() {
        return this.mOcThread;
    }

    public static /* synthetic */ String lambda$new$0(String str, Participant participant) {
        if (str.equals(Long.toString(participant.mParticipantId))) {
            return null;
        }
        return participant.mAlias;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public long getLastActivityTimestampMs() {
        Thread thread = this.mOcThread;
        if (thread == null) {
            return 0;
        }
        return thread.mUpdatedTime;
    }

    public List<String> getParticipantIds() {
        return this.mParticipantIds;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    @Nullable
    public Pair<String, String> getParticipantProfilePictureUrlsForThreadPicture() {
        List<String> list = this.mPatchedThreadPictureUrls;
        if (list == null || list.size() != 2) {
            return null;
        }
        return new Pair<>(this.mPatchedThreadPictureUrls.get(0), this.mPatchedThreadPictureUrls.get(1));
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public String getSnippet() {
        Message[] messageArr;
        Message message;
        Thread thread = this.mOcThread;
        if (thread == null) {
            messageArr = new Message[0];
        } else {
            messageArr = thread.mMessages;
        }
        if (messageArr.length == 0 || (message = messageArr[0]) == null) {
            return "";
        }
        return message.mBody;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public long getThreadKey() {
        return this.mThreadKey;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public String getThreadName() {
        return this.mPatchedThreadName;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    @Nullable
    public String getThreadPictureUrl() {
        List<String> list = this.mPatchedThreadPictureUrls;
        if (list == null || list.size() != 1) {
            return null;
        }
        return this.mPatchedThreadPictureUrls.get(0);
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public int hashCode() {
        return Objects.hash(this.mOcThread, this.mPatchedThreadName, this.mParticipantIds, this.mPatchedThreadPictureUrls);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000f, code lost:
        if (r1 > java.lang.System.currentTimeMillis()) goto L_0x0011;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0011, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0016, code lost:
        return java.lang.Boolean.valueOf(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        if (r1 >= 0) goto L_0x0008;
     */
    @Override // com.oculus.messengervr.interfaces.MessengerThread
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Boolean isMuted() {
        /*
            r6 = this;
            com.oculus.messenger.models.Thread r0 = r6.mOcThread
            r3 = 0
            if (r0 != 0) goto L_0x0017
            r1 = 0
        L_0x0008:
            long r4 = java.lang.System.currentTimeMillis()
            int r3 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            r0 = 0
            if (r3 <= 0) goto L_0x0012
        L_0x0011:
            r0 = 1
        L_0x0012:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            return r0
        L_0x0017:
            long r1 = r0.mMuteExpireTime
            int r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r0 < 0) goto L_0x0011
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.messengervr.oc.models.OcMessengerThread.isMuted():java.lang.Boolean");
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public boolean isUnread() {
        Thread thread = this.mOcThread;
        if (thread == null) {
            return false;
        }
        return thread.mIsUnread;
    }

    @Override // com.oculus.messengervr.interfaces.MessengerThread
    public Optional<Long> getLastReadWatermarkTimestampMs() {
        return Optional.empty();
    }

    public OcMessengerThread(@Nullable Thread thread, String str, @Nullable List<String> list, long j, @Nullable String str2) {
        this.mOcThread = thread;
        this.mPatchedThreadPictureUrls = list;
        if (thread == null) {
            this.mThreadKey = j;
            this.mParticipantIds = new ArrayList(Arrays.asList(str, Long.toString(j)));
            this.mPatchedThreadName = str2 == null ? "" : str2;
            return;
        }
        this.mThreadKey = thread.mThreadKey;
        String str3 = thread.mName;
        List asList = Arrays.asList(thread.mParticipants);
        this.mPatchedThreadName = str3.isEmpty() ? (String) asList.parallelStream().map(new Function(str) {
            /* class com.oculus.messengervr.oc.models.$$Lambda$OcMessengerThread$4B5rsoh5sMdSWCXk_9h3EeFQZA2 */
            public final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return OcMessengerThread.lambda$new$0(this.f$0, (Participant) obj);
            }
        }).filter($$Lambda$ysW_fvT8H1f4ly8te8Kd64ujJxw2.INSTANCE).collect(Collectors.joining(", ")) : str3;
        this.mParticipantIds = (List) asList.parallelStream().map($$Lambda$OcMessengerThread$7teUfeOo3tfFGwfouAUBCADjX5U2.INSTANCE).collect(Collectors.toList());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(OcMessengerThread ocMessengerThread) {
        Builder builder = new Builder();
        builder.mergeFrom(ocMessengerThread);
        return builder;
    }
}
