package com.oculus.horizon.api.common;

import X.AnonymousClass13N;

public class PartyInvite extends Invite {
    public String party_id;

    public static PartyInvite fromNotificationResponse(String str, String str2) {
        PartyInvite partyInvite = (PartyInvite) new AnonymousClass13N().A05(str2, PartyInvite.class);
        partyInvite.notification_id = str;
        return partyInvite;
    }
}
