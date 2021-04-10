package com.oculus.messenger.models;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AnonymousClass0IH;
import X.AnonymousClass0Iq;
import androidx.core.app.NotificationCompat$CarExtender;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.oculus.messenger.service.MessengerService;
import java.io.IOException;

public class ThreadSerializer extends JsonSerializer<Thread> {
    static {
        AnonymousClass0IH.A06.putIfAbsent(Thread.class, new ThreadSerializer());
    }

    public static void serializeFields(Thread thread, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException {
        long j = thread.mThreadKey;
        r4.A0R("threadId");
        r4.A0N(j);
        AnonymousClass0Iq.A01(r4, "name", thread.mName);
        long j2 = thread.mUpdatedTime;
        r4.A0R("updatedTimestamp");
        r4.A0N(j2);
        long j3 = thread.mMuteExpireTime;
        r4.A0R("muteExpireTime");
        r4.A0N(j3);
        boolean z = thread.mIsArchived;
        r4.A0R("isArchived");
        r4.A0Y(z);
        boolean z2 = thread.mIsUnread;
        r4.A0R("isUnread");
        r4.A0Y(z2);
        AnonymousClass0Iq.A01(r4, MessengerService.GetNonInboxThreadsParamKeys.FOLDER, thread.mFolder);
        Message[] messageArr = thread.mMessages;
        if (messageArr != null) {
            r4.A0R(NotificationCompat$CarExtender.KEY_MESSAGES);
            AnonymousClass0Iq.A00(r4, r5, messageArr);
        }
        Participant[] participantArr = thread.mParticipants;
        if (participantArr != null) {
            r4.A0R(NotificationCompat$CarExtender.KEY_PARTICIPANTS);
            AnonymousClass0Iq.A00(r4, r5, participantArr);
        }
    }

    public void serialize(Thread thread, AbstractC02300iS r2, AbstractC02120i3 r3) throws IOException {
        if (thread == null) {
            r2.A0G();
        }
        r2.A0I();
        serializeFields(thread, r2, r3);
        r2.A0F();
    }
}
