package com.oculus.messenger.manager;

import X.AnonymousClass006;
import X.AnonymousClass0l0;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.tigon.iface.TigonServiceHolder;
import com.facebook.tigon.oktigon.OkTigonService;
import com.facebook.tigon.oktigon.OkTigonServiceHolder;
import com.oculus.messenger.models.Message;
import com.oculus.messenger.models.Thread;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MessengerManagerJNI {
    public static final String TAG = "MessengerManagerJNI";
    @Nullable
    public Callback mCallback = null;
    @Nullable
    public TigonServiceHolder mTigonServiceHolder = null;
    public final String mUserAgentString;

    public interface Callback {
        void onNewMessage(long j, Message message);

        void onThreadDeletion(long j);

        void onThreadUpdate(Thread thread, boolean z);

        void onUnreadThreadCountsUpdate(Map<String, Long> map);
    }

    public interface GetMessagesCallback {
        @DoNotStrip
        void onResult(@Nullable Message[] messageArr, int i, @Nullable String str);
    }

    public interface GetThreadCallback {
        @DoNotStrip
        void onResult(@Nullable Thread thread, @Nullable String str);
    }

    public interface GetThreadsCallback {
        @DoNotStrip
        void onResult(@Nullable Thread[] threadArr, int i, @Nullable String str);
    }

    public interface GetUnreadThreadCountsCallback {
        @DoNotStrip
        void onResult(@Nullable Map<String, Long> map, @Nullable String str);
    }

    public interface MutationCallback {
        @DoNotStrip
        void onResult(boolean z, @Nullable String str);
    }

    public native void addParticipantsToThread(long j, long[] jArr, MutationCallback mutationCallback);

    public native void createGroupThread(long[] jArr, String str, GetThreadCallback getThreadCallback);

    public native void deleteThread(long j, MutationCallback mutationCallback);

    public native void getNonInboxThreadMessages(long j, int i, GetMessagesCallback getMessagesCallback);

    public native void getNonInboxThreads(String str, int i, int i2, GetThreadsCallback getThreadsCallback);

    public native void getThread(long j, int i, GetThreadCallback getThreadCallback);

    public native void getThreadMessages(long j, int i, long j2, GetMessagesCallback getMessagesCallback);

    public native void getThreads(int i, int i2, long j, GetThreadsCallback getThreadsCallback);

    public native void getUnreadThreadCounts(GetUnreadThreadCountsCallback getUnreadThreadCountsCallback);

    public native void hideThread(long j, MutationCallback mutationCallback);

    public native void initNative(long j, String str, String str2, String str3, String str4, TigonServiceHolder tigonServiceHolder, Executor executor);

    public native void markThreadRead(long j, long j2, MutationCallback mutationCallback);

    public native void muteThread(long j, int i, MutationCallback mutationCallback);

    public native void onDestroy();

    public native void removeParticipantFromThread(long j, long j2, MutationCallback mutationCallback);

    public native boolean retrySendingMessage(long j, long j2);

    public native boolean sendMessage(long j, String str);

    static {
        AnonymousClass0l0.A06("ovrmessenger-jni");
    }

    @DoNotStrip
    private void onNewMessage(long j, Message message) {
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onNewMessage(j, message);
        }
    }

    @DoNotStrip
    private void onThreadDeletion(long j) {
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onThreadDeletion(j);
        }
    }

    @DoNotStrip
    private void onThreadUpdate(Thread thread, boolean z) {
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onThreadUpdate(thread, z);
        }
    }

    @DoNotStrip
    private void onUnreadThreadCountsUpdate(Map<String, Long> map) {
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onUnreadThreadCountsUpdate(map);
        }
    }

    public void init(long j, final String str, String str2, String str3, String str4, Executor executor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors.add(new Interceptor() {
            /* class com.oculus.messenger.manager.MessengerManagerJNI.AnonymousClass1 */

            @Override // okhttp3.Interceptor
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request.Builder newBuilder = chain.request().newBuilder();
                newBuilder.addHeader("Authorization", AnonymousClass006.A07("OAuth ", str));
                return chain.proceed(newBuilder.build());
            }
        });
        OkTigonServiceHolder okTigonServiceHolder = new OkTigonServiceHolder(new OkTigonService(builder.build(), null, this.mUserAgentString));
        this.mTigonServiceHolder = okTigonServiceHolder;
        initNative(j, str, str2, str3, str4, okTigonServiceHolder, executor);
    }

    public MessengerManagerJNI(String str) {
        this.mUserAgentString = str;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }
}
