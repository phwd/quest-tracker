package com.oculus.messenger.models;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AnonymousClass0IH;
import X.AnonymousClass0Iq;
import androidx.core.app.NotificationCompat$CarExtender;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;

public class MessageSerializer extends JsonSerializer<Message> {
    static {
        AnonymousClass0IH.A06.putIfAbsent(Message.class, new MessageSerializer());
    }

    public static void serializeFields(Message message, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException {
        AnonymousClass0Iq.A01(r4, "body", message.mBody);
        AnonymousClass0Iq.A01(r4, "messageId", message.mMessageId);
        long j = message.mActorId;
        r4.A0R("senderId");
        r4.A0N(j);
        long j2 = message.mTimestamp;
        r4.A0R(NotificationCompat$CarExtender.KEY_TIMESTAMP);
        r4.A0N(j2);
        AnonymousClass0Iq.A01(r4, "status", message.mStatus);
        boolean z = message.mIsSending;
        r4.A0R("isSending");
        r4.A0Y(z);
        AnonymousClass0Iq.A01(r4, "type", message.mType);
        long j3 = message.mOfflineThreadingId;
        r4.A0R("offlineThreadingId");
        r4.A0N(j3);
        Attachment[] attachmentArr = message.mAttachments;
        if (attachmentArr != null) {
            r4.A0R("attachments");
            AnonymousClass0Iq.A00(r4, r5, attachmentArr);
        }
    }

    public void serialize(Message message, AbstractC02300iS r2, AbstractC02120i3 r3) throws IOException {
        if (message == null) {
            r2.A0G();
        }
        r2.A0I();
        serializeFields(message, r2, r3);
        r2.A0F();
    }
}
