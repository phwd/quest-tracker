package com.oculus.messenger.models;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AnonymousClass0IH;
import X.AnonymousClass0Iq;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;

public class AttachmentSerializer extends JsonSerializer<Attachment> {
    static {
        AnonymousClass0IH.A06.putIfAbsent(Attachment.class, new AttachmentSerializer());
    }

    public static void serializeFields(Attachment attachment, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException {
        long j = attachment.mAttachmentId;
        r4.A0R("attachmentId");
        r4.A0N(j);
        AnonymousClass0Iq.A01(r4, "attachmentType", attachment.mAttachmentType);
    }

    public void serialize(Attachment attachment, AbstractC02300iS r2, AbstractC02120i3 r3) throws IOException {
        if (attachment == null) {
            r2.A0G();
        }
        r2.A0I();
        serializeFields(attachment, r2, r3);
        r2.A0F();
    }
}
