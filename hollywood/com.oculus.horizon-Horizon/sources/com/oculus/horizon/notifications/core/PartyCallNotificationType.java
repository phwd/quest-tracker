package com.oculus.horizon.notifications.core;

public enum PartyCallNotificationType {
    PARTY_CALL("oculus_party_call"),
    PARTY_CALL_ACCEPT("oculus_party_call_accept"),
    PARTY_CALL_REJECT("oculus_party_call_reject"),
    PARTY_CALL_CANCEL("oculus_party_call_cancel");
    
    public final String graphQLName;

    public static PartyCallNotificationType fromGraphQLName(String str) {
        PartyCallNotificationType partyCallNotificationType = PARTY_CALL;
        if (!partyCallNotificationType.graphQLName.equals(str)) {
            partyCallNotificationType = PARTY_CALL_ACCEPT;
            if (!partyCallNotificationType.graphQLName.equals(str)) {
                partyCallNotificationType = PARTY_CALL_REJECT;
                if (!partyCallNotificationType.graphQLName.equals(str)) {
                    return PARTY_CALL_CANCEL;
                }
            }
        }
        return partyCallNotificationType;
    }

    public static boolean isPartyCallNotification(String str) {
        if (PARTY_CALL.graphQLName.equals(str) || PARTY_CALL_ACCEPT.graphQLName.equals(str) || PARTY_CALL_REJECT.graphQLName.equals(str) || PARTY_CALL_CANCEL.graphQLName.equals(str)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: public */
    PartyCallNotificationType(String str) {
        this.graphQLName = str;
    }
}
