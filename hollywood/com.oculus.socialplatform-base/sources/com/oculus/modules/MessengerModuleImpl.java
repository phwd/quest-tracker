package com.oculus.modules;

import X.AnonymousClass006;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat$CarExtender;
import com.oculus.messenger.service.MessengerService;
import com.oculus.messengervr.oc.models.OcMessengerMessage;
import com.oculus.modules.codegen.MessengerModule;
import com.oculus.modules.codegen.Resolver;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class MessengerModuleImpl extends MessengerModule {
    public static final long ASYNC_TIMEOUT_MS = 30000;
    public String AccessToken = null;
    public String AppId = null;
    public final String TAG = "MessengerModuleImpl";
    public String UserId = null;
    public int callIdCounter = 0;
    public Boolean hasInitedMessengerService = false;
    public ServiceConnection mConnection;
    public final Context mContext;
    public final IncomingHandler mHandler;
    public final Messenger mMessenger;
    public Messenger mService;
    public Boolean mServiceBound = false;
    public SparseArray<PendingAsyncCall> pendingServiceCalls = new SparseArray<>();
    public ArrayList<ServiceCall> queuedServiceCalls = new ArrayList<>();

    public class IncomingHandler extends Handler {
        public IncomingHandler() {
            super(Looper.getMainLooper());
        }

        private void convertAttachment(JSONObject jSONObject) throws JSONException {
            convertLongToString(jSONObject, "attachmentId");
            convertLongToString(jSONObject, "attachmentType");
        }

        private JSONObject convertGetMessages(JSONObject jSONObject) throws JSONException {
            convertLongToString(jSONObject, "threadId");
            String string = jSONObject.getString("threadId");
            JSONArray jSONArray = jSONObject.getJSONArray(NotificationCompat$CarExtender.KEY_MESSAGES);
            Boolean valueOf = Boolean.valueOf(jSONObject.getBoolean("reachedEndOfThread"));
            for (int i = 0; i < jSONArray.length(); i++) {
                convertMessage(jSONArray.getJSONObject(i));
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", "MESSAGE_LIST");
            jSONObject2.put("reachedEndOfThread", valueOf);
            jSONObject2.put("threadId", string);
            jSONObject2.put(NotificationCompat$CarExtender.KEY_MESSAGES, jSONArray);
            return jSONObject2;
        }

        private JSONObject convertGetThreads(JSONObject jSONObject) throws JSONException {
            JSONArray jSONArray = jSONObject.getJSONArray("threads");
            Boolean valueOf = Boolean.valueOf(jSONObject.getBoolean("reachedEndOfThreads"));
            for (int i = 0; i < jSONArray.length(); i++) {
                convertThread(jSONArray.getJSONObject(i));
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", "THREAD_LIST");
            jSONObject2.put("reachedEndOfThreads", valueOf);
            jSONObject2.put("threads", jSONArray);
            return jSONObject2;
        }

        private void convertMessage(JSONObject jSONObject) throws JSONException {
            convertLongToString(jSONObject, "senderId");
            convertLongToString(jSONObject, "offlineThreadingId");
            if (!jSONObject.has("type")) {
                jSONObject.put("type", OcMessengerMessage.NORMAL_MESSAGE_TYPE);
            }
            if (jSONObject.has("attachments")) {
                JSONArray jSONArray = jSONObject.getJSONArray("attachments");
                for (int i = 0; i < jSONArray.length(); i++) {
                    convertAttachment(jSONArray.getJSONObject(i));
                }
            }
        }

        private JSONObject convertNewMessage(JSONObject jSONObject) throws JSONException {
            convertLongToString(jSONObject, "threadId");
            String string = jSONObject.getString("threadId");
            JSONObject jSONObject2 = jSONObject.getJSONObject("message");
            convertMessage(jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("type", "NEW_MESSAGE");
            jSONObject3.put("threadId", string);
            jSONObject3.put("message", jSONObject2);
            return jSONObject3;
        }

        private void convertParticipant(JSONObject jSONObject) throws JSONException {
            convertLongToString(jSONObject, "participantId");
        }

        private void convertThread(JSONObject jSONObject) throws JSONException {
            convertLongToString(jSONObject, "threadId");
            JSONArray jSONArray = jSONObject.getJSONArray(NotificationCompat$CarExtender.KEY_PARTICIPANTS);
            for (int i = 0; i < jSONArray.length(); i++) {
                convertParticipant(jSONArray.getJSONObject(i));
            }
            JSONArray jSONArray2 = jSONObject.getJSONArray(NotificationCompat$CarExtender.KEY_MESSAGES);
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                convertMessage(jSONArray2.getJSONObject(i2));
            }
            if (!jSONObject.has("name")) {
                jSONObject.put("name", "");
            }
        }

        private JSONObject convertThreadDeletion(JSONObject jSONObject) throws JSONException {
            convertLongToString(jSONObject, "threadId");
            String string = jSONObject.getString("threadId");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", "THREAD_DELETION");
            jSONObject2.put("threadId", string);
            return jSONObject2;
        }

        private JSONObject convertThreadUpdate(JSONObject jSONObject) throws JSONException {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", "THREAD_UPDATE");
            JSONObject jSONObject3 = jSONObject.getJSONObject("thread");
            convertThread(jSONObject3);
            jSONObject2.put("thread", jSONObject3);
            jSONObject2.put("reloadThread", jSONObject.getBoolean("reloadThread"));
            return jSONObject2;
        }

        private JSONObject convertUnreadThreadCountsUpdate(JSONObject jSONObject) throws JSONException {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", "UNREAD_THREAD_COUNTS_UPDATE");
            jSONObject2.put("counts", jSONObject.getJSONArray("counts"));
            return jSONObject2;
        }

        public void handleMessage(Message message) {
            JSONObject jSONObject;
            String string = message.getData().getString("result");
            try {
                JSONObject jSONObject2 = (JSONObject) new JSONTokener(string).nextValue();
                int i = message.what;
                if (i == 4) {
                    jSONObject = convertNewMessage(jSONObject2);
                } else if (i == 15) {
                    jSONObject = convertThreadDeletion(jSONObject2);
                } else if (i == 5) {
                    jSONObject = convertThreadUpdate(jSONObject2);
                } else if (i == 20) {
                    try {
                        jSONObject = convertUnreadThreadCountsUpdate(jSONObject2);
                    } catch (JSONException e) {
                        Log.w(MessengerModuleImpl.this.TAG, AnonymousClass006.A0B("handleMessage: Failed to encode messengerEvent from \"", string, "\". ", e.getMessage()));
                        return;
                    }
                } else {
                    PendingAsyncCall GetPendingCall = MessengerModuleImpl.this.GetPendingCall(jSONObject2);
                    if (GetPendingCall == null) {
                        Log.w(MessengerModuleImpl.this.TAG, AnonymousClass006.A09("handleMessage: Failed to get matching call for id \"", string, "\"."));
                        return;
                    }
                    try {
                        int i2 = message.what;
                        if (i2 != -1) {
                            if (i2 == 7) {
                                jSONObject2 = jSONObject2.getJSONObject("thread");
                                convertThread(jSONObject2);
                            } else if (i2 != 12) {
                                if (i2 == 2) {
                                    jSONObject2 = convertGetThreads(jSONObject2);
                                } else if (i2 != 3) {
                                    switch (i2) {
                                        case 17:
                                            jSONObject2 = convertGetThreads(jSONObject2);
                                            break;
                                        case 18:
                                            jSONObject2 = convertGetMessages(jSONObject2);
                                            break;
                                        case 19:
                                            break;
                                        default:
                                            jSONObject2 = new JSONObject();
                                            break;
                                    }
                                } else {
                                    jSONObject2 = convertGetMessages(jSONObject2);
                                }
                            } else if (jSONObject2.isNull("thread")) {
                                jSONObject2 = null;
                            } else {
                                jSONObject2 = jSONObject2.getJSONObject("thread");
                                convertThread(jSONObject2);
                            }
                            GetPendingCall.resolver.resolve(jSONObject2);
                            return;
                        }
                        MessengerModuleImpl.this.rejectAndLogWithMessage(GetPendingCall.resolver, jSONObject2.optString("message"));
                        return;
                    } catch (JSONException e2) {
                        MessengerModuleImpl.this.rejectAndLogWithMessage(GetPendingCall.resolver, AnonymousClass006.A0B("Failed to parse response from Messenger Service: \"", string, "\". ", e2.getMessage()));
                        return;
                    }
                }
                MessengerModuleImpl.this.emitOnMessengerEvent(MessengerModule.CallbackData.makeFromJSONObject(jSONObject));
            } catch (JSONException e3) {
                Log.w(MessengerModuleImpl.this.TAG, AnonymousClass006.A0B("handleMessage: Failed to parse \"", string, "\". ", e3.getMessage()));
            } catch (ClassCastException e4) {
                Log.w(MessengerModuleImpl.this.TAG, AnonymousClass006.A0B("handleMessage: Wrong JSON type received from \"", string, "\". ", e4.getMessage()));
            }
        }

        private void convertLongToString(JSONObject jSONObject, String str) throws JSONException {
            String optString = jSONObject.optString(str);
            if (!optString.isEmpty()) {
                jSONObject.put(str, optString);
            }
        }
    }

    public static class MessageTypes {
        public static final int ADD_PARTICIPANTS_TO_THREAD = 6;
        public static final int CREATE_GROUP_THREAD = 7;
        public static final int DELETE_THREAD = 14;
        public static final int ERROR = -1;
        public static final int GET_MESSAGES = 3;
        public static final int GET_NON_INBOX_MESSAGES = 18;
        public static final int GET_NON_INBOX_THREADS = 17;
        public static final int GET_THREAD = 12;
        public static final int GET_THREADS = 2;
        public static final int GET_UNREAD_THREAD_COUNTS = 19;
        public static final int HIDE_THREAD = 16;
        public static final int INIT = 1;
        public static final int MARK_THREAD_READ = 8;
        public static final int MUTE_THREAD = 9;
        public static final int NEW_MESSAGE_CALLBACK = 4;
        public static final int REMOVE_PARTICIPANT_FROM_THREAD = 10;
        public static final int RETRY_SENDING_MESSAGE = 13;
        public static final int SEND_MESSAGE = 11;
        public static final int THREAD_DELETION_CALLBACK = 15;
        public static final int THREAD_UPDATE_CALLBACK = 5;
        public static final int UNREAD_THREAD_COUNTS_UPDATE_CALLBACK = 20;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private PendingAsyncCall GetPendingCall(JSONObject jSONObject) {
        try {
            int i = jSONObject.getInt("callId");
            PendingAsyncCall pendingAsyncCall = this.pendingServiceCalls.get(i);
            if (pendingAsyncCall != null) {
                this.mHandler.removeCallbacksAndMessages(pendingAsyncCall.timeoutToken);
                this.pendingServiceCalls.remove(i);
                return pendingAsyncCall;
            }
        } catch (JSONException unused) {
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void AddPendingCall(final int i, Resolver<JSONObject> resolver) {
        PendingAsyncCall pendingAsyncCall = new PendingAsyncCall();
        pendingAsyncCall.resolver = resolver;
        Object obj = new Object();
        pendingAsyncCall.timeoutToken = obj;
        this.pendingServiceCalls.put(i, pendingAsyncCall);
        this.mHandler.postAtTime(new Runnable() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass2 */

            public void run() {
                MessengerModuleImpl messengerModuleImpl = MessengerModuleImpl.this;
                PendingAsyncCall pendingAsyncCall = messengerModuleImpl.pendingServiceCalls.get(i);
                if (pendingAsyncCall != null) {
                    messengerModuleImpl.rejectAndLogWithMessage(pendingAsyncCall.resolver, "Call timed out waiting for response from messenger service");
                    MessengerModuleImpl.this.pendingServiceCalls.remove(i);
                }
            }
        }, obj, SystemClock.uptimeMillis() + 30000);
    }

    private void InvokeService(final int i, final Bundle bundle, final Resolver<JSONObject> resolver) {
        this.mHandler.post(new Runnable() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass3 */

            public void run() {
                MessengerModuleImpl messengerModuleImpl = MessengerModuleImpl.this;
                if (messengerModuleImpl.hasInitedMessengerService.booleanValue()) {
                    Message obtain = Message.obtain((Handler) null, i);
                    MessengerModuleImpl messengerModuleImpl2 = MessengerModuleImpl.this;
                    int i = messengerModuleImpl2.callIdCounter;
                    messengerModuleImpl2.callIdCounter = i + 1;
                    bundle.putInt("call_id", i);
                    obtain.setData(bundle);
                    MessengerModuleImpl messengerModuleImpl3 = MessengerModuleImpl.this;
                    obtain.replyTo = messengerModuleImpl3.mMessenger;
                    try {
                        messengerModuleImpl3.AddPendingCall(i, resolver);
                        MessengerModuleImpl.this.mService.send(obtain);
                    } catch (RemoteException e) {
                        MessengerModuleImpl.this.rejectAndLogWithMessage(resolver, AnonymousClass006.A07("InvokeServicePending got remote exception:", e.getMessage()));
                    }
                } else {
                    ServiceCall serviceCall = new ServiceCall();
                    serviceCall.messageType = i;
                    serviceCall.bundle = bundle;
                    serviceCall.resolver = resolver;
                    messengerModuleImpl.queuedServiceCalls.add(serviceCall);
                }
            }
        });
    }

    public static /* synthetic */ int access$1108(MessengerModuleImpl messengerModuleImpl) {
        int i = messengerModuleImpl.callIdCounter;
        messengerModuleImpl.callIdCounter = i + 1;
        return i;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void initMessengerService() {
        if (this.hasInitedMessengerService.booleanValue() || this.UserId == null || this.AccessToken == null || this.AppId == null || this.mService == null) {
            String str = "";
            if (this.UserId == null) {
                str = AnonymousClass006.A07(str, " UserId");
            }
            if (this.AppId == null) {
                str = AnonymousClass006.A07(str, " AppId");
            }
            if (this.AccessToken == null) {
                str = AnonymousClass006.A07(str, " AccessToken");
            }
            if (this.mService == null) {
                AnonymousClass006.A07(str, " Service");
                return;
            }
            return;
        }
        Message obtain = Message.obtain((Handler) null, 1);
        Bundle bundle = new Bundle();
        bundle.putLong("user_id", Long.parseLong(this.UserId));
        bundle.putString("access_token", this.AccessToken);
        bundle.putString(MessengerService.InitParamKeys.APP_ID, this.AppId);
        obtain.setData(bundle);
        obtain.replyTo = this.mMessenger;
        try {
            this.mService.send(obtain);
        } catch (RemoteException e) {
            Log.w(this.TAG, AnonymousClass006.A07("Got remote exception:", e.getMessage()));
        }
        this.hasInitedMessengerService = true;
        ArrayList<ServiceCall> arrayList = this.queuedServiceCalls;
        if (!arrayList.isEmpty()) {
            arrayList.size();
            Iterator<ServiceCall> it = arrayList.iterator();
            while (it.hasNext()) {
                ServiceCall next = it.next();
                InvokeService(next.messageType, next.bundle, next.resolver);
            }
            this.queuedServiceCalls.clear();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void rejectAndLogWithMessage(Resolver resolver, String str) {
        Log.w(this.TAG, AnonymousClass006.A07("Rejecting call with error: ", str));
        resolver.reject(new Error(str));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void rejectPendingServiceCalls() {
        this.mHandler.post(new Runnable() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass23 */

            public void run() {
                int i = 0;
                while (true) {
                    MessengerModuleImpl messengerModuleImpl = MessengerModuleImpl.this;
                    SparseArray<PendingAsyncCall> sparseArray = messengerModuleImpl.pendingServiceCalls;
                    if (i < sparseArray.size()) {
                        PendingAsyncCall valueAt = sparseArray.valueAt(i);
                        messengerModuleImpl.mHandler.removeCallbacksAndMessages(valueAt.timeoutToken);
                        MessengerModuleImpl.this.rejectAndLogWithMessage(valueAt.resolver, "service_shutdown");
                        i++;
                    } else {
                        sparseArray.clear();
                        return;
                    }
                }
            }
        });
    }

    public void SetAccessToken(final String str) {
        this.mHandler.post(new Runnable() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass5 */

            public void run() {
                MessengerModuleImpl messengerModuleImpl = MessengerModuleImpl.this;
                messengerModuleImpl.AccessToken = str;
                messengerModuleImpl.initMessengerService();
            }
        });
    }

    public void SetAppId(final String str) {
        this.mHandler.post(new Runnable() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass6 */

            public void run() {
                MessengerModuleImpl messengerModuleImpl = MessengerModuleImpl.this;
                messengerModuleImpl.AppId = str;
                messengerModuleImpl.initMessengerService();
            }
        });
    }

    public void SetUserId(final String str) {
        this.mHandler.post(new Runnable() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass4 */

            public void run() {
                MessengerModuleImpl messengerModuleImpl = MessengerModuleImpl.this;
                messengerModuleImpl.UserId = str;
                messengerModuleImpl.initMessengerService();
            }
        });
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void bindServiceImpl() {
        if (!this.mServiceBound.booleanValue()) {
            ComponentName componentName = new ComponentName("com.oculus.socialplatform", "com.oculus.messenger.service.MessengerService");
            Intent intent = new Intent();
            intent.setComponent(componentName);
            this.mContext.bindService(intent, this.mConnection, 1);
            this.mServiceBound = true;
        }
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void deleteThreadImpl(String str, final Resolver<Void> resolver) {
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(str));
        InvokeService(14, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass9 */

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable th) {
                resolver.reject(th);
            }

            public void resolve(JSONObject jSONObject) {
                resolver.resolve(null);
            }
        });
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void hideThreadImpl(String str, final Resolver<Void> resolver) {
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(str));
        InvokeService(16, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass10 */

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable th) {
                resolver.reject(th);
            }

            public void resolve(JSONObject jSONObject) {
                resolver.resolve(null);
            }
        });
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void markThreadReadImpl(String str, double d, final Resolver<Void> resolver) {
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(str));
        bundle.putLong(MessengerService.MarkThreadReadParamKeys.WATERMARK_TIMESTAMP, (long) d);
        InvokeService(8, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass11 */

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable th) {
                resolver.reject(th);
            }

            public void resolve(JSONObject jSONObject) {
                resolver.resolve(null);
            }
        });
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void muteThreadImpl(String str, double d, final Resolver<Void> resolver) {
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(str));
        bundle.putInt(MessengerService.MuteThreadParamKeys.MUTE_FOR_S, (int) d);
        InvokeService(9, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass12 */

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable th) {
                resolver.reject(th);
            }

            public void resolve(JSONObject jSONObject) {
                resolver.resolve(null);
            }
        });
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void removeParticipantImpl(String str, String str2, final Resolver<Void> resolver) {
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(str));
        bundle.putLong(MessengerService.RemoveParticipantFromThreadParamKeys.PARTICIPANT_ID, Long.parseLong(str2));
        InvokeService(10, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass13 */

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable th) {
                resolver.reject(th);
            }

            public void resolve(JSONObject jSONObject) {
                resolver.resolve(null);
            }
        });
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void requestMessagesImpl(String str, double d, double d2, final Resolver<MessengerModule.MessageList> resolver) {
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(str));
        bundle.putInt("max_messages", (int) d);
        bundle.putLong("older_than", (long) d2);
        InvokeService(3, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass14 */

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable th) {
                resolver.reject(th);
            }

            public void resolve(JSONObject jSONObject) {
                resolver.resolve(MessengerModule.MessageList.makeFromJSONObject(jSONObject));
            }
        });
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void requestNonInboxMessagesImpl(String str, double d, final Resolver<MessengerModule.MessageList> resolver) {
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(str));
        bundle.putInt("max_messages", (int) d);
        InvokeService(18, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass15 */

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable th) {
                resolver.reject(th);
            }

            public void resolve(JSONObject jSONObject) {
                resolver.resolve(MessengerModule.MessageList.makeFromJSONObject(jSONObject));
            }
        });
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void requestNonInboxThreadsImpl(String str, double d, double d2, final Resolver<MessengerModule.ThreadList> resolver) {
        Bundle bundle = new Bundle();
        bundle.putString(MessengerService.GetNonInboxThreadsParamKeys.FOLDER, str);
        bundle.putInt("max_threads", (int) d);
        bundle.putInt("max_messages_per_thread", (int) d2);
        InvokeService(17, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass16 */

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable th) {
                resolver.reject(th);
            }

            public void resolve(JSONObject jSONObject) {
                resolver.resolve(MessengerModule.ThreadList.makeFromJSONObject(jSONObject));
            }
        });
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void requestThreadImpl(String str, double d, final Resolver<MessengerModule.Thread> resolver) {
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(str));
        bundle.putInt("max_messages", (int) d);
        InvokeService(12, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass17 */

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable th) {
                resolver.reject(th);
            }

            public void resolve(JSONObject jSONObject) {
                resolver.resolve(MessengerModule.Thread.makeFromJSONObject(jSONObject));
            }
        });
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void requestThreadsImpl(double d, double d2, double d3, final Resolver<MessengerModule.ThreadList> resolver) {
        Bundle bundle = new Bundle();
        bundle.putInt("max_threads", (int) d);
        bundle.putInt("max_messages_per_thread", (int) d2);
        bundle.putLong("older_than", (long) d3);
        InvokeService(2, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass18 */

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable th) {
                resolver.reject(th);
            }

            public void resolve(JSONObject jSONObject) {
                resolver.resolve(MessengerModule.ThreadList.makeFromJSONObject(jSONObject));
            }
        });
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void requestUnreadThreadCountsImpl(final Resolver<MessengerModule.UnreadThreadCounts> resolver) {
        InvokeService(19, new Bundle(), new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass19 */

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable th) {
                resolver.reject(th);
            }

            public void resolve(JSONObject jSONObject) {
                resolver.resolve(MessengerModule.UnreadThreadCounts.makeFromJSONObject(jSONObject));
            }
        });
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void retrySendingMessageImpl(String str, String str2, final Resolver<Void> resolver) {
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(str));
        bundle.putLong(MessengerService.RetrySendingMessageParamKeys.OFFLINE_THREADING_ID, Long.parseLong(str2));
        InvokeService(13, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass20 */

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable th) {
                resolver.reject(th);
            }

            public void resolve(JSONObject jSONObject) {
                resolver.resolve(null);
            }
        });
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void sendMessageImpl(String str, String str2, final Resolver<Void> resolver) {
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(str));
        bundle.putString("message", str2);
        InvokeService(11, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass21 */

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable th) {
                resolver.reject(th);
            }

            public void resolve(JSONObject jSONObject) {
                resolver.resolve(null);
            }
        });
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void unbindServiceImpl() {
        if (this.mServiceBound.booleanValue()) {
            try {
                this.mContext.unbindService(this.mConnection);
            } catch (IllegalArgumentException e) {
                Log.w(this.TAG, AnonymousClass006.A07("unbindService failed with exception: ", e.getMessage()));
            }
            this.hasInitedMessengerService = false;
            this.mServiceBound = false;
            rejectPendingServiceCalls();
        }
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void unmuteThreadImpl(String str, final Resolver<Void> resolver) {
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(str));
        bundle.putInt(MessengerService.MuteThreadParamKeys.MUTE_FOR_S, 0);
        InvokeService(9, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass22 */

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable th) {
                resolver.reject(th);
            }

            public void resolve(JSONObject jSONObject) {
                resolver.resolve(null);
            }
        });
    }

    public MessengerModuleImpl(Context context) {
        IncomingHandler incomingHandler = new IncomingHandler();
        this.mHandler = incomingHandler;
        this.mMessenger = new Messenger(incomingHandler);
        this.mService = null;
        this.mConnection = new ServiceConnection() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass1 */

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                MessengerModuleImpl.this.mService = new Messenger(iBinder);
                MessengerModuleImpl.this.initMessengerService();
            }

            public void onServiceDisconnected(ComponentName componentName) {
                MessengerModuleImpl messengerModuleImpl = MessengerModuleImpl.this;
                messengerModuleImpl.mService = null;
                messengerModuleImpl.hasInitedMessengerService = false;
                messengerModuleImpl.rejectPendingServiceCalls();
            }
        };
        this.mContext = context;
    }

    private long[] convertIdsJSONArrayToLongArray(JSONArray jSONArray) throws JSONException {
        long[] jArr = new long[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            jArr[i] = Long.parseLong(jSONArray.getString(i));
        }
        return jArr;
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void addParticipantsImpl(String str, MessengerModule.UserIdsWrapper userIdsWrapper, final Resolver<Void> resolver) {
        userIdsWrapper.toString();
        try {
            long[] convertIdsJSONArrayToLongArray = convertIdsJSONArrayToLongArray(userIdsWrapper.convertToJSONObject().getJSONArray("userIds"));
            Bundle bundle = new Bundle();
            bundle.putLong("thread_key", Long.parseLong(str));
            bundle.putLongArray("participant_ids", convertIdsJSONArrayToLongArray);
            InvokeService(6, bundle, new Resolver<JSONObject>() {
                /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass7 */

                @Override // com.oculus.modules.codegen.Resolver
                public void reject(Throwable th) {
                    resolver.reject(th);
                }

                public void resolve(JSONObject jSONObject) {
                    resolver.resolve(null);
                }
            });
        } catch (JSONException e) {
            rejectAndLogWithMessage(resolver, AnonymousClass006.A07("Failed converting JSONArray to long[]:", e.getMessage()));
        }
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void createThreadImpl(MessengerModule.UserIdsWrapper userIdsWrapper, String str, final Resolver<MessengerModule.Thread> resolver) {
        try {
            long[] convertIdsJSONArrayToLongArray = convertIdsJSONArrayToLongArray(userIdsWrapper.convertToJSONObject().getJSONArray("userIds"));
            Bundle bundle = new Bundle();
            bundle.putLongArray("participant_ids", convertIdsJSONArrayToLongArray);
            bundle.putString("message", str);
            InvokeService(7, bundle, new Resolver<JSONObject>() {
                /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass8 */

                @Override // com.oculus.modules.codegen.Resolver
                public void reject(Throwable th) {
                    resolver.reject(th);
                }

                public void resolve(JSONObject jSONObject) {
                    resolver.resolve(MessengerModule.Thread.makeFromJSONObject(jSONObject));
                }
            });
        } catch (JSONException e) {
            rejectAndLogWithMessage(resolver, AnonymousClass006.A07("Failed converting JSONArray to long[]:", e.getMessage()));
        }
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void shutdownModule() {
        unbindServiceImpl();
    }

    public class ServiceCall {
        public Bundle bundle;
        public int messageType;
        public Resolver<JSONObject> resolver;

        public ServiceCall() {
        }
    }

    public class PendingAsyncCall {
        public Resolver<JSONObject> resolver;
        public Object timeoutToken;

        public PendingAsyncCall() {
        }
    }
}
