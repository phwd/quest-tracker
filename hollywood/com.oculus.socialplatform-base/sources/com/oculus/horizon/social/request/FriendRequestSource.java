package com.oculus.horizon.social.request;

public class FriendRequestSource {

    public enum Origin {
        PROFILE("PROFILE"),
        SEARCH("SEARCH"),
        PEOPLE_YOU_MAY_KNOW("PEOPLE_YOU_MAY_KNOW"),
        MESSAGE_THREAD("MESSAGE_THREAD");
        
        public String value;

        public String toString() {
            return this.value;
        }

        /* access modifiers changed from: public */
        Origin(String str) {
            this.value = str;
        }
    }
}
