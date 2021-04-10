package com.facebook.reliability.anr;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class AnrState extends Enum<AnrState> {
    private static final /* synthetic */ int[] $VALUES$1ff70fa = {NO_ANR_DETECTED$65befc1, DURING_ANR$65befc1, ANR_RECOVERED$65befc1, SIGQUIT_RECEIVED_AM_UNCONFIRMED_MT_BLOCKED$65befc1, SIGQUIT_RECEIVED_AM_CONFIRMED_MT_BLOCKED$65befc1, SIGQUIT_RECEIVED_AM_CONFIRMED_MT_UNBLOCKED$65befc1, SIGQUIT_RECEIVED_AM_UNCONFIRMED_MT_UNBLOCKED$65befc1, SIGQUIT_RECEIVED_AM_EXPIRED_MT_BLOCKED$65befc1, NO_SIGQUIT_AM_CONFIRMED_MT_BLOCKED$65befc1, NO_SIGQUIT_AM_CONFIRMED_MT_UNBLOCKED$65befc1};
    public static final int ANR_RECOVERED$65befc1 = 3;
    public static final int DURING_ANR$65befc1 = 2;
    public static final int NO_ANR_DETECTED$65befc1 = 1;
    public static final int NO_SIGQUIT_AM_CONFIRMED_MT_BLOCKED$65befc1 = 9;
    public static final int NO_SIGQUIT_AM_CONFIRMED_MT_UNBLOCKED$65befc1 = 10;
    public static final int SIGQUIT_RECEIVED_AM_CONFIRMED_MT_BLOCKED$65befc1 = 5;
    public static final int SIGQUIT_RECEIVED_AM_CONFIRMED_MT_UNBLOCKED$65befc1 = 6;
    public static final int SIGQUIT_RECEIVED_AM_EXPIRED_MT_BLOCKED$65befc1 = 8;
    public static final int SIGQUIT_RECEIVED_AM_UNCONFIRMED_MT_BLOCKED$65befc1 = 4;
    public static final int SIGQUIT_RECEIVED_AM_UNCONFIRMED_MT_UNBLOCKED$65befc1 = 7;

    public static int[] values$548840f9() {
        return (int[]) $VALUES$1ff70fa.clone();
    }
}
