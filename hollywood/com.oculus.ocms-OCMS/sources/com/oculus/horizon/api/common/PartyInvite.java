package com.oculus.horizon.api.common;

import com.google.gson.Gson;

public class PartyInvite extends Invite {
    public String party_id;

    public static PartyInvite fromNotificationResponse(String str, String str2) {
        PartyInvite partyInvite = (PartyInvite) new Gson().fromJson(str2, PartyInvite.class);
        partyInvite.notification_id = str;
        return partyInvite;
    }
}
