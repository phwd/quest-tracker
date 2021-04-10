package com.oculus.horizon.api.common;

import com.oculus.common.securedactions.SecuredActions;

public class ChallengeAnswer {
    public final String type;

    public static class PIN extends ChallengeAnswer {
        public final String pin;

        public PIN(String str) {
            super("PIN");
            if (str != null) {
                this.pin = str;
                return;
            }
            throw null;
        }
    }

    public static class Password extends ChallengeAnswer {
        public final String password;

        public Password(String str) {
            super(SecuredActions.PASSWORD_CHALLENGE_TYPE);
            if (str != null) {
                this.password = str;
                return;
            }
            throw null;
        }
    }

    public ChallengeAnswer(String str) {
        this.type = str;
    }
}
