package com.oculus.horizon.api.common;

import com.google.common.base.Preconditions;

public class ChallengeAnswer {
    public final String type;

    public static class Password extends ChallengeAnswer {
        public final String password;

        public Password(String str) {
            super("PASSWORD");
            Preconditions.checkNotNull(str);
            this.password = str;
        }
    }

    public static class PIN extends ChallengeAnswer {
        public final String pin;

        public PIN(String str) {
            super("PIN");
            Preconditions.checkNotNull(str);
            this.pin = str;
        }
    }

    private ChallengeAnswer(String str) {
        this.type = str;
    }
}
