package com.oculus.partystatemanager.logging;

import javax.annotation.Nullable;

public class PartyEventFields {
    public static final String CALLER = "caller";
    public static final String PARTY_ID = "party_id";
    public static final String REASON = "reason";
    public static final String SUCCESS = "success";

    public class CallerName {
        public static final String ON_APP_SWITCH = "onAppSwitch";
        public static final String PARTY_API = "PartyAPI";
        public static final String RECOVER = "RecoverState";
        public static final String REMOUNT = "remount";
        public final /* synthetic */ PartyEventFields this$0;
    }

    public static class Extra {
        public final boolean mIsSuccess = true;
        @Nullable
        public final String mReason = null;
    }

    public enum EventName {
        VOIP_START("oculus_party_voip_start"),
        VOIP_STOP("oculus_party_voip_stop"),
        VOIP_PAUSE("oculus_party_voip_pause"),
        VOIP_RESUME("oculus_party_voip_resume");
        
        public String value;

        /* access modifiers changed from: public */
        EventName(String str) {
            this.value = str;
        }
    }
}
