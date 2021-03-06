package com.facebook.reliability.anr;

public enum AnrState {
    NO_ANR_DETECTED,
    DURING_ANR,
    ANR_RECOVERED,
    SIGQUIT_RECEIVED_AM_UNCONFIRMED_MT_BLOCKED,
    SIGQUIT_RECEIVED_AM_CONFIRMED_MT_BLOCKED,
    SIGQUIT_RECEIVED_AM_CONFIRMED_MT_UNBLOCKED,
    SIGQUIT_RECEIVED_AM_UNCONFIRMED_MT_UNBLOCKED,
    SIGQUIT_RECEIVED_AM_EXPIRED_MT_BLOCKED,
    NO_SIGQUIT_AM_CONFIRMED_MT_BLOCKED,
    NO_SIGQUIT_AM_CONFIRMED_MT_UNBLOCKED
}
