package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0VV  reason: invalid class name */
public enum AnonymousClass0VV {
    NO_ANR_DETECTED,
    DURING_ANR,
    ANR_RECOVERED,
    SIGQUIT_RECEIVED_AM_UNCONFIRMED_MT_BLOCKED,
    SIGQUIT_RECEIVED_AM_CONFIRMED_MT_BLOCKED,
    SIGQUIT_RECEIVED_AM_CONFIRMED_MT_UNBLOCKED,
    SIGQUIT_RECEIVED_AM_UNCONFIRMED_MT_UNBLOCKED,
    SIGQUIT_RECEIVED_AM_EXPIRED_MT_BLOCKED,
    NO_SIGQUIT_AM_CONFIRMED_MT_BLOCKED,
    NO_SIGQUIT_AM_CONFIRMED_MT_UNBLOCKED;

    public boolean isEnteringAnrState() {
        if (this == DURING_ANR || this == SIGQUIT_RECEIVED_AM_CONFIRMED_MT_BLOCKED || this == NO_SIGQUIT_AM_CONFIRMED_MT_BLOCKED) {
            return true;
        }
        return false;
    }
}
