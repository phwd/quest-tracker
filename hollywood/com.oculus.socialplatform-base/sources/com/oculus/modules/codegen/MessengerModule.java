package com.oculus.modules.codegen;

import android.util.Pair;
import androidx.core.app.NotificationCompat$CarExtender;
import com.oculus.horizon.api.rating.ReviewsRequest;
import com.oculus.messenger.service.MessengerService;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class MessengerModule extends RCTBaseJavaModule {
    public static final String MODULE_NAME = "MessengerModule";

    public static class Attachment extends NativeModuleParcel {
        public String attachmentId;
        public String attachmentType;

        public static final Attachment makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            Attachment attachment = new Attachment();
            attachment.setFromJSONObject(jSONObject);
            return attachment;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("attachmentId", this.attachmentId);
                jSONObject.put("attachmentType", this.attachmentType);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.attachmentId = jSONObject.optString("attachmentId");
            this.attachmentType = jSONObject.optString("attachmentType");
        }
    }

    public static class CallbackData extends NativeModuleParcel {
        public List<UnreadThreadCount> counts;
        public SingleMessage message;
        public Boolean reloadThread;
        public Thread thread;
        public String threadId;
        public String type;

        public static final CallbackData makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            CallbackData callbackData = new CallbackData();
            callbackData.setFromJSONObject(jSONObject);
            return callbackData;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                List<UnreadThreadCount> list = this.counts;
                if (list != null) {
                    jSONObject.put("counts", NativeModuleParcel.convertParcelListToJSONArray(list));
                }
                SingleMessage singleMessage = this.message;
                if (singleMessage != null) {
                    jSONObject.put("message", singleMessage.convertToJSONObject());
                }
                Boolean bool = this.reloadThread;
                if (bool != null) {
                    jSONObject.put("reloadThread", bool);
                }
                Thread thread2 = this.thread;
                if (thread2 != null) {
                    jSONObject.put("thread", thread2.convertToJSONObject());
                }
                String str = this.threadId;
                if (str != null) {
                    jSONObject.put("threadId", str);
                }
                jSONObject.put("type", this.type);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            ArrayList convertJSONArrayToParcelList;
            SingleMessage makeFromJSONObject;
            Boolean valueOf;
            Thread makeFromJSONObject2;
            String str = null;
            if (jSONObject.isNull("counts")) {
                convertJSONArrayToParcelList = null;
            } else {
                convertJSONArrayToParcelList = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("counts"), UnreadThreadCount.class);
            }
            this.counts = convertJSONArrayToParcelList;
            if (jSONObject.isNull("message")) {
                makeFromJSONObject = null;
            } else {
                makeFromJSONObject = SingleMessage.makeFromJSONObject(jSONObject.optJSONObject("message"));
            }
            this.message = makeFromJSONObject;
            if (jSONObject.isNull("reloadThread")) {
                valueOf = null;
            } else {
                valueOf = Boolean.valueOf(jSONObject.optBoolean("reloadThread"));
            }
            this.reloadThread = valueOf;
            if (jSONObject.isNull("thread")) {
                makeFromJSONObject2 = null;
            } else {
                makeFromJSONObject2 = Thread.makeFromJSONObject(jSONObject.optJSONObject("thread"));
            }
            this.thread = makeFromJSONObject2;
            if (!jSONObject.isNull("threadId")) {
                str = jSONObject.optString("threadId");
            }
            this.threadId = str;
            this.type = jSONObject.optString("type");
        }
    }

    public static class MessageList extends NativeModuleParcel {
        public List<SingleMessage> messages;
        public boolean reachedEndOfThread;
        public String threadId;

        public static final MessageList makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            MessageList messageList = new MessageList();
            messageList.setFromJSONObject(jSONObject);
            return messageList;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(NotificationCompat$CarExtender.KEY_MESSAGES, NativeModuleParcel.convertParcelListToJSONArray(this.messages));
                jSONObject.put("reachedEndOfThread", this.reachedEndOfThread);
                jSONObject.put("threadId", this.threadId);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.messages = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray(NotificationCompat$CarExtender.KEY_MESSAGES), SingleMessage.class);
            this.reachedEndOfThread = jSONObject.optBoolean("reachedEndOfThread");
            this.threadId = jSONObject.optString("threadId");
        }
    }

    public static class Participant extends NativeModuleParcel {
        public double actionTimestamp;
        public String alias;
        public String participantId;
        public double watermarkTimestamp;

        public static final Participant makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            Participant participant = new Participant();
            participant.setFromJSONObject(jSONObject);
            return participant;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("actionTimestamp", this.actionTimestamp);
                jSONObject.put("alias", this.alias);
                jSONObject.put("participantId", this.participantId);
                jSONObject.put("watermarkTimestamp", this.watermarkTimestamp);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.actionTimestamp = jSONObject.optDouble("actionTimestamp", 0.0d);
            this.alias = jSONObject.optString("alias");
            this.participantId = jSONObject.optString("participantId");
            this.watermarkTimestamp = jSONObject.optDouble("watermarkTimestamp", 0.0d);
        }
    }

    public static class SingleMessage extends NativeModuleParcel {
        public List<Attachment> attachments;
        public String body;
        public boolean isSending;
        public String messageId;
        public String offlineThreadingId;
        public String senderId;
        public String status;
        public double timestamp;
        public String type;

        public static final SingleMessage makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            SingleMessage singleMessage = new SingleMessage();
            singleMessage.setFromJSONObject(jSONObject);
            return singleMessage;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("attachments", NativeModuleParcel.convertParcelListToJSONArray(this.attachments));
                jSONObject.put("body", this.body);
                jSONObject.put("isSending", this.isSending);
                jSONObject.put("messageId", this.messageId);
                jSONObject.put("offlineThreadingId", this.offlineThreadingId);
                jSONObject.put("senderId", this.senderId);
                jSONObject.put("status", this.status);
                jSONObject.put(NotificationCompat$CarExtender.KEY_TIMESTAMP, this.timestamp);
                jSONObject.put("type", this.type);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.attachments = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("attachments"), Attachment.class);
            this.body = jSONObject.optString("body");
            this.isSending = jSONObject.optBoolean("isSending");
            this.messageId = jSONObject.optString("messageId");
            this.offlineThreadingId = jSONObject.optString("offlineThreadingId");
            this.senderId = jSONObject.optString("senderId");
            this.status = jSONObject.optString("status");
            this.timestamp = jSONObject.optDouble(NotificationCompat$CarExtender.KEY_TIMESTAMP, 0.0d);
            this.type = jSONObject.optString("type");
        }
    }

    public static class Thread extends NativeModuleParcel {
        public String folder;
        public boolean isArchived;
        public List<SingleMessage> messages;
        public double muteExpireTime;
        public String name;
        public List<Participant> participants;
        public String threadId;
        public double updatedTimestamp;

        public static final Thread makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            Thread thread = new Thread();
            thread.setFromJSONObject(jSONObject);
            return thread;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(MessengerService.GetNonInboxThreadsParamKeys.FOLDER, this.folder);
                jSONObject.put("isArchived", this.isArchived);
                jSONObject.put(NotificationCompat$CarExtender.KEY_MESSAGES, NativeModuleParcel.convertParcelListToJSONArray(this.messages));
                jSONObject.put("muteExpireTime", this.muteExpireTime);
                jSONObject.put("name", this.name);
                jSONObject.put(NotificationCompat$CarExtender.KEY_PARTICIPANTS, NativeModuleParcel.convertParcelListToJSONArray(this.participants));
                jSONObject.put("threadId", this.threadId);
                jSONObject.put("updatedTimestamp", this.updatedTimestamp);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.folder = jSONObject.optString(MessengerService.GetNonInboxThreadsParamKeys.FOLDER);
            this.isArchived = jSONObject.optBoolean("isArchived");
            this.messages = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray(NotificationCompat$CarExtender.KEY_MESSAGES), SingleMessage.class);
            this.muteExpireTime = jSONObject.optDouble("muteExpireTime", 0.0d);
            this.name = jSONObject.optString("name");
            this.participants = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray(NotificationCompat$CarExtender.KEY_PARTICIPANTS), Participant.class);
            this.threadId = jSONObject.optString("threadId");
            this.updatedTimestamp = jSONObject.optDouble("updatedTimestamp", 0.0d);
        }
    }

    public static class ThreadList extends NativeModuleParcel {
        public boolean reachedEndOfThreads;
        public List<Thread> threads;

        public static final ThreadList makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            ThreadList threadList = new ThreadList();
            threadList.setFromJSONObject(jSONObject);
            return threadList;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("reachedEndOfThreads", this.reachedEndOfThreads);
                jSONObject.put("threads", NativeModuleParcel.convertParcelListToJSONArray(this.threads));
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.reachedEndOfThreads = jSONObject.optBoolean("reachedEndOfThreads");
            this.threads = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("threads"), Thread.class);
        }
    }

    public static class UnreadThreadCount extends NativeModuleParcel {
        public double count;
        public String folder;

        public static final UnreadThreadCount makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            UnreadThreadCount unreadThreadCount = new UnreadThreadCount();
            unreadThreadCount.setFromJSONObject(jSONObject);
            return unreadThreadCount;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(ReviewsRequest.KEY_COUNT, this.count);
                jSONObject.put(MessengerService.GetNonInboxThreadsParamKeys.FOLDER, this.folder);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.count = jSONObject.optDouble(ReviewsRequest.KEY_COUNT, 0.0d);
            this.folder = jSONObject.optString(MessengerService.GetNonInboxThreadsParamKeys.FOLDER);
        }
    }

    public static class UnreadThreadCounts extends NativeModuleParcel {
        public List<UnreadThreadCount> counts;

        public static final UnreadThreadCounts makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            UnreadThreadCounts unreadThreadCounts = new UnreadThreadCounts();
            unreadThreadCounts.setFromJSONObject(jSONObject);
            return unreadThreadCounts;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("counts", NativeModuleParcel.convertParcelListToJSONArray(this.counts));
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.counts = NativeModuleParcel.convertJSONArrayToParcelList(jSONObject.optJSONArray("counts"), UnreadThreadCount.class);
        }
    }

    public static class UserIdsWrapper extends NativeModuleParcel {
        public List<String> userIds;

        public static final UserIdsWrapper makeFromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            UserIdsWrapper userIdsWrapper = new UserIdsWrapper();
            userIdsWrapper.setFromJSONObject(jSONObject);
            return userIdsWrapper;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("userIds", NativeModuleParcel.convertStringListToJSONArray(this.userIds));
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject jSONObject) {
            this.userIds = NativeModuleParcel.convertJSONArrayToStringList(jSONObject.optJSONArray("userIds"));
        }
    }

    public abstract void addParticipantsImpl(String str, UserIdsWrapper userIdsWrapper, Resolver<Void> resolver);

    public abstract void bindServiceImpl();

    public abstract void createThreadImpl(UserIdsWrapper userIdsWrapper, String str, Resolver<Thread> resolver);

    public abstract void deleteThreadImpl(String str, Resolver<Void> resolver);

    public abstract void hideThreadImpl(String str, Resolver<Void> resolver);

    public abstract void markThreadReadImpl(String str, double d, Resolver<Void> resolver);

    public final String marshallJSConstants() {
        return null;
    }

    public abstract void muteThreadImpl(String str, double d, Resolver<Void> resolver);

    public abstract void removeParticipantImpl(String str, String str2, Resolver<Void> resolver);

    public abstract void requestMessagesImpl(String str, double d, double d2, Resolver<MessageList> resolver);

    public abstract void requestNonInboxMessagesImpl(String str, double d, Resolver<MessageList> resolver);

    public abstract void requestNonInboxThreadsImpl(String str, double d, double d2, Resolver<ThreadList> resolver);

    public abstract void requestThreadImpl(String str, double d, Resolver<Thread> resolver);

    public abstract void requestThreadsImpl(double d, double d2, double d3, Resolver<ThreadList> resolver);

    public abstract void requestUnreadThreadCountsImpl(Resolver<UnreadThreadCounts> resolver);

    public abstract void retrySendingMessageImpl(String str, String str2, Resolver<Void> resolver);

    public abstract void sendMessageImpl(String str, String str2, Resolver<Void> resolver);

    public void shutdownModule() {
    }

    public abstract void unbindServiceImpl();

    public abstract void unmuteThreadImpl(String str, Resolver<Void> resolver);

    public static final List<Pair<String, String>> describe() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair("addParticipants", "+sjii"));
        arrayList.add(new Pair("bindService", ""));
        arrayList.add(new Pair("createThread", "+jsii"));
        arrayList.add(new Pair("deleteThread", "+sii"));
        arrayList.add(new Pair("hideThread", "+sii"));
        arrayList.add(new Pair("markThreadRead", "+sdii"));
        arrayList.add(new Pair("muteThread", "+sdii"));
        arrayList.add(new Pair("removeParticipant", "+ssii"));
        arrayList.add(new Pair("requestMessages", "+sddii"));
        arrayList.add(new Pair("requestNonInboxMessages", "+sdii"));
        arrayList.add(new Pair("requestNonInboxThreads", "+sddii"));
        arrayList.add(new Pair("requestThread", "+sdii"));
        arrayList.add(new Pair("requestThreads", "+dddii"));
        arrayList.add(new Pair("requestUnreadThreadCounts", "+ii"));
        arrayList.add(new Pair("retrySendingMessage", "+ssii"));
        arrayList.add(new Pair("sendMessage", "+ssii"));
        arrayList.add(new Pair("unbindService", ""));
        arrayList.add(new Pair("unmuteThread", "+sii"));
        return arrayList;
    }

    public final void emitOnMessengerEvent(CallbackData callbackData) {
        RCTBaseJavaModule.nativeEmitEventWithJsonData(this.RVRCtxTag, "MessengerModule_onMessengerEvent", String.valueOf(callbackData.convertToJSONObject()));
    }

    public final void addParticipants(String str, JSONObject jSONObject, int i, int i2) {
        addParticipantsImpl(str, UserIdsWrapper.makeFromJSONObject(jSONObject), ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void bindService() {
        bindServiceImpl();
    }

    public final void createThread(JSONObject jSONObject, String str, int i, int i2) {
        createThreadImpl(UserIdsWrapper.makeFromJSONObject(jSONObject), str, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void deleteThread(String str, int i, int i2) {
        deleteThreadImpl(str, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final String getModuleName() {
        return MODULE_NAME;
    }

    public final void hideThread(String str, int i, int i2) {
        hideThreadImpl(str, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void markThreadRead(String str, double d, int i, int i2) {
        markThreadReadImpl(str, d, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void muteThread(String str, double d, int i, int i2) {
        muteThreadImpl(str, d, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void removeParticipant(String str, String str2, int i, int i2) {
        removeParticipantImpl(str, str2, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void requestMessages(String str, double d, double d2, int i, int i2) {
        requestMessagesImpl(str, d, d2, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void requestNonInboxMessages(String str, double d, int i, int i2) {
        requestNonInboxMessagesImpl(str, d, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void requestNonInboxThreads(String str, double d, double d2, int i, int i2) {
        requestNonInboxThreadsImpl(str, d, d2, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void requestThread(String str, double d, int i, int i2) {
        requestThreadImpl(str, d, new NullableResolverImpl(ResolverFactory.createParcelResolver(this, i, i2), this, i, i2));
    }

    public final void requestThreads(double d, double d2, double d3, int i, int i2) {
        requestThreadsImpl(d, d2, d3, ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void requestUnreadThreadCounts(int i, int i2) {
        requestUnreadThreadCountsImpl(ResolverFactory.createParcelResolver(this, i, i2));
    }

    public final void retrySendingMessage(String str, String str2, int i, int i2) {
        retrySendingMessageImpl(str, str2, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void sendMessage(String str, String str2, int i, int i2) {
        sendMessageImpl(str, str2, ResolverFactory.createVoidResolver(this, i, i2));
    }

    public final void unbindService() {
        unbindServiceImpl();
    }

    public final void unmuteThread(String str, int i, int i2) {
        unmuteThreadImpl(str, ResolverFactory.createVoidResolver(this, i, i2));
    }
}
