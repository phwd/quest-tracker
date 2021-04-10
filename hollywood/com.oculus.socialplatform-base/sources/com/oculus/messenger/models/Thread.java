package com.oculus.messenger.models;

import androidx.core.app.NotificationCompat$CarExtender;
import com.facebook.common.json.AutoGenJsonSerializer;
import com.facebook.proguard.annotations.DoNotStripAny;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oculus.messenger.service.MessengerService;

@AutoGenJsonSerializer
@DoNotStripAny
public class Thread {
    @JsonProperty(MessengerService.GetNonInboxThreadsParamKeys.FOLDER)
    public final String mFolder;
    @JsonProperty("isArchived")
    public final boolean mIsArchived;
    @JsonProperty("isUnread")
    public final boolean mIsUnread;
    @JsonProperty(NotificationCompat$CarExtender.KEY_MESSAGES)
    public final Message[] mMessages;
    @JsonProperty("muteExpireTime")
    public final long mMuteExpireTime;
    @JsonProperty("name")
    public final String mName;
    @JsonProperty(NotificationCompat$CarExtender.KEY_PARTICIPANTS)
    public final Participant[] mParticipants;
    @JsonProperty("threadId")
    public final long mThreadKey;
    @JsonProperty("updatedTimestamp")
    public final long mUpdatedTime;

    public Thread(long j, String str, long j2, long j3, boolean z, boolean z2, String str2, Message[] messageArr, Participant[] participantArr) {
        this.mThreadKey = j;
        this.mName = str;
        this.mUpdatedTime = j2;
        this.mMuteExpireTime = j3;
        this.mIsArchived = z;
        this.mIsUnread = z2;
        this.mFolder = str2;
        this.mMessages = messageArr;
        this.mParticipants = participantArr;
    }

    public String getFolder() {
        return this.mFolder;
    }

    public boolean getIsArchived() {
        return this.mIsArchived;
    }

    public boolean getIsUnread() {
        return this.mIsUnread;
    }

    public Message[] getMessages() {
        return this.mMessages;
    }

    public long getMuteExpireTime() {
        return this.mMuteExpireTime;
    }

    public String getName() {
        return this.mName;
    }

    public Participant[] getParticipants() {
        return this.mParticipants;
    }

    public long getThreadKey() {
        return this.mThreadKey;
    }

    public long getUpdatedTime() {
        return this.mUpdatedTime;
    }
}
