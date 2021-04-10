package com.oculus.messenger.service;

import X.AbstractC02170iC;
import X.AbstractC05710wh;
import X.AnonymousClass006;
import X.AnonymousClass00r;
import X.AnonymousClass04K;
import X.AnonymousClass04L;
import X.AnonymousClass04S;
import X.AnonymousClass04V;
import X.AnonymousClass04X;
import X.AnonymousClass0Ck;
import X.AnonymousClass0II;
import X.AnonymousClass0RE;
import X.AnonymousClass0VF;
import X.AnonymousClass0lg;
import X.C000200q;
import X.C01210Vd;
import X.C03620oC;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat$CarExtender;
import com.facebook.annotations.OkToExtend;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableList;
import com.oculus.horizon.api.rating.ReviewsRequest;
import com.oculus.messenger.manager.MessengerManagerJNI;
import com.oculus.messenger.models.Thread;
import com.oculus.messenger.service.handlers.SendMessageHandler;
import com.oculus.util.device.DeviceUtils;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class MessengerService extends Service {
    public static final String CALL_ID_KEY = "call_id";
    public static final String MSG_RESULT_KEY = "result";
    public static final String TAG = "MessengerService";
    public static boolean sActive;
    public AnonymousClass0RE _UL_mInjectionContext;
    public Set<Messenger> mClients;
    @Inject
    @Eager
    public C01210Vd mFbObjectMapper;
    public final IncomingHandler mHandler;
    public boolean mInitialized;
    public final Messenger mMessenger;
    public MessengerManagerJNI mMessengerManager;

    public static class AddParticipantsToThreadParamKeys {
        public static final String PARTICIPANT_IDS = "participant_ids";
        public static final String THREAD_KEY = "thread_key";
    }

    @OkToExtend
    public class BaseCallback {
        public int mCallId;
        public Messenger mClient;
        public int mWhat;

        public BaseCallback(Message message) {
            this.mWhat = message.what;
            this.mClient = message.replyTo;
            this.mCallId = message.getData().getInt("call_id", -1);
        }

        public void sendErrorReply(@Nullable String str) {
            if (MessengerService.this.mClients.contains(this.mClient)) {
                if (str == null) {
                    str = "Failed in Messenger Manager";
                }
                MessengerService.this.sendErrorReply(this.mCallId, this.mClient, str);
            }
        }

        public void sendReply(AnonymousClass04L r5) {
            if (MessengerService.this.mClients.contains(this.mClient)) {
                MessengerService.this.sendReply(this.mCallId, this.mWhat, this.mClient, r5);
            }
        }
    }

    public static class CreateGroupThreadParamKeys {
        public static final String MESSAGE = "message";
        public static final String PARTICIPANT_IDS = "participant_ids";
    }

    public static class DeleteThreadParamKeys {
        public static final String THREAD_KEY = "thread_key";
    }

    public class GetMessagesCallback extends BaseCallback implements MessengerManagerJNI.GetMessagesCallback {
        public long mThreadKey;

        public GetMessagesCallback(Message message, long j) {
            super(message);
            this.mThreadKey = j;
        }

        @Override // com.oculus.messenger.manager.MessengerManagerJNI.GetMessagesCallback
        public void onResult(@Nullable com.oculus.messenger.models.Message[] messageArr, int i, @Nullable String str) {
            AnonymousClass04V r0;
            if (str != null) {
                super.sendErrorReply(str);
                return;
            }
            AnonymousClass04L A04 = MessengerService.this.mFbObjectMapper.A04();
            long j = this.mThreadKey;
            Map<String, AbstractC02170iC> map = A04.A00;
            map.put("threadId", new C000200q(j));
            boolean z = false;
            if (i == 2) {
                z = true;
            }
            if (z) {
                r0 = AnonymousClass04V.A02;
            } else {
                r0 = AnonymousClass04V.A01;
            }
            map.put("reachedEndOfThread", r0);
            map.put(NotificationCompat$CarExtender.KEY_MESSAGES, new AnonymousClass04K(messageArr));
            super.sendReply(A04);
        }
    }

    public static class GetMessagesParamKeys {
        public static final String MAX_MESSAGES = "max_messages";
        public static final String OLDER_THAN = "older_than";
        public static final String THREAD_KEY = "thread_key";
    }

    public static class GetNonInboxMessagesParamKeys {
        public static final String MAX_MESSAGES = "max_messages";
        public static final String THREAD_KEY = "thread_key";
    }

    public static class GetNonInboxThreadsParamKeys {
        public static final String FOLDER = "folder";
        public static final String MAX_MESSAGES = "max_messages_per_thread";
        public static final String MAX_THREADS = "max_threads";
    }

    public class GetThreadCallback extends BaseCallback implements MessengerManagerJNI.GetThreadCallback {
        public GetThreadCallback(Message message) {
            super(message);
        }

        @Override // com.oculus.messenger.manager.MessengerManagerJNI.GetThreadCallback
        public void onResult(@Nullable Thread thread, @Nullable String str) {
            if (str != null) {
                super.sendErrorReply(str);
                return;
            }
            AnonymousClass04L A04 = MessengerService.this.mFbObjectMapper.A04();
            A04.A00.put("thread", new AnonymousClass04K(thread));
            super.sendReply(A04);
        }
    }

    public static class GetThreadParamKeys {
        public static final String MAX_MESSAGES = "max_messages";
        public static final String THREAD_KEY = "thread_key";
    }

    public class GetThreadsCallback extends BaseCallback implements MessengerManagerJNI.GetThreadsCallback {
        public GetThreadsCallback(Message message) {
            super(message);
        }

        @Override // com.oculus.messenger.manager.MessengerManagerJNI.GetThreadsCallback
        public void onResult(@Nullable Thread[] threadArr, int i, @Nullable String str) {
            AnonymousClass04V r0;
            if (str != null) {
                super.sendErrorReply(str);
                return;
            }
            AnonymousClass04L A04 = MessengerService.this.mFbObjectMapper.A04();
            boolean z = false;
            if (i == 2) {
                z = true;
            }
            Map<String, AbstractC02170iC> map = A04.A00;
            if (z) {
                r0 = AnonymousClass04V.A02;
            } else {
                r0 = AnonymousClass04V.A01;
            }
            map.put("reachedEndOfThreads", r0);
            map.put("threads", new AnonymousClass04K(threadArr));
            super.sendReply(A04);
        }
    }

    public static class GetThreadsParamKeys {
        public static final String MAX_MESSAGES = "max_messages_per_thread";
        public static final String MAX_THREADS = "max_threads";
        public static final String OLDER_THAN = "older_than";
    }

    public class GetUnreadThreadCountsCallback extends BaseCallback implements MessengerManagerJNI.GetUnreadThreadCountsCallback {
        public GetUnreadThreadCountsCallback(Message message) {
            super(message);
        }

        @Override // com.oculus.messenger.manager.MessengerManagerJNI.GetUnreadThreadCountsCallback
        public void onResult(@Nullable Map<String, Long> map, @Nullable String str) {
            if (str != null) {
                super.sendErrorReply(str);
                return;
            }
            AnonymousClass04L A04 = MessengerService.this.mFbObjectMapper.A04();
            AnonymousClass04X r5 = new AnonymousClass04X(((AnonymousClass0Ck) A04).A00);
            A04.A00.put("counts", r5);
            if (map != null) {
                for (Map.Entry<String, Long> entry : map.entrySet()) {
                    AnonymousClass04L r2 = new AnonymousClass04L(((AnonymousClass0Ck) r5).A00);
                    r5.A00.add(r2);
                    r2.A03(GetNonInboxThreadsParamKeys.FOLDER, entry.getKey());
                    Long value = entry.getValue();
                    if (value == null) {
                        r2.A00.put(ReviewsRequest.KEY_COUNT, AnonymousClass04S.A00);
                    } else {
                        r2.A00.put(ReviewsRequest.KEY_COUNT, new C000200q(value.longValue()));
                    }
                }
            }
            super.sendReply(A04);
        }
    }

    public static class HideThreadParamKeys {
        public static final String THREAD_KEY = "thread_key";
    }

    public class IncomingHandler extends Handler {
        public IncomingHandler() {
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                MessengerService messengerService = MessengerService.this;
                if (!messengerService.mInitialized) {
                    messengerService.sendErrorReply(message, "Service not initialized, aborting");
                    return;
                }
            }
            switch (i) {
                case 1:
                    MessengerService.this.onInit(message);
                    return;
                case 2:
                    MessengerService.this.onGetThreads(message);
                    return;
                case 3:
                    MessengerService.this.onGetMessages(message);
                    return;
                case 4:
                case 5:
                case 15:
                default:
                    super.handleMessage(message);
                    return;
                case 6:
                    MessengerService.this.onAddParticipantsToThread(message);
                    return;
                case 7:
                    MessengerService.this.onCreateGroupThread(message);
                    return;
                case 8:
                    MessengerService.this.onMarkThreadRead(message);
                    return;
                case 9:
                    MessengerService.this.onMuteThread(message);
                    return;
                case 10:
                    MessengerService.this.onRemoveParticipantFromThread(message);
                    return;
                case 11:
                    MessengerService messengerService2 = MessengerService.this;
                    ((SendMessageHandler) AnonymousClass0VF.A03(0, 2, messengerService2._UL_mInjectionContext)).handle(messengerService2.mClients, messengerService2.mMessengerManager, message);
                    return;
                case 12:
                    MessengerService.this.onGetThread(message);
                    return;
                case 13:
                    MessengerService.this.onRetrySendingMessage(message);
                    return;
                case 14:
                    MessengerService.this.onDeleteThread(message);
                    return;
                case 16:
                    MessengerService.this.onHideThread(message);
                    return;
                case 17:
                    MessengerService.this.onGetNonInboxThreads(message);
                    return;
                case 18:
                    MessengerService.this.onGetNonInboxMessages(message);
                    return;
                case 19:
                    MessengerService.this.onGetUnreadThreadCounts(message);
                    return;
            }
        }
    }

    public static class InitParamKeys {
        public static final String ACCESS_TOKEN = "access_token";
        public static final String APP_ID = "app_id";
        public static final String USER_ID = "user_id";
    }

    public static class MarkThreadReadParamKeys {
        public static final String THREAD_KEY = "thread_key";
        public static final String WATERMARK_TIMESTAMP = "watermark_timestamp";
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

    public class MutationCallback extends BaseCallback implements MessengerManagerJNI.MutationCallback {
        public MutationCallback(Message message) {
            super(message);
        }

        @Override // com.oculus.messenger.manager.MessengerManagerJNI.MutationCallback
        public void onResult(boolean z, @Nullable String str) {
            if (z) {
                super.sendReply(MessengerService.this.mFbObjectMapper.A04());
            } else {
                super.sendErrorReply(str);
            }
        }
    }

    public static class MuteThreadParamKeys {
        public static final String MUTE_FOR_S = "mute_for_s";
        public static final String THREAD_KEY = "thread_key";
    }

    public static class RemoveParticipantFromThreadParamKeys {
        public static final String PARTICIPANT_ID = "participant_id";
        public static final String THREAD_KEY = "thread_key";
    }

    public static class RetrySendingMessageParamKeys {
        public static final String OFFLINE_THREADING_ID = "offline_threading_id";
        public static final String THREAD_KEY = "thread_key";
    }

    public static final void _UL_staticInjectMe(AnonymousClass0lg r2, MessengerService messengerService) {
        messengerService._UL_mInjectionContext = new AnonymousClass0RE(2, r2);
        messengerService.mFbObjectMapper = AnonymousClass0II.A00(r2);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onGetUnreadThreadCounts(Message message) {
        if (isMessageValid(message, null)) {
            this.mMessengerManager.getUnreadThreadCounts(new GetUnreadThreadCountsCallback(message));
        }
    }

    public IBinder onBind(Intent intent) {
        sActive = true;
        return this.mMessenger.getBinder();
    }

    public void onDestroy() {
        sActive = false;
        this.mMessengerManager.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    private boolean isMessageValid(Message message, @Nullable ImmutableList<String> immutableList) {
        String A07;
        if (!this.mInitialized) {
            A07 = "Service not initialized, aborting";
        } else if (!this.mClients.contains(message.replyTo)) {
            A07 = "Invalid caller, aborting";
        } else if (immutableList == null) {
            return true;
        } else {
            Bundle data = message.getData();
            AbstractC05710wh<String> A0I = immutableList.iterator();
            while (A0I.hasNext()) {
                String next = A0I.next();
                if (!data.containsKey(next)) {
                    A07 = AnonymousClass006.A07("Missing param: ", next);
                }
            }
            return true;
        }
        sendErrorReply(message, A07);
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onAddParticipantsToThread(Message message) {
        if (isMessageValid(message, ImmutableList.A07("thread_key", "participant_ids"))) {
            Bundle data = message.getData();
            this.mMessengerManager.addParticipantsToThread(data.getLong("thread_key"), data.getLongArray("participant_ids"), new MutationCallback(message));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onCreateGroupThread(Message message) {
        if (isMessageValid(message, ImmutableList.A07("participant_ids", "message"))) {
            Bundle data = message.getData();
            this.mMessengerManager.createGroupThread(data.getLongArray("participant_ids"), data.getString("message"), new GetThreadCallback(message));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onDeleteThread(Message message) {
        if (isMessageValid(message, ImmutableList.A06("thread_key"))) {
            this.mMessengerManager.deleteThread(message.getData().getLong("thread_key"), new MutationCallback(message));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onGetMessages(Message message) {
        if (isMessageValid(message, ImmutableList.A08("thread_key", "max_messages", "older_than"))) {
            Bundle data = message.getData();
            long j = data.getLong("thread_key");
            this.mMessengerManager.getThreadMessages(j, data.getInt("max_messages"), data.getLong("older_than"), new GetMessagesCallback(message, j));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onGetNonInboxMessages(Message message) {
        if (isMessageValid(message, ImmutableList.A07("thread_key", "max_messages"))) {
            Bundle data = message.getData();
            long j = data.getLong("thread_key");
            this.mMessengerManager.getNonInboxThreadMessages(j, data.getInt("max_messages"), new GetMessagesCallback(message, j));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onGetNonInboxThreads(Message message) {
        if (isMessageValid(message, ImmutableList.A08(GetNonInboxThreadsParamKeys.FOLDER, "max_threads", "max_messages_per_thread"))) {
            Bundle data = message.getData();
            this.mMessengerManager.getNonInboxThreads(data.getString(GetNonInboxThreadsParamKeys.FOLDER), data.getInt("max_threads"), data.getInt("max_messages_per_thread"), new GetThreadsCallback(message));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onGetThread(Message message) {
        if (isMessageValid(message, ImmutableList.A07("thread_key", "max_messages"))) {
            Bundle data = message.getData();
            this.mMessengerManager.getThread(data.getLong("thread_key"), data.getInt("max_messages"), new GetThreadCallback(message));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onGetThreads(Message message) {
        if (isMessageValid(message, ImmutableList.A08("max_threads", "max_messages_per_thread", "older_than"))) {
            Bundle data = message.getData();
            this.mMessengerManager.getThreads(data.getInt("max_threads"), data.getInt("max_messages_per_thread"), data.getLong("older_than"), new GetThreadsCallback(message));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onHideThread(Message message) {
        if (isMessageValid(message, ImmutableList.A06("thread_key"))) {
            this.mMessengerManager.hideThread(message.getData().getLong("thread_key"), new MutationCallback(message));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onInit(Message message) {
        String str;
        Messenger messenger = message.replyTo;
        if (messenger != null) {
            this.mClients.add(messenger);
            if (!this.mInitialized) {
                Bundle data = message.getData();
                if (!data.containsKey("user_id")) {
                    str = "Missing initialization param: user_id";
                } else if (TextUtils.isEmpty(data.getString("access_token"))) {
                    str = "Missing initialization param: access_token";
                } else if (TextUtils.isEmpty(data.getString(InitParamKeys.APP_ID))) {
                    str = "Missing initialization param: app_id";
                } else {
                    this.mMessengerManager.init(data.getLong("user_id"), data.getString("access_token"), data.getString(InitParamKeys.APP_ID), DeviceUtils.getDeviceId(this), getFilesDir().toString(), new Executor() {
                        /* class com.oculus.messenger.service.MessengerService.AnonymousClass2 */

                        public void execute(Runnable runnable) {
                            MessengerService.this.mHandler.post(runnable);
                        }
                    });
                    this.mInitialized = true;
                    return;
                }
                sendErrorReply(message, str);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onMarkThreadRead(Message message) {
        if (isMessageValid(message, ImmutableList.A07("thread_key", MarkThreadReadParamKeys.WATERMARK_TIMESTAMP))) {
            Bundle data = message.getData();
            this.mMessengerManager.markThreadRead(data.getLong("thread_key"), data.getLong(MarkThreadReadParamKeys.WATERMARK_TIMESTAMP), new MutationCallback(message));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onMuteThread(Message message) {
        if (isMessageValid(message, ImmutableList.A07("thread_key", MuteThreadParamKeys.MUTE_FOR_S))) {
            Bundle data = message.getData();
            this.mMessengerManager.muteThread(data.getLong("thread_key"), data.getInt(MuteThreadParamKeys.MUTE_FOR_S), new MutationCallback(message));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onRemoveParticipantFromThread(Message message) {
        if (isMessageValid(message, ImmutableList.A07("thread_key", RemoveParticipantFromThreadParamKeys.PARTICIPANT_ID))) {
            Bundle data = message.getData();
            this.mMessengerManager.removeParticipantFromThread(data.getLong("thread_key"), data.getLong(RemoveParticipantFromThreadParamKeys.PARTICIPANT_ID), new MutationCallback(message));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onRetrySendingMessage(Message message) {
        if (isMessageValid(message, ImmutableList.A07("thread_key", RetrySendingMessageParamKeys.OFFLINE_THREADING_ID))) {
            Bundle data = message.getData();
            sendGenericReply(message, this.mMessengerManager.retrySendingMessage(data.getLong("thread_key"), data.getLong(RetrySendingMessageParamKeys.OFFLINE_THREADING_ID)));
        }
    }

    private void sendGenericReply(Message message, boolean z) {
        if (z) {
            sendReply(message.getData().getInt("call_id", -1), message.what, message.replyTo, this.mFbObjectMapper.A04());
        } else {
            sendErrorReply(message, "Failed in Messenger Manager");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendReply(int i, int i2, Messenger messenger, AnonymousClass04L r8) {
        AnonymousClass00r r0;
        Map<String, AbstractC02170iC> map = r8.A00;
        if (i > 10 || i < -1) {
            r0 = new AnonymousClass00r(i);
        } else {
            r0 = AnonymousClass00r.A01[i - -1];
        }
        map.put("callId", r0);
        try {
            String A06 = this.mFbObjectMapper.A06(r8);
            Bundle bundle = new Bundle();
            bundle.putString("result", A06);
            Message obtain = Message.obtain((Handler) null, i2);
            obtain.setData(bundle);
            try {
                messenger.send(obtain);
            } catch (RemoteException unused) {
                this.mClients.remove(messenger);
            }
        } catch (C03620oC unused2) {
        }
    }

    public void onNewMessage(long j, com.oculus.messenger.models.Message message) {
        if (!this.mClients.isEmpty()) {
            AnonymousClass04L A04 = this.mFbObjectMapper.A04();
            Map<String, AbstractC02170iC> map = A04.A00;
            map.put("threadId", new C000200q(j));
            map.put("message", new AnonymousClass04K(message));
            try {
                String A06 = this.mFbObjectMapper.A06(A04);
                Bundle bundle = new Bundle();
                bundle.putString("result", A06);
                Message obtain = Message.obtain((Handler) null, 4);
                obtain.setData(bundle);
                for (Messenger messenger : this.mClients) {
                    try {
                        messenger.send(obtain);
                    } catch (RemoteException unused) {
                        this.mClients.remove(messenger);
                    }
                }
            } catch (C03620oC unused2) {
            }
        }
    }

    public void onThreadDeletion(long j) {
        if (!this.mClients.isEmpty()) {
            AnonymousClass04L A04 = this.mFbObjectMapper.A04();
            A04.A00.put("threadId", new C000200q(j));
            try {
                String A06 = this.mFbObjectMapper.A06(A04);
                Bundle bundle = new Bundle();
                bundle.putString("result", A06);
                Message obtain = Message.obtain((Handler) null, 15);
                obtain.setData(bundle);
                for (Messenger messenger : this.mClients) {
                    try {
                        messenger.send(obtain);
                    } catch (RemoteException unused) {
                        this.mClients.remove(messenger);
                    }
                }
            } catch (C03620oC unused2) {
            }
        }
    }

    public void onThreadUpdate(Thread thread, boolean z) {
        AnonymousClass04V r0;
        if (!this.mClients.isEmpty()) {
            AnonymousClass04L A04 = this.mFbObjectMapper.A04();
            Map<String, AbstractC02170iC> map = A04.A00;
            if (z) {
                r0 = AnonymousClass04V.A02;
            } else {
                r0 = AnonymousClass04V.A01;
            }
            map.put("reloadThread", r0);
            map.put("thread", new AnonymousClass04K(thread));
            try {
                String A06 = this.mFbObjectMapper.A06(A04);
                Bundle bundle = new Bundle();
                bundle.putString("result", A06);
                Message obtain = Message.obtain((Handler) null, 5);
                obtain.setData(bundle);
                for (Messenger messenger : this.mClients) {
                    try {
                        messenger.send(obtain);
                    } catch (RemoteException unused) {
                        this.mClients.remove(messenger);
                    }
                }
            } catch (C03620oC unused2) {
            }
        }
    }

    public void onUnreadThreadCountsUpdate(Map<String, Long> map) {
        if (!this.mClients.isEmpty()) {
            AnonymousClass04L A04 = this.mFbObjectMapper.A04();
            AnonymousClass04X r6 = new AnonymousClass04X(((AnonymousClass0Ck) A04).A00);
            A04.A00.put("counts", r6);
            if (map != null) {
                for (Map.Entry<String, Long> entry : map.entrySet()) {
                    AnonymousClass04L r2 = new AnonymousClass04L(((AnonymousClass0Ck) r6).A00);
                    r6.A00.add(r2);
                    r2.A03(GetNonInboxThreadsParamKeys.FOLDER, entry.getKey());
                    Long value = entry.getValue();
                    if (value == null) {
                        r2.A00.put(ReviewsRequest.KEY_COUNT, AnonymousClass04S.A00);
                    } else {
                        r2.A00.put(ReviewsRequest.KEY_COUNT, new C000200q(value.longValue()));
                    }
                }
            }
            try {
                String A06 = this.mFbObjectMapper.A06(A04);
                Bundle bundle = new Bundle();
                bundle.putString("result", A06);
                Message obtain = Message.obtain((Handler) null, 20);
                obtain.setData(bundle);
                for (Messenger messenger : this.mClients) {
                    try {
                        messenger.send(obtain);
                    } catch (RemoteException unused) {
                        this.mClients.remove(messenger);
                    }
                }
            } catch (C03620oC unused2) {
            }
        }
    }

    public MessengerService() {
        IncomingHandler incomingHandler = new IncomingHandler();
        this.mHandler = incomingHandler;
        this.mMessenger = new Messenger(incomingHandler);
    }

    public static final void _UL_injectMe(Context context, MessengerService messengerService) {
        _UL_staticInjectMe(AnonymousClass0VF.get(context), messengerService);
    }

    public static /* synthetic */ String access$100() {
        return TAG;
    }

    public void onCreate() {
        getBaseContext().getPackageName();
        sActive = false;
        this.mInitialized = false;
        this.mClients = new HashSet();
        _UL_injectMe(this, this);
        MessengerManagerJNI messengerManagerJNI = new MessengerManagerJNI((String) AnonymousClass0VF.A03(1, 62, this._UL_mInjectionContext));
        this.mMessengerManager = messengerManagerJNI;
        messengerManagerJNI.mCallback = new MessengerManagerJNI.Callback() {
            /* class com.oculus.messenger.service.MessengerService.AnonymousClass1 */

            @Override // com.oculus.messenger.manager.MessengerManagerJNI.Callback
            public void onNewMessage(long j, com.oculus.messenger.models.Message message) {
                MessengerService.this.onNewMessage(j, message);
            }

            @Override // com.oculus.messenger.manager.MessengerManagerJNI.Callback
            public void onThreadDeletion(long j) {
                MessengerService.this.onThreadDeletion(j);
            }

            @Override // com.oculus.messenger.manager.MessengerManagerJNI.Callback
            public void onThreadUpdate(Thread thread, boolean z) {
                MessengerService.this.onThreadUpdate(thread, z);
            }

            @Override // com.oculus.messenger.manager.MessengerManagerJNI.Callback
            public void onUnreadThreadCountsUpdate(Map<String, Long> map) {
                MessengerService.this.onUnreadThreadCountsUpdate(map);
            }
        };
        getBaseContext().getPackageName();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendErrorReply(int i, Messenger messenger, String str) {
        AnonymousClass04L A04 = this.mFbObjectMapper.A04();
        A04.A03("message", str);
        sendReply(i, -1, messenger, A04);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendErrorReply(Message message, String str) {
        sendErrorReply(message.getData().getInt("call_id", -1), message.replyTo, str);
    }
}
