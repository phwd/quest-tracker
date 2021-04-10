package com.oculus.modules;

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
import com.oculus.modules.codegen.MessengerModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.os.Version;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class MessengerModuleImpl extends MessengerModule {
    private static final long ASYNC_TIMEOUT_MS = 30000;
    private String AccessToken = null;
    private String AppId = null;
    private final String TAG = MessengerModuleImpl.class.getSimpleName();
    private String UserId = null;
    private int callIdCounter = 0;
    private Boolean hasInitedMessengerService = false;
    private ServiceConnection mConnection = new ServiceConnection() {
        /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass1 */

        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.i(MessengerModuleImpl.this.TAG, "onServiceConnected");
            MessengerModuleImpl.this.mService = new Messenger(service);
            MessengerModuleImpl.this.initMessengerService();
        }

        public void onServiceDisconnected(ComponentName name) {
            Log.i(MessengerModuleImpl.this.TAG, "onServiceDisconnected");
            MessengerModuleImpl.this.mService = null;
            MessengerModuleImpl.this.hasInitedMessengerService = false;
            MessengerModuleImpl.this.rejectPendingServiceCalls();
        }
    };
    private final Context mContext;
    private final IncomingHandler mHandler = new IncomingHandler();
    private final Messenger mMessenger = new Messenger(this.mHandler);
    private Messenger mService = null;
    private Boolean mServiceBound = false;
    private SparseArray<PendingAsyncCall> pendingServiceCalls = new SparseArray<>();
    private ArrayList<ServiceCall> queuedServiceCalls = new ArrayList<>();

    static /* synthetic */ int access$1108(MessengerModuleImpl x0) {
        int i = x0.callIdCounter;
        x0.callIdCounter = i + 1;
        return i;
    }

    private static class MessageTypes {
        private static final int ADD_PARTICIPANTS_TO_THREAD = 6;
        private static final int CREATE_GROUP_THREAD = 7;
        private static final int DELETE_THREAD = 14;
        private static final int ERROR = -1;
        private static final int GET_MESSAGES = 3;
        private static final int GET_NON_INBOX_MESSAGES = 18;
        private static final int GET_NON_INBOX_THREADS = 17;
        private static final int GET_THREAD = 12;
        private static final int GET_THREADS = 2;
        private static final int GET_UNREAD_THREAD_COUNTS = 19;
        private static final int HIDE_THREAD = 16;
        private static final int INIT = 1;
        private static final int MARK_THREAD_READ = 8;
        private static final int MUTE_THREAD = 9;
        private static final int NEW_MESSAGE_CALLBACK = 4;
        private static final int REMOVE_PARTICIPANT_FROM_THREAD = 10;
        private static final int RETRY_SENDING_MESSAGE = 13;
        private static final int SEND_MESSAGE = 11;
        private static final int THREAD_DELETION_CALLBACK = 15;
        private static final int THREAD_UPDATE_CALLBACK = 5;
        private static final int UNREAD_THREAD_COUNTS_UPDATE_CALLBACK = 20;

        private MessageTypes() {
        }
    }

    /* access modifiers changed from: private */
    public class ServiceCall {
        public Bundle bundle;
        public int messageType;
        public Resolver<JSONObject> resolver;

        private ServiceCall() {
        }
    }

    /* access modifiers changed from: private */
    public class PendingAsyncCall {
        private Resolver<JSONObject> resolver;
        public Object timeoutToken;

        private PendingAsyncCall() {
        }
    }

    /* access modifiers changed from: package-private */
    public class IncomingHandler extends Handler {
        public IncomingHandler() {
            super(Looper.getMainLooper());
        }

        private void convertLongToString(JSONObject obj, String key) throws JSONException {
            String valueAsString = obj.optString(key);
            if (!valueAsString.isEmpty()) {
                obj.put(key, valueAsString);
            }
        }

        private void convertParticipant(JSONObject participant) throws JSONException {
            convertLongToString(participant, "participantId");
        }

        private void convertAttachment(JSONObject attachment) throws JSONException {
            convertLongToString(attachment, "attachmentId");
            convertLongToString(attachment, "attachmentType");
        }

        private void convertMessage(JSONObject message) throws JSONException {
            convertLongToString(message, "senderId");
            convertLongToString(message, "offlineThreadingId");
            if (!message.has("type")) {
                message.put("type", "NORMAL");
            }
            if (message.has("attachments")) {
                JSONArray attachments = message.getJSONArray("attachments");
                for (int k = 0; k < attachments.length(); k++) {
                    convertAttachment(attachments.getJSONObject(k));
                }
            }
        }

        private void convertThread(JSONObject thread) throws JSONException {
            convertLongToString(thread, "threadId");
            JSONArray participants = thread.getJSONArray("participants");
            for (int j = 0; j < participants.length(); j++) {
                convertParticipant(participants.getJSONObject(j));
            }
            JSONArray messages = thread.getJSONArray("messages");
            for (int j2 = 0; j2 < messages.length(); j2++) {
                convertMessage(messages.getJSONObject(j2));
            }
            if (!thread.has("name")) {
                thread.put("name", "");
            }
        }

        private JSONObject convertGetThreads(JSONObject threadList) throws JSONException {
            JSONArray threads = threadList.getJSONArray("threads");
            Boolean reachedEndOfThreads = Boolean.valueOf(threadList.getBoolean("reachedEndOfThreads"));
            for (int i = 0; i < threads.length(); i++) {
                convertThread(threads.getJSONObject(i));
            }
            JSONObject result = new JSONObject();
            result.put("type", "THREAD_LIST");
            result.put("reachedEndOfThreads", reachedEndOfThreads);
            result.put("threads", threads);
            return result;
        }

        private JSONObject convertGetMessages(JSONObject messageList) throws JSONException {
            convertLongToString(messageList, "threadId");
            String threadId = messageList.getString("threadId");
            JSONArray messages = messageList.getJSONArray("messages");
            Boolean reachedEndOfThread = Boolean.valueOf(messageList.getBoolean("reachedEndOfThread"));
            for (int i = 0; i < messages.length(); i++) {
                convertMessage(messages.getJSONObject(i));
            }
            JSONObject result = new JSONObject();
            result.put("type", "MESSAGE_LIST");
            result.put("reachedEndOfThread", reachedEndOfThread);
            result.put("threadId", threadId);
            result.put("messages", messages);
            return result;
        }

        private JSONObject convertNewMessage(JSONObject newMessage) throws JSONException {
            convertLongToString(newMessage, "threadId");
            String threadId = newMessage.getString("threadId");
            JSONObject message = newMessage.getJSONObject("message");
            convertMessage(message);
            JSONObject result = new JSONObject();
            result.put("type", "NEW_MESSAGE");
            result.put("threadId", threadId);
            result.put("message", message);
            return result;
        }

        private JSONObject convertThreadDeletion(JSONObject payload) throws JSONException {
            convertLongToString(payload, "threadId");
            String threadId = payload.getString("threadId");
            JSONObject result = new JSONObject();
            result.put("type", "THREAD_DELETION");
            result.put("threadId", threadId);
            return result;
        }

        private JSONObject convertThreadUpdate(JSONObject payload) throws JSONException {
            JSONObject result = new JSONObject();
            result.put("type", "THREAD_UPDATE");
            JSONObject thread = payload.getJSONObject("thread");
            convertThread(thread);
            result.put("thread", thread);
            result.put("reloadThread", payload.getBoolean("reloadThread"));
            return result;
        }

        private JSONObject convertUnreadThreadCountsUpdate(JSONObject counts) throws JSONException {
            JSONObject result = new JSONObject();
            result.put("type", "UNREAD_THREAD_COUNTS_UPDATE");
            result.put("counts", counts.getJSONArray("counts"));
            return result;
        }

        public void handleMessage(Message msg) {
            JSONObject messengerEvent;
            JSONObject messengerResponse;
            String result = msg.getData().getString("result");
            Log.i(MessengerModuleImpl.this.TAG, "handleMessage " + msg.what);
            try {
                JSONObject jsonResult = (JSONObject) new JSONTokener(result).nextValue();
                if (msg.what == 4 || msg.what == 15 || msg.what == 5 || msg.what == 20) {
                    try {
                        switch (msg.what) {
                            case 4:
                                Log.i(MessengerModuleImpl.this.TAG, "New Message: " + result);
                                messengerEvent = convertNewMessage(jsonResult);
                                break;
                            case 5:
                                Log.i(MessengerModuleImpl.this.TAG, "Thread Update: " + result);
                                messengerEvent = convertThreadUpdate(jsonResult);
                                break;
                            case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
                                Log.i(MessengerModuleImpl.this.TAG, "Thread Deleted: " + result);
                                messengerEvent = convertThreadDeletion(jsonResult);
                                break;
                            case 20:
                                Log.i(MessengerModuleImpl.this.TAG, "Unread Thread Counts Update: " + result);
                                messengerEvent = convertUnreadThreadCountsUpdate(jsonResult);
                                break;
                            default:
                                Log.w(MessengerModuleImpl.this.TAG, "handleMessage: Unrecognised message type: " + msg.what);
                                super.handleMessage(msg);
                                return;
                        }
                        MessengerModuleImpl.this.emitOnMessengerEvent(MessengerModule.CallbackData.makeFromJSONObject(messengerEvent));
                    } catch (JSONException e) {
                        Log.w(MessengerModuleImpl.this.TAG, "handleMessage: Failed to encode messengerEvent from \"" + result + "\". " + e.getMessage());
                    }
                } else {
                    PendingAsyncCall call = MessengerModuleImpl.this.GetPendingCall(jsonResult);
                    if (call == null) {
                        Log.w(MessengerModuleImpl.this.TAG, "handleMessage: Failed to get matching call for id \"" + result + "\".");
                        return;
                    }
                    try {
                        switch (msg.what) {
                            case -1:
                                MessengerModuleImpl.this.rejectAndLogWithMessage(call.resolver, jsonResult.optString("message"));
                                return;
                            case 2:
                                Log.i(MessengerModuleImpl.this.TAG, "Get Threads: " + result);
                                messengerResponse = convertGetThreads(jsonResult);
                                break;
                            case 3:
                                Log.i(MessengerModuleImpl.this.TAG, "Get Messages: " + result);
                                messengerResponse = convertGetMessages(jsonResult);
                                break;
                            case 7:
                                Log.i(MessengerModuleImpl.this.TAG, "Create Group Thread: " + result);
                                messengerResponse = jsonResult.getJSONObject("thread");
                                convertThread(messengerResponse);
                                break;
                            case 12:
                                Log.i(MessengerModuleImpl.this.TAG, "Get Thread: " + result);
                                if (!jsonResult.isNull("thread")) {
                                    messengerResponse = jsonResult.getJSONObject("thread");
                                    convertThread(messengerResponse);
                                    break;
                                } else {
                                    messengerResponse = null;
                                    break;
                                }
                            case Version.VERSION_17 /*{ENCODED_INT: 17}*/:
                                Log.i(MessengerModuleImpl.this.TAG, "Get Non-Inbox Threads: " + result);
                                messengerResponse = convertGetThreads(jsonResult);
                                break;
                            case Version.VERSION_18 /*{ENCODED_INT: 18}*/:
                                Log.i(MessengerModuleImpl.this.TAG, "Get Non-Inbox Messages: " + result);
                                messengerResponse = convertGetMessages(jsonResult);
                                break;
                            case Version.VERSION_19 /*{ENCODED_INT: 19}*/:
                                Log.i(MessengerModuleImpl.this.TAG, "Get Unread Thread Counts: " + result);
                                messengerResponse = jsonResult;
                                break;
                            default:
                                Log.i(MessengerModuleImpl.this.TAG, "Got acknowledgment from Messenger Service for msg type: " + msg.what);
                                messengerResponse = new JSONObject();
                                break;
                        }
                        call.resolver.resolve(messengerResponse);
                    } catch (JSONException e2) {
                        MessengerModuleImpl.this.rejectAndLogWithMessage(call.resolver, "Failed to parse response from Messenger Service: \"" + result + "\". " + e2.getMessage());
                    }
                }
            } catch (JSONException e3) {
                Log.w(MessengerModuleImpl.this.TAG, "handleMessage: Failed to parse \"" + result + "\". " + e3.getMessage());
            } catch (ClassCastException e4) {
                Log.w(MessengerModuleImpl.this.TAG, "handleMessage: Wrong JSON type received from \"" + result + "\". " + e4.getMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void rejectAndLogWithMessage(Resolver resolver, String message) {
        Log.w(this.TAG, "Rejecting call with error: " + message);
        resolver.reject(new Error(message));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private PendingAsyncCall GetPendingCall(JSONObject result) {
        try {
            int callId = result.getInt("callId");
            PendingAsyncCall call = this.pendingServiceCalls.get(callId);
            if (call == null) {
                return null;
            }
            this.mHandler.removeCallbacksAndMessages(call.timeoutToken);
            this.pendingServiceCalls.remove(callId);
            return call;
        } catch (JSONException e) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void AddPendingCall(final int callId, Resolver<JSONObject> resolver) {
        PendingAsyncCall asyncCall = new PendingAsyncCall();
        asyncCall.resolver = resolver;
        asyncCall.timeoutToken = new Object();
        this.pendingServiceCalls.put(callId, asyncCall);
        this.mHandler.postAtTime(new Runnable() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass2 */

            public void run() {
                PendingAsyncCall call = (PendingAsyncCall) MessengerModuleImpl.this.pendingServiceCalls.get(callId);
                if (call != null) {
                    MessengerModuleImpl.this.rejectAndLogWithMessage(call.resolver, "Call timed out waiting for response from messenger service");
                    MessengerModuleImpl.this.pendingServiceCalls.remove(callId);
                }
            }
        }, asyncCall.timeoutToken, SystemClock.uptimeMillis() + ASYNC_TIMEOUT_MS);
    }

    private void InvokeService(final int messageType, final Bundle bundle, final Resolver<JSONObject> resolver) {
        this.mHandler.post(new Runnable() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass3 */

            public void run() {
                if (MessengerModuleImpl.this.hasInitedMessengerService.booleanValue()) {
                    Message msg = Message.obtain((Handler) null, messageType);
                    int callId = MessengerModuleImpl.access$1108(MessengerModuleImpl.this);
                    bundle.putInt("call_id", callId);
                    msg.setData(bundle);
                    msg.replyTo = MessengerModuleImpl.this.mMessenger;
                    try {
                        MessengerModuleImpl.this.AddPendingCall(callId, resolver);
                        MessengerModuleImpl.this.mService.send(msg);
                    } catch (RemoteException e) {
                        MessengerModuleImpl.this.rejectAndLogWithMessage(resolver, "InvokeServicePending got remote exception:" + e.getMessage());
                    }
                } else {
                    ServiceCall call = new ServiceCall();
                    call.messageType = messageType;
                    call.bundle = bundle;
                    call.resolver = resolver;
                    MessengerModuleImpl.this.queuedServiceCalls.add(call);
                    Log.i(MessengerModuleImpl.this.TAG, "Trying to InvokeService before messenger service is initialised, queueing call for later...");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void initMessengerService() {
        if (this.hasInitedMessengerService.booleanValue() || this.UserId == null || this.AccessToken == null || this.AppId == null || this.mService == null) {
            String missingFields = "";
            if (this.UserId == null) {
                missingFields = missingFields + " UserId";
            }
            if (this.AppId == null) {
                missingFields = missingFields + " AppId";
            }
            if (this.AccessToken == null) {
                missingFields = missingFields + " AccessToken";
            }
            if (this.mService == null) {
                missingFields = missingFields + " Service";
            }
            if (!missingFields.isEmpty()) {
                Log.i(this.TAG, "Can't initialise yet, missing:" + missingFields);
                return;
            }
            return;
        }
        Message msg = Message.obtain((Handler) null, 1);
        Bundle b = new Bundle();
        b.putLong("user_id", Long.parseLong(this.UserId));
        b.putString("access_token", this.AccessToken);
        b.putString("app_id", this.AppId);
        msg.setData(b);
        msg.replyTo = this.mMessenger;
        try {
            this.mService.send(msg);
        } catch (RemoteException e) {
            Log.w(this.TAG, "Got remote exception:" + e.getMessage());
        }
        this.hasInitedMessengerService = true;
        if (!this.queuedServiceCalls.isEmpty()) {
            Log.i(this.TAG, "Flushing " + this.queuedServiceCalls.size() + " calls to the Messenger Service");
            Iterator<ServiceCall> it = this.queuedServiceCalls.iterator();
            while (it.hasNext()) {
                ServiceCall call = it.next();
                InvokeService(call.messageType, call.bundle, call.resolver);
            }
            this.queuedServiceCalls.clear();
        }
    }

    public MessengerModuleImpl(Context context) {
        Log.i(this.TAG, "MessengerModule");
        this.mContext = context;
    }

    public void SetUserId(final String userId) {
        Log.i(this.TAG, "SetUserId " + userId);
        this.mHandler.post(new Runnable() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass4 */

            public void run() {
                MessengerModuleImpl.this.UserId = userId;
                MessengerModuleImpl.this.initMessengerService();
            }
        });
    }

    public void SetAccessToken(final String accessToken) {
        Log.i(this.TAG, "SetAccessToken");
        this.mHandler.post(new Runnable() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass5 */

            public void run() {
                MessengerModuleImpl.this.AccessToken = accessToken;
                MessengerModuleImpl.this.initMessengerService();
            }
        });
    }

    public void SetAppId(final String appId) {
        Log.i(this.TAG, "SetAppId " + appId);
        this.mHandler.post(new Runnable() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass6 */

            public void run() {
                MessengerModuleImpl.this.AppId = appId;
                MessengerModuleImpl.this.initMessengerService();
            }
        });
    }

    @Override // com.oculus.modules.codegen.MessengerModule
    public void shutdownModule() {
        Log.i(this.TAG, "shutdownModule");
        unbindService();
    }

    private long[] convertIdsJSONArrayToLongArray(JSONArray ids) throws JSONException {
        long[] longIds = new long[ids.length()];
        for (int i = 0; i < ids.length(); i++) {
            longIds[i] = Long.parseLong(ids.getString(i));
        }
        return longIds;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void addParticipantsImpl(String threadId, MessengerModule.UserIdsWrapper userIds, final Resolver<Void> resolver) {
        Log.i(this.TAG, "addParticipants " + threadId + ", " + userIds.toString());
        try {
            long[] participantIds = convertIdsJSONArrayToLongArray(userIds.convertToJSONObject().getJSONArray("userIds"));
            Bundle bundle = new Bundle();
            bundle.putLong("thread_key", Long.parseLong(threadId));
            bundle.putLongArray("participant_ids", participantIds);
            InvokeService(6, bundle, new Resolver<JSONObject>() {
                /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass7 */

                public void resolve(JSONObject response) {
                    resolver.resolve(null);
                }

                @Override // com.oculus.modules.codegen.Resolver
                public void reject(Throwable error) {
                    resolver.reject(error);
                }
            });
        } catch (JSONException e) {
            rejectAndLogWithMessage(resolver, "Failed converting JSONArray to long[]:" + e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void createThreadImpl(MessengerModule.UserIdsWrapper userIds, String message, final Resolver<MessengerModule.Thread> resolver) {
        Log.i(this.TAG, "createThread " + userIds + ", " + message);
        try {
            long[] participantIds = convertIdsJSONArrayToLongArray(userIds.convertToJSONObject().getJSONArray("userIds"));
            Bundle bundle = new Bundle();
            bundle.putLongArray("participant_ids", participantIds);
            bundle.putString("message", message);
            InvokeService(7, bundle, new Resolver<JSONObject>() {
                /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass8 */

                public void resolve(JSONObject response) {
                    resolver.resolve(MessengerModule.Thread.makeFromJSONObject(response));
                }

                @Override // com.oculus.modules.codegen.Resolver
                public void reject(Throwable error) {
                    resolver.reject(error);
                }
            });
        } catch (JSONException e) {
            rejectAndLogWithMessage(resolver, "Failed converting JSONArray to long[]:" + e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void deleteThreadImpl(String threadId, final Resolver<Void> resolver) {
        Log.i(this.TAG, "deleteThread " + threadId);
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(threadId));
        InvokeService(14, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass9 */

            public void resolve(JSONObject response) {
                resolver.resolve(null);
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                resolver.reject(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void hideThreadImpl(String threadId, final Resolver<Void> resolver) {
        Log.i(this.TAG, "hideThread " + threadId);
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(threadId));
        InvokeService(16, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass10 */

            public void resolve(JSONObject response) {
                resolver.resolve(null);
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                resolver.reject(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void markThreadReadImpl(String threadId, double watermarkTimestamp, final Resolver<Void> resolver) {
        Log.i(this.TAG, "markThreadRead " + threadId + ", " + watermarkTimestamp);
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(threadId));
        bundle.putLong("watermark_timestamp", (long) watermarkTimestamp);
        InvokeService(8, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass11 */

            public void resolve(JSONObject response) {
                resolver.resolve(null);
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                resolver.reject(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void muteThreadImpl(String threadId, double muteDurationInSec, final Resolver<Void> resolver) {
        Log.i(this.TAG, "muteThread " + threadId + ", " + muteDurationInSec);
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(threadId));
        bundle.putInt("mute_for_s", (int) muteDurationInSec);
        InvokeService(9, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass12 */

            public void resolve(JSONObject response) {
                resolver.resolve(null);
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                resolver.reject(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void removeParticipantImpl(String threadId, String userId, final Resolver<Void> resolver) {
        Log.i(this.TAG, "removeParticipant " + threadId + ", " + userId);
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(threadId));
        bundle.putLong("participant_id", Long.parseLong(userId));
        InvokeService(10, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass13 */

            public void resolve(JSONObject response) {
                resolver.resolve(null);
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                resolver.reject(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void requestMessagesImpl(String threadId, double maxMessages, double olderThan, final Resolver<MessengerModule.MessageList> resolver) {
        Log.i(this.TAG, "requestMessages " + threadId + ", " + maxMessages + ", " + olderThan);
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(threadId));
        bundle.putInt("max_messages", (int) maxMessages);
        bundle.putLong("older_than", (long) olderThan);
        InvokeService(3, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass14 */

            public void resolve(JSONObject response) {
                resolver.resolve(MessengerModule.MessageList.makeFromJSONObject(response));
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                resolver.reject(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void requestNonInboxMessagesImpl(String threadId, double maxMessages, final Resolver<MessengerModule.MessageList> resolver) {
        Log.i(this.TAG, "requestNonInboxMessages " + threadId + ", " + maxMessages);
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(threadId));
        bundle.putInt("max_messages", (int) maxMessages);
        InvokeService(18, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass15 */

            public void resolve(JSONObject response) {
                resolver.resolve(MessengerModule.MessageList.makeFromJSONObject(response));
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                resolver.reject(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void requestNonInboxThreadsImpl(String folder, double maxThreads, double maxMessagesPerThread, final Resolver<MessengerModule.ThreadList> resolver) {
        Log.i(this.TAG, "requestNonInboxThreads " + folder + ", " + maxThreads + ", " + maxMessagesPerThread);
        Bundle bundle = new Bundle();
        bundle.putString("folder", folder);
        bundle.putInt("max_threads", (int) maxThreads);
        bundle.putInt("max_messages_per_thread", (int) maxMessagesPerThread);
        InvokeService(17, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass16 */

            public void resolve(JSONObject response) {
                resolver.resolve(MessengerModule.ThreadList.makeFromJSONObject(response));
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                resolver.reject(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void requestThreadImpl(String threadId, double maxMessages, final Resolver<MessengerModule.Thread> resolver) {
        Log.i(this.TAG, "requestThread " + threadId + ", " + maxMessages);
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(threadId));
        bundle.putInt("max_messages", (int) maxMessages);
        InvokeService(12, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass17 */

            public void resolve(JSONObject response) {
                resolver.resolve(MessengerModule.Thread.makeFromJSONObject(response));
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                resolver.reject(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void requestThreadsImpl(double maxThreads, double maxMessagesPerThread, double olderThan, final Resolver<MessengerModule.ThreadList> resolver) {
        Log.i(this.TAG, "requestThreads " + maxThreads + ", " + maxMessagesPerThread + ", " + olderThan);
        Bundle bundle = new Bundle();
        bundle.putInt("max_threads", (int) maxThreads);
        bundle.putInt("max_messages_per_thread", (int) maxMessagesPerThread);
        bundle.putLong("older_than", (long) olderThan);
        InvokeService(2, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass18 */

            public void resolve(JSONObject response) {
                resolver.resolve(MessengerModule.ThreadList.makeFromJSONObject(response));
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                resolver.reject(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void requestUnreadThreadCountsImpl(final Resolver<MessengerModule.UnreadThreadCounts> resolver) {
        Log.i(this.TAG, "requestUnreadThreadCounts");
        InvokeService(19, new Bundle(), new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass19 */

            public void resolve(JSONObject response) {
                resolver.resolve(MessengerModule.UnreadThreadCounts.makeFromJSONObject(response));
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                resolver.reject(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void retrySendingMessageImpl(String threadId, String offlineThreadingId, final Resolver<Void> resolver) {
        Log.i(this.TAG, "retrySendingMessage " + threadId + ", " + offlineThreadingId);
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(threadId));
        bundle.putLong("offline_threading_id", Long.parseLong(offlineThreadingId));
        InvokeService(13, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass20 */

            public void resolve(JSONObject response) {
                resolver.resolve(null);
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                resolver.reject(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void sendMessageImpl(String threadId, String message, final Resolver<Void> resolver) {
        Log.i(this.TAG, "sendMessage " + threadId + ", " + message);
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(threadId));
        bundle.putString("message", message);
        InvokeService(11, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass21 */

            public void resolve(JSONObject response) {
                resolver.resolve(null);
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                resolver.reject(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void unmuteThreadImpl(String threadId, final Resolver<Void> resolver) {
        Log.i(this.TAG, "unmuteThread " + threadId);
        Bundle bundle = new Bundle();
        bundle.putLong("thread_key", Long.parseLong(threadId));
        bundle.putInt("mute_for_s", 0);
        InvokeService(9, bundle, new Resolver<JSONObject>() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass22 */

            public void resolve(JSONObject response) {
                resolver.resolve(null);
            }

            @Override // com.oculus.modules.codegen.Resolver
            public void reject(Throwable error) {
                resolver.reject(error);
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void bindServiceImpl() {
        if (!this.mServiceBound.booleanValue()) {
            Log.i(this.TAG, "Binding to Messenger Service");
            ComponentName componentName = new ComponentName("com.oculus.socialplatform", "com.oculus.messenger.service.MessengerService");
            Intent intent = new Intent();
            intent.setComponent(componentName);
            this.mContext.bindService(intent, this.mConnection, 1);
            this.mServiceBound = true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MessengerModule
    public void unbindServiceImpl() {
        if (this.mServiceBound.booleanValue()) {
            Log.i(this.TAG, "Unbinding from Messenger Service");
            try {
                this.mContext.unbindService(this.mConnection);
            } catch (IllegalArgumentException e) {
                Log.w(this.TAG, "unbindService failed with exception: " + e.getMessage());
            }
            this.hasInitedMessengerService = false;
            this.mServiceBound = false;
            rejectPendingServiceCalls();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void rejectPendingServiceCalls() {
        this.mHandler.post(new Runnable() {
            /* class com.oculus.modules.MessengerModuleImpl.AnonymousClass23 */

            public void run() {
                Log.i(MessengerModuleImpl.this.TAG, "rejecting pending service calls");
                for (int i = 0; i < MessengerModuleImpl.this.pendingServiceCalls.size(); i++) {
                    PendingAsyncCall call = (PendingAsyncCall) MessengerModuleImpl.this.pendingServiceCalls.valueAt(i);
                    MessengerModuleImpl.this.mHandler.removeCallbacksAndMessages(call.timeoutToken);
                    MessengerModuleImpl.this.rejectAndLogWithMessage(call.resolver, "service_shutdown");
                }
                MessengerModuleImpl.this.pendingServiceCalls.clear();
            }
        });
    }
}
