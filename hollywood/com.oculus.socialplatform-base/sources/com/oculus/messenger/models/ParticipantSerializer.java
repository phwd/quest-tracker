package com.oculus.messenger.models;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AnonymousClass0IH;
import X.AnonymousClass0Iq;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;

public class ParticipantSerializer extends JsonSerializer<Participant> {
    static {
        AnonymousClass0IH.A06.putIfAbsent(Participant.class, new ParticipantSerializer());
    }

    public static void serializeFields(Participant participant, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException {
        long j = participant.mParticipantId;
        r4.A0R("participantId");
        r4.A0N(j);
        AnonymousClass0Iq.A01(r4, "alias", participant.mAlias);
        long j2 = participant.mWatermarkTimestamp;
        r4.A0R("watermarkTimestamp");
        r4.A0N(j2);
        long j3 = participant.mActionTimestamp;
        r4.A0R("actionTimestamp");
        r4.A0N(j3);
    }

    public void serialize(Participant participant, AbstractC02300iS r2, AbstractC02120i3 r3) throws IOException {
        if (participant == null) {
            r2.A0G();
        }
        r2.A0I();
        serializeFields(participant, r2, r3);
        r2.A0F();
    }
}
