package com.facebook.crudolib.appstrictmode;

import com.facebook.infer.annotation.Nullsafe;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class AppStrictMode {
    static final int DETECT_RESOURCE_NOT_RELEASED = 1;
    public static final int INEFFICIENT_USAGE = 2;
    static final int PENALTY_DIALOG = 131072;
    static final int PENALTY_LOG = 65536;
    private static AppPolicy sAppPolicy;

    @Retention(RetentionPolicy.SOURCE)
    @interface Penalty {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Violation {
    }

    public static final class AppPolicy {
        public static final AppPolicy LAX = new AppPolicy(0);
        final int mMask;

        public AppPolicy(int i) {
            this.mMask = i;
        }

        public String toString() {
            return "AppStrictMode.AppPolicy{mask=" + this.mMask + "}";
        }

        public static final class Builder {
            private int mMask;

            public Builder() {
                this.mMask = 0;
                this.mMask = 0;
            }

            public Builder(AppPolicy appPolicy) {
                this.mMask = 0;
                this.mMask = appPolicy.mMask;
            }

            public Builder detectResourceNotReleased() {
                return enableViolation(1);
            }

            public Builder permitResourceNotReleased() {
                return disableViolation(1);
            }

            public Builder detectInefficientUsage() {
                return enableViolation(2);
            }

            public Builder permitInefficientUsage() {
                return disableViolation(2);
            }

            public Builder penaltyLog() {
                return enablePenalty(65536);
            }

            public Builder penaltyDialog() {
                return enablePenalty(131072);
            }

            private Builder enableViolation(int i) {
                return enable(i);
            }

            private Builder disableViolation(int i) {
                return disable(i);
            }

            private Builder enablePenalty(int i) {
                return enable(i);
            }

            private Builder disablePenalty(int i) {
                return disable(i);
            }

            private Builder enable(int i) {
                this.mMask = i | this.mMask;
                return this;
            }

            private Builder disable(int i) {
                this.mMask = (i ^ -1) & this.mMask;
                return this;
            }

            public AppPolicy build() {
                int i = this.mMask;
                if (i == 0 || (i & 196608) != 0) {
                    return new AppPolicy(this.mMask);
                }
                throw new IllegalArgumentException("You have specified violations, but no penalties. This is likely an error.");
            }
        }
    }

    public static void reportViolation(int i, String str, @Nullable Throwable th) {
        PolicyEnforcer.reportViolation(i, str, th);
    }

    public static void reportViolation(int i, String str) {
        PolicyEnforcer.reportViolation(i, str);
    }

    public static synchronized void setAppPolicy(AppPolicy appPolicy) {
        synchronized (AppStrictMode.class) {
            sAppPolicy = appPolicy;
            PolicyEnforcer.setAppPolicy(sAppPolicy);
        }
    }
}
