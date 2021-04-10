package com.oculus.modules.codegen;

import android.util.Pair;
import com.oculus.panellib.modules.RCTBaseJavaModule;
import com.oculus.provider.OculusContent;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class MessengerModule extends RCTBaseJavaModule {
    protected static final String MODULE_NAME = MessengerModule.class.getSimpleName();

    /* access modifiers changed from: protected */
    public abstract void addParticipantsImpl(String str, UserIdsWrapper userIdsWrapper, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void bindServiceImpl();

    /* access modifiers changed from: protected */
    public abstract void createThreadImpl(UserIdsWrapper userIdsWrapper, String str, Resolver<Thread> resolver);

    /* access modifiers changed from: protected */
    public abstract void deleteThreadImpl(String str, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void hideThreadImpl(String str, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void markThreadReadImpl(String str, double d, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void muteThreadImpl(String str, double d, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void removeParticipantImpl(String str, String str2, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void requestMessagesImpl(String str, double d, double d2, Resolver<MessageList> resolver);

    /* access modifiers changed from: protected */
    public abstract void requestNonInboxMessagesImpl(String str, double d, Resolver<MessageList> resolver);

    /* access modifiers changed from: protected */
    public abstract void requestNonInboxThreadsImpl(String str, double d, double d2, Resolver<ThreadList> resolver);

    /* access modifiers changed from: protected */
    public abstract void requestThreadImpl(String str, double d, Resolver<Thread> resolver);

    /* access modifiers changed from: protected */
    public abstract void requestThreadsImpl(double d, double d2, double d3, Resolver<ThreadList> resolver);

    /* access modifiers changed from: protected */
    public abstract void requestUnreadThreadCountsImpl(Resolver<UnreadThreadCounts> resolver);

    /* access modifiers changed from: protected */
    public abstract void retrySendingMessageImpl(String str, String str2, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void sendMessageImpl(String str, String str2, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public abstract void unbindServiceImpl();

    /* access modifiers changed from: protected */
    public abstract void unmuteThreadImpl(String str, Resolver<Void> resolver);

    /* access modifiers changed from: protected */
    public final String getModuleName() {
        return MODULE_NAME;
    }

    protected static final List<Pair<String, String>> describe() {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<>("addParticipants", "+sjii"));
        list.add(new Pair<>("bindService", ""));
        list.add(new Pair<>("createThread", "+jsii"));
        list.add(new Pair<>("deleteThread", "+sii"));
        list.add(new Pair<>("hideThread", "+sii"));
        list.add(new Pair<>("markThreadRead", "+sdii"));
        list.add(new Pair<>("muteThread", "+sdii"));
        list.add(new Pair<>("removeParticipant", "+ssii"));
        list.add(new Pair<>("requestMessages", "+sddii"));
        list.add(new Pair<>("requestNonInboxMessages", "+sdii"));
        list.add(new Pair<>("requestNonInboxThreads", "+sddii"));
        list.add(new Pair<>("requestThread", "+sdii"));
        list.add(new Pair<>("requestThreads", "+dddii"));
        list.add(new Pair<>("requestUnreadThreadCounts", "+ii"));
        list.add(new Pair<>("retrySendingMessage", "+ssii"));
        list.add(new Pair<>("sendMessage", "+ssii"));
        list.add(new Pair<>("unbindService", ""));
        list.add(new Pair<>("unmuteThread", "+sii"));
        return list;
    }

    /* access modifiers changed from: protected */
    public final String marshallJSConstants() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final void emitOnMessengerEvent(CallbackData data) {
        nativeEmitEventWithJsonData(this.RVRCtxTag, "MessengerModule_onMessengerEvent", String.valueOf(data.convertToJSONObject()));
    }

    /* access modifiers changed from: protected */
    public final void addParticipants(String threadId, JSONObject userIds, int resolveID, int rejectID) {
        addParticipantsImpl(threadId, UserIdsWrapper.makeFromJSONObject(userIds), ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void bindService() {
        bindServiceImpl();
    }

    /* access modifiers changed from: protected */
    public final void createThread(JSONObject otherParticipantIds, String message, int resolveID, int rejectID) {
        createThreadImpl(UserIdsWrapper.makeFromJSONObject(otherParticipantIds), message, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void deleteThread(String threadId, int resolveID, int rejectID) {
        deleteThreadImpl(threadId, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void hideThread(String threadId, int resolveID, int rejectID) {
        hideThreadImpl(threadId, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void markThreadRead(String threadId, double watermarkTimestamp, int resolveID, int rejectID) {
        markThreadReadImpl(threadId, watermarkTimestamp, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void muteThread(String threadId, double muteDurationInSec, int resolveID, int rejectID) {
        muteThreadImpl(threadId, muteDurationInSec, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void removeParticipant(String threadId, String userId, int resolveID, int rejectID) {
        removeParticipantImpl(threadId, userId, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void requestMessages(String threadId, double maxMessages, double olderThan, int resolveID, int rejectID) {
        requestMessagesImpl(threadId, maxMessages, olderThan, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void requestNonInboxMessages(String threadId, double maxMessages, int resolveID, int rejectID) {
        requestNonInboxMessagesImpl(threadId, maxMessages, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void requestNonInboxThreads(String folder, double maxThreads, double maxMessagesPerThread, int resolveID, int rejectID) {
        requestNonInboxThreadsImpl(folder, maxThreads, maxMessagesPerThread, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void requestThread(String threadId, double maxMessages, int resolveID, int rejectID) {
        requestThreadImpl(threadId, maxMessages, ResolverFactory.createNullableResolver(ResolverFactory.createParcelResolver(this, resolveID, rejectID), this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void requestThreads(double maxThreads, double maxMessagesPerThread, double olderThan, int resolveID, int rejectID) {
        requestThreadsImpl(maxThreads, maxMessagesPerThread, olderThan, ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void requestUnreadThreadCounts(int resolveID, int rejectID) {
        requestUnreadThreadCountsImpl(ResolverFactory.createParcelResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void retrySendingMessage(String threadId, String offlineThreadingId, int resolveID, int rejectID) {
        retrySendingMessageImpl(threadId, offlineThreadingId, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void sendMessage(String threadId, String message, int resolveID, int rejectID) {
        sendMessageImpl(threadId, message, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    /* access modifiers changed from: protected */
    public final void unbindService() {
        unbindServiceImpl();
    }

    /* access modifiers changed from: protected */
    public final void unmuteThread(String threadId, int resolveID, int rejectID) {
        unmuteThreadImpl(threadId, ResolverFactory.createVoidResolver(this, resolveID, rejectID));
    }

    public void shutdownModule() {
    }

    public static class CallbackData extends NativeModuleParcel {
        public List<UnreadThreadCount> counts;
        public SingleMessage message;
        public Boolean reloadThread;
        public Thread thread;
        public String threadId;
        public String type;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                if (this.counts != null) {
                    parcel.put("counts", NativeModuleParcel.convertParcelListToJSONArray(this.counts));
                }
                if (this.message != null) {
                    parcel.put("message", this.message.convertToJSONObject());
                }
                if (this.reloadThread != null) {
                    parcel.put("reloadThread", this.reloadThread);
                }
                if (this.thread != null) {
                    parcel.put("thread", this.thread.convertToJSONObject());
                }
                if (this.threadId != null) {
                    parcel.put("threadId", this.threadId);
                }
                parcel.put("type", this.type);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            String str = null;
            this.counts = json.isNull("counts") ? null : NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("counts"), UnreadThreadCount.class);
            this.message = json.isNull("message") ? null : SingleMessage.makeFromJSONObject(json.optJSONObject("message"));
            this.reloadThread = json.isNull("reloadThread") ? null : Boolean.valueOf(json.optBoolean("reloadThread"));
            this.thread = json.isNull("thread") ? null : Thread.makeFromJSONObject(json.optJSONObject("thread"));
            if (!json.isNull("threadId")) {
                str = json.optString("threadId");
            }
            this.threadId = str;
            this.type = json.optString("type");
        }

        public static final CallbackData makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            CallbackData result = new CallbackData();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class UnreadThreadCount extends NativeModuleParcel {
        public double count;
        public String folder;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("count", this.count);
                parcel.put("folder", this.folder);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.count = json.optDouble("count", 0.0d);
            this.folder = json.optString("folder");
        }

        public static final UnreadThreadCount makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            UnreadThreadCount result = new UnreadThreadCount();
            result.setFromJSONObject(json);
            return result;
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

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("attachments", NativeModuleParcel.convertParcelListToJSONArray(this.attachments));
                parcel.put(OculusContent.FriendList.MESSAGE_BODY_VALUE, this.body);
                parcel.put("isSending", this.isSending);
                parcel.put("messageId", this.messageId);
                parcel.put("offlineThreadingId", this.offlineThreadingId);
                parcel.put("senderId", this.senderId);
                parcel.put("status", this.status);
                parcel.put("timestamp", this.timestamp);
                parcel.put("type", this.type);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.attachments = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("attachments"), Attachment.class);
            this.body = json.optString(OculusContent.FriendList.MESSAGE_BODY_VALUE);
            this.isSending = json.optBoolean("isSending");
            this.messageId = json.optString("messageId");
            this.offlineThreadingId = json.optString("offlineThreadingId");
            this.senderId = json.optString("senderId");
            this.status = json.optString("status");
            this.timestamp = json.optDouble("timestamp", 0.0d);
            this.type = json.optString("type");
        }

        public static final SingleMessage makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            SingleMessage result = new SingleMessage();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class Attachment extends NativeModuleParcel {
        public String attachmentId;
        public String attachmentType;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("attachmentId", this.attachmentId);
                parcel.put("attachmentType", this.attachmentType);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.attachmentId = json.optString("attachmentId");
            this.attachmentType = json.optString("attachmentType");
        }

        public static final Attachment makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            Attachment result = new Attachment();
            result.setFromJSONObject(json);
            return result;
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

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("folder", this.folder);
                parcel.put("isArchived", this.isArchived);
                parcel.put("messages", NativeModuleParcel.convertParcelListToJSONArray(this.messages));
                parcel.put("muteExpireTime", this.muteExpireTime);
                parcel.put("name", this.name);
                parcel.put("participants", NativeModuleParcel.convertParcelListToJSONArray(this.participants));
                parcel.put("threadId", this.threadId);
                parcel.put("updatedTimestamp", this.updatedTimestamp);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.folder = json.optString("folder");
            this.isArchived = json.optBoolean("isArchived");
            this.messages = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("messages"), SingleMessage.class);
            this.muteExpireTime = json.optDouble("muteExpireTime", 0.0d);
            this.name = json.optString("name");
            this.participants = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("participants"), Participant.class);
            this.threadId = json.optString("threadId");
            this.updatedTimestamp = json.optDouble("updatedTimestamp", 0.0d);
        }

        public static final Thread makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            Thread result = new Thread();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class Participant extends NativeModuleParcel {
        public double actionTimestamp;
        public String alias;
        public String participantId;
        public double watermarkTimestamp;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("actionTimestamp", this.actionTimestamp);
                parcel.put(OculusContent.FriendList.ALIAS_COLUMN, this.alias);
                parcel.put("participantId", this.participantId);
                parcel.put("watermarkTimestamp", this.watermarkTimestamp);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.actionTimestamp = json.optDouble("actionTimestamp", 0.0d);
            this.alias = json.optString(OculusContent.FriendList.ALIAS_COLUMN);
            this.participantId = json.optString("participantId");
            this.watermarkTimestamp = json.optDouble("watermarkTimestamp", 0.0d);
        }

        public static final Participant makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            Participant result = new Participant();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class UserIdsWrapper extends NativeModuleParcel {
        public List<String> userIds;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("userIds", NativeModuleParcel.convertStringListToJSONArray(this.userIds));
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.userIds = NativeModuleParcel.convertJSONArrayToStringList(json.optJSONArray("userIds"));
        }

        public static final UserIdsWrapper makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            UserIdsWrapper result = new UserIdsWrapper();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class MessageList extends NativeModuleParcel {
        public List<SingleMessage> messages;
        public boolean reachedEndOfThread;
        public String threadId;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("messages", NativeModuleParcel.convertParcelListToJSONArray(this.messages));
                parcel.put("reachedEndOfThread", this.reachedEndOfThread);
                parcel.put("threadId", this.threadId);
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.messages = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("messages"), SingleMessage.class);
            this.reachedEndOfThread = json.optBoolean("reachedEndOfThread");
            this.threadId = json.optString("threadId");
        }

        public static final MessageList makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            MessageList result = new MessageList();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class ThreadList extends NativeModuleParcel {
        public boolean reachedEndOfThreads;
        public List<Thread> threads;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("reachedEndOfThreads", this.reachedEndOfThreads);
                parcel.put("threads", NativeModuleParcel.convertParcelListToJSONArray(this.threads));
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.reachedEndOfThreads = json.optBoolean("reachedEndOfThreads");
            this.threads = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("threads"), Thread.class);
        }

        public static final ThreadList makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            ThreadList result = new ThreadList();
            result.setFromJSONObject(json);
            return result;
        }
    }

    public static class UnreadThreadCounts extends NativeModuleParcel {
        public List<UnreadThreadCount> counts;

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final JSONObject convertToJSONObject() {
            JSONObject parcel = new JSONObject();
            try {
                parcel.put("counts", NativeModuleParcel.convertParcelListToJSONArray(this.counts));
            } catch (JSONException e) {
            }
            return parcel;
        }

        @Override // com.oculus.modules.codegen.NativeModuleParcel
        public final void setFromJSONObject(JSONObject json) {
            this.counts = NativeModuleParcel.convertJSONArrayToParcelList(json.optJSONArray("counts"), UnreadThreadCount.class);
        }

        public static final UnreadThreadCounts makeFromJSONObject(JSONObject json) {
            if (json == null) {
                return null;
            }
            UnreadThreadCounts result = new UnreadThreadCounts();
            result.setFromJSONObject(json);
            return result;
        }
    }
}
