package com.oculus.horizon.api.common;

import X.C08780ya;

public class PartyInvite extends Invite {
    public String party_id;

    public static PartyInvite fromNotificationResponse(String str, String str2) {
        PartyInvite partyInvite = (PartyInvite) new C08780ya().A05(str2, PartyInvite.class);
        partyInvite.notification_id = str;
        return partyInvite;
    }
}
